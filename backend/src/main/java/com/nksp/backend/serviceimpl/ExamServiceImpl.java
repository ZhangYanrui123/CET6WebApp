package com.nksp.backend.serviceimpl;

import com.nksp.backend.entity.Exam;
import com.nksp.backend.entity.Student;
import com.nksp.backend.entity.Teacher;
import com.nksp.backend.mapper.ExamMapper;
import com.nksp.backend.mapper.TeacherMapper;
import com.nksp.backend.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    private ExamMapper examMapper;

    @Override
    public Exam findById(Integer eid) {
        return examMapper.findById(eid);
    }
}
