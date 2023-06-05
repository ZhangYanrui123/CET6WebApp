package com.nksp.backend.serviceimpl;

import com.nksp.backend.entity.Grade;
import com.nksp.backend.entity.Paper;
import com.nksp.backend.mapper.GradeMapper;
import com.nksp.backend.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {
    @Autowired
    private GradeMapper gradeMapper;

    @Override
    public Grade findById(Integer gid) {
        return gradeMapper.findById(gid);
    }

    @Override
    public int updateSOScore(Grade grade){return gradeMapper.updateSOScore(grade);}

    @Override
    public int updateSubScore(Grade grade){return gradeMapper.updateSubScore(grade);}

    @Override
    public List<Grade> getAllGrade(Integer uuid){return gradeMapper.getAllGrade(uuid);}
}
