package com.example.providerservice.controller;

import com.example.providerservice.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:服务提供者Controller
 * @Author LinJia
 * @Date 2020/11/3
 **/
@RestController
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @GetMapping("/hi")
    public String home(@RequestParam(value = "name", defaultValue = "forezp") String name){
        System.out.println("-----------provider-service");
        return providerService.home(name);
    }

    /**
     * @Description:从配置中心获取配置
     * @Author LinJia
     * @Date 2020/11/4 10:04
     * @Param []
     * @return java.lang.String
     **/
    @GetMapping("/getConfig")
    public String getConfig(){
        System.out.println("-----------provider-service");
        return providerService.getConfig();
    }
}
