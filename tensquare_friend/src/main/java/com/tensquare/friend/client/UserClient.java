package com.tensquare.friend.client;

import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @description:
 * @author:
 * @create: 2019-05-19 22:06
 **/
@FeignClient(value = "tensquare-user")
public interface UserClient {
    /**
     * 关注某用户
     * @return
     */
    @RequestMapping(value="/user/follow/{userid}/{friendid}/{x}",method= RequestMethod.PUT)
    public Result followUser(@PathVariable(value = "userid") String userid,@PathVariable(value = "friendid") String friendid,@PathVariable(value = "x") int x);

    /**
     * 取消关注某用户
     * @return
     */
    @RequestMapping(value="/user/follow/{userid}/{friendid}/{x}",method= RequestMethod.DELETE)
    public Result cancelFollowUser(@PathVariable(value = "userid") String userid,@PathVariable(value = "friendid") String friendid,@PathVariable(value = "x") int x);

}