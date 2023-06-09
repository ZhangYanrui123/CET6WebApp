package com.nksp.backend.serviceimpl;

import com.nksp.backend.entity.Question;
import com.nksp.backend.mapper.QuestionMapper;
import com.nksp.backend.mapper.StudentMapper;
import com.nksp.backend.service.QuestionService;
import com.nksp.backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Override
    public Question findById(Integer qid) {
        return questionMapper.findById(qid);
    }

    @Override
    public int insertQues(Question question){return questionMapper.insertQues(question);}

    @Override
    public List<Question> getSubQuesList(Integer qid){return questionMapper.getSubQuesList(qid);}
}
