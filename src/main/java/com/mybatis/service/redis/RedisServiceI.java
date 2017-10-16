package com.mybatis.service.redis;

/**
 * Created by yunkai on 2017/10/11.
 */
public interface RedisServiceI {

    /**
     * redis 设置<key, value>
     * @param key
     * @param value
     */
    public void setKeyValue(String key, String value);

    /**
     * redis 通过key获取value
     * @param key
     * @return
     */
    public String getValue(String key);
}
