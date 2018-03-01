package com.elon.service;

import com.elon.comm.AbstractService;
import com.elon.comm.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * 2017/6/23 13:48.
 * <p>
 * Email: cheerUpPing@163.com
 * <p>
 * 客户端处理服务端返回信息
 */
public class ClientServiceFactory {

    private static Map<String, AbstractService> clientServiceMap = new HashMap<String, AbstractService>();

    static {
        clientServiceMap.put(Constants.Command.login, new LoginClientService());
        clientServiceMap.put(Constants.Command.register, new RegisterClientService());
        clientServiceMap.put(Constants.Command.friends, new FriendsClientService());
        clientServiceMap.put(Constants.Command.chat, new ChatClientService());
        clientServiceMap.put(Constants.Command.logout, new LogoutClientService());

    }

    public static AbstractService getService(String serviceName) {
        return clientServiceMap.get(serviceName);
    }


}
