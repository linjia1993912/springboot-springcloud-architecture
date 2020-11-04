#模块说明
* ribbon-service 服务消费者(ribbon方式消费服务)

ribbon是一个负载均衡客户端，可以很好的控制htt和tcp的一些行为。Feign默认集成了ribbon
Spring cloud有两种服务调用方式，一种是ribbon+restTemplate，另一种是feign

ribbon方式消费服务实现负载均衡

###熔断机制
ribbon使用hystrix(断路器)

#测试
ribbon-service当服务消费者