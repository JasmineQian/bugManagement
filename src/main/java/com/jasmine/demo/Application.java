package com.jasmine.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
//public class Application  {
//
////    @Override
////    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
////        return application.sources(Application.class);
////    }
//
//    public static void main(String[] args) {
//        //SSLUtils.trustSelfSignedSSL();
//        SpringApplication.run(Application.class,args);
//    }}


public class Application extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(Application.class);
    }

    public static void main(String[] args) {
        //SSLUtils.trustSelfSignedSSL();
        SpringApplication.run(Application.class, args);
    }
}