package com.liuyf.demo.netty.handler;

import com.liuyf.demo.netty.handler.inbound.ChannelInboundHelloHandler;
import com.liuyf.demo.netty.handler.inbound.ChannelInboundMessageHandler;
import com.liuyf.demo.netty.handler.inbound.ChannelInboundMessageInterceptor;
import com.liuyf.demo.netty.handler.outbound.ChannelOutboundMsgPrefixHandler;
import com.liuyf.demo.netty.handler.outbound.GoodbyeOutboundHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

/**
 *  子 channel的handler初始器
 * <p>
 * <p>
 * Created by liuyf on 2020/7/25.
 */

@Slf4j
public class ChildHandlerInitializer extends ChannelInitializer<NioSocketChannel> {

    ByteBuf delimiterChar = Unpooled.copiedBuffer("," .getBytes());
    ChannelGroup allChannels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    @Override
    protected void initChannel(NioSocketChannel ch) {
        log.info("channel初始化器" + this + "为" + ch + "初始化channel handler");
        allChannels.add(ch);
        ch.pipeline()
                .addLast("lineBasedFrameDecoder", new LineBasedFrameDecoder(1024))  //基于换行符的 tcp 黏包 拆包 解码器
                //.addLast(new DelimiterBasedFrameDecoder(1024, delimiterChar))            // 基于指定字符的tcp 黏包  拆包 解码器
                .addLast("string decoder", new StringDecoder(StandardCharsets.UTF_8))
                .addLast("string encoder", new StringEncoder(StandardCharsets.UTF_8))


                .addLast(new ChannelOutboundMsgPrefixHandler())
                .addLast(new GoodbyeOutboundHandler())
                .addLast(new ChannelInboundHelloHandler())
                .addLast(new ChannelInboundMessageInterceptor())
                .addLast(new ChannelInboundMessageHandler(allChannels));

    }
}
