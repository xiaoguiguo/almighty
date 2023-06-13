package com.test.spring.async;
/*
 * spring 异步任务实现， 使用两个注解
 * @EnableAsync
 * @Async
 * 我们在使用多线程的时候，往往需要创建Thread类，或者实现Runnable接口，如果要使用到线程池，我们还需要来创建Executors，
 * Spring已经给我们做了很好的支持。
 * 只要使用@EnableAsync就可以使用多线程。
 * 使用@Async就可以定义一个线程任务。
 * 通过spring给我们提供的ThreadPoolTaskExecutor就可以使用线程池。
 *
 * 默认情况下，Spring将搜索相关的线程池定义：
 * 要么在上下文中搜索唯一的TaskExecutor bean，
 * 要么搜索名为“taskExecutor”的Executor bean。
 * 如果两者都无法解析，则将使用SimpleAsyncTaskExecutor来处理异步方法调用。
 *
 * 1.在Async 方法上标注@Transactional是没用的。 在Async 方法调用的方法上标注@Transactional 有效。
 * 2.调用被@Async标记的方法的调用者不能和被调用的方法在同一类中不然不会起作用！
 * 3.使用@Async时要求是不能有返回值的不然会报错的 因为异步要求是不关心结果的
 */
