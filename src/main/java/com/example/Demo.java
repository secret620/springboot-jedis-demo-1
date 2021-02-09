package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.JedisPool;

/**
 * @Description 表示 ：
 * @Author Shi You Qin
 * @Email secret620@163.com
 * @Date Create 12:59 2021/2/9
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class Demo {
    @Autowired
    private RedisJedisUtil redisJedisUtil;

    @Test
    public void test( ) {
        System.out.println( redisJedisUtil.get("xx") );
    }
}
