package com.nksp.backend.controller;

import com.nksp.backend.entity.ApiResult;
import com.nksp.backend.entity.Exam;
import com.nksp.backend.entity.Grade;
import com.nksp.backend.serviceimpl.ExamServiceImpl;
import com.nksp.backend.serviceimpl.GradeServiceImpl;
import com.nksp.backend.util.ApiResultHandler;
import com.nksp.backend.vo.ScoreTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class GradeController {
    @Autowired
    private GradeServiceImpl gradeService;

    @Autowired
    private ExamServiceImpl examService;

    @GetMapping("/api/grade/{gid}")
    public ApiResult findById(@PathVariable("gid") Integer userId) {
        Grade res = gradeService.findById(userId);
        if (res != null) {
            System.out.println(res);
            return ApiResultHandler.buildApiResult(200, "请求成功", res);
        } else {
            return ApiResultHandler.buildApiResult(404, "查询的用户不存在", null);
        }
    }

    @PostMapping("/api/totalscore")
    public ApiResult getTotalScore(@RequestBody Map<String, String> u){
        int uuid = Integer.parseInt(u.get("uuid"));
        List<Grade> gradeList = gradeService.getAllGrade(uuid);
        List<ScoreTable> res = new ArrayList<>();
        for(Grade grade:gradeList){
            int eid = grade.getEid();
            Exam exam = examService.findById(eid);
            ScoreTable scoreTable = new ScoreTable();
            scoreTable.setInfo(exam.getEsubject(), exam.getEbegin(), exam.getEend(), grade.getGdictation(), grade.getGwriting());
            res.add(scoreTable);
        }
        if (res != null) {
            System.out.println(res);
            return ApiResultHandler.buildApiResult(200, "请求成功", res);
        } else {
            return ApiResultHandler.buildApiResult(404, "查询的用户不存在", null);
        }
    }
}