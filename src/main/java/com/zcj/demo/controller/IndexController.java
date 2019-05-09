package com.zcj.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: 10062376
 * @Date: 2019/5/9 10:49
 * @Description:
 */
@Controller
@RequestMapping("/index")
public class IndexController {
    @RequestMapping("/test")
    public String test(){
        return "test";
    }

}
