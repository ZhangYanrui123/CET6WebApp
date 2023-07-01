package com.nksp.backend.controller;

import com.nksp.backend.entity.*;
import com.nksp.backend.entity.Record;
import com.nksp.backend.serviceimpl.*;
import com.nksp.backend.util.ApiResultHandler;
import com.nksp.backend.util.GetQuestionRightAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AnswerController {
    @Autowired
    private AnswerServiceImpl answerService;

    @Autowired
    private QuestionServiceImpl questionService;

    @Autowired
    private OptionServiceImpl optionService;

    @Autowired
    private RecordServiceImpl recordService;

    @Autowired
    private GradeServiceImpl gradeService;

    @Autowired
    private PaperServiceImpl paperService;
    @GetMapping("/api/answer/{aid}")
    public ApiResult findById(@PathVariable("aid") Integer userId) {
        Answer res = answerService.findById(userId);
        if (res != null) {
            System.out.println(res);
            return ApiResultHandler.buildApiResult(200, "请求成功", res);
        } else {
            return ApiResultHandler.buildApiResult(404, "查询的用户不存在", null);
        }
    }

    @PostMapping("/api/pusha")
    public ApiResult addAnswer(@RequestBody Answer answer){
        int ret = answerService.insertAnswer(answer);
        String options = answer.getAobjective();
        String[] optionlist = options.split("&");
        // 1. 通过pid获取所有选择题及其正确答案，并获取当前试卷的总分
        int pid = answer.getPid();
        Paper paper = paperService.findById(pid);
        String questions = paper.getPquestions();
        String[] questionlist = questions.split("&");

        String scores = paper.getPscores();
        String[] scorelist = scores.split("&");

        int curQcnt= 0;
        int objScore = 0;
        int right = 0;
        for(String questionid:questionlist){
            int qid = Integer.parseInt(questionid);
            // 2. 检查question是否有subquestion，检查当前question的类型(主客观)，需要再查表
            Question question = questionService.findById(qid);
            if(question.getQtype()==5 || question.getQtype() == 6){
                // 主观题

            }else{
                // 客观题，累加分值
                objScore += Integer.parseInt(scorelist[curQcnt]);
            }
//            if(question.getQbelong() != 0){
//                // 小题subquestion
//                List<Question> subquestions = questionService.getSubQuesList(qid);
//                for(Question subq : subquestions){
//                    String rightAns = GetQuestionRightAnswer.getAnswer(subq, subq.getQid(), optionService);
//                    if(optionlist[curQcnt].equals(rightAns)) right++;
//                    curQcnt++;
//                }
//            }else{
//                // 没有小题的question，查option找答案，和当前optionlist比对累计正确个数
//                // 阅读大题干
//                if(question.getQtype() == 4) continue;
//                String rightAns = GetQuestionRightAnswer.getAnswer(question, qid, optionService);
//                if(optionlist[curQcnt].equals(rightAns)) right++;
//                curQcnt++;
//            }
            if (question.getQtype() == 5) {
                continue;
            } else {
                if (question.getQtype() == 4 && question.getQnum() != 0) {
                    List<Question> subquestions = questionService.getSubQuesList(qid);
                    for (Question subq : subquestions) {
                        String rightAns = GetQuestionRightAnswer.getAnswer(subq, subq.getQid(), optionService);
                        System.out.println(rightAns);
                        if (optionlist[curQcnt].equals(rightAns)) right++;
                        curQcnt++;
                    }
                }
            }
        }

        // 总curQcnt，对right，总分objScore，可以计算出客观题的正确率，从而简单计算得分
        float objFinal = (float) right / curQcnt * objScore;

        // 3. 查Record，获取主观题得分，并和客观题得分一起写入Grade表
        int uuid = answer.getUuid();
        int eid = answer.getEid();
        System.out.println(uuid+ "," + eid);
        List<Record> records = recordService.findByUEid(uuid, eid);
        Record record = records.get(0);
        float subFinal = record.getRscore();
        Grade grade = new Grade();
        grade.setInfo(uuid, eid, objFinal, subFinal);
        grade.setGtotal(0);
        System.out.println(grade);
        int res = gradeService.updateSOScore(grade);

        System.out.println(objFinal);
        System.out.println(subFinal);
        if(ret > 0 && res > 0){
            return ApiResultHandler.buildApiResult(200, "请求成功", null);
        } else {
            return ApiResultHandler.buildApiResult(404, "插入answer失败", null);
        }
    }
}