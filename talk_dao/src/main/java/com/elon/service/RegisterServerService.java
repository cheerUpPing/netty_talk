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

import java.util.Date;

/**
 * 2017/6/22 14:30.
 * <p>
 * Email: cheerUpPing@163.com
 * <p>
 * 登陆服务
 */
public class RegisterServerService extends AbstractService {

    public void doBusiness(ChannelHandlerContext channelHandlerContext, RequestEntity requestEntity) {

        String from = requestEntity.getFrom();
        User user = UserService.queryByUserName(from);
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setCommand(requestEntity.getCommand());
        if (user != null){
            throw new TalkException(Constants.Error.user_already_register,Constants.Error.errMap.get(Constants.Error.user_already_register),requestEntity.getCommand());
        }
        user = new User(from, (String) requestEntity.getData(),new Date());
        user.save();
        ServerConstants.client_map.put(from,channelHandlerContext.channel());
        channelHandlerContext.writeAndFlush(responseEntity);
    }

}
