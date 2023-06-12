package com.test.spring.framework.serialize;
/**
 * spring boot默认是采用Jackson来做json的序列化的。当我们使用LocalDateTime来表示时间的时候，如果不做相应的配置，
 * 那么前端接收到的时间就会是这样的数据："2023-06-12T10:22:58.4437059"
 * 引发这个问题的原因是json序列化的时候没有配置规则，所以采用了默认的序列化规则
 * 解决的方法就是配置一个规则，这样，所有的时间格式都会按照"yyyy-MM-dd HH:mm:ss"来显示，
 * 如果需要特殊的格式，例如"yyyy年MM月dd日"，那么只需要在字段上加上@JsonFormat(pattern = "yyyy年MM月dd日")注解即可。
 */