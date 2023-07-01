package com.nksp.backend.service;

import com.nksp.backend.entity.User;
import com.nksp.backend.vo.RegisterInfo;

public interface UserService {
    User findById(Integer userId);

    User findByName(String uname);

    int insertUser(RegisterInfo info);
}
