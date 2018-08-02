package com.one.javatest.exportWord.fourMain;

import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vtstar on 2018/3/1.
 */
public class testmain {

    public static void main(String[] args) throws IOException, TemplateException {

        String tempath = "../SSH/src/com/one/javatest/exportWord/fourMain/";
        String totempath = tempath.replaceAll("/","\\\\");

        //xml的模板路径*/*
        String xmlTemplate ="template/test.xml";
        //设置docx的模板路径 和文件名
        String docxTemplate ="com/one/javatest/exportWord/fourMain/template/test.docx"; // D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\out\production\SSHTest1\com\one\javatest\exportWord\fourMain\template\test.docx (系统找不到指定的路径。)


        System.out.println("totempath=="+totempath);
        //String toFilePath = "d:\\test.docx";
        String toFilePath = totempath+"test2.docx";//     ..\SSH\src\com\one\javatest\exportWord\fourMain\test2.docx

        //填充完数据的临时xml
        //String xmlTemp = "d:\\temp.xml";
        String xmlTemp = totempath+"temp.xml";      //  ..\SSH\src\com\one\javatest\exportWord\fourMain\temp.xml
        Writer w = new FileWriter(new File(xmlTemp));

        //1.需要动态传入的数据
        Map<String,Object> p = new HashMap<String,Object>();
        List<String> students = new ArrayList<String>();
        students.add("张三");
        students.add("李四");
        students.add("王二");
        p.put("test", "测试一下是否成功");
        p.put("students", students);

        //2.把map中的数据动态由freemarker传给xml
        XmlToExcel.process(xmlTemplate, p, w);

        //  只能输出 doc
        new CreateDocT().createDoc(p);

/**有错误*/
//        //3.把填充完成的xml写入到docx中
//        XmlToDocx xtd = new XmlToDocx();
//        xtd.outDocx(new File(xmlTemp), docxTemplate, toFilePath);
    }

}
