package com.tensquare.manage.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author:
 * @create: 2019-06-01 20:41
 **/
@Component
public class ManageFilter extends ZuulFilter {
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String header = request.getHeader("Authorization");
        System.out.println("头信息为： "+header);
        //两个不拦截
        String url = request.getRequestURL().toString();
        if(request.getMethod().equals("OPTIONS") || url.indexOf("/admin/login")>0){
            return null;
        }
        if(header!=null || header.startsWith("Bearer ")){
            String token = header.substring(7);
            Claims claims = jwtUtil.parseJWT(token);
            if (claims!=null){
                String roles = (String) claims.get("roles");
                if("admin".equals(roles)){
                    currentContext.addZuulRequestHeader("Authorization","header");
                    System.out.println("token 验证通过，添加了头信息"+header);
                    return null;
                }
            }
        }
        //不往下执行
        currentContext.setSendZuulResponse(false);
        currentContext.setResponseStatusCode(403);
        currentContext.setResponseBody("无权访问");
        currentContext.getResponse().setContentType("text/html;charset=utf-8");
        return null;
    }
}