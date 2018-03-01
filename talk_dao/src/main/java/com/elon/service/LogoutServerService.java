package com.elon.service;

import com.elon.ServerConstants;
import com.elon.comm.AbstractService;
import com.elon.entity.RequestEntity;
import io.netty.channel.ChannelHandlerContext;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 2017/6/22 14:30.
 * <p>
 * Email: cheerUpPing@163.com
 * <p>
 * 退出服务
 */
public class LogoutServerService extends AbstractService {

    private Logger logger = Logger.getLogger(LogoutServerService.class);

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void doBusiness(ChannelHandlerContext channelHandlerContext, RequestEntity requestEntity) {
        String from = requestEntity.getFrom();
        ServerConstants.client_map.remove(from);
        logger.info("用户退出------------------>" + from + "时间：" + sdf.format(new Date()));
    }

}
