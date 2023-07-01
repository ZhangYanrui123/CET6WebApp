package com.nksp.backend.service;

import com.nksp.backend.entity.Student;
import com.nksp.backend.entity.User;
import com.nksp.backend.vo.RegisterInfo;

public interface StudentService {
    Student findById(Integer userId);
    int insertStudent(Student info);
}
