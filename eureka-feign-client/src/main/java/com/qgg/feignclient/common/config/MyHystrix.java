package com.qgg.feignclient.common.config;

import com.qgg.feignclient.common.interf.MyEurekaFeignClient;
import org.springframework.stereotype.Component;

@Component
public class MyHystrix implements MyEurekaFeignClient {

    @Override
    public String getPortForEurekaClient(String name) {
        return "测试feign结合hystrix-error>:"+name;
    }
}
