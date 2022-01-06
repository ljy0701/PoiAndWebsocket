package com.liu.project0811.mapper;

import com.liu.project0811.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface UserMapper {
    User doLogin(@Param("account") String account,@Param("password") String password);

    ArrayList<User> getFriends(@Param("id") int id);

}
