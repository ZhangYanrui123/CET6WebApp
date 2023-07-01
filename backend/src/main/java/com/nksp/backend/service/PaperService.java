package com.nksp.backend.service;

import com.nksp.backend.entity.Paper;

public interface PaperService {
    Paper findById(Integer pid);

    Paper findMaxId();

    int insertPaper(Paper paper);

    int updateQuestions(Paper paper);
}
