package com.nksp.backend.serviceimpl;

import com.nksp.backend.entity.Join;
import com.nksp.backend.entity.Student;
import com.nksp.backend.mapper.JoinMapper;
import com.nksp.backend.mapper.StudentMapper;
import com.nksp.backend.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JoinServiceImpl implements JoinService {
    @Autowired
    private JoinMapper joinMapper;
    @Override
    public Join findById(Integer jid) {
        return joinMapper.findById(jid);
    }

    @Override
    public List<Join> getAllJoin(Integer uuid){return joinMapper.getAllJoin(uuid);}
}
