package com.czd.util;

import com.czd.domain.Message;

import java.io.IOException;

/**
 * 测试
 *
 * @author: czd
 * @create: 2018/3/26 14:46
 */
public class Test {
    public static byte[] redisKey = "key".getBytes();
    static {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void init() throws IOException {
        for (int i = 0; i < 1000; i++) {
            Message message = new Message(i, "这是第" + i + "个内容");
            JedisUtil.lpush(redisKey, SerializableUtil.object2Bytes(message));
        }

    }

    public static void main(String[] args) {
        try {
            while(JedisUtil.llen(redisKey)>0){
                pop();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void pop() throws Exception {
        byte[] bytes = JedisUtil.rpop(redisKey);
        Message msg = (Message) SerializableUtil.bytes2Object(bytes);
        if (msg != null) {
            System.out.println(msg.getId() + "----" + msg.getContent());
        }
    }
}
