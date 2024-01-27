package com.example.be_study.service.user.service;

import com.example.be_study.service.user.repository.RedisRepositoryImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RedisService {

    private final RedisRepositoryImpl redisRepositoryImpl;


    public RedisService(RedisRepositoryImpl redisRepositoryImpl) {
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

    @Transactional(readOnly = true)
    public void setDataExpire(String key, String value, long timeout) {
        redisRepositoryImpl.setDataExpire(key, value, timeout);
    }

    @Transactional(readOnly = true)
    public void deleteData(String key) {
        redisRepositoryImpl.deleteData(key);
    }
}
