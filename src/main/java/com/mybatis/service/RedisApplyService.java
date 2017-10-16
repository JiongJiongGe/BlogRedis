package com.mybatis.service;

import com.mybatis.domain.user.CalmWangUserModel;
import com.mybatis.service.redis.RedisServiceI;
import com.mybatis.service.user.CalmWangUserServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yunkai on 2017/10/11.
 */
@Service
public class RedisApplyService {

    private static ReentrantLock lock = new ReentrantLock();

    private static Logger logger = LoggerFactory.getLogger(RedisApplyService.class);

    @Autowired
    private CalmWangUserServiceI calmWangUserService;

    @Autowired
    private RedisServiceI redisService;

    public String retun(String key){
        String userName = redisService.getValue(key);
        if(StringUtils.isEmpty(userName)){
            CalmWangUserModel user = calmWangUserService.getByPhone(key);
            try {
                if (user == null && lock.tryLock(3, TimeUnit.SECONDS)) {
                    try {
                        logger.info("lock success ...");
                        calmWangUserService.saveUser("未知用户", key);
                        CalmWangUserModel newUser = calmWangUserService.getByPhone(key);
                        userName = newUser.getUserName();
                        redisService.setKeyValue(key, newUser.getUserName());
                    }catch (Exception e){
                        logger.error("user is null error is {}", e);
                    }finally {
                        lock.unlock();
                    }
                } else {
                    logger.info("lock fail, user is exist");
                    if(user != null){
                        userName = user.getUserName();
                        redisService.setKeyValue(key, userName);
                    }else {
                        userName = redisService.getValue(key);
                    }
                }
            }catch (Exception e){
                logger.error("error is : {}", e);
            }
        }else{
            logger.info("redis is exist");
        }
        logger.info("userName = {}", userName);
        return userName;
    }
}
