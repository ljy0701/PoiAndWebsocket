package com.liu.project0811.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class ChatRecord {
    private Long id;
    private int fromUserId;
    private int toUserId;
    private String content;
    private Date createTime;
}
