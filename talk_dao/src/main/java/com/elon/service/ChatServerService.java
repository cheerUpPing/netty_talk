package com.elon.service;

import com.elon.ServerConstants;
import com.elon.comm.AbstractService;
import com.elon.comm.Constants;
import com.elon.entity.RequestEntity;
import com.elon.entity.ResponseEntity;
import io.netty.channel.Channel;
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
public class ChatServerService extends AbstractService {

    private Logger logger = Logger.getLogger(ChatServerService.class);

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void doBusiness(ChannelHandlerContext channelHandlerContext, RequestEntity requestEntity) {

        String to = requestEntity.getTo();
        Channel to_channel = ServerConstants.client_map.get(to);
        ResponseEntity responseEntity = new ResponseEntity(requestEntity);
        logger.info("聊天室-------------------->聊天时间：" + sdf.format(new Date()) + "内容：" + requestEntity);
        if (to_channel == null) {
            responseEntity.setData(Constants.Error.errMap.get(Constants.Error.to_not_online));
            channelHandlerContext.writeAndFlush(responseEntity);
            return;
        }
        to_channel.writeAndFlush(responseEntity);
    }

}
