package com.zcj.demo.model;

import com.zcj.demo.constant.enums.UserStatesEnum;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zengchengjie
 * @Date: 2018/9/4 17:43
 * @Description:
 */
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//
    private Long id;
    @Column(name = "username")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name = "user";
    @Column(name = "age")
    private Integer age;
    @Column(name = "salt")
    private String salt;
    @Column(name = "state")
    private UserStatesEnum state;
    @Column(name = "isdelete")
    private Integer isdelete;
    @Column(name = "create_time")
    private Integer createTime;
    @Column(name = "update_time")
    private Integer updateTime;
    @Transient
    List<Role> roles = new ArrayList<>();//一个用户对应多个角色

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public UserStatesEnum getState() {
        return state;
    }

    public void setState(UserStatesEnum state) {
        this.state = state;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }
    /**
     * 密码盐.
     * @return
     */
    public String getCredentialsSalt(){
        return this.userName+this.salt;
    }
    //重新对盐重新进行了定义，用户名+salt，这样就更加不容易被破解
}
