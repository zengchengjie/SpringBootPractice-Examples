package com.zcj.demo.testcollection.beantest;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

/**
 * @Auther: 10062376
 * @Date: 2019/5/21 16:53
 * @Description:
 */
public class Person implements BeanFactoryAware, BeanNameAware, InitializingBean, DisposableBean {

    private String name;
    private String address;
    private int phone;

    private BeanFactory beanFactory;
    private String beanName;

    public Person() {
        System.out.println("7:【构造器】调用Person的构造器实例化");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("10【注入属性】注入属性name");
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        System.out.println("9:【注入属性】注入属性address");
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        System.out.println("11:【注入属性】注入属性phone");
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "20:Person [address=" + address + ", name=" + name + ", phone=" + phone + "]";
    }

    // 这是BeanFactoryAware接口方法
    @Override
    public void setBeanFactory(BeanFactory arg0) throws BeansException {
        System.out.println("13:【BeanFactoryAware接口】调用BeanFactoryAware.setBeanFactory()");
        this.beanFactory = arg0;
    }

    // 这是BeanNameAware接口方法
    @Override
    public void setBeanName(String arg0) {
        System.out.println("12:【BeanNameAware接口】调用BeanNameAware.setBeanName()");
        this.beanName = arg0;
    }

    // 这是InitializingBean接口方法
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("15:【InitializingBean接口】调用InitializingBean.afterPropertiesSet()");
    }

    // 这是DiposibleBean接口方法
    @Override
    public void destroy() throws Exception {
        System.out.println("22:【DiposibleBean接口】调用DiposibleBean.destory()");
    }

    // 通过的init-method属性指定的初始化方法
    public void myInit() {
        System.out.println("16:【init-method】调用的init-method属性指定的初始化方法");
    }

    // 通过的destroy-method属性指定的初始化方法
    public void myDestory() {
        System.out.println("23:【destroy-method】调用的destroy-method属性指定的初始化方法");
    }
}