package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.NoFriend;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description:
 * @author:
 * @create: 2019-05-19 21:36
 **/
public interface NoFriendDao extends JpaRepository<NoFriend,String> {

    int countByUseridAndFriendid(String userid,String friendid);

    void deleteByUseridAndFriendid(String userid,String friendid);

}
