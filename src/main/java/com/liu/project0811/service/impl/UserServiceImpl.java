package com.liu.project0811.service.impl;

import com.liu.project0811.mapper.ChatRecordMapper;
import com.liu.project0811.mapper.UserMapper;
import com.liu.project0811.pojo.ChatRecord;
import com.liu.project0811.pojo.User;
import com.liu.project0811.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ChatRecordMapper chatRecordMapper;

    @Override
    public User doLogin(String account, String password) {
        return userMapper.doLogin(account, password);
    }

    @Override
    public int addRecord(ChatRecord c) {
        return chatRecordMapper.addRecord(c);
    }

    @Override
    public ArrayList<ChatRecord> getRecord(int fid, int tid) {
        return chatRecordMapper.getRecord(fid, tid);
    }

    @Override
    public ArrayList<User> getFriends(int id) {
        return userMapper.getFriends(id);
    }
}
