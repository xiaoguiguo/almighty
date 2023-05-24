package com.test.datasource.service.goal;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.test.datasource.entity.User;
import com.test.datasource.repository.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/*
 * @className: GoalUserService
 * @author: doudou
 * @datetime: 2023/5/24
 * @description: TODO
 */
@Service
@DS("goal")
@RequiredArgsConstructor
public class GoalUserService {
    private final UserDao userDao;

    public User findById(Long id) {
        return userDao.selectById(id);
    }
}
