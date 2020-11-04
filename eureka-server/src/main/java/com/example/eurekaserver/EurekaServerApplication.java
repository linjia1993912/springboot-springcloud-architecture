package com.example.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
//启动一个服务注册中心，只需要一个注解@EnableEurekaServer
//这个注解需要在springboot工程的启动application类上加
@EnableEurekaServer
public class EurekaServerApplication {

	//eureka server 是有界面的，启动工程,打开浏览器访问： http://localhost:8761
	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);
		System.out.println("注册中心已启动");
	}

}
