package com.nksp.backend.controller;

import com.nksp.backend.entity.*;
import com.nksp.backend.serviceimpl.ExamServiceImpl;
import com.nksp.backend.serviceimpl.FeeServiceImpl;
import com.nksp.backend.serviceimpl.JoinServiceImpl;
import com.nksp.backend.serviceimpl.StudentServiceImpl;
import com.nksp.backend.util.ApiResultHandler;
import com.nksp.backend.vo.JoinData;
import com.nksp.backend.vo.LoginInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class JoinController {
    @Autowired
    private JoinServiceImpl joinService;

    @Autowired
    private ExamServiceImpl examService;

    @Autowired
    private FeeServiceImpl feeService;

    @GetMapping("/api/join/{jid}")
    public ApiResult findById(@PathVariable("jid") Integer userId) {
        Join res = joinService.findById(userId);
        if (res != null) {
            System.out.println(res);
            return ApiResultHandler.buildApiResult(200, "请求成功", res);
        } else {
            return ApiResultHandler.buildApiResult(404, "查询的用户不存在", null);
        }
    }

    @PostMapping("/api/getjoin")
    public ApiResult getJoin(@RequestBody LoginInfo params) {
        List<Join> joinlist = joinService.getAllJoin(params.getUuid());
        System.out.println(joinlist);
        List<JoinData> res = new ArrayList<>();
        for(Join join : joinlist) {
            Exam exam = examService.findById(join.getEid());
            // compareTo:如果调用者在参数之前，则小于0
            if(new Date().compareTo(exam.getEbegin())<0){
                Fee fee = feeService.findById(join.getFid());
                JoinData joinData = new JoinData();
                joinData.setInfo(exam.getEid(), exam.getEsubject(), exam.getEbegin(), exam.getEend(), join.getJstate(), fee.getFamount());
                res.add(joinData);
            }
        }
        if (res != null) {
            System.out.println(res);
            return ApiResultHandler.buildApiResult(200, "请求成功", res);
        } else {
            return ApiResultHandler.buildApiResult(404, "查询的考试不存在", null);
        }
    }
}