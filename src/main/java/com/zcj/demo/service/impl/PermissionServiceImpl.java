package com.zcj.demo.service.impl;

import com.zcj.demo.core.AbstractService;
import com.zcj.demo.dao.PermissionMapper;
import com.zcj.demo.model.Permission;
import com.zcj.demo.service.PermissionService;

import javax.annotation.Resource;

public class PermissionServiceImpl extends AbstractService<Permission> implements PermissionService {
    @Resource
    PermissionMapper permissionMapper;
    @Override
    public Permission findPermissionByUsername(String username) {
        return permissionMapper.findPermissionByUsername(username);
    }
}
