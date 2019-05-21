package com.zcj.demo.testcollection.beantest;

/**
 * @Auther: 10062376
 * @Date: 2019/5/21 16:56
 * @Description:
 */

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        System.out.println("1:现在开始初始化容器");

        ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext.xml");
        System.out.println("19:容器初始化成功");
        //得到Preson，并使用
        Person person = factory.getBean("person", Person.class);
        System.out.println(person);
        System.out.println("21:现在开始关闭容器！");
        ((ClassPathXmlApplicationContext) factory).registerShutdownHook();
    }
}
