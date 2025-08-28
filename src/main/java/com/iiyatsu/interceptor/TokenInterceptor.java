package com.iiyatsu.interceptor;

import com.iiyatsu.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.获取请求路径
        String requestURI = request.getRequestURI();

        //2.判断请求路径是否为登录
        if(requestURI.contains("/login")){
            log.info("登录请求");
            return true;
        }

        //3.获取token，判断token是否存在，不存在返回错误信息，响应401
        String token = request.getHeader("token");
        if(token == null || token.isEmpty()){
            log.info("token为空");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        //4.解析token，判断token是否正确，不正确返回错误信息，响应401
        Claims claims;
        try{
           claims = JwtUtils.parseToken(token);
        }catch(Exception e){
            log.info("token解析错误");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        //5.从token中获取用户信息，并且验证权限等级
        String userId = (String) claims.get("id");
        Boolean isAdmin = (Boolean) claims.get("admin");

        request.setAttribute("userId", userId);
        request.setAttribute("isAdmin", isAdmin);

        log.info("令牌校验通过，用户ID: {}, 是否管理员: {}", userId, isAdmin);
        //6.校验通过，放行
        return true;

    }
}
