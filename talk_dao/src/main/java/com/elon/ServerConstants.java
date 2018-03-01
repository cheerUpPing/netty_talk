package com.elon;

import com.elon.util.PropertiesUtil;
import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 2017/6/22 15:46.
 * <p>
 * Email: cheerUpPing@163.com
 */
public interface ServerConstants {

    /**
     * 端口配置
     **/
    String bind_port = PropertiesUtil.getInstance().getPropertyValue("server_config.properties", "bind_port");

    /**
     * 服务端保存和客户端通讯的连接，这样就可以从服务端主动推送消息给客户端
     */
    Map<String, Channel> client_map = new ConcurrentHashMap<String, Channel>();
}
