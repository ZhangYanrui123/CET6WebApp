package com.nksp.backend.controller;

import com.nksp.backend.entity.ApiResult;
import com.nksp.backend.entity.Exam;
import com.nksp.backend.entity.Student;
import com.nksp.backend.entity.User;
import com.nksp.backend.serviceimpl.StudentServiceImpl;
import com.nksp.backend.serviceimpl.UserServiceImpl;
import com.nksp.backend.util.ApiResultHandler;
import com.nksp.backend.vo.RegisterInfo;
import com.nksp.backend.vo.StudentInfo;
import com.nksp.backend.vo.StudentMainInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class StudentController {
    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/api/student/{userId}")
    public ApiResult findById(@PathVariable("userId") Integer userId) {
        Student res = studentService.findById(userId);
        if (res != null) {
            System.out.println(res);
            return ApiResultHandler.buildApiResult(200, "请求成功", res);
        } else {
            return ApiResultHandler.buildApiResult(404, "查询的用户不存在", null);
        }
    }

    @PostMapping("/api/student/allStudents")
    public ApiResult getAllStudent() {
        List<Student> students = studentService.getAllStudent();
        List<StudentInfo> res = new ArrayList<>();
        for(Student stu:students){
            User user = userService.findById(stu.getUuid());
            StudentInfo si = new StudentInfo();
            si.setStudentInfo(stu.getSuniversity(), stu.getScollege(), stu.getSmajority(),stu.getSno()
            ,user.getUname(),stu.getSclass(),stu.getSgrade(),user.getUtel());
            res.add(si);
        }
        if (res != null) {
            System.out.println(res);
            return ApiResultHandler.buildApiResult(200, "请求成功", res);
        } else {
            return ApiResultHandler.buildApiResult(404, "查询的考试不存在", null);
        }
    }

    @PostMapping("/api/student/addStudent")
    public ApiResult addStudent(@RequestBody StudentInfo si) {
        System.out.println(si);
        User user = new User(0, si.getUname(),si.getUsex(),null,si.getUtel(),si.getUmail(),si.getUdoctype(),
                si.getUdocno(),si.getUpwd(),1,2);
        int ret1 = userService.addUser(user);
        user = userService.findByName(user.getUname());
        Student student = new Student(user.getUuid(), si.getSuniversity(),si.getScollege(), si.getSmajority(), si.getSno(),
                si.getSclass(), si.getSgrade());
        int ret2 = studentService.insertStudent(student);
        if (ret1>0 && ret2>0) {
            System.out.println(si);
            return ApiResultHandler.buildApiResult(200, "请求成功", true);
        } else {
            return ApiResultHandler.buildApiResult(404, "查询的考试不存在", null);
        }
    }

    @PostMapping("/api/admin/updateStudent")
    public ApiResult updateStudent(@RequestBody StudentInfo si) {
        System.out.println(si);
        // 先查，否则信息会丢失
        User user = userService.findByName(si.getUname());
        user.updateInfo(si.getUsex(), si.getUtel(), si.getUmail(), si.getUdoctype(), si.getUdocno(), si.getUpwd());
        int ret1 = userService.updateUser(user);
        Student student = studentService.findByNo(si.getSno());
        student.updateInfo(si.getSuniversity(), si.getScollege(), si.getSmajority(), si.getSclass(), si.getSgrade());
        int ret2 = studentService.updateStudent(student);
        if (ret1>0 && ret2>0) {
            System.out.println(si);
            return ApiResultHandler.buildApiResult(200, "请求成功", true);
        } else {
            return ApiResultHandler.buildApiResult(404, "请求失败", null);
        }
    }

    @PostMapping("/api/admin/deleteStudent")
    public ApiResult deleteStudent(@RequestBody StudentMainInfo smi) {
        System.out.println(smi.getUname() + smi.getSno());
        int ret1 = studentService.deleteStudentByNo(smi.getSno());
        int ret2 = userService.deleteUserByName(smi.getUname());
        if (ret1>0 && ret2>0) {
            return ApiResultHandler.buildApiResult(200, "请求成功", true);
        } else {
            return ApiResultHandler.buildApiResult(404, "请求失败", null);
        }
    }
}