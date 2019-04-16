package com.zcj.demo.controller;

import com.zcj.demo.core.EnumTest;
import com.zcj.demo.core.Result;
import com.zcj.demo.core.ResultGenerator;
import com.zcj.demo.model.User;
import com.zcj.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
    @Resource
    UserService userService;
    @GetMapping("/test")
    public Result pic(){
        User user = userService.selectUserByName("supadmin");
        return ResultGenerator.genSuccessResult("success");
    }
    @GetMapping("/testMapData")
    public String getMapData(){
        String mapData = "";
        return mapData;
    }
    @PostMapping("/postTest")
    public String Test(){
        String name  = EnumTest.SPRING.getName();
        return name;
    }
    @PostMapping("/login")
    public Result login(@RequestParam String username,
                        @RequestParam String password){
        return ResultGenerator.genSuccessResult();

    }
    @PostMapping("/register")
    public Result register(@RequestParam String username,
                           @RequestParam String password){
        return ResultGenerator.genSuccessResult();
    }
    /**
     * 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
     * @return
     */
    @RequestMapping(value = "/unauth")
    @ResponseBody
    public Object unauth() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", "1000000");
        map.put("msg", "未登录");
        return map;
    }

}
