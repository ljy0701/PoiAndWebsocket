package com.liu.project0811.controller;

import com.liu.project0811.utils.WebSocketServer;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RestController
@Api(tags = "WebSocket")
@RequestMapping("WebSocket")
@CrossOrigin
public class WebSocketController {
    @GetMapping("index")
    public ResponseEntity<String> index(){
        return ResponseEntity.ok("请求成功.....");
    }

    @GetMapping("page")
    public ModelAndView page(){
        return new ModelAndView("websocket");
    }

    @RequestMapping("/push/{toUserId}")
    public ResponseEntity<String> pushToWeb(String message, @PathVariable String toUserId) throws IOException {
        WebSocketServer.sendInfo(message,toUserId);
        return ResponseEntity.ok("MSG SEND SUCCESS");
    }

}
