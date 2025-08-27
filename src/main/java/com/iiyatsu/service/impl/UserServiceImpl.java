package com.iiyatsu.service.impl;

import com.iiyatsu.mapper.UserMapper;
import com.iiyatsu.pojo.user.LoginInfo;
import com.iiyatsu.pojo.user.User;
import com.iiyatsu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public LoginInfo login(User user) {
        User u = userMapper.selectByIdAndPasswd(user);
        if(u == null) return null;
        return new LoginInfo(u.getId(), u.getName(), "");
    }

    @Override
    public boolean register(User user) {
        User u = userMapper.selectById(user);
        if(u != null) return false;
        userMapper.insertNewUser(user);
        return true;
    }
}
