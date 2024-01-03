package com.momo.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = WarframeConfig.PREFIX)
public class WarframeConfig {
    public static final String PREFIX = "warframe";

    /**
     *
     */
    private String wfQuery;

    /**
     *
     */
    private String wmQuery;

    /**
     *
     */
    private String wmrQuery;

    /**
     *
     */
    private String relicQuery;
}
