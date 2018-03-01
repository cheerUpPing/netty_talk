package com.elon.service;

import com.elon.ServerConstants;
import com.elon.comm.AbstractService;
import com.elon.comm.Constants;
import com.elon.comm.TalkException;
import com.elon.dao.UserService;
import com.elon.entity.RequestEntity;
import com.elon.entity.ResponseEntity;
import com.elon.entity.User;
import io.netty.channel.ChannelHandlerContext;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 2017/6/22 14:30.
 * <p>
 * Email: cheerUpPing@163.com
 * <p>
 * 登陆服务
 */
public class LoginServerService extends AbstractService {

    private Logger logger = Logger.getLogger(LoginServerService.class);

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void doBusiness(ChannelHandlerContext channelHandlerContext, RequestEntity requestEntity) {

        String from = requestEntity.getFrom();
        User user = UserService.queryByUserName(from);
        if (user == null || !user.get("user_pass").equals(requestEntity.getData())) {
            throw new TalkException(Constants.Error.user_not_exit, Constants.Error.errMap.get(Constants.Error.user_not_exit), requestEntity.getCommand());
        }
        logger.info("用户登录------------------>" + from + "时间：" + sdf.format(new Date()));
        ServerConstants.client_map.put(from, channelHandlerContext.channel());
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setCommand(requestEntity.getCommand());
        channelHandlerContext.writeAndFlush(responseEntity);
    }

}
