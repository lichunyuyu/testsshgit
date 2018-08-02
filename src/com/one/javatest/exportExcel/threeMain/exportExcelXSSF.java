package com.one.javatest.exportExcel.threeMain;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vtstar on 2018/6/13.
 */
public class exportExcelXSSF {

    public static void main(String[] args) throws IOException {
        testExcel();
    }

    public static void testExcel() throws IOException {
        // 创建HSSFWorkbook 对象（Excel的文档对象）
        XSSFWorkbook wb = new XSSFWorkbook();
        /**
         * 设置样式
         * */
        CellStyle style = wb.createCellStyle();
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);//居中
        style.setBorderBottom(CellStyle.BORDER_DOUBLE);// 下  双
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(CellStyle.BORDER_THICK);// 上 最粗
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        // solid 填充色 foreground  前景色
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  //设置前景填充样式
        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);// 设置前景填充色

        CellStyle style2 = wb.createCellStyle();
        style2.setBorderRight(CellStyle.BORDER_THIN);// 右 细
        style2.setRightBorderColor(IndexedColors.RED.getIndex());

        CellStyle style3 = wb.createCellStyle();
        style3.setBorderLeft(CellStyle.BORDER_DASH_DOT);
        style3.setLeftBorderColor(IndexedColors.GREEN.getIndex());// 红左

        CellStyle style6 = wb.createCellStyle();
        style6.setBorderTop(CellStyle.BORDER_DASH_DOT_DOT);
        style6.setTopBorderColor(IndexedColors.YELLOW.getIndex());

        CellStyle style7 = wb.createCellStyle();
        style7.setBorderBottom(CellStyle.BORDER_DOTTED);
        style7.setBottomBorderColor(IndexedColors.BLACK.getIndex());

        CellStyle style8 = wb.createCellStyle();
        style8.setBorderRight(CellStyle.BORDER_HAIR);
        style8.setRightBorderColor(IndexedColors.RED.getIndex());

        CellStyle style9 = wb.createCellStyle();
        style9.setBorderLeft(CellStyle.BORDER_MEDIUM);  // 中粗
        style9.setBottomBorderColor(IndexedColors.GREEN.getIndex());

        CellStyle style4 = wb.createCellStyle();
        style4.setBorderTop(CellStyle.BIG_SPOTS);
        style4.setTopBorderColor(IndexedColors.YELLOW.getIndex());

        CellStyle style5 = wb.createCellStyle();
        style5.setBorderBottom(CellStyle.THICK_BACKWARD_DIAG);
        style5.setBottomBorderColor(IndexedColors.BLACK.getIndex());

        CellStyle style10 = wb.createCellStyle();
        style10.setBorderRight(CellStyle.THIN_BACKWARD_DIAG);
        style10.setRightBorderColor(IndexedColors.BLUE.getIndex());

        CellStyle style11 = wb.createCellStyle();
        style11.setBorderLeft(CellStyle.THICK_FORWARD_DIAG);
        style11.setLeftBorderColor(IndexedColors.GREEN.getIndex());

        CellStyle style12 = wb.createCellStyle();
        style12.setBorderTop(CellStyle.THICK_HORZ_BANDS);
        style12.setTopBorderColor(IndexedColors.YELLOW.getIndex());


        CellStyle style13 = wb.createCellStyle();
        style13.setBorderBottom(CellStyle.THICK_VERT_BANDS);
        style13.setBottomBorderColor(IndexedColors.BLACK.getIndex());

        CellStyle style14 = wb.createCellStyle();
        style14.setBorderRight(CellStyle.THIN_HORZ_BANDS);
        style14.setBottomBorderColor(IndexedColors.BLUE.getIndex());

        CellStyle style15 = wb.createCellStyle();
        style15.setBorderLeft(CellStyle.THIN_VERT_BANDS);
        style15.setRightBorderColor(IndexedColors.GREEN.getIndex());

