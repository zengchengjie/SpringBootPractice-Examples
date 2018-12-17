package com.zcj.demo.service;

import com.zcj.demo.core.Service;
import com.zcj.demo.model.User;

/**
 * @Auther: 10062376
 * @Date: 2018/9/4 17:43
 * @Description:
 */
public interface UserService extends Service<User> {
    User selectUserByName(String name);

    User findUserByUserName(String name);
}
