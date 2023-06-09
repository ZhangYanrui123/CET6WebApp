package com.nksp.backend.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nksp.backend.entity.*;
import com.nksp.backend.serviceimpl.ExamServiceImpl;
import com.nksp.backend.serviceimpl.OptionServiceImpl;
import com.nksp.backend.serviceimpl.PaperServiceImpl;
import com.nksp.backend.serviceimpl.QuestionServiceImpl;
import com.nksp.backend.util.ApiResultHandler;
import com.nksp.backend.vo.QuestionData;
import com.nksp.backend.vo.QuestionJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.nksp.backend.util.AddQuestionIntoPaper.addQuestionIntoPaper;
import static com.nksp.backend.util.StringHelper.getValue;
import static com.nksp.backend.util.StringHelper.getValues;

@RestController
public class QuestionController {
    @Autowired
    private QuestionServiceImpl questionService;

    @Autowired
    private OptionServiceImpl optionService;

    @Autowired
    private PaperServiceImpl paperService;

    @Autowired
    private ExamServiceImpl examService;

    @GetMapping("/api/question/{qid}")
    public ApiResult findById(@PathVariable("qid") Integer userId) {
        Question res = questionService.findById(userId);
        if (res != null) {
            System.out.println(res);
            return ApiResultHandler.buildApiResult(200, "请求成功", res);
        } else {
            return ApiResultHandler.buildApiResult(404, "查询的用户不存在", null);
        }
    }

    @PostMapping("/api/getq")
    public ApiResult getQuestionById(@RequestBody Map<String, String> q) {
        Question res = questionService.findById(Integer.parseInt(q.get("qid")));
        if (res != null) {
            System.out.println(res);
            return ApiResultHandler.buildApiResult(200, "请求成功", res);
        } else {
            return ApiResultHandler.buildApiResult(404, "查询的用户不存在", null);
        }
    }

    @PostMapping("/api/getsubqlist")
    public ApiResult getSubQuesList(@RequestBody Map<String, String> q) {
        List<Question> res = questionService.getSubQuesList(Integer.parseInt(q.get("qid")));
        if (res != null) {
            List<Integer> subqids = new ArrayList<Integer>();
            for(Question r : res){
                subqids.add(r.getQid());
            }
            System.out.println(subqids);

            return ApiResultHandler.buildApiResult(200, "请求成功", subqids);
        } else {
            return ApiResultHandler.buildApiResult(404, "查询的用户不存在", null);
        }
    }

    @PostMapping("/api/question/add")
    public ApiResult addQuestion(@RequestBody QuestionData params){
        // 1. get stem
        String stem = params.getStem();
        System.out.println(stem);
        // 2. get questions
        List<Object> questions = params.getOptions();
        System.out.println(questions);
        int questionCnt = questions.toArray().length;
        Question question = new Question();
        question.setQuestion(4, stem, questionCnt, 0);
        questionService.insertQues(question);
        // 2.5 add question into its paper
        int questionId = question.getQid();
        System.out.println(params.getEid());
        Exam exam = examService.findById(params.getEid());
        Paper paper = paperService.findById(exam.getPid());
        addQuestionIntoPaper(paper, questionId);
        int res = paperService.updateQuestions(paper);
        // 3. get questionText
        // 4. get optionText (many)
        // 5. get correctAnswer
        for (Object subquestions : questions){
            // 3. get questionText
//            JSONObject jsonObject =  JSON.parseObject(subquestions.toString());
            String  subQuesStr = subquestions.toString();
            System.out.println(subQuesStr);

            // 提取questionText的值
            String questionText = getValue(subQuesStr, "questionText");
            System.out.println("questionText: " + questionText);

            // 提取options中的所有optionTextr值
            String[] optionTexts = getValues(subQuesStr, "optionText");
            System.out.println("optionText个数: " + optionTexts.length);
            for (String optionText : optionTexts) {
                System.out.println("optionText: " + optionText);
            }

            // 提取correctAnswer的值
            String correctAnswer = getValue(subQuesStr, "correctAnswer");
            System.out.println("correctAnswer: " + correctAnswer);

            Question subquestion = new Question();
            subquestion.setQuestion(4, questionText, 0, questionId);
            questionService.insertQues(subquestion);
            int subQuestionId = subquestion.getQid();

            int tmpcnt = 0;
            for (String optionText : optionTexts) {
                System.out.println("optionText: " + optionText);
                Option option = new Option();
                boolean isright = Integer.parseInt(correctAnswer) == tmpcnt; // 正确选项
                option.setOption(subQuestionId, optionText, isright);
                optionService.insertOption(option);
                tmpcnt++;
            }

        }

        return ApiResultHandler.buildApiResult(200, "请求成功", null);
    }
}