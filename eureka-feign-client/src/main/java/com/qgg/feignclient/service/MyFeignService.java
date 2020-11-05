package com.qgg.feignclient.service;

import com.qgg.feignclient.common.config.MyHystrix;
import com.qgg.feignclient.common.interf.MyEurekaFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyFeignService {

/*    @Autowired
    private MyEurekaFeignClient myEurekaFeignClient;
    */
    @Autowired
    private MyEurekaFeignClient myHystrix;

    public String getPort(String name) {
//        return myEurekaFeignClient.getPortForEurekaClient(name);
        return myHystrix.getPortForEurekaClient(name);
    }

}
