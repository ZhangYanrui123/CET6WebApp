package com.nksp.backend.serviceimpl;

import com.nksp.backend.entity.Exam;
import com.nksp.backend.mapper.ExamMapper;
import com.nksp.backend.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    private ExamMapper examMapper;

    @Override
    public Exam findById(Integer eid) {
        return examMapper.findById(eid);
    }

    @Override
    public List<Exam> findByUid(Integer uuid){return examMapper.findByUid(uuid);}

    @Override
    public int addExam(Exam exam){return examMapper.addExam(exam);}

    @Override
    public List<Exam> getAllExams() {
        return examMapper.getAllExams();}

    @Override
    public Integer deleteById(Integer eid) {return examMapper.deleteById(eid);}
}
