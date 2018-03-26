package com.czd.domain;

import java.io.Serializable;

/**
 * 消息类
 *
 * @author: czd
 * @create: 2018/3/26 14:22
 */
public class Message implements Serializable{
    private int id;
    private String content;
    public Message(int id,String content){
        this.id=id;
        this.content=content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
