package com.zcj.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zcj.demo.constant.ConstantData;
import com.zcj.demo.core.EnumTest;
import com.zcj.demo.core.Result;
import com.zcj.demo.core.ResultGenerator;
import com.zcj.demo.model.User;
import com.zcj.demo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zengchengjie
 * @Date: 2018/9/4 17:43
 * @Description:
 */
@RestController
@RequestMapping(value = "user")
public class UserController {
    //随机数生成器
    private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    //指定散列算法为md5
    private String algorithmName = "MD5";
    //散列迭代次数
    private final int hashIterations = 2;
    @Resource
    UserService userService;

    @RequestMapping(value = "/index")
    public String returnIndex(Map<String, Object> paramMap) {
        paramMap.put("name", "张三");
        return "index";
    }

    //开启事务支持
    @Transactional
    @GetMapping("/test")
    public Result pic() {
        User user = userService.selectUserByName("supadmin");
        user.setName("测试修改");
        userService.updateUser(user);
        return ResultGenerator.genSuccessResult(user.getName());
    }

    @GetMapping("/testPageHelper")
    public Result getMapData() {
        Integer page = 0;
        Integer size = 10;
        PageHelper.startPage(page, size);
        List<String> list = userService.selectTestPageHelperData();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/postTest")
    public String Test() {
        String name = EnumTest.SPRING.getName();
        return name;
    }

    @PostMapping("/login")
    public Result login(@RequestParam String username,
                        @RequestParam String password) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return ResultGenerator.genFailResult("登录失败，用户或密码为空");
        }
        Subject sub = SecurityUtils.getSubject();

        //如果已经登录的话
        if (sub.isAuthenticated()) {
            User loginUser = (User) sub.getPrincipals().getPrimaryPrincipal();
            //检查是否当前用户
            if (!loginUser.getUserName().equals(username)) {
                try {
                    //先登出之前的用户
                    sub.logout();
                    //在登录新的用户
                    UsernamePasswordToken token = new UsernamePasswordToken(username, password);
                    sub.login(token);
                } catch (Exception ex) {
                    return ResultGenerator.genFailResult("登录失败，请检查用户或密码是否输错");
                }
            }
        }
        //未登录
        else {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            try {
                //进行验证，这里可以捕获异常，然后返回对应信息
                sub.login(token);
            }catch (UnknownSessionException e){
                return ResultGenerator.genFailResult(e.getMessage());
            }catch (Exception ex) {
                return ResultGenerator.genFailResult("登录失败，请检查用户或密码是否输错");
            }
//            logger.info("User [" + sub.getPrincipal() + "] logged in successfully.");
        }


        JSONObject jsonObject = new JSONObject();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            jsonObject.put("token", subject.getSession().getId());
            jsonObject.put("msg", "登录成功");
        } catch (IncorrectCredentialsException e) {
            jsonObject.put("msg", "密码错误");
        } catch (LockedAccountException e) {
            jsonObject.put("msg", "登录失败，该用户已被冻结");
        } catch (AuthenticationException e) {
            jsonObject.put("msg", "该用户不存在");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultGenerator.genSuccessResult(jsonObject);

    }

    @PostMapping("/logout")
    public Result logout() {
        //添加用户认证信息
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser != null) {
            if (currentUser.isAuthenticated()) {
                currentUser.logout();
                return ResultGenerator.genSuccessResult();
            }
        }
        return ResultGenerator.genSuccessResult("退出登录");
    }

    /**
     * 用户注册时，系统为输入的密码进行加密，此处使用MD5算法，“密码+盐（用户名+随机数）”的方式生成散列值
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestParam String username,
                           @RequestParam String password) {
        User user = new User();
        user.setUserName(username);
        user.setSalt(randomNumberGenerator.nextBytes().toHex());
        String newPassword = new SimpleHash(ConstantData.ALGORITHMNAME, password,
                user.getSalt(), ConstantData.HASHITERATIONS).toString();
        user.setPassword(newPassword);
        userService.save(user);
        return ResultGenerator.genSuccessResult("注册成功！");
    }

    /**
     * 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
     *
     * @return
     */
    @RequestMapping(value = "/unauth", method = RequestMethod.GET)
    @ResponseBody
    public Object unauth() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", "1000000");
        map.put("msg", "未登录");
        return map;
//        return ResultGenerator.genSuccessResult();
    }

}
