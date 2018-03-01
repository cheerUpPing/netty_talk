package com.elon.dao;

import com.elon.entity.Friends;

import java.util.List;

/**
 * 2017/6/23 16:40.
 * <p>
 * Email: cheerUpPing@163.com
 */
public class FriendService {

    public static List<Friends> queryByUserName(String userName) {
        return Friends.friends.find("select * from friend where user_name = '" + userName + "'");
    }

}
