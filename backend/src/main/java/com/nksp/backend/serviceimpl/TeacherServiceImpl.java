package com.nksp.backend.serviceimpl;

import com.nksp.backend.entity.Teacher;
import com.nksp.backend.entity.User;
import com.nksp.backend.mapper.TeacherMapper;
import com.nksp.backend.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherMapper {
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public Teacher findById(Integer userId) {
        return teacherMapper.findById(userId);
    }
}
