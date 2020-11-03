package com.example.consumer.service;

import com.example.consumer.hystric.ServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description:通过openFeign来消费服务
 * 定义一个feign接口，通过@ FeignClient（“服务名”），来指定调用哪个服务
 *
 * Feign是自带熔断机制的
 * 只需要在FeignClient的IFeignService接口的注解中加上fallback指定一个类就行了
 * ServiceHystric类就是一个断路器，当服务不可用时马上返回指定值
 *
 * @Author: linJia
 * @Date: 2020/11/3 14:01
 */
@FeignClient(name = "provider-service",fallback = ServiceHystrix.class)
public interface IFeignService {

    /**
     * @Description:消费provider-service里的home接口
     *
     * 此处需注意 @GetMapping("/hi") 是原服务提供者的url映射
     *
     * @Author LinJia
     * @Date 2020/11/3 14:04
     * @Param [name]
     * @return java.lang.String
     **/
    @GetMapping("/hi")
    String home(@RequestParam(value = "name") String name);

}
