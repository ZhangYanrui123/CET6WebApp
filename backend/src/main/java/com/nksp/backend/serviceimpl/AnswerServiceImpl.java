package com.nksp.backend.serviceimpl;

import com.nksp.backend.entity.Answer;
import com.nksp.backend.mapper.AnswerMapper;
import com.nksp.backend.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    private AnswerMapper answerMapper;

    @Override
    public Answer findById(Integer aid) {
        return answerMapper.findById(aid);
    }
}