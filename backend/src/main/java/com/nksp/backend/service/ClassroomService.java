package com.nksp.backend.service;

import com.nksp.backend.entity.Classroom;
import com.nksp.backend.entity.Student;

import java.util.List;

public interface ClassroomService {
    Classroom findById(Integer cid);

    Classroom findByName(String name);

    int addClassromm(Classroom classroom);

    List<Classroom> getAllClassrooms();
}