//        CellStyle style16 = wb.createCellStyle();
//        style16.setBorderTop(CellStyle.THIN_FORWARD_DIAG);
//        style16.setTopBorderColor(IndexedColors.YELLOW.getIndex());

        // 建立新的sheet对象（Excel的表单）
        XSSFSheet sheet = wb.createSheet("test表");
        // 在sheet里创建第一行，参数为 行索引（excel的行），可以是 0 ~ 635535之间的任何一个
        XSSFRow row1 = sheet.createRow(0);
        // 创建单元格（excel的单元格，参数为 列索引 ，可以使 0~ 255之间的任何一个）
        XSSFCell cell = row1.createCell(0);
        // 设置单元格内容
        cell.setCellValue("学院考试成绩一览表");
        // 合并单元格 CellRangeAddress 构造参数依次表示 起始行，截止行，起始列，截止列
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));
        //在sheet里创建第二行
        XSSFRow row2 = sheet.createRow(1);
        //创建单元格并设置单元格内容
        row2.createCell(0).setCellValue("姓名");
        row2.createCell(1).setCellValue("班级");
        row2.createCell(2).setCellValue("笔试成绩");
        row2.createCell(3).setCellValue("机试成绩");
        // 在sheet中创建第n 行
        int i = 1;
        Map map = selectData();
        List<Map<String,Object>> list = (List<Map<String, Object>>) map.get("scoreList");
        for(int j=0;j<list.size();j++){
            Map map1 = list.get(j);
            i++;
            XSSFRow rown = sheet.createRow(i);
            rown.createCell(0).setCellValue((String) map1.get("name"));
            if(j==0){
                rown.getCell(0).setCellStyle(style);
            }
            if(j==2){
                rown.getCell(0).setCellStyle(style2);
            }
            if(j==4){
                rown.getCell(0).setCellStyle(style3);
            }
            if(j==6){
                rown.getCell(0).setCellStyle(style4);
            }
            rown.createCell(1).setCellValue((String) map1.get("class"));
            if(j==1){
                rown.getCell(1).setCellStyle(style5);
            }
            if(j==3){
                rown.getCell(1).setCellStyle(style6);
            }
            if(j==5){
                rown.getCell(1).setCellStyle(style7);
            }
            if(j==7){
                rown.getCell(1).setCellStyle(style8);
            }
            rown.createCell(2).setCellValue((Integer) map1.get("writeScore"));
            if(j==0){
                rown.getCell(2).setCellStyle(style9);
            }
            if(j==2){
                rown.getCell(2).setCellStyle(style10);
            }
            if(j==4){
                rown.getCell(2).setCellStyle(style11);
            }
            if(j==6){
                rown.getCell(2).setCellStyle(style12);
            }
            rown.createCell(3).setCellValue((Integer) map1.get("machineResults"));
            if(j==1){
                rown.getCell(3).setCellStyle(style13);
            }
            if(j==3){
                rown.getCell(3).setCellStyle(style14);
            }
            if(j==5){
                rown.getCell(3).setCellStyle(style15);
            }
//            if(j==7){
//                rown.getCell(3).setCellStyle(style16);
//            }

        }

        //设置自动列宽
        System.out.println("sheet.getLastRowNum()..."+sheet.getLastRowNum()); // 8
        for (int j = 0; j < sheet.getLastRowNum()-1; j++) {
            sheet.autoSizeColumn(j+1);
            // sheet.setColumnWidth(i+1,sheet.getColumnWidth(i+1)*17/10);
        }


        //输出Excel 文件
        //OutputStream outputStream = response.getOutputStream();
        String outpath = "../SSH/src/com/one/javatest/exportExcel/threeMain/outFiles/";
        String outfilename = System.currentTimeMillis()+"-test.xlsx";
        String outfile = outpath+outfilename;
        OutputStream outputStream = new FileOutputStream(outfile);
//        response.reset(); // 清除空白行
//        //response.setHeader("Content-disposition", "attachment; filename=details.xls");
//        response.setHeader("Content-disposition", "attachment; filename="+System.currentTimeMillis()+"-test.xlsx");
//        response.setContentType("application/msexcel");
        wb.write(outputStream);
        outputStream.close();

    }

    public static Map<String,Object> selectData(){
        Map map = new HashMap();
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        for(int i=0;i<7;i++){
            Map map1 = new HashMap();
            map1.put("name","小明"+i);
            map1.put("class",i+"班");
            map1.put("writeScore",i+60);
            map1.put("machineResults",i+80);
            if(i==6){
                map1.put("machineResults",i+80);
            }
            list.add(map1);
        }
        map.put("scoreList",list);
        return map;
    }
}


/**
 *  response.reset() 与response.resetbuffer使用场景
 getResponse的getWriter()方法

 getResponse的getWriter()方法连续两次输出流到页面的时候，第二次的流会包括第一次的流，所以可以使用response.reset或者resetBuffer的方法。

 reset():
 response.reset()的使用有一个条件受限：response的任何打开流关闭之后都不能再reset .

 resetBuffer():
 可以看到resetBuffer方法与reset方法的区别是，头和状态码没有清除。

 一般用于下载文件excel操作时：
 空白行的出现原因，jsp代码编译后产生。就是有jsp生成html文件的时候，html文件内部会出现很多空白行。下载后的文件内的空白行也是这样产生的。
 因此，需要 response.reset() 来清除首部的空白行。
 * */