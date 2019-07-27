package com.xingyu.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskDecorator;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 自定义Async执行线程池
 */
@Configuration
@ConfigurationProperties(prefix = "executor.async")
public class ExecutorConfiguration implements AsyncConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(ExecutorConfiguration.class);

    public static final String TASK_EXECUTOR_BEAN_NAME = "taskExecutor";

    /** Set the ThreadPoolExecutor's core pool size. */
    private int corePoolSize = 10;
    /** Set the ThreadPoolExecutor's maximum pool size. */
    private int maxPoolSize = 100;
    /** Set the capacity for the ThreadPoolExecutor's BlockingQueue. */
    private int queueCapacity = 200;

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public int getQueueCapacity() {
        return queueCapacity;
    }

    public void setQueueCapacity(int queueCapacity) {
        this.queueCapacity = queueCapacity;
    }

    @Bean(name=TASK_EXECUTOR_BEAN_NAME)
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setTaskDecorator(new MdcTaskDecorator());
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    @Nullable
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }

    /**
     *  Created by maobg on 2018/12/14
     * 实现复制父线程的MDC数据到async子线程中
     */
    public class MdcTaskDecorator implements TaskDecorator {
        @Override
        public Runnable decorate(Runnable runnable) {
            Map<String, String> contextMap = MDC.getCopyOfContextMap();
            return () -> {
                if (null != contextMap) {
                    MDC.setContextMap(contextMap);
                }
                try {
                    ThreadPoolTaskExecutor executor = SpringUtils.getBean(TASK_EXECUTOR_BEAN_NAME, ThreadPoolTaskExecutor.class);
                    logger.info("taskExecutor.active.count:{}", executor.getActiveCount());
                    logger.info("taskExecutor.queue.size():{}", executor.getThreadPoolExecutor().getQueue().size());
                }catch (Exception e){
                    logger.error(e.getMessage(),e);
                }
                try {
                    runnable.run();
                }catch (Exception e){
                    logger.error(e.getMessage(),e);
                } finally {
                    MDC.clear();
                }
            };
        }
    }
}
