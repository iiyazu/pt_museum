package com.iiyatsu.controller;

import com.iiyatsu.pojo.Result;
import com.iiyatsu.pojo.user.Feedback;
import com.iiyatsu.pojo.user.LoginInfo;
import com.iiyatsu.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.iiyatsu.pojo.user.User;

@Slf4j
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

    /**
     * 提交反馈
     * @param feedback
     * @param request
     * @return
     */
    @PostMapping("/feedback")
    public Result<Void> feedback(@RequestBody Feedback feedback, HttpServletRequest request){
        // 从请求属性中获取拦截器传递过来的用户ID
        String userId = (String) request.getAttribute("userId");
        if (userId == null) {
            // 这通常不应该发生，因为拦截器会保证用户已登录
            log.warn("未登录用户尝试提交反馈");
            return Result.error("未登录或登录信息过期，请重新登录");
        }

        log.info("用户 {} 提交了新的反馈", userId);
        // 将从令牌中获取的userId设置到反馈对象中
        feedback.setUserId(userId);
        userService.submitFeedback(feedback);
        return Result.success();
    }
}
