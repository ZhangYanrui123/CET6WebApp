package com.nksp.backend.service;

import com.nksp.backend.entity.Option;
import com.nksp.backend.entity.Question;

import java.util.List;

public interface OptionService {
    Option findById(Integer oid);

    int insertOption(Option option);

    List<Option> getOptionList(Integer qid);
}
