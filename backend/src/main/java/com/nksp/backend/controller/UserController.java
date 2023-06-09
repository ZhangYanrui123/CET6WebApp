package com.nksp.backend.controller;

import com.nksp.backend.entity.ApiResult;
import com.nksp.backend.vo.LoginInfo;
import com.nksp.backend.entity.Student;
import com.nksp.backend.entity.User;
import com.nksp.backend.serviceimpl.StudentServiceImpl;
import com.nksp.backend.serviceimpl.UserServiceImpl;
import com.nksp.backend.util.ApiResultHandler;
import com.nksp.backend.vo.LoginInfo2;
import com.nksp.backend.vo.ManageData;
import com.nksp.backend.vo.RegisterInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private StudentServiceImpl studentService;
    @GetMapping("/api/user/{studentId}")
    public ApiResult findById(@PathVariable("userId") Integer userId) {
        User res = userService.findById(userId);
        if (res != null) {
            System.out.println(res);
            return ApiResultHandler.buildApiResult(200,"请求成功",res);
        } else {
            return ApiResultHandler.buildApiResult(404,"查询的用户不存在",null);
        }
    }

    @PostMapping("/api/login")
    public ApiResult findById(@RequestBody LoginInfo2 params) {
        User res = userService.findByName(params.getUname());
        System.out.println(params.getUname());
        if (res!=null && params.getUpwd().equals(res.getUpwd())) {
            System.out.println(res);
            return ApiResultHandler.buildApiResult(200,"请求成功", res);
        } else {
            return ApiResultHandler.buildApiResult(404,"查询的用户不存在",null);
        }
    }
    @PostMapping("/api/studentData")
    public ApiResult getUserData(@RequestBody LoginInfo params){
        System.out.println(params);
        User userRes = userService.findById(params.getUuid());
        Student stuRes = studentService.findById(params.getUuid());
        System.out.println(userRes);
        System.out.println(stuRes);

        ManageData manageData = new ManageData();
        manageData.setInfo(userRes, stuRes);
        System.out.println(manageData);
        if (manageData != null) {
            return ApiResultHandler.buildApiResult(200,"请求成功", manageData);
        } else {
            return ApiResultHandler.buildApiResult(404,"查询的用户不存在",null);
        }
    }

    @PostMapping("/api/register")
    public ApiResult userRegister(@RequestBody RegisterInfo params){
        System.out.println(params);
        int ret1 = userService.insertUser(params);
        User res = userService.findByName(params.getUname());

        Student student = new Student(res.getUuid(),params.getSuniversity(),params.getScollege(),params.getSmajority(),params.getSno(),params.getSclass(),params.getSgrade());
        System.out.println(student);
        int ret2 = studentService.insertStudent(student);
//        if((ret1 > 0) & (ret2 > 0) ){
//            System.out.println(params);
//            return ApiResultHandler.buildApiResult(200, "请求成功", ret1 + ret2);
//        } else {
//            return ApiResultHandler.buildApiResult(404, "插入user失败", null);
//        }
        if ((ret1 > 0) & (ret2 > 0)) {
            System.out.println(res);
            return ApiResultHandler.buildApiResult(200,"请求成功", res);
        } else {
            return ApiResultHandler.buildApiResult(404,"插入user失败",null);
        }

//        return ApiResultHandler.buildApiResult(200,"请求成功", null);
    }

}
