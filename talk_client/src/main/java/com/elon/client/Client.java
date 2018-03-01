package com.elon.client;

import com.elon.comm.ClientConstants;
import com.elon.comm.Constants;
import com.elon.entity.RequestEntity;
import com.elon.util.LogUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.apache.log4j.Logger;

/**
 * Email cheerupping@163.com
 * Time  2017/5/13 17:20
 * <p>
 * 描述  对象传输，使用netty自身的 ObjectEncoder对象编码器  ObjectDecoder对象解码器
 * ObjectEncoder对象编码器  ObjectDecoder对象解码器  是netty自身对对象的解码和编码
 */
public class Client {

    private static Logger logger = Logger.getLogger(Client.class);

    private static Channel channel = null;

    static {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.handler(new ClientInitializer());
        try {
            channel = bootstrap.connect(ClientConstants.remote_host_ip, Integer.parseInt(ClientConstants.remote_host_port)).sync().channel();
        } catch (InterruptedException e) {
            logger.info("socket连接--------------->连接失败," + LogUtil.getStackTrace(e));
        }
    }

    /**
     * 发送信息
     *
     * @param requestEntity
     */
    public static void sendMessage(RequestEntity requestEntity) {
        if (channel != null) {
            channel.writeAndFlush(requestEntity);
        } else {
            logger.info("channel发送信息--------------->没有成功建立socket,请重新登陆。");
        }
    }

    public static void main(String[] args) {
        RequestEntity requestEntity = new RequestEntity();
        requestEntity.setCommand(Constants.Command.login);
        requestEntity.setFrom("ddd");
        requestEntity.setData("dddd");
        Client.sendMessage(requestEntity);
    }

}
