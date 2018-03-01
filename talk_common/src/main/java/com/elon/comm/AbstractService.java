package com.elon.comm;

import com.elon.entity.RequestEntity;
import io.netty.channel.ChannelHandlerContext;

/**
 * 2017/6/22 14:15.
 * <p>
 * Email: cheerUpPing@163.com
 * <p>
 * 抽象服务
 */
public abstract class AbstractService {

    public abstract void doBusiness(ChannelHandlerContext channelHandlerContext, RequestEntity entity);
}
