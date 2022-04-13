package com.qgg.ribbon;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableHystrix   // 开启hystrix熔断器功能
@EnableHystrixDashboard   // 开启监控熔断器状态
public class EurekaRibbonClientApplication {


    //方式1，通过@LoadBalanced注解
    /*@Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }*/

    //方式2，通过LoadBalancerClient进行负载均衡
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }


    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }


    public static void main(String[] args) {
        SpringApplication.run(EurekaRibbonClientApplication.class, args);
    }


}
