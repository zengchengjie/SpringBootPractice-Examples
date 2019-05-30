package com.zcj.demo.configurer.shiro;

import com.zcj.demo.constant.enums.UserStatesEnum;
import com.zcj.demo.dao.UserMapper;
import com.zcj.demo.model.User;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: zengchengjie
 * @Date: 2019/4/28 09:39
 * @Description:
 */
public class MyCredentialsMatcher extends SimpleCredentialsMatcher {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserMapper userMapper;
    private Cache<String,AtomicInteger> passwordRetryCache;

    public MyCredentialsMatcher(CacheManager cacheManager) {
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }

    /**
     * 验证账户密码，并限制登录失败次数
     * @param token
     * @param info
     * @return
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {

        //获取用户名
        String username = (String)token.getPrincipal();
        //获取用户登录次数
        AtomicInteger retryCount = passwordRetryCache.get(username);
        if (retryCount == null) {
            //如果用户没有登陆过,登陆次数加1 并放入缓存
            retryCount = new AtomicInteger(0);
            passwordRetryCache.put(username, retryCount);
        }
        if (retryCount.incrementAndGet() > 5) {
            //如果用户登陆失败次数大于5次 抛出锁定用户异常  并修改数据库字段
            User user = userMapper.selectUserByName(username);
            if (user != null && user.getState().getCode()==0){
                //数据库字段 默认为 0  就是正常状态 所以 要改为1
                //修改数据库的状态字段为锁定
                user.setState(UserStatesEnum.FREEZE);
                userMapper.lockAccount(user.getId());
            }
            logger.info("锁定用户" + user.getUserName());
            //抛出用户锁定异常
            throw new LockedAccountException("账户被冻结，请联系管理员。");
        }
        //判断用户账号和密码是否正确
        boolean matches = super.doCredentialsMatch(token, info);
        if (matches) {
            //如果正确,从缓存中将用户登录计数 清除
            passwordRetryCache.remove(username);
        }else {
            passwordRetryCache.put(username,retryCount);
            throw new IncorrectCredentialsException("密码错误，剩余"+(5-retryCount.get())+"次重试机会");
        }
        return matches;
    }

    /**
     * 根据用户名 解锁用户
     * @param username
     * @return
     */
    public void unlockAccount(String username){
        User user = userMapper.selectUserByName(username);
        if (user != null){
            //修改数据库的状态字段为锁定
            user.setState(UserStatesEnum.NORMAL);
            userMapper.lockAccount(user.getId());
            passwordRetryCache.remove(username);
        }
    }
}
