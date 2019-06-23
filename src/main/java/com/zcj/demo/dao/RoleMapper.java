package com.zcj.demo.dao;

import com.zcj.demo.core.Mapper;
import com.zcj.demo.model.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMapper extends Mapper<Role> {
    Role findRoleByUsername(String username);
}
