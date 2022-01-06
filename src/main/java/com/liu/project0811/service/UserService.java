package com.liu.project0811.service;

import com.liu.project0811.pojo.ChatRecord;
import com.liu.project0811.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface UserService {
    User doLogin(String account, String password);

    int addRecord(ChatRecord c);

    ArrayList<ChatRecord> getRecord(int fid,int tid);

    ArrayList<User> getFriends(int id);
}
