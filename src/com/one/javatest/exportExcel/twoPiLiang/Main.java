package com.one.javatest.exportExcel.twoPiLiang;

/**
 * Created by vtstar on 2018/4/16.
 */
public class Main {
//
//
//        /**
//         *
//         * <p>Description: 根据根节点获取嵌套科目树</p>
//         * @param root 根节点，只需itemCode
//         * @return 嵌套科目树
//         */
//        public List<Subject> getSubjectListByRoot(Subject root){
//            //定义一个空的科目列表
//            List<Subject> subjectTree = new ArrayList<Subject>();
//            //查询出根节点的具体信息
//            root = this.subjectManager.searchSubjectByCode(root.getItemCode());
//            //将根节点插入到嵌套科目树里，因为迭代出来的科目树是没有根节点的
//            subjectTree.add(root);
//            //调用迭代器
//            subjectTree = SubjectIteration(root, subjectTree);
//            return subjectTree;
//        }
//
///**
// *
// * <p>Description: 嵌套科目树迭代器</p>
// * @param subject 父节点
// * @param subjectTree 科目树
// * @return 嵌套科目树
// */
//        public List<Subject> SubjectIteration(Subject subject, List<Subject> subjectTree) {
//            //根据父节点查子节点
//            List<Subject> subjects = this.subjectManager.searchListSubjectByParent(subject);
//            if (subjects != null) {
//                //遍历子节点并迭代
//                for (Subject subjectResult : subjects) {
//                    //将节点插入到嵌套科目树里
//                    subjectTree.add(subjectResult);
//                    SubjectIteration(subjectResult,subjectTree);
//                }
//            }
//            return subjectTree;
//        }
//
//
//
//        /**
//         *
//         * <p>Description: 科目树导出</p>
//         * @param request
//         * @param response
//         * @return Excel文件
//         * @throws Exception
//         */
//        public ModelAndView downloadExcel(HttpServletRequest request,
//                HttpServletResponse response) throws Exception {
//            //文件配置
//            String fileName = new String(("科目导出文件.xls").getBytes("UTF-8"), "iso-8859-1");
//            response.reset();
//            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
//            response.setContentType("application/vnd.ms-excel;charset=utf-8");
//            //创建POI-workbook文件
//            XSSFWorkbook workbook = new XSSFWorkbook();
//            XSSFSheet sheet = workbook.createSheet("科目树");
//            //插入抬头
//            PoiUtil.addRowSXSSF(sheet, 0, new String[] { "说明：", "level1", "level2", "level3", "level4", "level5", "level6",
//                    "level7", "level8" });
//            //调整sheet样式
//            //红
//            CellStyle styleRed = workbook.createCellStyle();
//            styleRed.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
//            styleRed.setFillForegroundColor(IndexedColors.RED.index);
//            //绿
//            CellStyle styleGreen = workbook.createCellStyle();
//            styleGreen.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
//            styleGreen.setFillForegroundColor(IndexedColors.LIME.index);
//            //灰
//            CellStyle styleGrey = workbook.createCellStyle();
//            styleGrey.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
//            styleGrey.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
//            //白
//            CellStyle styleWhite = workbook.createCellStyle();
//            styleWhite.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
//            styleWhite.setFillForegroundColor(IndexedColors.WHITE.index);
//            //金黄
//            CellStyle styleGold = workbook.createCellStyle();
//            styleGold.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
//            styleGold.setFillForegroundColor(IndexedColors.GOLD.index);
//            //黄
//            CellStyle styleYellow = workbook.createCellStyle();
//            styleYellow.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
//            styleYellow.setFillForegroundColor(IndexedColors.YELLOW.index);
//            //亮黄
//            CellStyle styleLight = workbook.createCellStyle();
//            styleLight.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
//            styleLight.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.index);
//            //柠檬
//            CellStyle styleLemon = workbook.createCellStyle();
//            styleLemon.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
//            styleLemon.setFillForegroundColor(IndexedColors.LEMON_CHIFFON.index);
//            //设置抬头颜色
//            XSSFRow row = sheet.getRow(0);
//            Cell cell = row.getCell(1);
//            cell.setCellStyle(styleRed);
//            cell = row.getCell(2);
//            cell.setCellStyle(styleGreen);
//            cell = row.getCell(3);
//            cell.setCellStyle(styleGrey);
//            cell = row.getCell(4);
//            cell.setCellStyle(styleWhite);
//            cell = row.getCell(5);
//            cell.setCellStyle(styleGold);
//            cell = row.getCell(6);
//            cell.setCellStyle(styleYellow);
//            cell = row.getCell(7);
//            cell.setCellStyle(styleLight);
//            cell = row.getCell(8);
//            cell.setCellStyle(styleLemon);
//            //查询Excel用科目树
//            List<Subject> subjectTree = new ArrayList<Subject>();
//            Subject root = new Subject();
//            root.setItemCode("1001");
//            subjectTree = getSubjectListByRoot(root);
//            //插入数据
//            int index = 0;
//            for(Subject subject : subjectTree){
//                index = index + 1;
//                Row dataRow = sheet.createRow(index);
//                String lv = subject.getLevel();
//                if(lv.equals("1")){
//                    Cell dataCell = dataRow.createCell(0);
//                    dataCell.setCellValue(subject.getItemName());
//                    dataCell.setCellStyle(styleRed);
//                }
//                else if(lv.equals("2")){
//                    Cell dataCell = dataRow.createCell(0);
//                    dataCell.setCellValue(subject.getItemName());
//                    dataCell.setCellStyle(styleGreen);
//                }
//                else if(lv.equals("3")){
//                    Cell dataCell = dataRow.createCell(0);
//                    dataCell.setCellValue(subject.getItemName());
//                    dataCell.setCellStyle(styleGrey);
//                }
//                else if(lv.equals("4")){
//                    Cell dataCell = dataRow.createCell(0);
//                    dataCell.setCellValue(subject.getItemName());
//                    dataCell.setCellStyle(styleWhite);
//                }
//                else if(lv.equals("5")){
//                    Cell dataCell = dataRow.createCell(0);
//                    dataCell.setCellValue(subject.getItemName());
//                    dataCell.setCellStyle(styleGold);
//                }
//                else if(lv.equals("6")){
//                    Cell dataCell = dataRow.createCell(0);
//                    dataCell.setCellValue(subject.getItemName());
//                    dataCell.setCellStyle(styleYellow);
//                }
//                else if(lv.equals("7")){
//                    Cell dataCell = dataRow.createCell(0);
//                    dataCell.setCellValue(subject.getItemName());
//                    dataCell.setCellStyle(styleLight);
//                }
//                else if(lv.equals("8")){
//                    Cell dataCell = dataRow.createCell(0);
//                    dataCell.setCellValue(subject.getItemName());
//                    dataCell.setCellStyle(styleLemon);
//                }
//            }
//            sheet.setColumnWidth(0, 9000);
//            //创建输出流，生成文件
//            OutputStream out = new BufferedOutputStream(response.getOutputStream());
//            workbook.write(out);
//            out.flush();
//            out.close();
//            return null;
//        }

}
