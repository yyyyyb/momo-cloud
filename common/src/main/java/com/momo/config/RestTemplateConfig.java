package com.momo.config;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Configuration
public class RestTemplateConfig {

    /**
     * 声明 RestTemplate
     */
    @Bean
    public RestTemplate restTemplate() {
        ClientHttpRequestFactory factory = httpRequestFactory();
        return new RestTemplate(factory);
    }

    /**
     * 工厂
     * @return 连接
     */
    private ClientHttpRequestFactory httpRequestFactory() {
        return new OkHttp3ClientHttpRequestFactory(okHttpConfigClient());
    }

    /**
     * 客户端
     * @return 连接
     */
    private OkHttpClient okHttpConfigClient() {
        return new OkHttpClient().newBuilder()
                .connectionPool(pool())
                .connectTimeout(10L, TimeUnit.SECONDS)
                .readTimeout(10L, TimeUnit.SECONDS)
                .writeTimeout(10L, TimeUnit.SECONDS)
                .hostnameVerifier((hostname, session) -> true)
                .build();
    }

    /**
     * 连接池
     * @return 连接池
     */
    private ConnectionPool pool() {
        return new ConnectionPool(30, 300L, TimeUnit.MINUTES);
    }

}
