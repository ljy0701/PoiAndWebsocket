package com.liu.project0811.mapper;

import com.liu.project0811.pojo.ChatRecord;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface ChatRecordMapper {

    ArrayList<ChatRecord> getRecord(@Param("fid") int fid,@Param("tid")  int tid);

    int addRecord(@Param("chatRecord") ChatRecord chatRecord);
}
