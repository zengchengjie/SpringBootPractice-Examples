package com.zcj.demo.service;

import com.zcj.demo.core.Service;
import com.zcj.demo.model.User;

import java.util.List;

/**
 * @Auther: 10062376
 * @Date: 2018/9/4 17:43
 * @Description:
 */
public interface UserService extends Service<User> {
    User selectUserByName(String name);
    User updateUser(User user);

    List<String> selectTestPageHelperData();
}
