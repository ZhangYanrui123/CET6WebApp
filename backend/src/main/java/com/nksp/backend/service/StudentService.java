package com.nksp.backend.service;

import com.nksp.backend.entity.Student;
import com.nksp.backend.entity.User;

public interface StudentService {
    Student findById(Integer userId);
}
