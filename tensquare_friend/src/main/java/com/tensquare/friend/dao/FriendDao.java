package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author:
 * @create: 2019-05-18 21:42
 **/
public interface FriendDao extends JpaRepository<Friend,String> {

    public Friend findByUseridAndFriendid(String userid, String friendid);

    @Modifying
    @Query(value = "update tb_friend set islike=?3 where userid=?1 and friendid=?2",nativeQuery = true)
    void updateIslike(String userid, String friendid,String islike);

    void deleteByUseridAndFriendid(String userid,String friendid);
}