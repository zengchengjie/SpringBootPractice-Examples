package com.zcj.demo.cron;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Auther: zengchengjie
 * @Date: 2019/4/30 14:42
 * @Description: 定时任务测试
 */
//@Component
//@Configuration
//@EnableScheduling
public class ScheduleTaskTest {
    /**
     * 1:基于注解编写定时任务
     */
    @Scheduled(cron = "0/5 * * * * ?")
    private void configureTasks() {
        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
    }

    /**
     * @Scheduled(fixedRate = 6000)：上一次开始执行时间点后每隔6秒执行一次。
     * @Scheduled(fixedDelay = 6000)：上一次执行完毕时间点之后6秒再执行。
     * @Scheduled(initialDelay=1000, fixedRate=6000)：第一次延迟1秒后执行，之后按fixedRate的规则每6秒执行一次
     */
    @Scheduled(initialDelay = 1 * 1000, fixedDelay = 3 * 1000)
    public void timeToSendTestData() {
        System.out.println("test");
    }

    /**
     * 不定义这个bean会抛出这个异常
     * Spring 定时器 No qualifying bean of type [org.springframework.scheduling.TaskScheduler] is defined
     * @return
     */
    @Bean
    public TaskScheduler scheduledExecutorService() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(8);
        scheduler.setThreadNamePrefix("scheduled-thread-");
        return scheduler;
    }
    /**
     * 2：基于接口设置执行定时任务，时间从数据库获取，
     *      定时任务类需要实现SchedulingConfigurer 接口重写configureTasks方法
     */
    /*@Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

        taskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
                () -> System.out.println("执行动态定时任务: " + LocalDateTime.now().toLocalTime()),
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    //2.1 从数据库获取执行周期
                    String cron = cronMapper.getCron();
                    //2.2 合法性校验.
                    if (StringUtils.isEmpty(cron)) {
                        // Omitted Code ..
                    }
                    //2.3 返回执行周期(Date)
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );
    }*/

    /**
     * 3：基于多线程做定时任务
     * @throws InterruptedException
     */
    @Async
    @Scheduled(fixedDelay = 1000)  //间隔1秒
    public void first() throws InterruptedException {
        System.out.println("第一个定时任务开始 : " + LocalDateTime.now().toLocalTime() + "\r\n线程 : " + Thread.currentThread().getName());
        System.out.println();
        Thread.sleep(1000 * 10);
    }

    @Async
    @Scheduled(fixedDelay = 2000)
    public void second() {
        System.out.println("第二个定时任务开始 : " + LocalDateTime.now().toLocalTime() + "\r\n线程 : " + Thread.currentThread().getName());
        System.out.println();
    }
}
