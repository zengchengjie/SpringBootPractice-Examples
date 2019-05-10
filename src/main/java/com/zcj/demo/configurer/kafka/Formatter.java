package com.zcj.demo.configurer.kafka;

/**
 * @Auther: zengchengjie
 * @Date: 2019/2/25 16:22
 * @Description:
 */
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * 定义格式化接口
 * @author liuyazhuang
 *
 */
public interface Formatter {

    String format(ILoggingEvent event);
}