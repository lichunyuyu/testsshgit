package com.one.javatest.exportWord.fourMain;

/**
 * Created by vtstar on 2018/3/1.
 */

import java.io.*;
import java.net.URISyntaxException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * 其实docx 属于zip 的一种，这里只需要操作 word/document.xml 中的数据，其他的数据不用动
 * */
public class XmlToDocx {

    /**
     *
     * @param documentFile  动态生成数据的docunment.xml文件
     * @param docxTemplate  docx的模板
     * @param toFileName    需要导出的文件路径
     * @throws ZipException
     * @throws IOException
     */
    public  void outDocx(File documentFile,String docxTemplate,String toFilePath) throws ZipException, IOException {

        try {
            String fileName = XmlToDocx.class.getClassLoader().getResource("").toURI().getPath()+docxTemplate;
            System.out.println("xmlToDoc--fileName=="+fileName);


            File docxFile = new File(fileName);
            ZipFile zipFile = new ZipFile(docxFile);
            Enumeration<? extends ZipEntry> zipEntrys = zipFile.entries();
            ZipOutputStream zipout = new ZipOutputStream(new FileOutputStream(toFilePath));
            int len=-1;
            byte[] buffer=new byte[1024];
            while(zipEntrys.hasMoreElements()) {
                ZipEntry next = zipEntrys.nextElement();
                InputStream is = zipFile.getInputStream(next);
                //把输入流的文件传到输出流中 如果是word/document.xml由我们输入
                zipout.putNextEntry(new ZipEntry(next.toString()));
                if("word/document.xml".equals(next.toString())){
                    //InputStream in = new FileInputStream(new File(XmlToDocx.class.getClassLoader().getResource("").toURI().getPath()+"template/test.xml"));
                    //InputStream in = new FileInputStream(new File(XmlToDocx.class.getClassLoader().getResource("").toURI().getPath()+"com/one/javatest/exportWord/fourMain/template/test.xml"));
                    InputStream in = new FileInputStream(documentFile);
                    while((len = in.read(buffer))!=-1){
                        zipout.write(buffer,0,len);
                    }
                    in.close();
                }else {
                    while((len = is.read(buffer))!=-1){
                        zipout.write(buffer,0,len);
                    }
                    is.close();
                }
            }
            zipout.flush();
            zipout.close();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
