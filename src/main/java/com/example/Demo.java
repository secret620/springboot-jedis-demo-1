package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.JedisPool;

import java.nio.charset.StandardCharsets;

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
        String add = redisJedisUtil.set("xx", "v1");
        System.out.println(add);
        System.out.println( redisJedisUtil.get("xx") );
    }

    @Test
    public void test2( ) {
        redisJedisUtil.set("x2".getBytes(StandardCharsets.UTF_8), "v2".getBytes(StandardCharsets.UTF_8), 2);
        redisJedisUtil.del(2,"x2" );
    }

    @Test
    public void test3(){
        System.out.println(redisJedisUtil.expire("x2", 10, 3));
    }

    @Test
    public void test4(){
        System.out.println(redisJedisUtil.pexpire("x2",1000 * 10, 3));
        System.out.println(redisJedisUtil.pttl("x2", 3));
        redisJedisUtil.persist("x2",3);
        System.out.println(redisJedisUtil.pttl("x2", 3));
    }

    @Test
    public void test5(){
        System.out.println(redisJedisUtil.expire("x2",10, 3));
    }

    @Test
    public void test6(){
        System.out.println(redisJedisUtil.setex("x3","v3",10, 3));
    }

    @Test
    public void test7(){
        redisJedisUtil.set("x4","Hello World", 3);
        System.out.println(redisJedisUtil.get("x4", 3));
        System.out.println(redisJedisUtil.setrange("x4","Redis",6, 3));
        System.out.println(redisJedisUtil.get("x4", 3));
    }

    @Test
    public void test8(){
        redisJedisUtil.mset(3, new String[]{"x4","v4", "x2","v2"});
        System.out.println(new String(redisJedisUtil.getSet("x4", "v44", 3), StandardCharsets.UTF_8));
    }

    @Test
    public void test9(){
        Long l = redisJedisUtil.incr("x4", 3);
        System.out.println(l);

        String r = redisJedisUtil.set("x4", "1",3);
        System.out.println(r);

        l = redisJedisUtil.incr("x4", 3);
        System.out.println(l);

        l = redisJedisUtil.incrBy("x4", 10L, 3);
        System.out.println(l);

        l = redisJedisUtil.decr("x4", 3);
        System.out.println(l);
    }

    @Test
    public void test10(){
        Long l = redisJedisUtil.hset("v5", "name","1", 3);
        System.out.println(l);
        l = redisJedisUtil.hset("v5", "name2","12", 3);
        System.out.println(l);
    }
}
