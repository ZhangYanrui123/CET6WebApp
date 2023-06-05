package com.nksp.backend.service;

import com.nksp.backend.entity.Question;
import com.nksp.backend.entity.Record;

import java.util.List;

public interface RecordService {
    Record findById(Integer rid);

    int insertRecord(Record record);

    int updateRecord(Record record);

    List<Record> findByUEid(Integer uuid, Integer eid);
}
