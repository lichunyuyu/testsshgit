package com.one.javatest.exportExcel.threeMain;

import org.apache.poi.hssf.record.ExtendedFormatRecord;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import java.awt.*;
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
public class exportExcel {

    public static void main(String[] args) throws IOException {
        testExcel();
    }

    public static void testExcel() throws IOException {
        // 创建HSSFWorkbook 对象（Excel的文档对象）
        HSSFWorkbook wb = new HSSFWorkbook();
        // 建立新的sheet对象（Excel的表单）
        HSSFSheet sheet = wb.createSheet("test表");
        // 在sheet里创建第一行，参数为 行索引（excel的行），可以是 0 ~ 635535之间的任何一个
        HSSFRow row1 = sheet.createRow(0);
        // 创建单元格（excel的单元格，参数为 列索引 ，可以使 0~ 255之间的任何一个）
        HSSFCell cell = row1.createCell(0);
        // 设置单元格内容
        cell.setCellValue("学院考试成绩一览表");
        // 合并单元格 CellRangeAddress 构造参数依次表示 起始行，截止行，起始列，截止列
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));
        //在sheet里创建第二行
        HSSFRow row2 = sheet.createRow(1);
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
            HSSFRow rown = sheet.createRow(i);
            rown.createCell(0).setCellValue((String) map1.get("name"));
            rown.createCell(1).setCellValue((String) map1.get("class"));
            rown.createCell(2).setCellValue((Integer) map1.get("writeScore"));
            rown.createCell(3).setCellValue((Integer) map1.get("machineResults"));
        }

        /**
         * 单元格  画斜线 http://www.anyrt.com/blog/list/poiline.html
         * 不对
         * */

        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        HSSFClientAnchor a = new HSSFClientAnchor(0,0,1023,255,(short)5,1,(short)5,1);
        HSSFShapeGroup group = patriarch.createGroup(a);
        group.setCoordinates(0,0,320,276);
        float verticalPointsPerPixel = a.getAnchorHeightInPoints(sheet) / Math.abs(group.getY2() - group.getY1());
        EscherGraphics g = new EscherGraphics(group,wb, Color.red,verticalPointsPerPixel);
        EscherGraphics2d g2d = new EscherGraphics2d(g);
        g2d.drawLine(0,0,320,276);

        /** 不对 */
        final String text = "AB\n\n\n CD";
        HSSFCell cell5 = row1.createCell(5);
        HSSFCellStyle cellStyle = getCellFormat(wb);
        int x1 = 61, y1 = 77;
        int x2 = 132, y2 = 76;
        int x3 = 144, y3 = 31;
        int[] xys = { x1, y1, x2, y2, x3, y3 };
        drawLine(sheet, row1, 0, 0, 144, 77, xys);
        cell5.setCellValue(text);
        //cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);  // 3.10
        cellStyle.setVerticalAlignment(VerticalAlignment.TOP);  // 3.17 版本
        cell5.setCellStyle(cellStyle);


        //输出Excel 文件
        //OutputStream outputStream = response.getOutputStream();
        String outpath = "../SSH/src/com/one/javatest/exportExcel/threeMain/outFiles/";
        String outfilename = System.currentTimeMillis()+"-test2d.xls";
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
        for(int i=0;i<5;i++){
            Map map1 = new HashMap();
            map1.put("name","小明"+i);
            map1.put("class",i+"班");
            map1.put("writeScore",i+60);
            map1.put("machineResults",i+80);
            list.add(map1);
        }
        map.put("scoreList",list);
        return map;
    }



    public static final int PERCENT_WIDTH = 50;
    public static final int PERCENT_HEIGHT = 20;
    public static final float PXTOPT = 0.75f;
    // draw cell line
    private static void drawLine(HSSFSheet sheet, HSSFRow row, int i, int j, int width, int height,
                                 int[] xys) {
        int cellWidth = (int) (PERCENT_WIDTH * PXTOPT * width);
        short cellHeight = (short) (PERCENT_HEIGHT * PXTOPT * height);
        sheet.setColumnWidth(j, cellWidth);
        row.setHeight(cellHeight);

        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        HSSFClientAnchor a = new HSSFClientAnchor(0, 0, 1023, 255, (short) j, i, (short) (j), i);
        HSSFShapeGroup group = patriarch.createGroup(a);
        float verticalPointsPerPixel = a.getAnchorHeightInPoints(sheet);
        EscherGraphics g = new EscherGraphics(group, sheet.getWorkbook(), Color.black,
                verticalPointsPerPixel);
        EscherGraphics2d g2d = new EscherGraphics2d(g);

        for (int l = 0; l < xys.length; l += 2) {
            int x = (int) ((PERCENT_WIDTH * 0.75 * xys[l] / cellWidth) * 1023);
            int y = (int) ((PERCENT_HEIGHT * 0.75 * xys[l + 1] / cellHeight) * 255);
            g2d.drawLine(0, 0, x, y);
        }
    }

    public static HSSFCellStyle getCellFormat(HSSFWorkbook wb) {
        HSSFCellStyle cellStyle = wb.createCellStyle();
        // poi-3.10 版本
//        if (cellStyle.getBorderBottom() != HSSFCellStyle.BORDER_THIN) {
//            cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        }
//        if (cellStyle.getBorderLeft() != HSSFCellStyle.BORDER_THIN) {
//            cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        }
//        if (cellStyle.getBorderTop() != HSSFCellStyle.BORDER_THIN) {
//            cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        }
//        if (cellStyle.getBorderRight() != HSSFCellStyle.BORDER_THIN) {
//            cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        }
        // poi -3.17 版本
        if (cellStyle.getBorderBottom() != ExtendedFormatRecord.THIN) {
            cellStyle.setBorderBottom(BorderStyle.THIN);
        }
        if (cellStyle.getBorderLeft() != ExtendedFormatRecord.THIN) {
            cellStyle.setBorderLeft(BorderStyle.THIN);
        }
        if (cellStyle.getBorderTop() != ExtendedFormatRecord.THIN) {
            cellStyle.setBorderTop(BorderStyle.THIN);
        }
        if (cellStyle.getBorderRight()!= ExtendedFormatRecord.THIN) {
            cellStyle.setBorderRight(BorderStyle.THIN);
        }
        cellStyle.setBottomBorderColor(createPette(wb));
        cellStyle.setLeftBorderColor(createPette(wb));
        cellStyle.setRightBorderColor(createPette(wb));
        cellStyle.setTopBorderColor(createPette(wb));
        return cellStyle;
    }

    public static short createPette(HSSFWorkbook wb) {
        short petteIndex = 0;
        Color rgb = new Color(0x00, 0x00, 0x00);
        HSSFPalette palette = wb.getCustomPalette();
        palette.setColorAtIndex(petteIndex, (byte) rgb.getRed(), (byte) rgb.getGreen(), (byte) rgb
                .getBlue());
        return petteIndex;
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