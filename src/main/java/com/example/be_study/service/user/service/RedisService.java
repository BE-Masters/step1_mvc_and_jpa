package com.example.be_study.service.user.service;

import com.example.be_study.service.user.repository.EmailRepositoryImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RedisService {

    private final EmailRepositoryImpl redisRepositoryImpl;


    public RedisService(EmailRepositoryImpl redisRepositoryImpl) {
        this.redisRepositoryImpl = redisRepositoryImpl;
    }

    @Transactional(readOnly = true)
    public String getData(String key) {
        return redisRepositoryImpl.getData(key);
    }

    @Transactional(readOnly = true)
    public boolean existData(String key) {
        return redisRepositoryImpl.existData(key);
    }

    @Transactional(readOnly = false)
    public void setDataExpire(String key, String value, long timeout) {
        redisRepositoryImpl.setDataExpire(key, value, timeout);
    }

    @Transactional(readOnly = false)
    public void deleteData(String key) {
        redisRepositoryImpl.deleteData(key);
    }
}
