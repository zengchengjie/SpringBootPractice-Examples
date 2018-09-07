package com.zcj.demo.controller;

import com.zcj.demo.core.EnumTest;
import com.zcj.demo.core.Result;
import com.zcj.demo.core.ResultCode;
import com.zcj.demo.core.ResultGenerator;
import com.zcj.demo.dao.UserMapper;
import com.zcj.demo.model.User;
import com.zcj.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * @Auther: 10062376
 * @Date: 2018/9/4 17:43
 * @Description:
 */
@RestController
@RequestMapping(value = "pic")
public class UserController {
    @Resource
    UserService userService;
    @GetMapping("/test")
    public Result pic(){
        User user = userService.selectUserByName("supadmin");
        return ResultGenerator.genSuccessResult(user);
    }
    @PostMapping("/postTest")
    public String Test(){
        String name  = EnumTest.SPRING.getName();
        return name;
    }
}
