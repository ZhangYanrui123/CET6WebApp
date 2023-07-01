package com.nksp.backend.serviceimpl;

import com.nksp.backend.entity.User;
import com.nksp.backend.mapper.UserMapper;
import com.nksp.backend.service.UserService;
import com.nksp.backend.vo.RegisterInfo;
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

    @Override
    public User findByName(String uname){return userMapper.findByName(uname);}

    @Override
    public int insertUser(RegisterInfo info){return userMapper.insertUser(info);}

    @Override
    public int addUser(User user){return userMapper.addUser(user);}

    @Override
    public int updateUser(User user) {return userMapper.updateUser(user);}

    @Override
    public int deleteUserByName(String uname) {return userMapper.deleteUserByName(uname);}
}
