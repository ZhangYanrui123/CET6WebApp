package com.nksp.backend.controller;

import com.nksp.backend.entity.ApiResult;
import com.nksp.backend.entity.Exam;
import com.nksp.backend.entity.Paper;
import com.nksp.backend.serviceimpl.PaperServiceImpl;
import com.nksp.backend.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class PaperController {
    @Autowired
    private PaperServiceImpl paperService;

    @GetMapping("/api/paper/{pid}")
    public ApiResult findById(@PathVariable("pid") Integer userId) {
        Paper res = paperService.findById(userId);
        if (res != null) {
            System.out.println(res);
            return ApiResultHandler.buildApiResult(200, "请求成功", res);
        } else {
            return ApiResultHandler.buildApiResult(404, "查询的试卷不存在", null);
        }
    }

    @PostMapping("/api/getpaper")
    public ApiResult findById(@RequestBody Map<String, String> p) {
        Paper res = paperService.findById(Integer.parseInt(p.get("pid")));
        if (res != null) {
            System.out.println(res);
            return ApiResultHandler.buildApiResult(200, "请求成功", res);
        } else {
            return ApiResultHandler.buildApiResult(404, "查询的试卷不存在", null);
        }
    }
}