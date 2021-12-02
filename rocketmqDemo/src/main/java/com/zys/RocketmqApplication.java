package com.zys;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration
public class RocketmqApplication {

    public static void main(String[] args) {
        System.out.println("start----------->");
        SpringApplication.run(RocketmqApplication.class,args);
        System.out.println("end----------->");
    }


}
