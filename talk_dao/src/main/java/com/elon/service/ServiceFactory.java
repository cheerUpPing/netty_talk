package com.elon.service;

import com.elon.DBFactory;
import com.elon.comm.AbstractService;
import com.elon.comm.Constants;
import com.elon.comm.TalkException;

import java.util.HashMap;
import java.util.Map;

/**
 * 2017/6/22 14:20.
 * <p>
 * Email: cheerUpPing@163.com
 */
public class ServiceFactory {

    private static Map<String, AbstractService> serviceMap = new HashMap<String, AbstractService>();

    static {
        /**启动jfinal数据库插件支持**/
        DBFactory.startDB();
        serviceMap.put(Constants.Command.login, new LoginServerService());
        serviceMap.put(Constants.Command.register, new RegisterServerService());
        serviceMap.put(Constants.Command.friends, new FriendsServerService());
        serviceMap.put(Constants.Command.chat, new ChatServerService());
        serviceMap.put(Constants.Command.logout, new LogoutServerService());


    }

    /**
     * 获取服务
     *
     * @param serviceName
     * @return
     */
    public static AbstractService getService(String serviceName) {
        AbstractService service = serviceMap.get(serviceName);
        if (service == null) {
            throw new TalkException(Constants.Error.no_service, Constants.Error.errMap.get(Constants.Error.no_service),serviceName);
        }
        return service;
    }

}
