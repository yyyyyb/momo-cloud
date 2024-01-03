package com.momo;


import com.momo.config.BotInfoConfig;
import com.momo.listener.BotEventListener;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.utils.BotConfiguration;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import top.mrxiaom.qsign.QSignService;

import java.io.File;

@Component
@Slf4j
public class BotInit implements ApplicationRunner {

    @Resource
    private BotInfoConfig botInfo;

    @Override
    public void run(ApplicationArguments args){
        try{
            //签名服务启动
            //qSignInit();
            //机器人初始化
            //botInit();
        } catch (Exception e) {
            log.error("机器人登陆失败");
            log.error(e.getMessage());
        }
    }

    /**
     *
     */
    private void qSignInit() {
        // 初始化签名服务，参数为签名服务工作目录，里面应当包含
        QSignService.Factory.init(new File(botInfo.getQsign()));
        // 加载签名服务所需协议信息，如果你的协议信息存在非 以上的工作目录 中的文件夹，请将参数 null 改为协议信息所在目录
        // 该方法将会扫描目录下以协议信息命名的文件进行加载，如 android_phone.json
        // 如果你想单独加载协议信息文件，详见 loadProtocolExample() 中的例子
        QSignService.Factory.loadProtocols(null);

        // 注册签名服务
        QSignService.Factory.register();
    }

    /**
     *  机器人初始化
     */
    private void botInit() {
        BotConfiguration configuration = BotConfiguration.getDefault();
        // 登录协议
        configuration.setProtocol(BotConfiguration.MiraiProtocol.valueOf(botInfo.getProtocol()));
        // 心跳协议
        configuration.setHeartbeatStrategy(BotConfiguration.HeartbeatStrategy.STAT_HB);
        // 设置 cache 目录
        configuration.setCacheDir(new File("./cache"));
        // 设置 device
        configuration.fileBasedDeviceInfo("./device.json");
        //设置是否掉线重登录
        configuration.setAutoReconnectOnForceOffline(true);

        Bot bot = BotFactory.INSTANCE.newBot(botInfo.getAccount(), botInfo.getPassword(), configuration);
        bot.login();
        bot.getEventChannel().registerListenerHost(new BotEventListener());
    }
}
