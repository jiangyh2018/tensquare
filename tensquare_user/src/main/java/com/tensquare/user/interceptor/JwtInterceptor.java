package com.tensquare.user.interceptor;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description:
 * @author:
 * @create: 2019-05-08 21:54
 **/
@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtUtil jwtUtil;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("经过拦截器。。。");

        String header = request.getHeader("Authorization");
        if(header!=null && !"".equals(header)){
            if(header.startsWith("Bearer ")){
                //获取token
                String token=header.substring(7);
                if(token!=null && !"".equals(token)) {
                    try {
                        Claims claims = jwtUtil.parseJWT(token);
                        if (claims != null) {
                            String roles = (String) claims.get("roles");
                            if (roles!=null && "admin".equals(roles)) {
                                request.setAttribute("claims_admin",token);
                            }
                            if (roles!=null && "user".equals(roles)) {
                                request.setAttribute("claims_user",token);
                            }
                        }
                    } catch (Exception e) {
//                        throw new RuntimeException("token解析失败");
                        System.out.println("token解析失败");
                    }
                }
            }
        }
        return true;
    }


}