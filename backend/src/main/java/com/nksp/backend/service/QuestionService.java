package com.nksp.backend.service;

import com.nksp.backend.entity.Question;
import com.nksp.backend.entity.Teacher;

public interface QuestionService {
    Question findById(Integer qid);
}
