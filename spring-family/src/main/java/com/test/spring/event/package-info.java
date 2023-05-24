package com.test.spring.event;
/*
 * 1.1 ApplicationStartingEvent
 *
 * 这个事件在 Spring Boot 应用运行开始时，且进行任何处理之前发送（除了监听器和初始化器注册之外）。
 *
 * 1.2 ApplicationEnvironmentPreparedEvent
 *
 * 这个事件在当已知要在上下文中使用 Spring 环境（Environment）时，在 Spring 上下文（context）创建之前发送。
 *
 * 1.3 ApplicationContextInitializedEvent
 *
 * 这个事件在当 Spring 应用上下文（ApplicationContext）准备好了，并且应用初始化器（ApplicationContextInitializers）已经被调用，在bean 的定义（bean definitions）被加载之前发送。
 *
 * 1.4 ApplicationPreparedEvent
 *
 * 这个事件是在 Spring 上下文（context）刷新之前，且在 bean 的定义（bean definitions）被加载之后发送。
 *
 * 1.5 ApplicationStartedEvent
 *
 * 这个事件是在 Spring 上下文（context）刷新之后，且在 application/ command-line runners被调用之前发送。
 *
 * 1.6 AvailabilityChangeEvent
 *
 * 这个事件紧随上个事件之后发送，状态：ReadinessState.CORRECT，表示应用已处于活动状态。
 *
 * 1.7 ApplicationReadyEvent
 *
 * 这个事件在任何 application/ command-line runners 调用之后发送。
 *
 * 1.8 AvailabilityChangeEvent
 *
 * 这个事件紧随上个事件之后发送，状态：ReadinessState.ACCEPTING_TRAFFIC，表示应用可以开始准备接收请求了。
 *
 * 1.9 ApplicationFailedEvent
 *
 * 这个事件在应用启动异常时进行发送。
 *
 * 设计模式：观察者模式
 *
 * 实现：
 * 1、实现ApplicationListener接口
 * 2、通过@EventListener注解
 *
 * 参考：
 * https://cloud.tencent.com/developer/article/1762937
 * https://mp.weixin.qq.com/s?__biz=MzI3ODcxMzQzMw==&mid=2247484366&idx=1&sn=7dc94038861fe9e10cdf132ffc83092f&scene=21#wechat_redirect
 */