package com.nksp.backend.serviceimpl;

import com.nksp.backend.entity.Blank;
import com.nksp.backend.mapper.BlankMapper;
import com.nksp.backend.service.BlankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlankServiceImpl implements BlankService {
    @Autowired
    private BlankMapper blankMapper;

    @Override
    public Blank findById(Integer bid) {
        return blankMapper.findById(bid);
    }
}