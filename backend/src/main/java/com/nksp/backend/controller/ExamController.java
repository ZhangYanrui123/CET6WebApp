package com.nksp.backend.controller;

import com.nksp.backend.entity.ApiResult;
import com.nksp.backend.entity.Exam;
import com.nksp.backend.entity.Student;
import com.nksp.backend.mapper.ExamMapper;
import com.nksp.backend.serviceimpl.ExamServiceImpl;
import com.nksp.backend.serviceimpl.StudentServiceImpl;
import com.nksp.backend.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExamController {
    @Autowired
    private ExamServiceImpl examService;

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
}