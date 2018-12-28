package com.jasmine.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {

    protected static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        logger.info("SpringBoot开始加载");
        SpringApplication.run(Application.class, args);
        logger.info("SpringBoot加载完毕");
    }
}


//public class Application extends SpringBootServletInitializer {
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        // 注意这里要指向原先用main方法执行的Application启动类
//        return builder.sources(Application.class);
//    }
//
//    public static void main(String[] args) {
//        //SSLUtils.trustSelfSignedSSL();
//        SpringApplication.run(Application.class, args);
//    }
//}