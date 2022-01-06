package com.liu.project0811.controller;

import com.liu.project0811.pojo.ExcelCell;
import com.liu.project0811.pojo.SheetInfo;
import com.liu.project0811.pojo.TestExcel;
import com.liu.project0811.utils.ExcelFunctions;
import com.liu.project0811.utils.JsonMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "excel表数据管理")
@RequestMapping("excelDate")
@CrossOrigin
public class ExcelController {


    @ApiOperation(value = "获取表格数据",httpMethod = "POST")
    @RequestMapping(value = "getExcelDate",method = RequestMethod.POST)
    public JsonMsg testGetListFromExcel(@RequestParam String fileUrl,@RequestParam  String sheetName) throws IOException {

        JsonMsg jsonMsg = new JsonMsg();
        ExcelFunctions excelFunctions = new ExcelFunctions();
        File file = new File(fileUrl);
        InputStream is = new FileInputStream(file);

        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        XSSFFormulaEvaluator evaluator = new XSSFFormulaEvaluator(xssfWorkbook);
        evaluator.evaluateAll();
        XSSFSheet xssfSheet = xssfWorkbook.getSheet(sheetName);
        if (xssfSheet == null) {
            jsonMsg.setMsg("文件不存在。");
            jsonMsg.setCode("400");
            return jsonMsg;
        }
        int maxCol = excelFunctions.getMaxCol(xssfSheet);
        String[][] datas = new String[xssfSheet.getPhysicalNumberOfRows()][maxCol];
        List<ExcelCell> list = new ArrayList<ExcelCell>();

        //遍历行row
        for(int rowNum = 0; rowNum < xssfSheet.getPhysicalNumberOfRows();rowNum++){
            //获取每一行
            XSSFRow row = xssfSheet.getRow(rowNum);
            if(row == null){
                continue;
            }
            //遍历列cell
            for(int cellNum = 0; cellNum < maxCol;cellNum++){
                //获取每一列
                Cell cell = row.getCell(cellNum);
                ExcelCell record = new ExcelCell();
                record.setRow(row.getRowNum());
                record.setCol(cellNum);
                record.setContent(excelFunctions.getCellValues(cell));
                list.add(record);

                datas[row.getRowNum()][cellNum] = excelFunctions.getCellValues(cell);
            }
            jsonMsg.setObject(datas);
        }


        jsonMsg.setMsg("获取成功");
        jsonMsg.setCode("200");
        jsonMsg.setInfoList(list);


        return jsonMsg;

    }

    //XLS
//    private TestExcel convertRowToSettlePay(HSSFRow hssfRow) {
//        TestExcel record = new TestExcel();
//        for (int i = 0; i < hssfRow.getLastCellNum(); i++) {
//            String b = getCellValue(hssfRow.getCell(i));
//            System.out.println("公式值 = " + b);
//        }
//
//        //将获取的值填入  TestExcel对象  返回
//        return record;
//    }
//
//    private String getCellValue(HSSFCell hssfCell) {
//        String value = "";
//        if (null == hssfCell) {
//            value = null;
//        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
//            // 返回布尔类型的值
//            value = String.valueOf(hssfCell.getBooleanCellValue());
//        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC || hssfCell.getCellType() == hssfCell.CELL_TYPE_FORMULA) {
//            // 返回数值类型的值
//            value = String.valueOf(hssfCell.getNumericCellValue());
//        }
//        return value;
//    }


