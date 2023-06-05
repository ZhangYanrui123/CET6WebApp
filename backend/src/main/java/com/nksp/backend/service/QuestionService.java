package com.nksp.backend.service;

import com.nksp.backend.entity.Question;
import com.nksp.backend.entity.Teacher;

import java.util.List;

public interface QuestionService {
    Question findById(Integer qid);

    int insertQues(Question question);

    List<Question> getSubQuesList(Integer qid);
}
