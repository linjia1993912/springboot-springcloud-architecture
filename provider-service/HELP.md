#模块说明
* provider-service 服务提供者

使用分布式配置中心config-server获取配置

使用Spring Cloud Bus消息总线刷新配置文件

#测试
需要测试高可用可以修改yml文件端口启动多个服务提供者

###测试接入配置中心、消息总线更新配置
1.测试分布式配置中心要先启动config-server
2.访问/getConfig接口验证是否能获取到配置
3.修改git配置文件，post请求http://localhost:8762/actuator/bus-refresh，config-client会重新读取配置文件
4.再次访问/getConfig接口发现配置文件值已经改变

说明使用Spring Cloud Bus 实现配置跟新成功，实现热加载

