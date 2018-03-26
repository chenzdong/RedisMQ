package com.czd.util;

import java.io.*;

/**
 * 序列化工具
 *
 * @author: czd
 * @create: 2018/3/26 14:16
 */
public class SerializableUtil {
    /**
     * 对象转化为byte[]
     * @param object
     * @return
     */
    public static byte[] object2Bytes(Object object) throws IOException{
        ByteArrayOutputStream bo=new ByteArrayOutputStream();
        ObjectOutputStream oo=new ObjectOutputStream(bo);
        oo.writeObject(object);
        byte[] bytes=bo.toByteArray();
        bo.close();
        oo.close();
        return bytes;
    }

    public static Object bytes2Object(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bin=new ByteArrayInputStream(bytes);
        ObjectInputStream oin=new ObjectInputStream(bin);
        return oin.readObject();
    }

}
