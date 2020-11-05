package com.qgg.zuulclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy         // 开启zuul的功能
@SpringBootApplication
@EnableEurekaClient
public class EurekaZuulClientApplication
{

    public static void main(String[] args) {
        SpringApplication.run(EurekaZuulClientApplication.class,args);
    }

}
