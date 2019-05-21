package com.zcj.demo.testcollection.beantest;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
/**
 * @Auther: 10062376
 * @Date: 2019/5/21 16:53
 * @Description:
 * 如下，BeanPostProcessor接口包括2个方法postProcessAfterInitialization和postProcessBeforeInitialization，
 * 这两个方法的第一个参数都是要处理的Bean对象，第二个参数都是Bean的name。返回值也都是要处理的Bean对象。这里要注意。
 *
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    public MyBeanPostProcessor() {
        super();
        System.out.println("4:这是BeanPostProcessor实现类构造器！！");
        // TODO Auto-generated constructor stub
    }

    @Override
    public Object postProcessAfterInitialization(Object arg0, String arg1) throws BeansException {
        System.out.println("17:【BeanPostProcessor接口】调用方法postProcessAfterInitialization对属性进行更改！");
        return arg0;
    }

    @Override
    public Object postProcessBeforeInitialization(Object arg0, String arg1) throws BeansException {
        System.out.println("14:【BeanPostProcessor接口】调用方法postProcessBeforeInitialization对属性进行更改！");
        return arg0;
    }
}
