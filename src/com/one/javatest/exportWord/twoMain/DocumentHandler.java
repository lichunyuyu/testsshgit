package com.one.javatest.exportWord.twoMain;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
//import org.apache.poi.ss.util.ImageUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by vtstar on 2018/2/26.
 */
public class DocumentHandler {

    private Configuration configuration = null;

    public DocumentHandler() {
        configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");
    }

    public void createDoc(){
        // 要填入模本的数据文件
        Map<String,Object> dataMap=new HashMap<String,Object>();
        getData(dataMap);
        //设置模本装置方法和路径，FreeMaker支持多种模板装载方法。可以重servlet，classpath,数据库装载
        // 这里我们的模板是放在com.php.documnet.template包下面    /com/one/javatest/exportWord/resource/freeMaker
        configuration.setClassForTemplateLoading(this.getClass(),"/com/one/javatest/exportWord/resource/freeMaker");
        Template t = null;
        // freeMakerTest.ftl为要装载的模板
        try {
            t = configuration.getTemplate("freeMakerTest.ftl");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 输出文档路径及名称
       // File outfile = new File("../outFile.docx"); // SpringMVCDemo1/outFile.docx
        File outfile = new File("../SSH/web/WEB-INF/outFile/outFile-"+System.currentTimeMillis()+".doc");  //  docx  不可以
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

    /**
     * 注意dataMap里存放的数据Key值要与模板中的参数相对应
     * @param dataMap
     */
    private void getData(Map<String,Object> dataMap)
    {
        dataMap.put("maker", "aaaa");
        dataMap.put("remarks", "测试freemarker");
        dataMap.put("time", new Date().toString());
        //  替换图片
        try {
            URL imageUrl=   new URL("http://d.hiphotos.baidu.com/image/w%3D230/sign=d9b34028708b4710ce2ffacff3cec3b2/83025aafa40f4bfb98ff265d014f78f0f736188f.jpg");
            //URL imageUrl2 = new URL("../SSH/src/com/one/javatest/exportWord/resource/images/jiyeon2___20184498.jpg");
            //URL imageUrl2 = new URL("../SSH/out/production/SSHTest1/com/one/javatest/exportWord/resource/images/jiyeon2___20184498.jpg");
            //File imageFile = new File("../SSH/src/com/one/javatest/exportWord/resource/images/1517452695(1).jpg");
            File imageFile = new File("../SSH/src/com/one/javatest/exportWord/resource/images/白色.png");
            String imageBase64 = ImageUtils.encodeImgageToBase64(imageFile);
           // String imageBase64 = ImageUtils.encodeImgageToBase64(imageUrl2);
            System.out.println("imageBase64==:"+imageBase64);
            dataMap.put("picture", imageBase64);
            dataMap.put("picture2", imageBase64);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public  static void main(String[] args){
        DocumentHandler documentHandler = new DocumentHandler();
        documentHandler.createDoc();
    }
}
