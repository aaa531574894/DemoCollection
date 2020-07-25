package com.liuyf.demo.netty.handler.outbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2020/7/25.
 */

public class GoodbyeOutboundHandler extends ChannelOutboundHandlerAdapter {


    @Override
    public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        ctx.writeAndFlush("good bye~");
        super.close(ctx, promise);
    }
}
