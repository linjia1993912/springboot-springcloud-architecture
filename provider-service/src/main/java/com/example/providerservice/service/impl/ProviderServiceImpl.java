package com.example.providerservice.service.impl;

import com.example.providerservice.service.ProviderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

/**
 * @Description:服务提供者service
 * @Author LinJia
 * @Date 2020/11/3
 **/
@Service
//使用消息总线刷新配置文件
//RefreshScope是spring cloud提供的一种特殊的scope实现，用来实现配置、实现热加载。
//RefreshScope是因为config client只会在第一次初始化bean的时候获取一次配置，后面如果需要更新的话，
// 需要设置这个注解在需要动态更新配置的类上面，并且引入spring actuator的包，通过发送post请求来更新bean里面的值
@RefreshScope
public class ProviderServiceImpl implements ProviderService {

    @Value("${server.port}")
    String port;

    @Override
    public String home(String name) {
        String port = "0";
        return "hi " + name + " ,i am from port:" + port;
    }

    //获取配置中心配置
    @Value("${foo}")
    String foo;

    @Override
    public String getConfig() {
        System.out.println("配置中心配置foo："+foo);
        return "配置中心配置foo-"+foo;
    }
}
