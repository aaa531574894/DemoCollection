package com.liuyf.demo.netty;

import com.liuyf.demo.netty.handler.ChildHandlerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.AdaptiveRecvByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * 尝试用netty写一个服务端， 对客户端的输入进行 封装并返回
 *
 * 顺带测试 netty 的 codec 编解码器
 *
 * 顺带测试了 ChannelGroup的用法，可以实现 channel的分组与广播
 *
 *
 *
 * 没有写客户端，测试时候是在 linux上使用nc命令进行测试的
 *
 * Created by liuyf on 2020/7/25.
 */


@Slf4j
public class Bootstrap {

    public final int port = 80;


    public static void main(String[] args) throws InterruptedException {
        new Bootstrap().run();
    }


    public void run() {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(2);
        NioEventLoopGroup workGroup = new NioEventLoopGroup(12);
        try {
            ServerBootstrap sbs = new ServerBootstrap();
            sbs.channel(NioServerSocketChannel.class)
                    .group(bossGroup, workGroup)
                    .childHandler(new ChildHandlerInitializer())
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true).childOption(ChannelOption.RCVBUF_ALLOCATOR, new AdaptiveRecvByteBufAllocator(2, 4, 8));


            ChannelFuture channelFuture = sbs.bind(new InetSocketAddress(port)).sync();
            channelFuture.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
            log.info("server stoped ... ");
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();

        }
    }


}
