package com.nksp.backend.controller;

import com.nksp.backend.entity.ApiResult;
import com.nksp.backend.entity.Option;
import com.nksp.backend.entity.Question;
import com.nksp.backend.serviceimpl.OptionServiceImpl;
import com.nksp.backend.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class OptionController {
    @Autowired
    private OptionServiceImpl optionService;

    @GetMapping("/api/option/{oid}")
    public ApiResult findById(@PathVariable("oid") Integer userId) {
        Option res = optionService.findById(userId);
        if (res != null) {
            System.out.println(res);
            return ApiResultHandler.buildApiResult(200, "请求成功", res);
        } else {
            return ApiResultHandler.buildApiResult(404, "查询的用户不存在", null);
        }
    }

    @PostMapping("/api/getolist")
    public ApiResult getOptionList(@RequestBody Map<String, String> q) {
        List<Option> res = optionService.getOptionList(Integer.parseInt(q.get("qid")));
        if (res != null) {
            System.out.println(res);
            return ApiResultHandler.buildApiResult(200, "请求成功", res);
        } else {
            return ApiResultHandler.buildApiResult(404, "查询的用户不存在", null);
        }
    }
}