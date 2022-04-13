package com.qgg.eurekaclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {

    @Value("${server.port}")
    public int port;

    @GetMapping(value = "/getPort/{name}")
    public String helloPort(@PathVariable String name) {
        int i = 1/0;
        return name+"-"+port;
    }
}