    //XLSX
//    private TestExcel convertRowToSettlePay2(XSSFRow xssfRow) {
//        TestExcel record = new TestExcel();
//        for (int i = 0; i < xssfRow.getLastCellNum(); i++) {
//            String b = getCellValue2(xssfRow.getCell(i));
//            System.out.println("公式值 = " + b);
//        }
//
//        //将获取的值填入  TestExcel对象  返回
//        return record;
//    }

//    private String getCellValue2(XSSFCell xssfCell) {
//        String value = "";
//        if (null == xssfCell) {
//            value = null;
//        } else  {
//            // 返回数值类型的值
//            value = String.valueOf(xssfCell.getNumericCellValue());
//        }
//        return value;
//    }

//    @ApiOperation(value = "lxj写excel",httpMethod = "POST")
//    @RequestMapping(value = "lxjwrite",method = RequestMethod.POST)
//    public void lxjwrite(@RequestBody SheetInfo sheetInfo) throws IOException, WriteException {
//
//        File file = new File("src/main/files/过渡文件.xls");
//        // 创建一个工作簿
//        WritableWorkbook iss = Workbook.createWorkbook(file);
//
//        // 创建工作表
//        WritableSheet writableSheet = iss.createSheet(sheetInfo.getSheetName(),sheetInfo.getSheetSort());
//
//        for(int row = 0;row<sheetInfo.getInfo().length;row++){
//            for(int col = 0;col<sheetInfo.getInfo()[0].length;col++){
//                writableSheet.addCell(new Label(col,row,sheetInfo.getInfo()[row][col]));
//            }
//        }
//        iss.write();
//        iss.close();
//
//
//        Workbook wb = null;
//        WritableWorkbook wwb = null;
//        try {
//            File is = new File("src/main/files/过渡文件.xls");
//            File os = new File("src/main/files/模板文件.xls");
//            if (!os.isFile())// 如果指定文件不存在，则新建该文件
//                os.createNewFile();
//
//            wb = Workbook.getWorkbook(is);// 获取数据源in.xls工作簿对象
//            wwb = Workbook.createWorkbook(os, wb);// 在原有工作簿out.xls上追加数据
//            // wwb = Workbook.createWorkbook(os);//区别于上一行代码，创建一个新的写入工作簿
//            if (wb != null && wwb != null) {
//
//                WritableSheet sheet = wwb.getSheet(0);// 获取out.xls第一个sheet
//                WritableCell cell = sheet.getWritableCell(2, 4);// 获取out.xls要写入数据的单元格
//
//                Sheet[] sheets = wb.getSheets();// 获取数据源in.xls的sheets
//                Cell[] cells = sheets[0].getRow(1);// 获取in.xls第一个sheet的第二行
//                if (cell.getType() == CellType.LABEL) {
//                    Label l = (Label) cell;
//                    l.setString(cells[1].getContents());// 将第二个单元格写入out.xls的第三列第五行
//                }
//                wwb.write();
//                System.out.println("工作簿写入数据成功！");
//            }
//            wwb.close();// 关闭
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            wb.close();
//        }
//
//
//    }
//
//    @ApiOperation(value = "lxj读excel",httpMethod = "POST")
//    @RequestMapping(value = "lxjread",method = RequestMethod.POST)
//    public JsonMsg lxjread(@RequestParam String fileUrl,
//                           @RequestParam String sheetName) throws IOException, BiffException {
//        JsonMsg jsonMsg = new JsonMsg();
//        File file = new File(fileUrl);
//        //获得工作簿
//        Workbook is = Workbook.getWorkbook(file);
//        //获得所有sheet表
//        Sheet[] sheets = is.getSheets();
//
//
//        ArrayList<ExcelCell> list = new ArrayList<>();
//
//        if(sheets != null){
//            for(Sheet sheet : sheets){
//                if(sheet.getName().equals(sheetName)){
//                    // 行数
//                    int rows = sheet.getRows();
//                    // 列数
//                    int cols = sheet.getColumns();
//                    // 数据数组
//                    String[][] datas = new String[rows][cols];
//                    // 数据
//                    for(int row = 0;row<rows;row++){
//                        for(int col = 0;col<cols;col++){
//                            ExcelCell excelCell = new ExcelCell();
//                            datas[row][col] = sheet.getCell(col,row).getContents();
//                            System.out.printf("%10s",sheet.getCell(col,row).getContents());
//
//                            excelCell.setRow(row);
//                            excelCell.setCol(col);
//                            excelCell.setContent(datas[row][col]);
//                            list.add(excelCell);
//                        }
//                        System.out.println();
//                    }
//
//                    jsonMsg.setMsg("获取成功");
//                    jsonMsg.setCode("200");
//                    jsonMsg.setObject(datas);
//                    is.close();
//                }
//
//            }
//            jsonMsg.setInfoList(list);
//        } else {
//            jsonMsg.setMsg("获取失败");
//            jsonMsg.setCode("400");
////            jsonMsg.setObject(datas);
//        }
//
//        return jsonMsg;
//    }




}