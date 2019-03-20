package com.zcj.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class,JpaRepositoriesAutoConfiguration.class})
@EnableCaching
public class DemoApplication {
    public DemoApplication(RedisTemplate<String,String> redisTemplate) {
        redisTemplate.opsForValue().set("hello","world");
        String ans = redisTemplate.opsForValue().get("hello");
//        Assert.isTrue(equals(ans), "world");
//        Assert.isTrue();
//        Assert.isTrue(ans.equals("worfffld"),"world");
        boolean isOpen = false;

        // 如果开启了断言，会将isOpen的值改为true
        assert isOpen = true;

        // 打印是否开启了断言，如果为false，则没有启用断言
        System.out.println(isOpen);
        System.out.println(ans);
    }

    /**
     * redis 定时任务 solr/ElacsticSearch mybatis  shiro
     * @param args
     */

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
