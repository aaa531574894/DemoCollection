package com.liuyf.demo.netty.handler.inbound;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * 处理用户传入的消息， 默认返回 原话
 * <p>
 * <p>
 * Created by liuyf on 2020/7/25.
 */


@Slf4j
public class ChannelInboundMessageHandler extends ChannelInboundHandlerAdapter {

    public final static String GOOD_BYE = "bye";

    private ChannelGroup channels;

    public ChannelInboundMessageHandler(ChannelGroup channels) {
        this.channels = channels;
    }


    @Override

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        if (msg.equals(GOOD_BYE)) {// 断开连接
            ctx.channel().close();
        }else {
            //向客户端返回确认消息
            ctx.writeAndFlush( msg );
        }


    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //有新的channel加入时  广播通知其他channel
        // 主要是测试 ChannlGroup的用法
        channels.writeAndFlush( LocalDateTime.now().toString() + "  " + ctx.channel().remoteAddress() + "加入了聊天" );
    }



}
