package com.liu.project0811.utils;

import lombok.Data;

import java.util.List;
@Data
public class JsonMsg {
    private String code;
    private String msg;
    private List infoList;
    private Object object;
}
