package com.nksp.backend.serviceimpl;

import com.nksp.backend.entity.Join;
import com.nksp.backend.entity.Student;
import com.nksp.backend.mapper.JoinMapper;
import com.nksp.backend.mapper.StudentMapper;
import com.nksp.backend.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JoinServiceImpl implements JoinService {
    @Autowired
    private JoinMapper joinMapper;
    @Override
    public Join findById(Integer jid) {
        return joinMapper.findById(jid);
    }
}
