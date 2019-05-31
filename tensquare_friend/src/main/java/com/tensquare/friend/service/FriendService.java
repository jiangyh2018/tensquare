package com.tensquare.friend.service;

import com.tensquare.friend.client.UserClient;
import com.tensquare.friend.dao.FriendDao;
import com.tensquare.friend.dao.NoFriendDao;
import com.tensquare.friend.pojo.Friend;
import com.tensquare.friend.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description:
 * @author:
 * @create: 2019-05-18 21:32
 **/
@Service
public class FriendService {
    @Autowired
    private FriendDao friendDao;
    @Autowired
    private NoFriendDao noFriendDao;
    @Autowired
    private UserClient userClient;

    @Transactional
    public int addFriend(String userid,String friendid){
        //删除非好友表
        noFriendDao.deleteByUseridAndFriendid(userid,friendid);
        //判断 是否已经存在
        Friend friend = friendDao.findByUseridAndFriendid(userid, friendid);
        if(friend!=null){
            return 1;
        }
        //判断 是否相互喜欢
        friend = friendDao.findByUseridAndFriendid(friendid,userid);
        Friend f=new Friend();
        f.setUserid(userid);
        f.setFriendid(friendid);
        //单向喜欢
        if(friend==null){
            f.setIslike("0");
            friendDao.save(f);
        }else{
            f.setIslike("1");
            friendDao.save(f);

            friendDao.updateIslike(friendid,userid,"1");
        }
        //关注某用户
        userClient.followUser(userid,friendid,1);

        return 0;
    }

    @Transactional
    public int deleteFriend(String userid, String friendid) {
        //判断 是否已经不是好友
        int flag=noFriendDao.countByUseridAndFriendid(userid,friendid);
        if(flag>0){
            return 1;
        }
        //插入非好友表
        NoFriend noFriend=new NoFriend();
        noFriend.setUserid(userid);
        noFriend.setFriendid(friendid);
        noFriendDao.save(noFriend);
        //删除好友表存在
//        friendDao.deleteByUseridAndFriendid(userid,friendid);
        //将好友表相互喜欢更新为单向喜欢
        friendDao.updateIslike(friendid,userid,"0");

        Friend friend = friendDao.findByUseridAndFriendid(userid, friendid);
        if(friend!=null){
            //删除好友表存在
            friendDao.deleteByUseridAndFriendid(userid,friendid);
            //取消关注某用户
            userClient.cancelFollowUser(userid,friendid,-1);
        }

        return 0;
    }
}