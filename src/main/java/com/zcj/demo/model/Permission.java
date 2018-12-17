package com.zcj.demo.model;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Permission implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "resource_type")
    private String resourceType;

    private String url;

    /**
     * 权限字符串,例如：role:create,role:update,role:delete,role:view
     */
    @Column(name = "permission_string")
    private String permissionString;

    /**
     * 父级别权限编号
     */
    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "logo")
    private String logo;

    @Transient
    private List<Role> roles;


    @Column(name = "create_time")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Column(name = "update_time")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @Column(name = "isdelete")
    @JSONField(serialize = false)
    private Integer isDelete = 0;

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return resourceType
     */
    public String getResourceType() {
        return resourceType;
    }

    /**
     * @param resourceType
     */
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取**权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view
     *
     * @return permission_string - **权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view
     */
    public String getPermissionString() {
        return permissionString;
    }

    /**
     * 设置**权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view
     *
     * @param permissionString **权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view
     */
    public void setPermissionString(String permissionString) {
        this.permissionString = permissionString;
    }

    /**
     * 获取父编号
     *
     * @return parent_id - 父编号
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置父编号
     *
     * @param parentId 父编号
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * @return createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return updateTime
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}