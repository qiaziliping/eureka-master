package com.qgg.feignclient.common.interf;

import com.qgg.feignclient.common.config.MyFeignConfig;
import com.qgg.feignclient.common.config.MyHystrix;
//import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "eureka-client",configuration = MyFeignConfig.class,fallback = MyHystrix.class)
public interface MyEurekaFeignClient {


    /**
     * 调用 eureka-client-4 服务的getPort接口
     * @PathVariable(name = "name")中的"name"必须要写，并且与eureka-client-4 服务的getPort接口对应
     */
    @GetMapping(value = "/getPort/{name}")
    public String getPortForEurekaClient(@PathVariable(name = "name") String name);

}
