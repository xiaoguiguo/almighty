# almighty

## 一、Java进阶
### 1. 多线程编程
### 2. IO编程
### 3. disruptor 内存队列

## 二、JavaUtil
### 1. Excel工具类实现
### 2. Zip工具类实现

## 三、Spring + SpringBoot
### 1. Spring源码学习
### 2. Spring注解缓存功能
### 3. Springboot扩展接口
    https://mp.weixin.qq.com/s/y3P7YS6tCqHQhaC9eH7P0A
### 4. Spring enabled系列
### 5. 异常统一处理 {framework.exception}
### 6. 操作日志统一处理 {framework.log}
### 7. Jackson序列化添加LocalDateTime支持 {framework.serialize}
### 8. 添加Spring事件模板 {event}
### 9. 添加Spring异步代码处理模板，包括自定义线程池处理 {async}
### 10. 添加重试机制模板 {retry}
### 11. Servlet生命周期 {lifecycle}
### 12. 定时任务的使用模板 {scheduler}
### 13. SpringDoc api文档 {apidoc}

## 四、SpringCloud + SpringCloudAlibaba
### 1. SpringCloud源码
### 2. 微服务架构
### 3. 高并发场景下容错、限流、熔断实现方式
### 4. SpringCloudGateway网关的二次开发和调优

## 五、SpringSecurity
### 1. SpringSecurity+Redis(session共享)

## 六、缓存+高性能
### 1. Redis
### 2. 高性能缓存Caffeine
    https://www.modb.pro/db/44421
### 3. ElasticSearch

## 七、消息队列
### 1. RocketMQ
### 2. Kafka

## 八、数据库
### 1. mysql
### 2. PostgreSQL
### 3. mysql日志分析工具
    pt-query-digest
### 4. mybatis(plus)
### 5. 分页工具
### 6. mongoDB

## 九、网络编程
### 1. netty的使用
### 2. Vertx
    https://vertx-china.github.io/docs/vertx-core/java/
### 3. Vertx的tcp编程
    https://www.larscheng.com/vertx-tcp
### 4. RSocket
### 5. 网络代理
### 6. WebSocket

## 十、分布式架构
### 1. 分布式事务
    seata
### 2. 分布式id
### 3. 分布式缓存
### 4. 分布式任务

## 十一、高可用 + 高并发架构
### 1.
    http://kaito-kidd.com/2021/10/15/what-is-the-multi-site-high-availability-design/
### 2. 高并发场景下容错、限流、熔断实现方式

## 十二、项目注意事项
### 1. 时区问题

## 十三、安全
### 1. csrf token
### 2. AK/SK
### 3. RSA非对称加密的使用
### 4. 白名单、黑名单
### 5. JWT实现

## 十四、运维+Linux
### 1. nginx和LVS
### 2. linux抓包和wireshark分析
### 3. tcpdump的使用
### 4. linux常用命令
    https://mp.weixin.qq.com/s/LQydK7JcQhs50K2aCLRvWA
### 5. 搭建docker仓库
### 6. 搭建mvn仓库

## 十五、规范与协议
### 1. http协议
### 2. TCP ip协议
### 3. oidc协议
### 4. oauth2协议
### 5. emqx协议、mqtt协议
### 6. openApi 规范
### 7. 接口规范

## 十六、CICD
### 1. jenkins + git + docker + k8s + prometheus
    unit_test/dependency_check/sonarqube/上传docker image/deploy/auto_test

## 十七、源码学习
### 1.

## 十八、设计模式

## 十九、其他语言
### 1. Python
### 2. Go

## 二十、设计案例
### 1. 防止重复下单
    https://mp.weixin.qq.com/s/GNthObUtxKL9h_ADAVHOxA
### 2. 10W qps会员系统的设计
    https://mp.weixin.qq.com/s/OUdderEQwPudWlNgu8GnWA

## 二十一、常见问题与方法
### 1. Java项目防止sql注入四种方式
    1. PreparedStatement防止SQL注入
    2. mybatis中#{}防止SQL注入
    3. 对请求参数的敏感词汇进行过滤
    4. nginx反向代理防止SQL注入
    https://mp.weixin.qq.com/s/N6VDNmVwSbYPMgXq_rTX8Q
### 2. 过期事件监听
    使用 RocketMQ、RabbitMQ、Pulsar 等消息队列的延时投递功能 
    使用 Redisson 提供的 DelayedQueue
