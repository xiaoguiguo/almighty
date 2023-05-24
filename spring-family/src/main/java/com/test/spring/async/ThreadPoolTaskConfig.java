package com.test.spring.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 注解@Configuration:
 *      用于定义配置类，被注解的类内部包含有一个或多个被@Bean注解的方法，
 *      这些方法将会被AnnotationConfigApplicationContext或AnnotationConfigWebApplicationContext类进行扫描，
 *      并用于构建bean定义，初始化Spring容器。
 * 注解@EnableAsync:
 *      开启对异步任务的支持
 */
@Configuration
@Slf4j
public class ThreadPoolTaskConfig {
    private static final int corePoolSize = 10;
    private static final int maxPoolSize = 100;
    private static final int keepAliveTime = 10;
    private static final int queueCapacity = 200;
    private static final String threadNamePrefix = "Async-spring-test-";

    /**
     * bean的名称，默认首字母小写
     *
     * 线程池拒绝策略：
     * CallerRunsPolicy（调用者运行策略）
     *      功能：当触发拒绝策略时，只要线程池没有关闭，就由提交任务的当前线程处理。
     *      使用场景：一般在不允许失败的、对性能要求不高、并发量较小的场景下使用，因为线程池一般情况下不会关闭，也就是提交的任务一定会被运行，
     *      但是由于是调用者线程自己执行的，当多次提交任务时，就会阻塞后续任务执行，性能和效率自然就慢了。
     * AbortPolicy（中止策略）
     *      功能：当触发拒绝策略时，直接抛出拒绝执行的异常，中止策略的意思也就是打断当前执行流程
     *      使用场景：这个就没有特殊的场景了，但是一点要正确处理抛出的异常。
     *      ThreadPoolExecutor 中默认的策略就是AbortPolicy，
     *      ExecutorService 接口的系列 ThreadPoolExecutor 因为都没有显示的设置拒绝策略，所以默认的都是这个。
     *      但是请注意，ExecutorService 中的线程池实例队列都是无界的，也就是说内存撑爆了都不会触发拒绝策略。
     *      当自己自定义线程池实例时，使用这个策略一定要处理好触发策略时抛的异常，因为他会打断当前的执行流程。
     * DiscardPolicy（丢弃策略）
     *      功能：直接静悄悄的丢弃这个任务，不触发任何动作
     *      使用场景：如果你提交的任务无关紧要，你就可以使用它 。因为它就是个空实现，会悄无声息的吞噬你的的任务。所以这个策略基本上不用了
     * DiscardOldestPolicy（弃老策略）
     *      功能：如果线程池未关闭，就弹出队列头部的元素，然后尝试执行
     *      使用场景：这个策略还是会丢弃任务，丢弃时也是毫无声息，但是特点是丢弃的是老的未执行的任务，而且是待执行优先级较高的任务。
     *      基于这个特性，我能想到的场景就是，发布消息，和修改消息，当消息发布出去后，还未执行，此时更新的消息又来了，
     *      这个时候未执行的消息的版本比现在提交的消息版本要低就可以被丢弃了。因为队列中还有可能存在消息版本更低的消息会排队执行，
     *      所以在真正处理消息的时候一定要做好消息的版本比较
     *
     * 参考资料：https://zhuanlan.zhihu.com/p/398913142
     */
    @Bean("taskExecutor")
    public ThreadPoolTaskExecutor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveTime);
        executor.setThreadNamePrefix(threadNamePrefix);

        // 线程池对拒绝任务的处理策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
//        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
//        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
//        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());

        // 初始化
        executor.initialize();
        return executor;
    }
}
