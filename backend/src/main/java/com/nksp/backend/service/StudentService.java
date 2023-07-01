package com.nksp.backend.service;

import com.nksp.backend.entity.Student;
import com.nksp.backend.entity.User;
import com.nksp.backend.vo.RegisterInfo;

import java.util.List;

public interface StudentService {
    Student findById(Integer userId);
    int insertStudent(Student info);

    List<Student> getAllStudent();

    int updateStudent(Student student);

    Student findByNo(int sno);

    int deleteStudentByNo(int sno);
}
