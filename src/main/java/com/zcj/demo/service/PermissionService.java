package com.zcj.demo.service;

import com.zcj.demo.core.Service;
import com.zcj.demo.model.Permission;

public interface PermissionService extends Service<Permission> {
    Permission findPermissionByUsername(String username);
}
