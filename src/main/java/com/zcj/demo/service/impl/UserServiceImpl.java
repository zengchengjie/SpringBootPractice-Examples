package com.zcj.demo.service.impl;

import com.zcj.demo.core.AbstractService;
import com.zcj.demo.dao.UserMapper;
import com.zcj.demo.model.User;
import com.zcj.demo.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: 10062376
 * @Date: 2018/9/4 17:44
 * @Description:
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    UserMapper userMapper;

    @Override
    public User selectUserByName(String name) {
        return userMapper.selectUserByName(name);
    }

    @Override
    public User updateUser(User user) {
        update(user);
        return user;
    }

    @Override
    public List<String> selectTestPageHelperData() {
        return  userMapper.selectTestPageHelperData();
    }

}
