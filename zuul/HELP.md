#模块说明
微服务网关

Zuul的主要功能是路由转发和过滤器。路由功能是微服务的一部分，比如／api/user转发到到user服务，
/api/shop转发到到shop服务。zuul默认和Ribbon结合实现了负载均衡的功能。

zuul不单单可以做路由，也可以做服务过滤

com.example.servicezuul.filter包下是服务过滤的代码示例

#注意事项
如果zuul启动报错，检查SpringBoot版本兼容问题

##报错信息
Description:
The bean 'proxyRequestHelper', defined in class path resource...

##版本
* SpringCloud.Finchley版本兼容的SpringBoot为2.0.X

#测试
依次启动
eureka-server
provider-service 可以启动2个服务
consumer-service 
service-ribbon 
zuul