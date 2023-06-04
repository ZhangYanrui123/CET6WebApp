package com.nksp.backend.serviceimpl;

import com.nksp.backend.entity.Fee;
import com.nksp.backend.mapper.FeeMapper;
import com.nksp.backend.service.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeeServiceImpl implements FeeService {
    @Autowired
    private FeeMapper feeMapper;
    @Override
    public Fee findById(Integer fid) {
        return feeMapper.findById(fid);
    }
}

