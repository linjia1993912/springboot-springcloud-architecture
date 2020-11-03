#模块说明
* consumer-service 服务消费者

该消费者使用openfeign来消费服务

Feign方式实现负载均衡，Feign默认集成了ribbon

#实现熔断机制
Feign是自带断路器的,在D版本的Spring Cloud之后，它没有默认打开。需要在配置文件中配置打开它
Feign中使用hystrix(断路器)
```feign.hystrix.enabled=true```

#测试
* 需要测试负载均衡，则修改provider-service端口启动多个服务