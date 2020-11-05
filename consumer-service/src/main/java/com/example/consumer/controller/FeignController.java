package com.example.consumer.controller;

import com.example.consumer.service.IFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:服务消费者
 * 通过openFeign来消费服务
 * @Author LinJia
 * @Date 2020/11/3
 **/
@RestController
public class FeignController {

    @Value("${server.port}")
    String port;

    //编译器报错，无视。 因为这个Bean是在程序启动的时候注入的，编译器感知不到，所以报错。
    @Autowired
    private IFeignService iFeignService;

    /**
     * @Description:调用服务
     * @Author LinJia
     * @Date 2020/11/3 14:04
     * @Param [name]
     * @return java.lang.String
     **/
    @GetMapping("/hi")
    public String sayHi(@RequestParam(value = "name", defaultValue = "forezp") String name) {
        System.out.println("----feign-service----"+port);
        return iFeignService.home(name);
    }

    @GetMapping("/getConfig")
    public String getConfig() {
        System.out.println("----feign-service----"+port);
        return iFeignService.getConfig();
    }
}
