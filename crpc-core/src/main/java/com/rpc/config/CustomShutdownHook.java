package com.rpc.config;

import com.rpc.config.cache.ConfigCache;
import com.rpc.registry.zk.util.CuratorUtils;
import com.rpc.remoting.transport.netty.server.NettyRpcServer;
import com.rpc.utils.NetUtil;
import com.rpc.utils.threadpool.ThreadPoolFactoryUtils;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import lombok.extern.slf4j.Slf4j;

/**
 * 注销服务的钩子函数，当用户正常停止程序时，把当前的服务注销掉，防止调用到已经下线的服务
 * @author cao wei
 */
@Slf4j
public class CustomShutdownHook {
    private static final CustomShutdownHook CUSTOM_SHUTDOWN_HOOK = new CustomShutdownHook();

    public static CustomShutdownHook getCustomShutdownHook() {
        return CUSTOM_SHUTDOWN_HOOK;
    }

    public void clearAll() {
        log.info("addShutdownHook for clearAll");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            InetSocketAddress inetSocketAddress = new InetSocketAddress(NetUtil.getLocalAddress(),
                ConfigCache.getConfig(ServerConfig.class).getRpcServerPort());
            CuratorUtils.clearRegistry(CuratorUtils.getZkClient(), inetSocketAddress);
            ThreadPoolFactoryUtils.shutDownAllThreadPool();
        }));
    }
}
