# springboot-springcloud-architecture
* 练习springboot+SpringCloud架构

#模块说明
* eureka-server 注册中心
* provider-service 服务提供者
* consumer-service 服务消费者(Feign方式消费服务)
* ribbon-service 服务消费者(ribbon方式消费服务)
* zuul 微服务网关,对外的唯一入口
* config-server 分布式配置中心服务端

#测试
需要测试高可用可以修改yml文件端口启动多个服务提供者、服务消费者

##启动顺序
eureka-server
config-server
provider-service
consumer-service/ribbon-service
zuul
