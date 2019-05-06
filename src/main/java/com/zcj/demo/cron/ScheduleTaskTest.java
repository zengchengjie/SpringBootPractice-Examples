package com.zcj.demo.cron;

import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

/**
 * @Auther: 10062376
 * @Date: 2019/4/30 14:42
 * @Description:
 */
//@Component
//@Configuration
//@EnableScheduling
public class ScheduleTaskTest {
    @Scheduled(cron = "0/5 * * * * ?")
    private void configureTasks() {
        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
    }
}
