package com.elon.service;

import com.elon.comm.AbstractService;
import com.elon.dao.FriendService;
import com.elon.entity.Friends;
import com.elon.entity.RequestEntity;
import com.elon.entity.ResponseEntity;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;

/**
 * 2017/6/22 14:30.
 * <p>
 * Email: cheerUpPing@163.com
 * <p>
 * 登陆服务
 */
public class FriendsServerService extends AbstractService {

    public void doBusiness(ChannelHandlerContext channelHandlerContext, RequestEntity requestEntity) {

        String from = requestEntity.getFrom();
        List<Friends> friends = FriendService.queryByUserName(from);
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setCommand(requestEntity.getCommand());
        responseEntity.setData(friends);
        channelHandlerContext.writeAndFlush(responseEntity);
    }

}
