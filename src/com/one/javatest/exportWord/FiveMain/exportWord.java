package com.one.javatest.exportWord.FiveMain;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vtstar on 2018/6/12.
 */
public class exportWord {

    public static void main(String[] args) throws IOException, TemplateException {
        exportWordtest();
    }
   public static void exportWordtest() throws IOException, TemplateException {
       //数据获取
      Map<String,Object> map = selectMapData();
      // 获取文件
       String tempaltePath = "template/five.xml";  // 模板路径
       String outpath = "../SSH/src/com/one/javatest/exportWord/FiveMain/outFiles/";
       outpath = outpath.replaceAll("/","\\\\");  // 转不转换都一样 ？

       String outFilename = outpath + System.currentTimeMillis() + "-33five.doc";// 导出 doc  可以， docx  不可以

       Configuration configuration = new Configuration();
       configuration.setTemplateLoader(new FileTemplateLoader(new File("../SSH/src/com/one/javatest/exportWord/FiveMain/template/"))); // 模板文件所在路径
       configuration.setDefaultEncoding("UTF-8");
       Template t = null;
       try {
           t = configuration.getTemplate("five33.ftl","UTF-8");  // 乱码  获取模板文件
           t.setEncoding("UTF-8");  //  xml  文件
       } catch (IOException e) {
           e.printStackTrace();
       }

       // 输出文档路径  及名称
       File file = new File(outpath);
       if(!file.exists()){
           file.mkdirs();
       }
       File outfile = new File(outFilename);
       Writer out = null;
       try {
           out = new BufferedWriter(new OutputStreamWriter(
                   new FileOutputStream(outfile), "UTF-8"));// 乱码
       } catch (Exception e1) {
           e1.printStackTrace();
       }

       // 写入
       t.process(map,out);
       out.flush();
       out.close();

   }
   /**
    * 数据获取
    * */
   public static Map<String,Object> selectMapData(){
       Map<String,Object> map = new HashMap<String,Object>();

       Map<String,Object> map1 = new HashMap<String,Object>();
       map1.put("name1","张三");
       map1.put("age1",17);
       map1.put("sex","girl");

       List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
       Map<String,Object> map2 = new HashMap<String,Object>();
       map2.put("name2","李四");
       map2.put("age2",18);
       map2.put("sex2","girl");
       Map<String,Object> map3 = new HashMap<String,Object>();
       map3.put("name2","王二");
       map3.put("age2",19);
       map3.put("sex2","boy");
       mapList.add(map2);
       mapList.add(map3);

       List<Map<String,Object>> mapList2 = new ArrayList<Map<String,Object>>();
       Map<String,Object> map4 = new HashMap<String,Object>();
       map4.put("name3","赵潜");
       map4.put("age3",18);
       map4.put("sex3","girl");
       Map<String,Object> map5 = new HashMap<String,Object>();
       map5.put("name3","孙立");
       map5.put("age3",19);
       map5.put("sex3","boy");
       Map<String,Object> map6 = new HashMap<String,Object>();
       map6.put("name3","毛小炮");
       map6.put("age3",20);
       map6.put("sex3","boy");
       mapList2.add(map4);
       mapList2.add(map5);
       mapList2.add(map6);

       map.put("map0",map);
       map.put("mapList",mapList);
       map.put("mapList2",mapList2);

       Map<String, Object> dataMap = new HashMap<String,Object>();
                dataMap.put("title", "标题");
                dataMap.put("nian", "2018");
                dataMap.put("sex", "boy");
                dataMap.put("age1", "6");
                dataMap.put("name1", "lc");

       List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
                for (int i = 0; i < 2; i++) {
                       Map<String,Object> mapd = new HashMap<String,Object>();
                    mapd.put("name2", i);
                    mapd.put("age2", "内容"+i);
                        list.add(mapd);
                    }

       List<Map<String,Object>> list2 = new ArrayList<Map<String,Object>>();
       for (int i = 0; i < 3; i++) {
           Map<String,Object> mapd2 = new HashMap<String,Object>();
           mapd2.put("name3", i);
           mapd2.put("age3", "内容"+i);
           mapd2.put("sex3", "girl"+i);
           list2.add(mapd2);
       }

       dataMap.put("mapList", list);
       dataMap.put("mapList2", list2);

       //return map;
       return dataMap;
   }

}
