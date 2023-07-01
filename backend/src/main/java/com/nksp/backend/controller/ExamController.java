package com.nksp.backend.controller;

import com.nksp.backend.entity.*;
import com.nksp.backend.mapper.ExamMapper;
import com.nksp.backend.serviceimpl.*;
import com.nksp.backend.util.ApiResultHandler;
import com.nksp.backend.vo.Eid;
import com.nksp.backend.vo.ExamInfo;
import com.nksp.backend.vo.AddExam;
import com.nksp.backend.vo.LoginInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ExamController {
    @Autowired
    private ExamServiceImpl examService;

    @Autowired
    private JoinServiceImpl joinService;

    @Autowired
    private ClassroomServiceImpl classroomService;

    @Autowired
    private PaperServiceImpl paperService;

    @GetMapping("/api/examination/{eid}")
    public ApiResult findById(@PathVariable("eid") Integer userId) {
        Exam res = examService.findById(userId);
        if (res != null) {
            System.out.println(res);
            return ApiResultHandler.buildApiResult(200, "请求成功", res);
        } else {
            return ApiResultHandler.buildApiResult(404, "查询的用户不存在", null);
        }
    }
    @PostMapping("/api/exam")
    public ApiResult getExam(@RequestBody Map<String, String> e) {
        Exam res = examService.findById(Integer.parseInt(e.get("eid")));
        if (res != null) {
            System.out.println(res);
            return ApiResultHandler.buildApiResult(200, "请求成功", res);
        } else {
            return ApiResultHandler.buildApiResult(404, "查询的考试不存在", null);
        }
    }

    @PostMapping("/api/getallexam")
    public ApiResult getAllExam(@RequestBody Map<String, String> u) {
        List<Join> joins = joinService.getAllJoin(Integer.parseInt(u.get("uuid")));
        List<Exam> res = new ArrayList<>();
        for(Join join: joins){
            Exam exam = examService.findById(join.getEid());
            if(exam != null)
                res.add(exam);
            else
                continue;
        }
        if (res != null) {
            System.out.println(res);
            return ApiResultHandler.buildApiResult(200, "请求成功", res);
        } else {
            return ApiResultHandler.buildApiResult(404, "查询的考试不存在", null);
        }
    }

    @PostMapping("/api/getpid")
    public ApiResult getPidByEid(@RequestBody Map<String, String> e) {
        Exam res = examService.findById(Integer.parseInt(e.get("eid")));
        if (res != null) {
            System.out.println(res);
            int pid = res.getPid();
            return ApiResultHandler.buildApiResult(200, "请求成功", pid);
        } else {
            return ApiResultHandler.buildApiResult(404, "查询的考试不存在", null);
        }
    }

    @PostMapping("/api/exam/addExam")
    public ApiResult addExam(@RequestBody AddExam params){
        System.out.println(params);
        Classroom classroom = classroomService.findByName(params.getCname());

        int res = paperService.insertPaper(new Paper());
        Paper paper = paperService.findMaxId(); // 刚刚插入的

        Exam exam = new Exam(classroom.getCid(), params.getEbegin(),params.getEend(),params.getEsubject(),1, paper.getPid());
        examService.addExam(exam);

        if (exam != null && res > 0) {
            System.out.println(exam);
            return ApiResultHandler.buildApiResult(200, "请求成功", res);
        } else {
            return ApiResultHandler.buildApiResult(404, "查询的考试不存在", null);
        }
    }

    @PostMapping("/api/exam/deleteExam")
    public ApiResult deleteExam(@RequestBody Eid eid){
        System.out.println(eid.getEid());
        Integer res = examService.deleteById(eid.getEid());
        if (res != null) {
            System.out.println(res);
            return ApiResultHandler.buildApiResult(200, "请求成功", res);
        } else {
            return ApiResultHandler.buildApiResult(404, "删除失败", null);
        }
    }

    @PostMapping("/api/exam/getAllExams")
    public ApiResult getAllExams() {
        List<ExamInfo> res = new ArrayList<>();
        List<Exam> examList = examService.getAllExams();
        for(Exam e: examList){
            ExamInfo ei = new ExamInfo();
            Classroom cr = classroomService.findById(e.getCid());
            if(cr == null)continue;
            ei.setInfo(e.getEid(), cr.getCclassroom(), e.getEbegin(), e.getEend(), e.getEsubject());
            res.add(ei);
        }
        if (res != null) {
            System.out.println(res);
            return ApiResultHandler.buildApiResult(200, "请求成功", res);
        } else {
            return ApiResultHandler.buildApiResult(404, "查询的考试不存在", null);
        }
    }

}