package com.qgg.feignclient.common.config;

import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class MyFeignConfig {

    /**
     * 注入该Bean，Feign在远程调用失败后会进行重试
     */
    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default(100, TimeUnit.SECONDS.toMillis(1),5);
    }
}
