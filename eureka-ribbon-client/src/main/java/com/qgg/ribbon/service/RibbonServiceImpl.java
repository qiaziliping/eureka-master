package com.qgg.ribbon.service;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Service
public class RibbonServiceImpl {


    @Autowired
    private RestTemplate restTemplate;


    /**
     * @HystrixCommand 的fallbackMethod指定回退的方法
     * @param name
     * @return
     */
    @HystrixCommand(fallbackMethod = "getPortError")
    public String getPortForHystrix(String name) {
        String result = restTemplate.getForObject("http://eureka-client-4/getPort/"+name,String.class);
        return result;
    }

    public String getPortError(String name) {
        String result = "获取端口出错了-hystrix.test》："+name;
        return result;
    }



}
