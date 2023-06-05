package com.nksp.backend.controller;

import com.nksp.backend.entity.ApiResult;
import com.nksp.backend.entity.Classroom;
import com.nksp.backend.serviceimpl.ClassroomServiceImpl;
import com.nksp.backend.serviceimpl.StudentServiceImpl;
import com.nksp.backend.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClassroomController {
    @Autowired
    private ClassroomServiceImpl classroomService;

    @GetMapping("/api/classroom/{cid}")
    public ApiResult findById(@PathVariable("cid") Integer cid) {
        Classroom res = classroomService.findById(cid);
        if (res != null) {
            System.out.println(res);
            return ApiResultHandler.buildApiResult(200, "请求成功", res);
        } else {
            return ApiResultHandler.buildApiResult(404, "查询的用户不存在", null);
        }
    }
}