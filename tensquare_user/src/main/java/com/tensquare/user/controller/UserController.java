package com.tensquare.user.controller;

import com.tensquare.user.pojo.User;
import com.tensquare.user.service.UserService;
import entity.Result;
import entity.StatusCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import util.JwtUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 用户注册模块
 * @author:
 * @create: 2019-04-25 21:40
 **/
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(value = "/sendsms/{mobile}", method = RequestMethod.POST)
    public Result sendMsg(@PathVariable String mobile) {
        userService.sendSms(mobile);

        return new Result(true, StatusCode.OK, "验证码发送成功");
    }

    @RequestMapping(value = "/register/{code}", method = RequestMethod.POST)
    public Result register(@PathVariable String code,@RequestBody User user) {
        if(code==null || StringUtils.isBlank(code)){
            return new Result(false,StatusCode.ERROR,"验证码不能为空!");
        }
        String checkcodeRedis= (String) redisTemplate.opsForValue().get("checkcode_"+user.getMobile());
        if(checkcodeRedis==null || checkcodeRedis.isEmpty()){
            return new Result(false, StatusCode.ERROR, "请先获取手机验证码");
        }
        if(!code.equals(checkcodeRedis)){
            return new Result(false,StatusCode.ERROR,"验证码输入错误!");
        }

        userService.add(user);

        return new Result(true, StatusCode.OK, "注册成功");
    }

    @RequestMapping(value = "/{userId}",method = RequestMethod.GET)
    public Result findById(@PathVariable String userId){
        User user = userService.findById(userId);
        return new Result(true,StatusCode.OK,"查询成功",user);
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result login(@RequestBody User user){
        User userLogin = userService.login(user.getMobile(), user.getPassword());
        if(userLogin==null){
            return new Result(false, StatusCode.LOGINERROR, "用户名或密码错误!");
        }
        //生成令牌,,使得前后端保持通话
        String token = jwtUtil.createJWT(userLogin.getId(), userLogin.getNickname(), "user");
        Map<String,Object> map=new HashMap<>();
        map.put("token",token);
        map.put("roles","user");

        return new Result(true,StatusCode.OK,"登录成功",map);
    }

    /**
     * 删除 必须有admin角色才能删除
     * @param id
     */
    @RequestMapping(value="/{id}",method= RequestMethod.DELETE)
    public Result delete(@PathVariable String id ){
        userService.deleteById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }


}