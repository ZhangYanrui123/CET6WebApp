package com.nksp.backend.serviceimpl;

import com.nksp.backend.entity.Grade;
import com.nksp.backend.mapper.GradeMapper;
import com.nksp.backend.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradeServiceImpl implements GradeService {
    @Autowired
    private GradeMapper gradeMapper;

    @Override
    public Grade findById(Integer gid) {
        return gradeMapper.findById(gid);
    }
}
