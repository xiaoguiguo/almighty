package com.test.datasource.service.source;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.test.datasource.entity.User;
import com.test.datasource.repository.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@DS("source")
@RequiredArgsConstructor
public class SourceUserService {
    private final UserDao userDao;

    public User findById(Long id) {
        return userDao.selectById(id);
    }
}
