package com.nksp.backend.controller;

import com.nksp.backend.entity.ApiResult;
import com.nksp.backend.entity.LoginInfo;
import com.nksp.backend.entity.User;
import com.nksp.backend.serviceimpl.UserServiceImpl;
import com.nksp.backend.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @GetMapping("/api/user/{userId}")
    public ApiResult findById(@PathVariable("userId") Integer userId) {
        User res = userService.findById(userId);
        if (res != null) {
            System.out.println(res);
            return ApiResultHandler.buildApiResult(200,"请求成功",res);
        } else {
            return ApiResultHandler.buildApiResult(404,"查询的用户不存在",null);
        }
    }
    @PostMapping("/api/user")
    public ApiResult getUserData(@RequestBody LoginInfo params){
        User res = userService.findById(params.getUuid());
        System.out.println(res);
        if (res != null) {
            System.out.println(res);
            return ApiResultHandler.buildApiResult(200,"请求成功",res);
        } else {
            return ApiResultHandler.buildApiResult(404,"查询的用户不存在",null);
        }
    }
}
