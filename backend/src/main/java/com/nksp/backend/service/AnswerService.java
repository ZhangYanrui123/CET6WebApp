package com.nksp.backend.service;

import com.nksp.backend.entity.Answer;
import com.nksp.backend.entity.Blank;

public interface AnswerService {
    Answer findById(Integer aid);
}
