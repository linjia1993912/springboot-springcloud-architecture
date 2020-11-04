#模块说明
分布式配置中心组件spring cloud config 
* config-server 分布式配置中心

它支持配置服务放在配置服务的内存中（即本地），也支持放在远程Git仓库中。在spring cloud config 组件中，分两个角色，
一是config server，二是config client (相当于服务提供者)

在provider-service里获取配置中心配置

#测试
启动程序：访问http://localhost:8888/配置文件名/dev
能显示配置文件则证明配置服务中心可以从远程程序获取配置信息。