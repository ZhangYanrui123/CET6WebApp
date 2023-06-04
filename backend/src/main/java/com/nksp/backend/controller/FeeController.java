package com.nksp.backend.controller;

import com.nksp.backend.entity.ApiResult;
import com.nksp.backend.entity.Fee;
import com.nksp.backend.entity.Join;
import com.nksp.backend.serviceimpl.FeeServiceImpl;
import com.nksp.backend.serviceimpl.JoinServiceImpl;
import com.nksp.backend.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeeController {
    @Autowired
    private FeeServiceImpl feeService;

    @GetMapping("/api/fee/{fid}")
    public ApiResult findById(@PathVariable("fid") Integer userId) {
        Fee res = feeService.findById(userId);
        if (res != null) {
            System.out.println(res);
            return ApiResultHandler.buildApiResult(200, "请求成功", res);
        } else {
            return ApiResultHandler.buildApiResult(404, "查询的用户不存在", null);
        }
    }
}