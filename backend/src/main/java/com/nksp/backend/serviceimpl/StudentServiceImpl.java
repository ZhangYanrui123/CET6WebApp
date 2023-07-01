package com.nksp.backend.serviceimpl;

import com.nksp.backend.entity.Student;
import com.nksp.backend.entity.User;
import com.nksp.backend.mapper.UserMapper;
import com.nksp.backend.mapper.StudentMapper;
import com.nksp.backend.service.StudentService;
import com.nksp.backend.vo.RegisterInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public Student findById(Integer userId) {
        return studentMapper.findById(userId);
    }

    @Override
    public int insertStudent(Student info){return studentMapper.insertStudent(info);}
}
