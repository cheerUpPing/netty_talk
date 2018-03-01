package com.elon.dao;

import com.elon.entity.User;

import java.util.List;

/**
 * 2017/6/22 16:07.
 * <p>
 * Email: cheerUpPing@163.com
 */
public class UserService {

    public static User queryByUserName(String username) {
        List<User> users = User.user.find("select * from user where user_name = '" + username + "'");

        return users == null || users.size() == 0 ? null : users.get(0);
    }

}
