package com.example.serviceribbon.controller;

import com.example.serviceribbon.service.RibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 服务消费者(ribbon方式消费服务)
 * @Author LinJia
 * @Date 2020/11/3
 **/
@RestController
public class RibbonController {

    @Autowired
    private RibbonService ribbonService;

    /**
     * @Description:消费provider-service服务的“/hi”接口
     * @Author LinJia
     * @Date 2020/11/3 14:49
     * @Param [name]
     * @return java.lang.String
     **/
    @GetMapping("/hi")
    public String hi(@RequestParam String name){
        return ribbonService.hi(name);
    }

    /**
     * @Description:增加熔断器
     * @Author LinJia
     * @Date 2020/11/3 14:56
     * @Param [name]
     * @return java.lang.String
     **/
    @GetMapping("/hiHystrix")
    public String hiHystrix(@RequestParam String name){
        return ribbonService.hiHystrix(name);
    }

}
