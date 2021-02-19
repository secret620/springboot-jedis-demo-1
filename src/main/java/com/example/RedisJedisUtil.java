package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.nio.charset.StandardCharsets;
import java.util.Map;

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

    private Jedis jedis(int indexDb) throws Exception{
        Jedis jedis  = jedisPool.getResource();
        jedis.select(indexDb);
        return jedis;
    }

    private Jedis jedis() throws Exception{
        Jedis jedis  = jedisPool.getResource();
        return jedis;
    }

    public String get(String key){
        return this.get(key, 0);
    }

    public String get(String key , int indexDb){
        Jedis jedis = null;
        try{
            jedis = this.jedis(indexDb);
            return jedis.get(key);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return null;
    }

    public byte[] get(byte[] key) {
        return this.get(key, 0);
    }

    public byte[] get(byte[] key , int indexDb){
        Jedis jedis = null;
        try{
            jedis = this.jedis(indexDb);
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

    public String set(String key, String value){
        return this.set(key, value, 0);
    }

    public String set(String key, String value, int indexDb){
        return this.set(key.getBytes(StandardCharsets.UTF_8), value == null? null : value.getBytes(StandardCharsets.UTF_8), indexDb);
    }

    public String set(byte[] key, byte[] value){
       return this.set(key, value, 0);
    }

    public String set(byte[] key, byte[] value, int indexDb){
        Jedis jedis = null;
        try{
            jedis = this.jedis(indexDb);
            return jedis.set(key, value);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return "0";
    }

    public String setex(String key, String value, int seconds){
        return this.setex(key.getBytes(StandardCharsets.UTF_8), value == null? null : value.getBytes(StandardCharsets.UTF_8), seconds, 0);
    }

    public String setex(byte[] key, byte[] value, int seconds){
        return this.setex(key, value, seconds, 0);
    }

    public String setex(String key, String value, int seconds, int indexDb){
        return this.setex(key.getBytes(StandardCharsets.UTF_8), value == null? null : value.getBytes(StandardCharsets.UTF_8), seconds, indexDb);
    }

    public String setex(byte[] key, byte[] value, int seconds, int indexDb){
        Jedis jedis = null;
        try{
            jedis = this.jedis(indexDb);
            return jedis.setex(key, seconds, value);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return "0";
    }

    public Long del(byte[] ... keys){
       return this.del(0, keys);
    }

    public Long del(int indexDb, byte[] ... keys){
        Jedis jedis = null;
        try{
            jedis = this.jedis(indexDb);
            return jedis.del(keys);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return 0L;
    }

    public Long del(int indexDb, String ... keys){
        Jedis jedis = null;
        try{
            jedis = this.jedis(indexDb);
            return jedis.del(keys);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return 0L;
    }

    public Long del(String ... keys){
        return this.del(0, keys);
    }

    public Long append(String key, String appendStr, int indexDb){
        Jedis jedis = null;
        try{
            jedis = this.jedis(indexDb);
            return jedis.append(key, appendStr);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return 0L;
    }

    public Long append(String key, String appendStr){
        return this.append(key, appendStr, 0);
    }

    public Boolean exists(String key, int indexDb){
        Jedis jedis = null;
        try{
            jedis = this.jedis(indexDb);
            return jedis.exists(key);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return false;
    }

    public Boolean exists(String key){
        return this.exists(key, 0);
    }

    public String flushDB(){
        Jedis jedis = null;
        try{
            jedis = this.jedis();
            return jedis.flushDB();
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return null;
    }

    public String flashAll(){
        Jedis jedis = null;
        try{
            jedis = this.jedis();
            return jedis.flushAll();
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return null;
    }

    public Long expire(String key, int seconds){
        return this.expire(key, seconds, 0);
    }

    public Long expire(String key, int seconds, int indexDb){
        return this.expire(key.getBytes(StandardCharsets.UTF_8), seconds, indexDb);
    }

    public Long expire(byte[] key, int seconds, int indexDb){
        Jedis jedis = null;
        try{
            jedis = this.jedis(indexDb);
            return jedis.expire(key, seconds);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return 0L;
    }

    public Long expire(byte[] key, int seconds){
        return this.expire(key,seconds, 0);
    }

    public Long pexpire(byte[] key, int milliseconds, int indexDb){
        Jedis jedis = null;
        try{
            jedis = this.jedis(indexDb);
            return jedis.pexpire(key, milliseconds);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return 0L;
    }

    public Long pexpire(byte[] key, int milliseconds){
        return this.pexpire(key, milliseconds, 0);
    }

    public Long pexpire(String key, int milliseconds, int indexDb){
        return this.pexpire(key.getBytes(StandardCharsets.UTF_8), milliseconds, indexDb);
    }

    public Long pexpire(String key, int milliseconds){
        return this.pexpire(key, milliseconds, 0);
    }

    public Long ttl(String key, int indexDb){
        return this.ttl(key.getBytes(StandardCharsets.UTF_8), indexDb);
    }

    public Long ttl(String key){
        return this.ttl(key, 0);
    }

    public Long ttl(byte[] key, int indexDb){
        Jedis jedis = null;
        try{
            jedis = this.jedis(indexDb);
            return jedis.ttl(key);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return 0L;
    }

    public Long ttl(byte[] key){
        return this.ttl(key, 0);
    }

    public Long pttl(byte[] key, int indexDb){
        Jedis jedis = null;
        try{
            jedis = this.jedis(indexDb);
            return jedis.pttl(key);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return 0L;
    }

    public Long pttl(byte[] key){
        return this.pttl(key, 0);
    }

    public Long pttl(String key, int indexDb){
        return this.pttl(key.getBytes(StandardCharsets.UTF_8), indexDb);
    }

    public Long pttl(String key){
        return this.pttl(key, 0);
    }

    public Long persist(byte[] key, int indexDb){
        Jedis jedis = null;
        Long result = 0L;
        try{
            jedis = this.jedis(indexDb);
            result =  jedis.persist(key);
        }catch (Exception e){
            e.printStackTrace();
            result = -1L;
        } finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return result;
    }

    public Long persist(String key, int indexDb){
        return this.persist(key.getBytes(StandardCharsets.UTF_8), indexDb);
    }

    public Long persist(String key){
        return this.persist(key.getBytes(StandardCharsets.UTF_8), 0);
    }

    public Long persist(byte[] key){
        return this.persist(key, 0);
    }

    public Long setrange(byte[] key, byte[] replaceStr, int offset, int indexDb){
        Jedis jedis = null;
        try{
            jedis = this.jedis(indexDb);
            return jedis.setrange(key, offset, replaceStr);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return 0L;
    }

    public Long setrange(byte[] key, byte[] replaceStr, int offset){
        return this.setrange(key, replaceStr, offset, 0);
    }

    public Long setrange(String key, String replaceStr, int offset, int indexDb){
        return this.setrange(key.getBytes(StandardCharsets.UTF_8), replaceStr == null ? null:replaceStr.getBytes(StandardCharsets.UTF_8), offset, indexDb);
    }

    public Long setrange(String key, String replaceStr, int offset){
        return this.setrange(key, replaceStr, offset, 0);
    }

    public String mset(int indexDb, String ... keyValues){
        Jedis jedis = null;
        try{
            jedis = this.jedis(indexDb);
            return jedis.mset(keyValues);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return null;
    }

    public String mset(String ... keyValues){
        return this.mset(0, keyValues);
    }

    public String mset(int indexDb, byte[] ... keyValues){
        Jedis jedis = null;
        try{
            jedis = this.jedis(indexDb);
            return jedis.mset(keyValues);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return null;
    }

    public String mset(byte[] ... keyValues){
        return this.mset(0, keyValues);
    }

    public Long msetnx(int indexDb, String ... keyValues){
        Jedis jedis = null;
        try{
            jedis = this.jedis(indexDb);
            return jedis.msetnx(keyValues);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return null;
    }

    public Long msetnx(String ... keyValues){
        return this.msetnx(0, keyValues);
    }

    public Long msetnx(int indexDb, byte[] ... keyValues){
        Jedis jedis = null;
        try{
            jedis = this.jedis(indexDb);
            return jedis.msetnx(keyValues);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return null;
    }

    public Long msetnx(byte[] ... keyValues){
        return this.msetnx(0, keyValues);
    }

    public byte[] getSet(byte[] key, byte[] value, int indexDb){
        Jedis jedis = null;
        try{
            jedis = this.jedis(indexDb);
            return jedis.getSet(key,value);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return null;
    }

    public byte[] getSet(byte[] key, byte[] value){
        return this.getSet(key, value, 0);
    }

    public byte[] getSet(String key, String value, int indexDb){
        return this.getSet(key.getBytes(StandardCharsets.UTF_8), value == null ? null : value.getBytes(StandardCharsets.UTF_8), indexDb);
    }

    public byte[] getSet(String key, String value){
        return this.getSet(key, value, 0);
    }

    public byte[] getrange(byte[] key, int startOffset, int endOffset, int indexDb){
        Jedis jedis = null;
        try{
            jedis = this.jedis(indexDb);
            return jedis.getrange(key, startOffset, endOffset);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return null;
    }

    public byte[] getrange(String key, int startOffset, int endOffset, int indexDb){
        return this.getrange(key.getBytes(StandardCharsets.UTF_8), startOffset, endOffset, indexDb);
    }

    public byte[] getrange(String key, int startOffset, int endOffset){
        return this.getrange(key, startOffset, endOffset, 0);
    }

    public byte[] getrange(byte[] key, int startOffset, int endOffset){
        return this.getrange(key, startOffset, endOffset, 0);
    }

    public Long incr(byte[] key, int indexDb){
        Jedis jedis = null;
        try{
            jedis = this.jedis(indexDb);
            return jedis.incr(key);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return null;
    }

    public Long incr(byte[] key){
        return this.incr(key, 0);
    }

    public Long incr(String key, int indexDb){
        return this.incr(key.getBytes(StandardCharsets.UTF_8), indexDb);
    }

    public Long incr(String key){
        return this.incr(key, 0);
    }

    public Long incrBy(byte[] key, Long integer, int indexDb){
        Jedis jedis = null;
        try{
            jedis = this.jedis(indexDb);
            return jedis.incrBy(key, integer);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return null;
    }

    public Long incrBy(byte[] key, Long integer){
        return this.incrBy(key, integer, 0);
    }

    public Long incrBy(String key, Long integer, int indexDb){
        return this.incrBy(key.getBytes(StandardCharsets.UTF_8),integer, indexDb);
    }

    public Long incrBy(String key, Long integer){
        return this.incrBy(key, integer, 0);
    }

    public Long decr(byte[] key, int indexDb){
        Jedis jedis = null;
        try{
            jedis = this.jedis(indexDb);
            return jedis.decr(key);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return null;
    }

    public Long decr(byte[] key){
        return this.decr(key, 0);
    }

    public Long decr(String key, int indexDb){
        return this.decr(key.getBytes(StandardCharsets.UTF_8), indexDb);
    }

    public Long decr(String key){
        return this.decr(key, 0);
    }

    public Long decrBy(byte[] key, Long integer, int indexDb){
        Jedis jedis = null;
        try{
            jedis = this.jedis(indexDb);
            return jedis.decrBy(key, integer);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return null;
    }

    public Long decrBy(byte[] key, Long integer){
        return this.decrBy(key, integer, 0);
    }

    public Long decrBy(String key, Long integer, int indexDb){
        return this.decrBy(key.getBytes(StandardCharsets.UTF_8),integer, indexDb);
    }

    public Long decrBy(String key, Long integer){
        return this.decrBy(key, integer, 0);
    }

    public Long strlen(byte[] key, int indexDb){
        Jedis jedis = null;
        try{
            jedis = this.jedis(indexDb);
            return jedis.strlen(key);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return null;
    }

    public Long strlen(byte[] key){
        return this.strlen(key, 0);
    }

    public Long strlen(String key){
        return this.strlen(key, 0);
    }

    public Long strlen(String key, int indexDb){
        return this.strlen(key.getBytes(StandardCharsets.UTF_8), indexDb);
    }

    public Long hset(byte[] key, Map<byte[], byte[]> fieldValue, int indexDb){
        Jedis jedis = null;
        try{
            jedis = this.jedis(indexDb);
            return jedis.hset(key, fieldValue);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return null;
    }

    public Long hset(byte[] key, Map<byte[], byte[]> fieldValue){
        return this.hset(key, fieldValue, 0);
    }

    public Long hset(String key, Map<String, String> fieldValue, int indexDb){
        Jedis jedis = null;
        try{
            jedis = this.jedis(indexDb);
            return jedis.hset(key, fieldValue);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return null;
    }

    public Long hset(String key, Map<String, String> fieldValue){
        return this.hset(key, fieldValue, 0);
    }

    public Long hset(byte[] key, byte[] field, byte[] value, int indexDb){
        Jedis jedis = null;
        try{
            jedis = this.jedis(indexDb);
            return jedis.hset(key, field, value);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return null;
    }

    public Long hset(byte[] key, byte[] field, byte[] value){
        return this.hset(key, field, value, 0);
    }

    public Long hset(String key, String field, String value, int indexDb){
        return this.hset(key.getBytes(StandardCharsets.UTF_8), field.getBytes(StandardCharsets.UTF_8), value == null ? null : value.getBytes(StandardCharsets.UTF_8), indexDb);
    }

    public Long hset(String key, String field, String value){
        return this.hset(key, field, value, 0);
    }

    public Long hsetnx(byte[] key, byte[] field, byte[] value, int indexDb){
        Jedis jedis = null;
        try{
            jedis = this.jedis(indexDb);
            return jedis.hsetnx(key, field, value);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return null;
    }

    public Long hsetnx(byte[] key, byte[] field, byte[] value){
        return this.hsetnx(key, field, value, 0);
    }

    public Long hsetnx(String key, String field, String value, int indexDb){
        return this.hsetnx(key.getBytes(StandardCharsets.UTF_8), field.getBytes(StandardCharsets.UTF_8), value == null ? null : value.getBytes(StandardCharsets.UTF_8), indexDb);
    }

    public Long hsetnx(String key, String field, String value){
        return this.hsetnx(key, field, value, 0);
    }
}

