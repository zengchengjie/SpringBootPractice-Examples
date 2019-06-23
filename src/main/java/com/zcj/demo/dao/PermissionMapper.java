package com.zcj.demo.dao;

import com.zcj.demo.core.Mapper;
import com.zcj.demo.model.Permission;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionMapper extends Mapper<Permission> {
    Permission findPermissionByUsername(String username);
}
