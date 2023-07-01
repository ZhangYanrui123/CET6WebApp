package com.nksp.backend.controller;

import com.nksp.backend.entity.ApiResult;
import com.nksp.backend.entity.Grade;
import com.nksp.backend.entity.Question;
import com.nksp.backend.entity.Record;
import com.nksp.backend.serviceimpl.GradeServiceImpl;
import com.nksp.backend.serviceimpl.QuestionServiceImpl;
import com.nksp.backend.serviceimpl.RecordServiceImpl;
import com.nksp.backend.util.ApiResultHandler;
import com.nksp.backend.vo.QuestionData;
import com.nksp.backend.vo.RecordData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class RecordController {
    @Autowired
    private RecordServiceImpl recordService;

    @Autowired
    private QuestionServiceImpl questionService;

    @Autowired
    private GradeServiceImpl gradeService;

    @GetMapping("/api/record/{rid}")
    public ApiResult findById(@PathVariable("rid") Integer userId) {
        Record res = recordService.findById(userId);
        if (res != null) {
            System.out.println(res);
            return ApiResultHandler.buildApiResult(200, "请求成功", res);
        } else {
            return ApiResultHandler.buildApiResult(404, "查询的用户不存在", null);
        }
    }

    @PostMapping("/api/getrecord")
    public ApiResult findById(@RequestBody Map<String, String> r){
        int rid = Integer.parseInt(r.get("rid"));
        Record res = recordService.findById(rid);
        int qid = res.getQid();
        // 题目和类型
        Question question = questionService.findById(qid);
        RecordData recordData = new RecordData();
        recordData.setRecordData(rid, question.getQtype(), question.getQstem(), res.getRanswer(),0);
        System.out.println(recordData);
        return ApiResultHandler.buildApiResult(200, "请求成功", recordData);
    }

    @PostMapping("/api/teacher/getAllRecords")
    public ApiResult getAllRecords(){
        List<Record> records = recordService.getAllRecords();
        List<RecordData> res = new ArrayList<>();
        for(Record r:records){
            // 已经判过分，直接跳过
            if(r.getRscore() > 0) continue;
            int qid = r.getQid();
            Question question = questionService.findById(qid);
            RecordData rd = new RecordData();
            rd.setRecordData(r.getRid(), question.getQtype(), question.getQstem(), r.getRanswer(), 0);
            res.add(rd);
        }
        if (res != null) {
            System.out.println(res);
            return ApiResultHandler.buildApiResult(200, "请求成功", res);
        } else {
            return ApiResultHandler.buildApiResult(404, "请求结果为空", null);
        }
    }

    @PostMapping("/api/pushr")
    public ApiResult addQuestion(@RequestBody Record record){
        int ret = recordService.insertRecord(record);
        if(ret > 0){
            System.out.println(record);
            return ApiResultHandler.buildApiResult(200, "请求成功", ret);
        } else {
            return ApiResultHandler.buildApiResult(404, "插入record失败", null);
        }
    }

    @PostMapping("/api/teacher/updateScore")
    public ApiResult updateScore(@RequestBody RecordData recordData){
        System.out.println(recordData);
        Record record = recordService.findById(recordData.getRid());
        record.setRscore(recordData.getRscore());
        int res = recordService.updateRecord(record);
        System.out.println(record);
        Grade grade = gradeService.findByUidEid(record.getUuid(), record.getEid());
        // 更新主观题分数到grade表
        grade.setSubInfo(record.getUuid(), record.getEid(), record.getRscore());
        int ret = gradeService.updateSubScore(grade);
        if(res > 0 && ret > 0){
            System.out.println(res);
            return ApiResultHandler.buildApiResult(200, "请求成功", null);
        } else {
            return ApiResultHandler.buildApiResult(404, "插入record失败", null);
        }
    }
}