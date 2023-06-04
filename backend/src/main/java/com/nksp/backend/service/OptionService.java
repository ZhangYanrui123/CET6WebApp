package com.nksp.backend.service;

import com.nksp.backend.entity.Option;
import com.nksp.backend.entity.Question;

public interface OptionService {
    Option findById(Integer oid);

    int insertOption(Option option);
}
