package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Description 表示 ：
 * @Author Shi You Qin
 * @Email secret620@163.com
 * @Date Create 23:08 2021/2/9
 **/
@Component
public class RedisJedisUtil {

    @Autowired
    private JedisPool jedisPool;

    public String get(String key){
        return this.get(key, 0);
    }

    public String get(String key , int indexDb){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            jedis.select(indexDb);
            return jedis.get(key);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return null;
    }
}
