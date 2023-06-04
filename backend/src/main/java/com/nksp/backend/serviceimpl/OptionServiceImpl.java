package com.nksp.backend.serviceimpl;

import com.nksp.backend.entity.Option;
import com.nksp.backend.mapper.OptionMapper;
import com.nksp.backend.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OptionServiceImpl implements OptionService {
    @Autowired
    private OptionMapper optionMapper;
    @Override
    public Option findById(Integer oid) {
        return optionMapper.findById(oid);
    }

    @Override
    public int insertOption(Option option){return optionMapper.insertOption(option);}
}

