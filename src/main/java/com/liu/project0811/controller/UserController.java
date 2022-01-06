package com.liu.project0811.controller;

import com.liu.project0811.pojo.ChatRecord;
import com.liu.project0811.pojo.User;
import com.liu.project0811.service.UserService;
import com.liu.project0811.utils.JsonMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
@Api(tags = "用户管理")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

//    @RequestMapping(value = "register",method = RequestMethod.POST)
//    @ApiOperation(value = "用户注册",httpMethod = "POST")
//    public JsonMsg register(@RequestParam String name){
//        JsonMsg jsonMsg = new JsonMsg();
//        Map<String,Object> a = new HashMap<>();
//
//        jsonMsg.setCode("200");
//        jsonMsg.setMsg(name);
//        return  jsonMsg;
//    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ApiOperation(value = "用户登录",httpMethod = "POST")
    public JsonMsg login(@RequestParam String account,@RequestParam String password){
        JsonMsg jsonMsg = new JsonMsg();
        User u = userService.doLogin(account, password);
        if(u==null || u.equals("")){
            jsonMsg.setCode("100");
            jsonMsg.setMsg("登录失败...");
        } else {
            jsonMsg.setCode("200");
            jsonMsg.setMsg("登录成功！！");
            jsonMsg.setObject(u);
        }

        return  jsonMsg;
    }

    @RequestMapping(value = "getFriends",method = RequestMethod.POST)
    @ApiOperation(value = "获取好友列表",httpMethod = "POST")
    public JsonMsg getFriends(@RequestParam int id){
        JsonMsg jsonMsg = new JsonMsg();
        ArrayList<User> list = userService.getFriends(id);
        if(list.size()==0){
            jsonMsg.setCode("100");
            jsonMsg.setMsg("无数据...");
        } else {
            jsonMsg.setCode("200");
            jsonMsg.setMsg("查询成功！！");
            jsonMsg.setInfoList(list);
        }

        return  jsonMsg;
    }

    @RequestMapping(value = "getRecord",method = RequestMethod.POST)
    @ApiOperation(value = "获取聊天记录",httpMethod = "POST")
    public JsonMsg getRecord(@RequestParam int fid,@RequestParam int tid){
        JsonMsg jsonMsg = new JsonMsg();
        ArrayList<ChatRecord> list = userService.getRecord(fid, tid);
        if(list.size()==0){
            jsonMsg.setCode("100");
            jsonMsg.setMsg("无数据...");
        } else {
            jsonMsg.setCode("200");
            jsonMsg.setMsg("查询成功！！");
            jsonMsg.setInfoList(list);
        }

        return  jsonMsg;
    }

//    public JsonMsg addRecord(ChatRecord chatRecord){
//        JsonMsg jsonMsg = new JsonMsg();
//        int a = userService.addRecord(chatRecord);
//        if(a!=0){
//            jsonMsg.setCode("200");
//            jsonMsg.setMsg("插入成功！！");
//            System.out.println("插入成功！！");
//        } else {
//            jsonMsg.setCode("100");
//            jsonMsg.setMsg("插入失败...");
//            System.out.println("插入失败...");
//        }
//
//        return  jsonMsg;
//    }
}
