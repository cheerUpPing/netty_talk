package com.elon.server;

import com.elon.comm.Constants;
import com.elon.comm.TalkException;
import com.elon.entity.RequestEntity;
import com.elon.entity.ResponseEntity;
import com.elon.comm.AbstractService;
import com.elon.service.ServiceFactory;
import com.elon.util.LogUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Email cheerupping@163.com
 * Time  2017/5/13 16:51
 * <p>
 * 描述 通道事件驱动器
 */
public class ServerHandler extends SimpleChannelInboundHandler {

    private static Logger logger = Logger.getLogger(ServerHandler.class);

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 读取到信息
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        RequestEntity requestEntity = (RequestEntity) msg;
        if (requestEntity != null) {
            AbstractService service = ServiceFactory.getService(requestEntity.getCommand());
            service.doBusiness(ctx, requestEntity);
        } else {
            throw new TalkException(Constants.Error.wrong_param, Constants.Error.errMap.get(Constants.Error.wrong_param), null);
        }

    }

    /**
     * 通道建立连接
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("我是服务器，收到客户端【" + channel + "】的连接");
        logger.info("建立连接--------------->远程地址：" + channel.remoteAddress() + "时间：" + sdf.format(new Date()));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.info("服务器异常--------------->" + LogUtil.getStackTrace(cause));
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setResult("-1");
        String[] msgs = cause.getMessage().split("\\|");
        responseEntity.setMsg(msgs[0]);
        responseEntity.setCommand(msgs[1]);
        responseEntity.setFrom("system");
        ctx.writeAndFlush(responseEntity);
    }

}
