package com.iiyatsu.controller;

import com.iiyatsu.pojo.Result;
import com.iiyatsu.pojo.user.LoginInfo;
import com.iiyatsu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.iiyatsu.pojo.user.User;

@RequestMapping("/api")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param user
     * @return
     */
    @PostMapping("/auth/login")
    public Result<LoginInfo> login(@RequestBody User user){
        System.out.println("登录：" + user.getId());
        LoginInfo info = userService.login(user);
        if(info == null) return Result.error("用户名或密码错误");
        return Result.success(info);
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @PostMapping("/auth/register")
    public Result<Void> register(@RequestBody User user){
        System.out.println("注册：" + user.getId());
        boolean code = userService.register(user);
        if(code == false) return Result.error("id已被占用，请重新注册");
        return Result.success();
    }
}
