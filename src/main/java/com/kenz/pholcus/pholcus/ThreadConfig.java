package com.kenz.pholcus.pholcus;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Description:
 *
 * @author kenzhao
 * @date 2019/9/30 16:41
 */
@Configuration
@EnableAsync
public class ThreadConfig implements AsyncConfigurer {
    /**
     * The {@link Executor} instance to be used when processing async
     * method invocations.
     */

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(15);
        executor.setThreadNamePrefix("Anno-Executor");
        executor.setQueueCapacity(25);
        executor.initialize();
        System.out.println("initialize complete ..");
        // 设置拒绝策略
        executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                // .....
                System.out.println("do somethings by myself ...");
            }
        });
        // 使用预定义的异常处理类
        // executor.setRejectedExecutionHandler(new
        // ThreadPoolExecutor.CallerRunsPolicy());

        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }
}
