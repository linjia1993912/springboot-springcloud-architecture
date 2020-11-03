package com.example.providerservice.controller;

import com.example.providerservice.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:服务提供者Controller
 * @Author LinJia
 * @Date 2020/11/3
 **/
@RestController
//使用消息总线刷新配置文件
//RefreshScope是spring cloud提供的一种特殊的scope实现，用来实现配置、实例热加载。
@RefreshScope
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @Value("${foo}")
    String foo;

    @GetMapping("/hi")
    public String home(@RequestParam(value = "name", defaultValue = "forezp") String name){
        System.out.println("-----------provider-service");
        return providerService.home(name);
    }


    @GetMapping("/getConfig")
    public String getConfig(){
        System.out.println("-----------provider-service");
        return providerService.home(foo);
    }
}
