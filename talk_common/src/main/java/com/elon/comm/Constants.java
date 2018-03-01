package com.elon.comm;

import com.elon.util.PropertiesUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 2017/6/22 11:18.
 * <p>
 * Email: cheerUpPing@163.com
 */
public interface Constants {

    /**
     * 数据库配置
     **/
    String jdbc_url = PropertiesUtil.getInstance().getPropertyValue("db.properties", "jdbc_url");
    String user_name = PropertiesUtil.getInstance().getPropertyValue("db.properties", "user_name");
    String user_pass = PropertiesUtil.getInstance().getPropertyValue("db.properties", "user_pass");

    /**
     * 命令码
     */
    class Command {

        //登陆
        public static String login = "S0001";
        public static String register = "S0002";
        public static String friends = "S0003";
        public static String chat = "S0004";
        public static String logout = "S0005";


    }

    /**
     * 错误信息
     */
    class Error {

        public static Map<String, String> errMap = new HashMap<String, String>();

        public static String no_service = "E0001";
        private static String no_service_msg = "请求服务不存在";

        public static String wrong_param = "E0002";
        private static String wrong_param_msg = "参数错误";

        public static String user_not_exit = "E0003";
        private static String user_not_exit_msg = "用户不存在或密码错误";

        public static String user_already_register = "E0004";
        private static String user_already_register_msg = "用户名已经注册";

        public static String to_not_online = "E0005";
        private static String to_not_online_msg = "来自系统消息,对方不在线";

        static {
            errMap.put(no_service, no_service_msg);
            errMap.put(wrong_param, wrong_param_msg);
            errMap.put(user_not_exit, user_not_exit_msg);
            errMap.put(user_already_register, user_already_register_msg);
            errMap.put(to_not_online, to_not_online_msg);


        }

    }

}
