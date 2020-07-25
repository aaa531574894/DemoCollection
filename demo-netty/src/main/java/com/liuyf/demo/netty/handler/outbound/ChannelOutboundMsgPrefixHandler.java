package com.liuyf.demo.netty.handler.outbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2020/7/25.
 */

@Slf4j
public class ChannelOutboundMsgPrefixHandler extends ChannelOutboundHandlerAdapter {



    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        log.info("outboundhandler 1 拦截了消息并加以包装 ");
        ctx.writeAndFlush(LocalDateTime.now().toString() + "\n" + msg + "\n");

    }
}
