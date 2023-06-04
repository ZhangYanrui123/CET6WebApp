package com.nksp.backend.service;

import com.nksp.backend.entity.Question;
import com.nksp.backend.entity.Record;

public interface RecordService {
    Record findById(Integer rid);
}
