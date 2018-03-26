package com.czd.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.Properties;

/**
 * Redis工具类
 *
 * @author: czd
 * @create: 2018/3/26 14:23
 */
public class JedisUtil {
    private static String JEDIS_iP;
    private static int JEDIS_PORT;
    private static String JEDIS_PASSWORD;
    private static int JEDIS_TIMEOUT;
    private static JedisPool jedisPool;
    static {

        //从配置文件中读取
        org.springframework.core.io.Resource resource = new ClassPathResource("/redis-config.properties");
        try {
            Properties props = PropertiesLoaderUtils.loadProperties(resource);
            JedisPoolConfig conf=new JedisPoolConfig();
            conf.setMaxIdle(100);
            conf.setMaxTotal(5000);
            JEDIS_iP = props.getProperty("redis.ip");
            JEDIS_PORT = Integer.parseInt(props.getProperty("redis.port"));
            JEDIS_TIMEOUT=3000;
            String password =  props.getProperty("redis.password");
            jedisPool = new JedisPool(conf, JEDIS_iP, JEDIS_PORT, JEDIS_TIMEOUT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取key的值
     * @param key
     * @return
     */
    public static String get(String key){
        Jedis jedis=jedisPool.getResource();
        try{
            return jedis.get(key);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return null;
    }

    public static void del(String key){
        Jedis jedis=jedisPool.getResource();
        try {
            if(jedis.exists(key)){
                jedis.del(key);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
    }

    /**
     * 存入队列
     * @param key
     * @param value
     */
    public static void lpush(byte[] key,byte[] value){
        Jedis jedis=jedisPool.getResource();
        try {
            jedis.lpush(key,value);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
    }

    /**
     * 从队列取出尾元素并删除此元素
     * @param key
     * @return
     */
    public static byte[] rpop(byte[] key){
        Jedis jedis=jedisPool.getResource();
        try {
            return jedis.lpop(key);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return null;
    }

    public static long llen(byte[] key){
        Jedis jedis=jedisPool.getResource();
        try {
            return jedis.llen(key);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return -1L;
    }
}
