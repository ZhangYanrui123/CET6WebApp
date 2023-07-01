package com.nksp.backend.service;

import com.nksp.backend.entity.Exam;
import com.nksp.backend.entity.Student;

import java.util.List;

public interface ExamService {
    Exam findById(Integer eid);

    int addExam(Exam exam);

    List<Exam> findByUid(Integer uuid);

    List<Exam> getAllExams();

    Integer deleteById(Integer eid);
}
