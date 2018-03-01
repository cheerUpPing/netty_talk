package com.elon.client;

import com.elon.comm.AbstractService;
import com.elon.entity.ResponseEntity;
import com.elon.service.ClientServiceFactory;
import com.elon.util.LogUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Email cheerupping@163.com
 * Time  2017/5/13 16:51
 * <p>
 * 描述 通道事件驱动器
 */
public class ClientHandler extends SimpleChannelInboundHandler {
    /**
     * 读取到信息
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        ResponseEntity responseEntity = (ResponseEntity) msg;
        AbstractService clientService = ClientServiceFactory.getService(responseEntity.getCommand());
        if (clientService != null){
            clientService.doBusiness(ctx, responseEntity);
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
        System.out.println("我是客户端，已成功和服务器建立连接【" + channel + "】");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("客户端发生异常：【" + LogUtil.getStackTrace(cause) + "】");
    }
}
