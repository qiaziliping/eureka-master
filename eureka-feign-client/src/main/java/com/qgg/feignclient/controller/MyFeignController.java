package com.qgg.feignclient.controller;

import com.qgg.feignclient.service.MyFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping(value = "/myFeign")
public class MyFeignController {

    @Autowired
    private MyFeignService myFeignService;

    @GetMapping(value = "/getPort/{name}")
    public String getPort(@PathVariable String name) {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strdate = format.format(date);

        String reuslt = myFeignService.getPort(name);
        return strdate+"---"+reuslt;
    }
}
