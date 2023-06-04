package com.nksp.backend.serviceimpl;

import com.nksp.backend.entity.User;
import com.nksp.backend.mapper.UserMapper;
import com.nksp.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findById(Integer userId) {
        return userMapper.findById(userId);
    }
}
