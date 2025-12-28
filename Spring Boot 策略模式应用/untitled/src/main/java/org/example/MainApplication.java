package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

/**
 * 主程序类
 * @SpringBootApplication：这是一个 Spring Boot 应用
 */
@SpringBootApplication(scanBasePackages = "org.example")
public class MainApplication {

    public static void main(String[] args) {
        //1、返回 IOC 容器
        ConfigurableApplicationContext context = SpringApplication.run(MainApplication.class, args);

        //2、查看容器里面的组件
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        Arrays.sort(beanDefinitionNames);
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }

    }

}
