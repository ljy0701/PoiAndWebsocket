package com.liu.project0811.utils;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;


public  class ExcelFunctions {


    public String getCellValues(Cell hssfCell) {
        String value = "";

        if(hssfCell!=null){
            switch (hssfCell.getCellType()){
                case Cell.CELL_TYPE_NUMERIC:
//                    if(hssfCell.getSheet().getSheetName().equals("检修时间选择")){
//                        Object inputValue = null;// 单元格值
//                        Long longValue = Math.round(hssfCell.getNumericCellValue());
//                        Double doubleVal = hssfCell.getNumericCellValue();
//                        if(Double.parseDouble(longValue + ".0") == doubleVal){   //判断是否含有小数位.0
//                            inputValue = longValue;
//                            DecimalFormat df = new DecimalFormat("#");    //格式化为整数数，按自己需求选择；
//                            value = df.format(inputValue);
//                        }
//                        else{
//                            DecimalFormat df = new DecimalFormat("#.#####");    //格式化为一位小数，按自己需求选择；
//                            inputValue = doubleVal;
//                            value = df.format(inputValue);
//                        }
//                    }
//                    else {
                        value = String.valueOf(hssfCell.getNumericCellValue());
//                    }

                break;
                case Cell.CELL_TYPE_STRING:
                    value = String.valueOf(hssfCell.getStringCellValue());
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    value = String.valueOf(hssfCell.getBooleanCellValue());
                    break;
                case Cell.CELL_TYPE_FORMULA:

                    switch(hssfCell.getCachedFormulaResultType()) {
                        case Cell.CELL_TYPE_NUMERIC:
//                            System.out.println("Last evaluated as: " + hssfCell.getNumericCellValue());
                            value = hssfCell.getNumericCellValue()+"";
                            break;
                        case Cell.CELL_TYPE_STRING:
//                            System.out.println("Last evaluated as \"" + hssfCell.getRichStringCellValue() + "\"");
                            value = hssfCell.getRichStringCellValue()+"";
                            break;
                    }

//                    Workbook wb = hssfCell.getSheet().getWorkbook();
//
//                    CreationHelper crateHelper = wb.getCreationHelper();
//
//                    FormulaEvaluator evaluator = crateHelper.createFormulaEvaluator();
//
//                    value = getCellValues(evaluator.evaluateInCell(hssfCell));

                    break;
            }

        }
        return value;
    }

    //XLSX
//    public String getCellValues(XSSFCell xssfCell) {
//        String value = "";
////        DecimalFormat format = new DecimalFormat("0.0");
//        if(xssfCell != null){
//            switch (xssfCell.getCellType()){
//                case NUMERIC:
//                    xssfCell.setCellType(CellType.STRING);
//                    value = String.valueOf(xssfCell.getStringCellValue());
//                    break;
//                case STRING:
//                    value = String.valueOf(xssfCell.getStringCellValue());
//                    break;
//                case BOOLEAN:
//                    value = String.valueOf(xssfCell.getBooleanCellValue());
//                    break;
//                case FORMULA:
//                    value = String.valueOf(xssfCell.getNumericCellValue());
////                    break;
////                    Workbook wb = xssfCell.getSheet().getWorkbook();
////
////                    CreationHelper crateHelper = wb.getCreationHelper();
////
////                    FormulaEvaluator evaluator = crateHelper.createFormulaEvaluator();
////
////                    value = getCellValue(evaluator.evaluateInCell(xssfCell));
//
//                    break;
//
//            }
//        }
//        return value;
//    }


    //获取最大列
    public int getMaxCol(XSSFSheet xssfSheet){
        int maxCol = 0;
        for(int rowNum = 0; rowNum<=xssfSheet.getPhysicalNumberOfRows();rowNum++){
            //获取每一行
            XSSFRow row = xssfSheet.getRow(rowNum);

            if(row == null){
                continue;
            }
            if(row.getLastCellNum() >= maxCol){
                maxCol = row.getLastCellNum();
            }
        }
        return maxCol;
    }

    //获取最大列
    public int getMaxCol(HSSFSheet hssfSheet){
        int maxCol = 0;
        for(int rowNum = 0; rowNum<=hssfSheet.getPhysicalNumberOfRows();rowNum++){
            //获取每一行
            HSSFRow row = hssfSheet.getRow(rowNum);

            if(row == null){
                continue;
            }
            if(row.getLastCellNum() >= maxCol){
                maxCol = row.getLastCellNum();
            }
        }
        return maxCol;
    }


}
