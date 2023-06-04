package com.nksp.backend.service;

import com.nksp.backend.entity.Student;
import com.nksp.backend.entity.Teacher;

public interface TeacherService {
    Teacher findById(Integer userId);
}
