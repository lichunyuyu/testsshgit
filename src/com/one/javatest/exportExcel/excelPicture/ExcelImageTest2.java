package com.one.javatest.exportExcel.excelPicture;



/**
 * Created by vtstar on 2018/10/24.
 */

import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.*;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.regex.Pattern;


/**
 poi导出Excel中图片的设置  https://blog.csdn.net/wu920604/article/details/51889250
 在POI中有HSSFPatriarch对象，该对象为画图的顶级管理器，它的createPicture(anchor, pictureIndex)方法就能够在Excel插入一张图片。
 所以要在Excel中插入图片，三步就可以搞定。
 一、获取HSSFPatriarch对象，二、new HSSFClientAnchor对象，三、调用createPicture方法即可。
 */
public class ExcelImageTest2 {

    public static void main(String[] args) {
        FileOutputStream fileOut = null;
        BufferedImage bufferImg = null;
        //先把读进来的图片放到一个ByteArrayOutputStream中，以便产生ByteArray
        try {
            ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
            String fileName = "D:\\MyGitHubProjects\\SSH\\src\\com\\one\\javatest\\exportExcel\\excelPicture\\images\\单波2.jpg";
            fileName=fileName.replaceAll("\\\\","/");
            // F:/图片/照片/无名氏/小昭11.jpg
            bufferImg = ImageIO.read(new File(fileName)); // test
            ImageIO.write(bufferImg, "jpg", byteArrayOut);//test

//            File file = new File(fileName);
//           byte[] bytes = encodeImgageToBase64png(file);


//  报错  ：java.lang.ClassNotFoundException: org.apache.commons.codec.digest.DigestUtils     jar版本
//            HSSFWorkbook wb = new HSSFWorkbook();
//            HSSFSheet sheet1 = wb.createSheet("test picture");
//            //画图的顶级管理器，一个sheet只能获取一个（一定要注意这点）
//            HSSFPatriarch patriarch = sheet1.createDrawingPatriarch();
//            //anchor主要用于设置图片的属性
//            HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 255, 255,(short) 1, 1, (short) 5, 8);
//            anchor.setAnchorType(3);
//            //插入图片
//            patriarch.createPicture(anchor, wb.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));

            XSSFWorkbook wb = new XSSFWorkbook();
            wb.addPicture(byteArrayOut.toByteArray(), Workbook.PICTURE_TYPE_JPEG); // test

            XSSFSheet sheet = wb.createSheet("test picture");
            //
            XSSFClientAnchor anchor1 = new XSSFClientAnchor(
                    0,     // 起始 坐标    dx1 =0  dy1 = 0  表示从 单元格同起始（头）
                    0,
                    255,   // 终止坐标  dx1 =0  dy1 = 0  表示从 单元格同起始（尾）
                    100,
                     2,   // 起始列 B （不包含）
                    2,  // 起始行  2 （不包含）
                    (short)5.83,  // 终止列  E （包含）
                    7); // 终止行  7（包含）
            //anchor1.setAnchorType(3); // 3.10
            anchor1.setAnchorType(ClientAnchor.AnchorType.DONT_MOVE_AND_RESIZE);// 3.17
            XSSFDrawing patriarch2 = sheet.createDrawingPatriarch();//创建绘图工具对象放循环外可正确显示
            //patriarch2.createPicture(anchor1,wb.addPicture(byteArrayOut.toByteArray(), XSSFWorkbook.PICTURE_TYPE_JPEG)); // 修改后可以

//            // 图片插入坐标
//            anchor1.setCol1(2);
//            anchor1.setRow1(2);
            // 指定 我想要的长宽 1.915   483  300  1.61  8.6229   15.0    0.5748   - 2.05 3.85       1.878
            //                          483  303  1.594  8.6229   15.15   0.5691  - 2.03  3.81       1.876
            //                          480  300  1.594  8.5693   15.15   0.5712  - 2.04  3.82       1.8725
            //                          482  301         8.6050  15.05    0.5717  - 2.04  3.83       1.877
            //                         483  308  1.568  8.6229   15.4    0.5599  - 1.99  3.75      1.8844
            //                         483  310  1.558  8.6229   15.5    0.5563   -1.98   3.72     1.8787
            //                         485  308  1.574  8.6586   15.4    0.5622  - 2  3.76
            //                         486  308  1.574  8.6764   15.4    0.5634  - 2.01  3.77
            //                         486  309  1.574  8.6764   15.45   0.5615  - 2  3.76
            //                         488  310  1.626  8.7121   15.5    0.5620  - 2  3.76        1.88
            //  483     301   3.83  2.04  （ pict.resize(a/b);  ）
            //  42.87  16.43  3.83  2   宽 3.83  高2  （  pict.resize(a,b); ）
//            double standardWidth = 483;
//            double standardHeight = 301;
            // 1.36
            //      850   350    5.67  3.02
            //      850  380    5.35  2.87              2.8  5.22
            //      850  400    5.08   2.72     1.867
            //      830  400    4.96  2.65      1.87
            //     39.3   41.9   3.54  5.12  宽  高
            //     41.9   39.3   3.82  4.75   a = 0.748 b = 1.965
            //     41.9   43.3   3.75  5.31   a 0.748  b 2.165   + 3

//            double standardWidth = 39.3;
//            double standardHeight = 41.9;

            // 224 > scaledWidth > 168
            //  160 > scaledHeight > 140
            //   double scaledWidth = scaleX == 1.7976931348623157E308D?imgSize.getWidth():anchorSize.getWidth() / 9525.0D * scaleX;
            //  double scaledHeight = scaleY == 1.7976931348623157E308D?imgSize.getHeight():anchorSize.getHeight() / 9525.0D * scaleY;
            //  standardWidth = 168 * scaleX   =  168 * a      224 > x > 168     1.33 > a > 1    74.48 > x > 56
            // standardHeight = 100 * scaleY  = 100 * b       160 > y > 140      0.95 > b > 0.83  19 > y > 16.6
            // // standardHeight = 100 * scaleY  = 100 * b       160 > y > 140     1.6 > b > 1.4  32 > y > 28
            // 60  30    5.4   3.67
            // 60  31    5.4   3.81
            // 59  31   5.32    3.81
            // 57  31    5.16   3.81
            // 57  30.8   5.16  3.78
            // 57.4  30.9  5.19  3.79
            // 57.4  30.95  5.19  3.8   宽 5.19  高3.8  （  pict.resize(a,b); ）
            // 42.87  16.43  3.83  2   宽 3.83  高2  （  pict.resize(a,b); ）
            double standardWidth = 42.87;
            double standardHeight = 16.43;
            // 计算单元格的长宽
            XSSFRow row = sheet.createRow(1);
            XSSFCell cell = row.createCell(1);

            //double cellWidth = sheet.getColumnWidthInPixels(1); // 以像素为单位获取宽度 poi-ooxml-3.1 未找到该方法   4.0可以找到。   3.17 也可以
            // 取的包里的方法
//            sheet.getColumnWidthInPixels(1);
//            float widthIn256 = (float)sheet.getColumnWidth(1);
//            float ColumnWidthInPixels =  (float)((double)widthIn256 / 256.0D * 7.001699924468994D);
//            double cellWidth = ColumnWidthInPixels;  // 以像素为单位获取宽度
            double cellWidth = sheet.getColumnWidthInPixels(3);
            double cellHeight = (cell.getRow().getHeightInPoints())/72*96;  //  3/4   获取行高的像素值   Height的值永远是HeightInPoints的20倍
            System.out.println("cellWidth="+cellWidth+",cellHeight="+cellHeight); // cellWidth=56.01359939575195,cellHeight=20.0
            System.out.println("row.getHeight()="+row.getHeight()+",row.getHeightInPoints()="+row.getHeightInPoints()); // row.getHeight()=300,row.getHeightInPoints()=15.0
            System.out.println("sheet.getColumnWidth(1)="+sheet.getColumnWidth(1)); // sheet.getColumnWidth(1)=2048

            //计算需要的长宽比例系数
            double a = standardWidth / cellWidth;
            double b = standardHeight / cellHeight;

            // 0.68  1.25   比例 1.83   383 200  比例 1.91      1.87   10    原始  3.86   6.69  比例 1.73
//            //使用固定的长宽比例系数
//            double a = 3.83;
//            double b = 2;
            // 插入图片  Picture
//            Picture pict = patriarch2.createPicture(anchor1,wb.addPicture(byteArrayOut.toByteArray(), XSSFWorkbook.PICTURE_TYPE_JPEG)); // 修改后可以  test
//            //pict.resize(a/b);    //   double standardWidth = 483;    double standardHeight = 301;    2.04   3.83   // test
//            pict.resize(a,b); // 设置图片的大小 。   // test

            patriarch2.createPicture(anchor1,wb.addPicture(byteArrayOut.toByteArray(), XSSFWorkbook.PICTURE_TYPE_JPEG));

            System.out.println("a="+a+",b="+b+",a/b="+a/b); // a=6.837625221939345, b=10.0, a/b=0.6837625221939345

            String outFileName = "D:\\MyGitHubProjects\\SSH\\src\\com\\one\\javatest\\exportExcel\\excelPicture\\outFiles\\测试Excel2.xlsx";
            outFileName=outFileName.replaceAll("\\\\","/");
            // D:/测试Excel.xls
            fileOut = new FileOutputStream(outFileName);
            // 写入excel文件
            wb.write(fileOut);
            System.out.println("----Excle文件已生成------");
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(fileOut != null){
                try {
                    fileOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


//    public float getColumnWidthInPixels(int columnIndex) {
//        float widthIn256 = (float)this.getColumnWidth(columnIndex);
//        return (float)((double)widthIn256 / 256.0D * 7.001699924468994D);
//    }


    /** 设置图片背景透明  签名 高2cm   宽3.83 cm*/
    public static byte[] encodeImgageToBase64png(File imageFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        ByteArrayOutputStream outputStream = null;
        try {
            BufferedImage image = ImageIO.read(imageFile);
            System.out.println("image.getWidth();=="+image.getWidth());
            outputStream = new ByteArrayOutputStream();
            ImageIcon imageIcon = new ImageIcon(image);
            /** 转化为 透明背景  --    只能上传非透明背景图  */
            BufferedImage bufferedImage = new BufferedImage(
                    imageIcon.getIconWidth(), imageIcon.getIconHeight(),
                    BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D g2D = (Graphics2D) bufferedImage.getGraphics();
            g2D.drawImage(imageIcon.getImage(), 0, 0,
                    imageIcon.getImageObserver());
            // 设置透明度
            g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.14f));  // 1.0f为透明度 ，值从0-1.0，依次变得不透明

            int alpha = 0;
            for (int j1 = bufferedImage.getMinY(); j1 < bufferedImage
                    .getHeight(); j1++) {
                for (int j2 = bufferedImage.getMinX(); j2 < bufferedImage
                        .getWidth(); j2++) {
                    int rgb = bufferedImage.getRGB(j2, j1);
                    if (colorInRange(rgb))
                        alpha = 0;
                    else
                        alpha = 255;
                    rgb = (alpha << 24) | (rgb & 0x00ffffff);
                    bufferedImage.setRGB(j2, j1, rgb);
                }
            }
            g2D.drawImage(bufferedImage, 0, 0, imageIcon.getImageObserver());
            // 生成图片为PNG
//            String outFile = path.substring(0, path.lastIndexOf("."));
//            ImageIO.write(bufferedImage, "png", new File(outFile + ".png"));

            ImageIO.write(bufferedImage, "png", outputStream);

        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();

        // return encoder.encode(outputStream.toByteArray());// 返回Base64编码过的字节数组字符串
        byte[] bytes = outputStream.toByteArray();
        return bytes;
    }
    public static boolean colorInRange(int color) {
        int red = (color & 0xff0000) >> 16;
        int green = (color & 0x00ff00) >> 8;
        int blue = (color & 0x0000ff);
        if (red >= color_range && green >= color_range && blue >= color_range)
            return true;
        return false;
    }

    public static int color_range = 210;
    public static Pattern pattern = Pattern.compile("[0-9]*");

    public static boolean isNo(String str) {
        return pattern.matcher(str).matches();
    }

}


/**
 主要是因为HSSFClientAnchor(0, 0, 255, 255,(short) 1, 1, (short) 5, 8)这个构造函数造成的，下面我就来解释这个构造函数：HSSFClientAnchor(int dx1,int dy1,int dx2,int dy2,short col1,int row1,short col2, int row2);各个参数的含义如下：

 dx1：the x coordinate within the first cell。

 dy1：the y coordinate within the first cell。

 dx2：the x coordinate within the second cell。

 dy2：the y coordinate within the second cell。

 col1：the column (0 based) of the first cell。

 row1：the row (0 based) of the first cell。

 col2：the column (0 based) of the second cell。

 row2：the row (0 based) of the second cell。

 这里dx1、dy1定义了该图片在开始cell的起始位置，dx2、dy2定义了在终cell的结束位置。col1、row1定义了开始cell、col2、row2定义了结束cell。
 * */
