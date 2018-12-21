package com.jasmine.demo;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StartAppTests {


    @Autowired
    StringEncryptor stringEncryptor;

    @Test
    public void test() {
        String passwd =stringEncryptor.encrypt("root");
        String passwd2 =stringEncryptor.encrypt("C935DD58");
        System.out.println(passwd);
        System.out.println(passwd2);

    }

    @Test
    public void contextLoads() {
    }

}

