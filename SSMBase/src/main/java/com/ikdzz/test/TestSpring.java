package com.ikdzz.test;

import com.ikdzz.service.Impl.shiro.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    @Test
    public void run1(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        UserService as = (UserService) ac.getBean("accountService");
        as.findAll();
    }
}
