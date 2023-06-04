package com.nksp.backend.controller;

import com.nksp.backend.entity.ApiResult;
import com.nksp.backend.util.ApiResultHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/api/user")
    public ApiResult testController(){
        return  ApiResultHandler.buildApiResult(200,"分页查询所有学生", null);
    }
}
