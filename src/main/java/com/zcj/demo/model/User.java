package com.zcj.demo.model;

import javax.persistence.*;

/**
 * @Auther: 10062376
 * @Date: 2018/9/4 17:43
 * @Description:
 */
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return name;
    }

    public void setUsername(String username) {
        this.name = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
