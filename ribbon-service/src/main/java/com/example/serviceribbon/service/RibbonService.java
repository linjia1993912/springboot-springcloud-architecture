package com.example.serviceribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Description:服务消费者
 * 通过之前注入ioc容器的restTemplate来消费provider-service服务的“/hi”接口
 * @Author LinJia
 * @Date 2020/11/3
 **/
@Service
public class RibbonService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * @Description:在这里我们直接用的程序名替代了具体的url地址，在ribbon中它会根据服务名来选择具体的服务实例
     * 根据服务实例在请求的时候会用具体的url替换掉服务名
     * @Author LinJia
     * @Date 2020/11/3 14:47
     * @Param [name]
     * @return java.lang.String
     **/
    public String hi(String name){
        System.out.println("----------ribbon-service");
        return restTemplate.getForObject("http://provider-service/hi?name="+name,String.class);
    }

    /**
     * @Description:ribbon使用Hystrix断路器
     * @HystrixCommand注解。该注解对该方法创建了熔断器的功能
     * 并指定了fallbackMethod熔断方法，熔断方法直接返回了一个字符串，字符串为”hi,”+name+”,sorry,error!”
     * @Author LinJia
     * @Date 2020/11/3 14:55
     * @Param [name]
     * @return java.lang.String
     **/
    @HystrixCommand(fallbackMethod = "hiError")
    public String hiHystrix(String name){
        System.out.println("----------ribbon-service");
        return restTemplate.getForObject("http://provider-service/hi?name="+name,String.class);
    }

    //测试
    //打开服务访问
    //关闭服务继续访问
    //返回hiError方法
    public String hiError(String name){
        return "hi,"+name+",sorry,error!";
    }

}
