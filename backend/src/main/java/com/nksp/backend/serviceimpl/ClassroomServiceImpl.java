package com.nksp.backend.serviceimpl;

import com.nksp.backend.entity.Classroom;
import com.nksp.backend.entity.Student;
import com.nksp.backend.mapper.ClassroomMapper;
import com.nksp.backend.mapper.StudentMapper;
import com.nksp.backend.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassroomServiceImpl implements ClassroomService {
    @Autowired
    private ClassroomMapper classroomMapper;
    @Override
    public Classroom findById(Integer cid) {
        return classroomMapper.findById(cid);
    }

}
