package com.zcj.demo.service.impl;

import com.zcj.demo.core.AbstractService;
import com.zcj.demo.dao.RoleMapper;
import com.zcj.demo.model.Role;
import com.zcj.demo.service.RoleService;

import javax.annotation.Resource;

public class RoleServiceImpl extends AbstractService<Role> implements RoleService {
    @Resource
    RoleMapper roleMapper;
    @Override
    public Role findRoleByUsername(String username) {
        return roleMapper.findRoleByUsername(username);
    }
}
