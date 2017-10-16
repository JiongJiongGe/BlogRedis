package com.mybatis.controller;

import com.mybatis.service.RedisApplyService;
import com.mybatis.service.redis.RedisServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yunkai on 2017/10/11.
 */
@RestController
@RequestMapping("redis")
public class RedisController {

    private Logger logger = LoggerFactory.getLogger(RedisController.class);

    @Autowired
    private RedisServiceI redisService;

    @Autowired
    private RedisApplyService redisApplyService;

    /**
     * redis 保存key-value
     * @return
     */
    @GetMapping("/singleSaveRedis")
    public String singleSaveRedis(){
        String key = "1";
        String value = "champion";
        redisService.setKeyValue(key, value);
        return "success";
    }

    /**
     * 通过key 获取value
     * @param key
     * @return
     */
    @GetMapping("/singleGetByKey")
    public String singleGetByKey(String key){
        String value = redisService.getValue(key);
        if(StringUtils.isEmpty(value)){
            return "no key";
        }
        return redisService.getValue(key);
    }

    /**
     * 通过key 获取到对应的name
     * @param key
     */
    @GetMapping("/singleLock")
    public String singleLock(String key){
        return redisApplyService.retun(key);
    }

}
