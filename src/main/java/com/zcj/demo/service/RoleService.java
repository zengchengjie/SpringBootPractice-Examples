package com.zcj.demo.service;

import com.zcj.demo.core.Service;
import com.zcj.demo.model.Role;

public interface RoleService extends Service<Role> {
    Role findRoleByUsername(String username);
}
