package com.liuyf.demo.netty.handler.inbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * 拦截服务端的输入
 * <p>
 * <p>
 * Created by liuyf on 2020/7/25.
 */
@Slf4j
public class ChannelInboundMessageInterceptor extends ChannelInboundHandlerAdapter {





    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //记录日志
        log.info(ctx.channel().remoteAddress() + "：" + msg);
        //向后fire 事件
        ctx.fireChannelRead(msg);

    }



}
