package com.elon;

import com.elon.comm.Constants;
import com.elon.entity.Friends;
import com.elon.entity.User;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;

/**
 * 2017/6/22 15:58.
 * <p>
 * Email: cheerUpPing@163.com
 */
public class DBFactory {

    /**
     * 启动数据库
     */
    public static void startDB(){
        C3p0Plugin c3p0Plugin = new C3p0Plugin(Constants.jdbc_url, Constants.user_name, Constants.user_pass);
        c3p0Plugin.start();
        ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
        arp.addMapping("user", User.class);
        arp.addMapping("friend", Friends.class);
        arp.setShowSql(true);
        arp.start();
    }

}
