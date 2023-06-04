package com.nksp.backend.service;

import com.nksp.backend.entity.Exam;
import com.nksp.backend.entity.Student;

public interface ExamService {
    Exam findById(Integer eid);
}
