package com.example.consumer.hystric;

import com.example.consumer.service.IFeignService;
import org.springframework.stereotype.Component;

/**
 * @Description:Feign使用Hystrix断路器
 * 需要在FeignClient的注解上fallback指定此类即可
 * 需要实现IFeignService接口,并注入到Ioc容器中
 * @Author LinJia
 * @Date 2020/11/3
 **/
@Component
public class ServiceHystrix implements IFeignService {

    /**
     * @Description:服务不可用时，立刻返回该方法值
     * @Author LinJia
     * @Date 2020/11/3 14:26
     * @Param [name]
     * @return java.lang.String
     **/
    @Override
    public String home(String name) {
        return "sorry "+name;
    }

    @Override
    public String getConfig() {
        return "sorry 无法获取配置中心配置";
    }
}
