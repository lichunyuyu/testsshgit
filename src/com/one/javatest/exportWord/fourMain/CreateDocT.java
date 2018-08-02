package com.one.javatest.exportWord.fourMain;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.Map;

/**
 * Created by vtstar on 2018/3/2.
 */
public class CreateDocT {

    public void createDoc(Map<String,Object> dataMap){
        // 要填入模本的数据文件  dataMap

        //设置模本装置方法和路径，FreeMaker支持多种模板装载方法。可以重servlet，classpath,数据库装载
        // 这里我们的模板是放在com.php.documnet.template包下面    /com/one/javatest/exportWord/resource/freeMaker
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(this.getClass(),"/com/one/javatest/exportWord/fourMain");
        Template t = null;
        // freeMakerTest.ftl为要装载的模板
        try {
            t = configuration.getTemplate("temp.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 输出文档路径及名称
        // File outfile = new File("../outFile.docx"); // SpringMVCDemo1/outFile.docx
        String tempath = "../SSH/src/com/one/javatest/exportWord/fourMain/";
        File outfile = new File("../SSH/src/com/one/javatest/exportWord/fourMain/test3.doc");  //  docx  不可以
        Writer out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outfile),"utf-8"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            t.process(dataMap,out);
            out.flush();
            out.close();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
