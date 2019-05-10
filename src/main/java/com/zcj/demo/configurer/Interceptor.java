package com.zcj.demo.configurer;

import com.alibaba.fastjson.JSON;
import com.zcj.demo.core.Result;
import com.zcj.demo.core.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: zengchengjie
 * @Date: 2019/3/11 11:56
 * @Description:API接口请求拦截器
 * 1、设备调用服务端请求，需要在Http Header中添加Mac字段,并将Mac转成大写 拼接 “DEMO”字符串，做MD5运算后，附带到HEADER中，命名为Token
 * 2、如果是未注册设备，除了注册请求外，其余均返回403
 */
public class Interceptor extends HandlerInterceptorAdapter {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    //Mac正则
    static final String regexPattern = "^([0-9a-fA-F]{2})(([0-9a-fA-F]{2}){5})$";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //校验是否在Header中传入Mac地址
        String deviceMac = request.getHeader("Mac");
        String token = request.getHeader("Token");
        if (deviceMac==null||!deviceMac.matches(regexPattern)||token==null) {
            Result result = new Result();
            result.setCode(ResultCode.UNAUTHORIZED).setMessage("Mac或Token地址缺失");
            responseResult(response, result);
            return false;
        }
        return super.preHandle(request, response, handler);
    }

    private void responseResult(HttpServletResponse response, Result result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(JSON.toJSONString(result));
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }
}
