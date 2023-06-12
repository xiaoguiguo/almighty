package com.test.spring.framework.serialize;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
@RequestMapping("serialize")
public class JacksonController {

    @GetMapping("time")
    public LocalDateTime getTime() {
        LocalDateTime datetime = LocalDateTime.now();
        return datetime;
    }

    @GetMapping("student")
    public Student getStudent() {
        Student student = new Student();
        log.info("student: {}", student);
        return student;
    }

    /**
     * 时间格式序列化处理，格式自定义
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizeLocalDateTimeFormat() {
        return jacksonObjectMapperBuilder -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
            LocalDateTimeDeserializer deserializer = new LocalDateTimeDeserializer(formatter);
            LocalDateTimeSerializer serializer = new LocalDateTimeSerializer(formatter);
            jacksonObjectMapperBuilder.serializerByType(LocalDateTime.class, serializer);
            jacksonObjectMapperBuilder.deserializerByType(LocalDateTime.class, deserializer);
        };
    }

    @Getter
    @Setter
    class Student {
        String name = "doudou";
        int age = 18;
        @JsonFormat(pattern = "yyyy年MM月dd日")
        LocalDateTime createTime = LocalDateTime.now();

        @Override public String toString() {
            return "Student{" + "name='" + name + '\'' + ", age=" + age + ", createTime=" + createTime + '}';
        }
    }
}
