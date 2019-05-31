package com.tensquare.friend.controller;

import com.tensquare.friend.service.FriendService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author:
 * @create: 2019-05-18 21:24
 **/
@RestController
@CrossOrigin
@RequestMapping("/friend")
public class FriendController {
    @Autowired
    private FriendService friendService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/like/{friendid}/{type}", method = RequestMethod.PUT)
    public Result addFriend(@PathVariable String friendid, @PathVariable String type) {
        if (type == null) {
            return new Result(false, StatusCode.ERROR,"type参数缺失");
        }
        Claims claims = (Claims) request.getAttribute("claims_user");
        if(claims==null){
            return new Result(false, StatusCode.ACCESSERROR,"无权访问");
        }
        String userid = claims.getId();
        if(friendid.equals(userid)){
            return new Result(false, StatusCode.ERROR,"自己不能喜欢或不喜欢自己");
        }

        //喜欢 1
        if ("1".equals(type)) {
            int flag=friendService.addFriend(userid,friendid);
            //已经存在了喜欢
            if(flag!=0){
                return new Result(false, StatusCode.ERROR,"已经喜欢了");
            }else{
                return new Result(true, StatusCode.OK,"添加成功");
            }
        //不喜欢
        } else {
            int flag=friendService.deleteFriend(userid,friendid);
            //已经不喜欢
            if(flag!=0){
                return new Result(false, StatusCode.ERROR,"已经不喜欢了");
            }else{
                return new Result(true, StatusCode.OK,"删除成功");
            }
        }
    }
}