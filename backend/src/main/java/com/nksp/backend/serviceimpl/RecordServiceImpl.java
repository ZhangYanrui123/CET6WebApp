package com.nksp.backend.serviceimpl;

import com.nksp.backend.entity.Question;
import com.nksp.backend.entity.Record;
import com.nksp.backend.mapper.QuestionMapper;
import com.nksp.backend.mapper.RecordMapper;
import com.nksp.backend.service.PaperService;
import com.nksp.backend.service.QuestionService;
import com.nksp.backend.service.RecordService;
//import org.apache.commons.beanutils.PropertyUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    private RecordMapper recordMapper;
    @Override
    public Record findById(Integer qid) {
        return recordMapper.findById(qid);
    }

    @Override
    public List<Record> findByUEid(Integer uuid, Integer eid) {
        return recordMapper.findByUEid(uuid, eid);
    }

    @Override
    public int insertRecord(Record record){return recordMapper.insertRecord(record);}

    @Override
    public int updateRecord(Record record){return recordMapper.updateRecord(record);}
}
