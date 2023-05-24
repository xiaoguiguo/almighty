package com.test.datasource.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.datasource.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<User> {
}
