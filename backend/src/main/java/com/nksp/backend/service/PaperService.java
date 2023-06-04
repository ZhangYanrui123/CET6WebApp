package com.nksp.backend.service;

import com.nksp.backend.entity.Paper;

public interface PaperService {
    Paper findById(Integer pid);
}
