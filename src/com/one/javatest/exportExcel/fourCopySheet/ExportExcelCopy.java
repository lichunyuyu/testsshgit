package com.one.javatest.exportExcel.fourCopySheet;

import com.ts.core.util.StringUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vtstar on 2018/8/13.
 */
public class ExportExcelCopy {

    public static void main(String args[]){
        ExportExcelCopy exportExcelCopy = new ExportExcelCopy();
        exportExcelCopy.exportExcel();
    }
    /**
     * 导出Excel
     * */
    public void exportExcel(){
        List<Map> mapList = selectDatas();
        OutputStream outputStream = null;
        InputStream inputStream = null;
        FileOutputStream outputStream1 = null;
        XSSFWorkbook workbook = null;

        String packagePath = "../SSH/src/com/one/javatest/exportExcel";
        String templatePath = packagePath+"/resource";
        String outPath = packagePath+"/fourceCopySheet/outFiles";

        String outPaths = outPath.replaceAll("/","\\\\");
        File pathFile = new File(outPaths);
        if(pathFile.exists()==false){
            pathFile.mkdirs();
        }
        String templateFileName = templatePath+"/"+"testcopySheet.xlsx";

        try{
            inputStream = new FileInputStream(new File(templateFileName));
            workbook = write(inputStream,mapList);

            String outFileName = "testCopyExcel-"+System.currentTimeMillis()+".xlsx";
            String outFile = outPaths+"\\"+outFileName;
            outFile = outFile.replaceAll("\\\\","/");
            outputStream = new FileOutputStream(outFile);
            workbook.write(outputStream);

        }catch(FileNotFoundException f){
            f.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                if(inputStream!=null){
                    inputStream.close();
                }
                if(outputStream!=null){
                    outputStream.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }


    public XSSFWorkbook write(InputStream inputStream, List<Map> mapList) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        //XSSFWorkbook newworkbook = new XSSFWorkbook(inputStream);
        int size = mapList.size();
        XSSFSheet sheet0 = workbook.getSheetAt(0);
        for(int num = 0;num<size-1;num++){
            String numStr = String.valueOf(num+2);
            XSSFSheet newsheet = workbook.createSheet("第"+num+"页");

            if(workbook.getNumberOfSheets() > 1){
                workbook.removeSheetAt(1);
            }

            //单元格合并
            CellRangeAddress region = null;
            XSSFSheet sheet2 = workbook.createSheet(sheet0.getSheetName() + "第"+num+"页");
            for (int i = 0; i < sheet0.getNumMergedRegions(); i++) {
                region = sheet0.getMergedRegion(i);
                if ((region.getFirstColumn() >= sheet0.getFirstRowNum())
                        && (region.getLastRow() <= sheet0.getLastRowNum())) {
                    sheet2.addMergedRegion(region);
                }
            }
            //复制内容
            Row rowFrom = null;
            Row rowTo = null;
            Cell cellFrom = null;
            Cell cellTo = null;
            for (int i = sheet0.getFirstRowNum(); i < sheet0.getLastRowNum(); i++) {
                rowFrom = sheet0.getRow(i);
                if (null == rowFrom){
                    continue;
                }

                rowTo = sheet2.createRow(i);
                rowTo.setHeight(rowFrom.getHeight());
                for (int j = 0; j < rowFrom.getLastCellNum(); j++) {
                    sheet2.setColumnWidth(j, sheet0.getColumnWidth(j));
                    if(null != sheet0.getColumnStyle(j)){
                        sheet2.setDefaultColumnStyle(j, sheet0.getColumnStyle(j));
                    }

                    cellFrom = rowFrom.getCell(j);
                    if (null == cellFrom){
                        continue;
                    }

                    cellTo = rowTo.createCell(j);
                    cellTo.setCellStyle(cellFrom.getCellStyle());
                    cellTo.setCellType(cellFrom.getCellType());

                    if(Cell.CELL_TYPE_STRING == cellFrom.getCellType()){
                        cellTo.setCellValue(cellFrom.getStringCellValue());
                    }else if(Cell.CELL_TYPE_NUMERIC == cellFrom.getCellType()){
                        cellTo.setCellValue(cellFrom.getNumericCellValue());
                    }
                }
            }

            sheet2.setDisplayGridlines(true);//


//            //设置打印参数
//            newsheet.setMargin(HSSFSheet.TopMargin,workbook.getMargin(HSSFSheet.TopMargin));// 页边距（上）
//            newsheet.setMargin(HSSFSheet.BottomMargin,workbook.getMargin(HSSFSheet.BottomMargin));// 页边距（下）
//            newsheet.setMargin(HSSFSheet.LeftMargin,workbook.getMargin(HSSFSheet.LeftMargin) );// 页边距（左）
//            newsheet.setMargin(HSSFSheet.RightMargin,workbook.getMargin(HSSFSheet.RightMargin));// 页边距（右

//            HSSFPrintSetup ps = newsheet.getPrintSetup();
//            ps.setLandscape(false); // 打印方向，true：横向，false：纵向(默认)
//            ps.setVResolution((short)600);
//            ps.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE); //纸张类型

//            SheetFunc.copyRows(workbook, 0, num+1,0 , 46, 0);//复制

            // poiutilCopy  的copy工具
//            PoiUtil.copyCell(sheet,newsheet,workbook,workbook);
//            workbook.getSheetAt(num+1).setColumnWidth((short)0, (short)2400);//256,31.38

        }

        for(int num = 0;num<size;num++){
            System.out.println("num....."+num);
            XSSFSheet sheet = workbook.getSheetAt(num);
            //公共界面CreationHelper
            CreationHelper helper = workbook.getCreationHelper();

            Configuration cfg = new Configuration();
//          Map root= JspUtil.getRequestModel(requestContext.getRequest());
            Map root = mapList.get(num);
            Map mapTeacher = (Map) root.get("teacher");
            System.out.println("mapTeacher======"+mapTeacher);
            List<Map> mapListStudent = new ArrayList<Map>();
            mapListStudent = (List<Map>) root.get("students");
            System.out.println("mapListStudent======="+mapListStudent);


            int deleteRow=-1;
            for(int rowIndex = 0; rowIndex <= sheet.getLastRowNum();rowIndex++){
                XSSFRow row = sheet.getRow(rowIndex);  // 行

                if(row==null){
                    continue;
                }
                if(row!=null) {
                    //System.out.println("sheet.getRow.null======="+row);
                    //continue;
                    XSSFCell firstCell = row.getCell(0);  // 单元格
                    //System.out.println("firstCell="+firstCell);
                    boolean isDetail=false;
                    if(firstCell!=null){
                        if(firstCell.getCellType()== XSSFCell.CELL_TYPE_STRING){
                            String value=firstCell.getStringCellValue();

                            if(firstCell.getStringCellValue().indexOf("$DetailRow")==0){
                                firstCell.setCellValue(value.substring("$DetailRow".length()));

                                isDetail=true;
                                deleteRow=rowIndex;
                                XSSFRow groupRow = sheet.getRow(rowIndex);

                                for(int i=0;i<mapListStudent.size();i++){
                                    Map groupDtl = mapListStudent.get(i);
                                    rowIndex++;
                                    root.put("student",groupDtl );    //  空着的 表合格 放 空格（不能不写 ）
                                    sheet.shiftRows(rowIndex, sheet.getLastRowNum(), 1,true,false);
                                    XSSFRow copyRow=sheet.createRow(rowIndex);
                                    copyRow(sheet,copyRow,row,root,cfg);
                                }

                            } else if(firstCell.getStringCellValue().indexOf("$DetailImageRow")==0){
                                firstCell.setCellValue(value.substring("$DetailImageRow".length()));

                            }
                        }
                    }
                    if(isDetail==false) {
                        for (int cellnum = 0; cellnum < row.getLastCellNum(); cellnum++) {
                            XSSFCell cell = row.getCell(cellnum);

                            if(cell != null){
                                parseCell(root, cell, cfg);
                            }else{
                                continue;
                            }
                        }
                    }

                }

            }

            //System.out.println("deleteRowdeleteRow==="+deleteRow+",sheet.getLastRowNum()==="+sheet.getLastRowNum());
            sheet.shiftRows(deleteRow+1, sheet.getLastRowNum(),-1,true,false);

            deleteMergeRegion(sheet,deleteRow+1);
        }

        return workbook;
    }
    /**
     * 数据源
     * */
    public List<Map> selectDatas(){

        List<Map> mapList = new ArrayList<Map>();
        for(int j=0;j<4;j++){
            Map map = new HashMap();
            List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
            for(int i=0;i<5;i++){
                Map map1 = new HashMap();
                map1.put("index",i+1);
                map1.put("name","小明-"+i+"-j-"+j);
                map1.put("score",80+i);
                list.add(map1);
            }
            map.put("students",list);

            Map teacherMap = new HashMap();
            teacherMap.put("name","老师-"+j);

            map.put("teacher",teacherMap);

            mapList.add(map);
        }
        return mapList;
    }


    private void copyRow(XSSFSheet sheet, XSSFRow newRow, XSSFRow row, Map root , Configuration cfg){
        newRow.setHeight(row.getHeight());
        for(int cellnum = row.getFirstCellNum(); cellnum < row.getLastCellNum(); cellnum++){
            XSSFCell cell = row.getCell(cellnum);
            XSSFCell newCell=newRow.createCell(cellnum);
            copyCell(cell,newCell);
            parseCell(root,newCell,cfg);

        }
        for(int i=0;i<sheet.getNumMergedRegions();i++){
            CellRangeAddress region =sheet.getMergedRegion(i);
            if(region.getFirstRow()==row.getRowNum()){
                CellRangeAddress newRegion=new CellRangeAddress(newRow.getRowNum(),newRow.getRowNum(),region.getFirstColumn(),region.getLastColumn());
                sheet.addMergedRegion(newRegion);
            }
        }
    }
    private   void copyCell(XSSFCell source, XSSFCell target) {
        target.setCellStyle(source.getCellStyle());
        target.setCellType(XSSFCell.CELL_TYPE_STRING);  //default to string...
        if(source.getCellType() != XSSFCell.CELL_TYPE_FORMULA)
            target.setCellType(source.getCellType());
        switch(source.getCellType()) {
            case (XSSFCell.CELL_TYPE_BOOLEAN):
                target.setCellValue(source.getBooleanCellValue());
                break;
            case (XSSFCell.CELL_TYPE_ERROR):
                target.setCellValue(source.getErrorCellValue());
                break;
            case (XSSFCell.CELL_TYPE_NUMERIC):
                target.setCellValue(source.getNumericCellValue());
                break;
            case (XSSFCell.CELL_TYPE_STRING):
                target.setCellValue(source.getStringCellValue());
                break;
            case (XSSFCell.CELL_TYPE_FORMULA):
            default:
                break;
        };
    }
    private void parseCell(Map root ,XSSFCell cell ,Configuration cfg ){
        if(cell.getCellType()== XSSFCell.CELL_TYPE_STRING){
            String text=cell.getRichStringCellValue().getString();
            //System.out.println("texttexttexttexttexttexttext==="+text);
            if(StringUtils.isNoValue(text)) return;

            freeMarkerParseCellText(root,cell,cfg);
        }
    }

    private void freeMarkerParseCellText(Map root ,XSSFCell cell ,Configuration cfg ){
        String text=cell.getRichStringCellValue().getString();
        try {
            Template t = new Template("",text, cfg);
            StringWriter out = new StringWriter();
            t.process(root, out);
            out.flush();
            cell.setCellValue(new XSSFRichTextString(out.getBuffer().toString()));

            //System.out.println("cell.getCellValue==="+cell.getRichStringCellValue().getString());// 报告编号 report No testnumber01
            out.close();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void deleteMergeRegion(XSSFSheet sheet,int rowIndex){
        for(int i=0;i<sheet.getNumMergedRegions();i++){
            //System.out.println("sheet.getNumMergedRegions()=="+sheet.getNumMergedRegions());
            CellRangeAddress region =sheet.getMergedRegion(i);
            if(region.getFirstRow()==rowIndex-1){
                sheet.removeMergedRegion(i);
            }
        }
    }
}
