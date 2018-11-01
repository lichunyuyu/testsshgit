package com.one.javatest.exportPDF;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by vtstar on 2018/10/13.
 */

/**
 * 需要引入 itextpdf-5.2.1.jar
 * itext-asian-5.2.0.jar  设置字体； 使用iText可以设置文字的字体 ； 幸好iText中有一个专门的包用来设置亚洲国家的字体你可以从http://itext.sourceforge.net/downloads/iTextAsian.jar下载这个包。然后把它直接放到你的ClassPath中就可以了。
 * https://mvnrepository.com/artifact/com.itextpdf/itext-asian/5.2.0
 * */
public class exportPdf {

    public static void main(String[] args) throws IOException, DocumentException {

        // 第一步，实例化一个document对象
        Document document = new Document();
        // 设置要输出的路径
        String outpath = "../SSH/src/com/one/javatest/exportPDF/outFiles/";
        String outfilename = System.currentTimeMillis()+"-testPdf.pdf";
        String outfile = outpath+outfilename;
        FileOutputStream fileOutputStream = new FileOutputStream(outfile);
        //如果是浏览器通过request 请求需要在浏览器中输出则使用下面方式
        // OutputStream oututStream = response.getOutputStream();

//        // 设置字符集   需加入 itext-asian-5.2.0.jar 包 http://blog.sina.com.cn/s/blog_85987afc0101coo2.html
//        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", false); //  本系统 没有 STSong-Light 该字体。
//        /**
//         * 采用的是：windows自带的字库
//         BaseFont bf = BaseFont.CreateFont( "c://windows//fonts//simsun.ttc,1" , BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
//         * */
//
//        Font fontZH = new Font(bfChinese,12.0F,0);

        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font fontZH = new Font(bfChinese, 12, Font.NORMAL);


        // 将pdf输出 到 磁盘
        PdfWriter writer = PdfWriter.getInstance(document,fileOutputStream);
        // 打开生成的pdf 文件
        document.open();
        //设置内容
        String title = "标题";
        document.add(new Paragraph(new Chunk(title,fontZH).setLocalDestination(title)));
        document.add(new Paragraph("\n"));
        // 创建table，注意这里的2是2列的意思，下面通过table.addCell添加的时候必须添加整行内容
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100.0F);
        table.setHeaderRows(1);
        table.getDefaultCell().setHorizontalAlignment(1);
        table.addCell(new Paragraph("序号",fontZH));
        table.addCell(new Paragraph("结果",fontZH));
        table.addCell(new Paragraph("1",fontZH));
        table.addCell(new Paragraph("出来了",fontZH));
        // 图片
        String resource_jpg = "https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=9179a1569125bc313f5009ca3fb6e6d4/8b82b9014a90f603cf01e8df3112b31bb151edf1.jpg";
        String resource_jpg2 = "../SSH/src/com/one/javatest/exportPDF/image/2.jpg"; // 中文不识别
        Image image = Image.getInstance(resource_jpg);
        image.setAbsolutePosition(0,0);
//        image.setAbsolutePosition((PageSize.POSTCARD.getWidth() - image.getScaledWidth())/4,
//                (PageSize.POSTCARD.getHeight() - image.getScaledHeight())/4);
        Image image1 = Image.getInstance(resource_jpg2);
        // 283     416     1200   708
        System.out.println("PageSize.POSTCARD.getWidth()=="+PageSize.POSTCARD.getWidth()+","+PageSize.POSTCARD.getHeight()+","+image1.getScaledWidth()+","+image1.getScaledHeight());
        image1.setAbsolutePosition((PageSize.POSTCARD.getWidth() - image1.getScaledWidth()/8),
                (PageSize.POSTCARD.getHeight() - image1.getScaledHeight()/8));
        document.add(image);
        document.add(image1);
        document.add(table);
        document.add(new Paragraph("\n"));
        //关闭document
        document.close();
        System.out.println("导出pdf成功");

    }

}
