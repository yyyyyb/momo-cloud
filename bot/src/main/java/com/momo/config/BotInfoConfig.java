package com.momo.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = BotInfoConfig.PREFIX)
public class BotInfoConfig {
    public static final String PREFIX = "bot";

    /**
     * 账号
     */
    private Long account;

    /**
     * 密码
     */
    private String password;

    /**
     * 设备协议
     */
    private String protocol;

    /**
     * 签名文件地址
     */
    private String qsign;

    /**
     * 管理员
     */
    private Long admin;
}
