package com.iiyatsu.service.impl;

import com.iiyatsu.mapper.UserMapper;
import com.iiyatsu.pojo.user.Feedback;
import com.iiyatsu.pojo.user.LoginInfo;
import com.iiyatsu.pojo.user.User;
import com.iiyatsu.service.UserService;
import com.iiyatsu.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public LoginInfo login(User user) {
        User u = userMapper.selectByIdAndPasswd(user);
        // 用户不存在
        if(u == null) return null;
        // 生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", u.getId());
        // 是否是管理员
        claims.put("admin", u.isAdmin());
        String jwt = JwtUtils.generateToken(claims);
        return new LoginInfo(u.getId(), u.getName(), jwt);
    }

    @Override
    public boolean register(User user) {
        User u = userMapper.selectById(user);
        if(u != null) return false;
        userMapper.insertNewUser(user);
        return true;
    }

    @Override
    public void submitFeedback(Feedback feedback) {
        feedback.setCreateTime(LocalDateTime.now());
        // 状态默认已在POJO中设置为PENDING，这里再次确认
        feedback.setStatus(Feedback.FStatus.PENDING);
        // 调用Mapper将反馈数据持久化到数据库
        userMapper.insertCommit(feedback);
    }
}
