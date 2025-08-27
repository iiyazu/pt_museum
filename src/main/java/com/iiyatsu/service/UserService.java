package com.iiyatsu.service;

import com.iiyatsu.pojo.user.LoginInfo;
import com.iiyatsu.pojo.user.User;

public interface UserService {

    LoginInfo login(User user);

    boolean register(User user);
}
