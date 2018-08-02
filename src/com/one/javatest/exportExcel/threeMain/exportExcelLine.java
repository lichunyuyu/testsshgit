package com.one.javatest.exportExcel.threeMain;

import org.apache.poi.hssf.usermodel.*;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by vtstar on 2018/6/26.
 */
public class exportExcelLine {
    public static final int PERCENT_WIDTH = 50;
    public static final int PERCENT_HEIGHT = 20;

    public static final float PXTOPT = 0.75f;

    public static void main(String[] args) throws Exception {
        String outpath = "../SSH/src/com/one/javatest/exportExcel/threeMain/outFiles/";
        String outfilename = System.currentTimeMillis()+"-testLine.xls";
        String outfile = outpath+outfilename;
        OutputStream fos = new FileOutputStream(outfile);

        //FileOutputStream fos = new FileOutputStream("D:/line.xls");
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("line");

        HSSFRow row = sheet.createRow(0);
        row.setHeightInPoints(77 * PXTOPT);

        final String text = "                       AB\n\n\n CD";
        HSSFCell cell = row.createCell(0);
        HSSFCellStyle cellStyle = getCellFormat(wb);
        int x1 = 61, y1 = 77;
        int x2 = 132, y2 = 76;
        int x3 = 144, y3 = 31;
        int[] xys = { x1, y1, x2, y2, x3, y3 };
        drawLine(sheet, row, 0, 0, 144, 77, xys);
        cell.setCellValue(text);
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
        cell.setCellStyle(cellStyle);

        cell = row.createCell(1);
        cell.setCellStyle(cellStyle);

        cell = row.createCell(2);
        cell.setCellStyle(cellStyle);

        cell = row.createCell(3);
        cell.setCellStyle(cellStyle);

        row = sheet.createRow(1);
        row.setHeightInPoints(83 * PXTOPT);
        cell = row.createCell(0);
        cell.setCellStyle(cellStyle);

        cell = row.createCell(1);
        cell.setCellStyle(cellStyle);

        cell = row.createCell(2);
        cell.setCellStyle(cellStyle);

        cell = row.createCell(3);
        cell.setCellStyle(cellStyle);

        int[] xys1 = { 112, 83 };
        drawLine(sheet, row, 1, 3, 110, 83, xys1);

        wb.write(fos);
    }

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
        if (cellStyle.getBorderBottom() != HSSFCellStyle.BORDER_THIN) {
            cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        }
        if (cellStyle.getBorderLeft() != HSSFCellStyle.BORDER_THIN) {
            cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        }
        if (cellStyle.getBorderTop() != HSSFCellStyle.BORDER_THIN) {
            cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        }
        if (cellStyle.getBorderRight() != HSSFCellStyle.BORDER_THIN) {
            cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
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
