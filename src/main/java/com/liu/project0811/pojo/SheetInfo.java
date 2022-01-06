package com.liu.project0811.pojo;

import lombok.Data;

@Data
public class SheetInfo {
    private String sheetName;
    private Integer sheetSort;
    private String[][] info;
}
