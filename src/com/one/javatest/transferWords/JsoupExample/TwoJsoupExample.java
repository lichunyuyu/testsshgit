package com.one.javatest.transferWords.JsoupExample;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;

/**
 * Created by vtstar on 2018/3/6.
 */
public class TwoJsoupExample {

    public static void main(String[] args) throws IOException {
        //TwoJsoupExample jsoupExample = new TwoJsoupExample();
        /**1. 载入文件-- 从URL 加载文档，使用Jsoup.connect()方法从URL加载HTML */
        Document document = Jsoup.connect("http://www.yiibai.com").get();
        System.out.println("1."+document.title());

        /**2.从文档加载模板 -- 使用Jsoup.parse() 方法从文件加载HTML */
        String pathName = "D:/SwingProjectTests/SpringProjectsTesto/SpringMVCDemo1/SSH/web/WEB-INF/html/twoJsoupExample.html";
        Document document1 = Jsoup.parse(new File(pathName),"UTF-8");
        System.out.println("2."+document1.title());

        /**3.从String 加载文档*/
        String html =  "<html><head><title>First parse</title></head>"
                + "<body><p>Parsed HTML into a doc.</p></body></html>";
        Document document2 = Jsoup.parse(html);
        System.out.println("3."+document2.title());

        /**4. 从HTML获取标题 --调用document.title()方法HTML电子杂志页面的标题。*/
        String pathName3 = "D:/SwingProjectTests/SpringProjectsTesto/SpringMVCDemo1/SSH/web/WEB-INF/html/twoJsoupExample.html";
        Document document3 = Jsoup.parse(new File(pathName3),"UTF-8");
        System.out.println("2."+document3.title());

        /**5. 获取HTML页面的Fav图标-- 假设favicon图像将HTML的英文的文档<head>部分中的第一个图像，您可以使用下面的代码。*/


    }
}
