package com.nksp.backend.controller;

import com.nksp.backend.entity.ApiResult;
import com.nksp.backend.entity.Teacher;
import com.nksp.backend.entity.User;
import com.nksp.backend.serviceimpl.TeacherServiceImpl;
import com.nksp.backend.serviceimpl.UserServiceImpl;
import com.nksp.backend.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeacherController {
    @Autowired
    private TeacherServiceImpl teacherService;
    @GetMapping("/api/teacher/{userId}")
    public ApiResult findById(@PathVariable("userId") Integer userId) {
        Teacher res = teacherService.findById(userId);
        if (res != null) {
            System.out.println(res);
            return ApiResultHandler.buildApiResult(200,"请求成功",res);
        } else {
            return ApiResultHandler.buildApiResult(404,"查询的用户不存在",null);
        }
    }
}
