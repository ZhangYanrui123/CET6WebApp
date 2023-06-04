package com.nksp.backend.serviceimpl;

import com.nksp.backend.entity.Question;
import com.nksp.backend.entity.Record;
import com.nksp.backend.mapper.QuestionMapper;
import com.nksp.backend.mapper.RecordMapper;
import com.nksp.backend.service.QuestionService;
import com.nksp.backend.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    private RecordMapper recordMapper;
    @Override
    public Record findById(Integer qid) {
        return recordMapper.findById(qid);
    }
}
