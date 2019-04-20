package com.zcj.demo.controller;

import com.alibaba.fastjson.JSONObject;
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
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: 10062376
 * @Date: 2018/9/4 17:43
 * @Description:
 */
@RestController
@RequestMapping(value = "pic")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/test")
    public Result pic() {
        User user = userService.selectUserByName("supadmin");
        return ResultGenerator.genSuccessResult(user.getName());
    }

    @GetMapping("/testMapData")
    public String getMapData() {
        String mapData = "test";
        return mapData;
    }

    @PostMapping("/postTest")
    public String Test() {
        String name = EnumTest.SPRING.getName();
        return name;
    }

    @PostMapping("/login")
    public Result login(@RequestParam String username,
                        @RequestParam String password) {
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

    @PostMapping("/register")
    public Result register(@RequestParam String username,
                           @RequestParam String password) {
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
     *
     * @return
     */
    @RequestMapping(value = "/unauth")
    @ResponseBody
    public Object unauth(User userInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", "1000000");
        map.put("msg", "未登录");
        return map;
//        return ResultGenerator.genSuccessResult();
    }

}
