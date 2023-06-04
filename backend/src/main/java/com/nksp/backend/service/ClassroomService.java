package com.nksp.backend.service;

import com.nksp.backend.entity.Classroom;
import com.nksp.backend.entity.Student;

public interface ClassroomService {
    Classroom findById(Integer cid);
}
