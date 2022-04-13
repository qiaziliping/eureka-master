package com.qgg.ribbon.controller;

import com.qgg.ribbon.service.RibbonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/myRibbon")
public class RibbonController {



    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RibbonServiceImpl ribbonServiceImpl;

    /**
     * 方式1，通过@LoadBalanced 注解方式进行负载均衡
     * @param name
     * @return
     */
    @RequestMapping(value = "/getPort/{name}")
    public String getPort(@PathVariable String name) {
        String result = restTemplate.getForObject("http://eureka-client/getPort/"+name,String.class);
        return result;
    }



    @Autowired
    private LoadBalancerClient loadBalancerClient;

    /**
     * 方式2，通过LoadBalancerClient进行负载均衡
     * @return
     */
    @GetMapping("/testRibbon")
    public String testRibbon() {
        Map<String,Object> result = new HashMap<String,Object>();

        ServiceInstance instance = loadBalancerClient.choose("eureka-client");
//        ServiceInstance instance = loadBalancerClient.choose("service-qia");

        String host = instance.getHost();
        int port = instance.getPort();
        String serviceId = instance.getServiceId();
        URI uri = instance.getUri();
        Map<String,String> metadata = instance.getMetadata(); // 元数据

        result.put("host",host);
        result.put("port",port);
        result.put("serviceId",serviceId);
        result.put("uri",uri);
        result.put("metadata",metadata);
        return result.toString();
    }

    /**
     * 方式2，通过LoadBalancerClient进行负载均衡
     * @return
     */
    @GetMapping("/getPort2/{name}")
    public String getPort2(@PathVariable String  name) {

        ServiceInstance instance = loadBalancerClient.choose("eureka-client");
//        ServiceInstance instance = loadBalancerClient.choose("service-qia");
        URI uri = instance.getUri();

        String result = restTemplate.getForObject(uri+"/getPort/"+name,String.class);
        return result;
    }


    @RequestMapping(value = "/getPortForHystrix/{name}")
    public String getPortForHystrix(@PathVariable String name) {
        String result = ribbonServiceImpl.getPortForHystrix(name);
        return result;
    }

}
