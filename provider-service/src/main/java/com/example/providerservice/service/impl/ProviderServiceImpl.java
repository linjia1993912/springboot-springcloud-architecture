package com.example.providerservice.service.impl;

import com.example.providerservice.service.ProviderService;
import org.springframework.stereotype.Service;

/**
 * @Description:服务提供者service
 * @Author LinJia
 * @Date 2020/11/3
 **/
@Service
public class ProviderServiceImpl implements ProviderService {

    /*@Value("${server.port}")
    String port;*/

    @Override
    public String home(String name) {
        String port = "0";
        return "hi " + name + " ,i am from port:" + port;
    }

    //获取配置中心配置
    @Override
    public String getConfig(String config) {
        System.out.println("配置中心配置foo："+config);
        return "配置中心配置foo-"+config;
    }
}
