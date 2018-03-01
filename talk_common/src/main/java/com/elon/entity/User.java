package com.elon.entity;

import com.jfinal.plugin.activerecord.Model;

import java.util.Date;

/**
 * 2017/6/22 14:58.
 * <p>
 * Email: cheerUpPing@163.com
 */
public class User extends Model<User> {

    public static User user = new User();


    public User() {

    }

    public User(String user_name, String user_pass, Date add_time, Date login_time) {
        set("user_name", user_name);
        set("user_pass", user_pass);
        set("add_time", add_time);
        set("login_time", login_time);
    }

    public User(String user_name, String user_pass, Date add_time) {
        this(user_name, user_pass, add_time, null);
    }
}
