package com.nksp.backend.service;

import com.nksp.backend.entity.User;

public interface UserService {
    User findById(Integer userId);

    User findByName(String uname);
}
