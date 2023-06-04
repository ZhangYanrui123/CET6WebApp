package com.nksp.backend.serviceimpl;

import com.nksp.backend.entity.Paper;
import com.nksp.backend.mapper.PaperMapper;
import com.nksp.backend.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaperServiceImpl implements PaperService {
    @Autowired
    private PaperMapper paperMapper;
    @Override
    public Paper findById(Integer pid) {
        return paperMapper.findById(pid);
    }
}
