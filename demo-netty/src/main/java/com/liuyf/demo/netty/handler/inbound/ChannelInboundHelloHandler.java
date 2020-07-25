package com.liuyf.demo.netty.handler.inbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2020/7/25.
 */

public class ChannelInboundHelloHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush("netty server : welcome ");
        ctx.fireChannelActive();
    }
}
