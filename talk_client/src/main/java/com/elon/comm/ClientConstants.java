package com.elon.comm;

import com.elon.gui.ChatFrame;
import com.elon.util.PropertiesUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 2017/6/22 15:47.
 * <p>
 * Email: cheerUpPing@163.com
 */
public class ClientConstants {

    /**
     * 端口
     **/
    public static String remote_host_ip = PropertiesUtil.getInstance().getPropertyValue("client_config.properties", "remote_host_ip");
    public static String remote_host_port = PropertiesUtil.getInstance().getPropertyValue("client_config.properties", "remote_host_port");

    public static String curr_user = null;

    /**
     * 保存所有的聊天窗口
     **/
    public static Map<String, ChatFrame> chatFrames = new ConcurrentHashMap<String, ChatFrame>();
}
