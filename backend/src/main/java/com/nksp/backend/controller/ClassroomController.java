package com.nksp.backend.controller;

import com.nksp.backend.entity.ApiResult;
import com.nksp.backend.entity.Classroom;
import com.nksp.backend.entity.Exam;
import com.nksp.backend.serviceimpl.ClassroomServiceImpl;
import com.nksp.backend.serviceimpl.StudentServiceImpl;
import com.nksp.backend.util.ApiResultHandler;
import com.nksp.backend.vo.AddExam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @PostMapping("/api/classroom/getAllClassrooms")
    public ApiResult getAllClassrooms(){
        List<Classroom> res = classroomService.getAllClassrooms();

        if (res != null) {
            System.out.println(res);
            return ApiResultHandler.buildApiResult(200, "请求成功", res);
        } else {
            return ApiResultHandler.buildApiResult(404, "请求数据为空", null);
        }
    }

    @PostMapping("/api/admin/addClassroom")
    public ApiResult addExam(@RequestBody Classroom params){
        System.out.println(params);
        classroomService.addClassromm(params);
        return ApiResultHandler.buildApiResult(200, "请求成功", params);
    }

}