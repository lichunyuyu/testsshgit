package com.one.javatest.transferWords;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by vtstar on 2018/3/6.
 */
/**
 * Jsoup 是一个用于处理HTML 的Java 库,他提供了一个非常方便类似于使用DOM，CSS和jquery 方法的API来提提取和操作数据。
 *
 * */
public class FirstJsoupExample {
    public static void main(String[] args) throws IOException {
        //Document document = Jsoup.connect("http://translate.google.com/?lang pair={0}&text={1}").get();
        Document document = Jsoup.connect("https://translate.google.cn/?lang pair={0}&text={1}").get();

        Document doc = Jsoup.connect("http://www.yiibai.com").get();
        String title = doc.title();
        String title2 = document.title();
        System.out.println("title is: " + title);//易百教程™ - 专注于IT教程和实例
        System.out.println("title2 is: " + title2);//Google 翻译
    }
}
/**jsoup实现WHATWG HTML5规范，并将HTML解析为与现代浏览器相同的DOM。

 从URL，文件或字符串中提取并解析HTML。
 查找和提取数据，使用DOM遍历或CSS选择器。
 操纵HTML元素，属性和文本。
 根据安全的白名单清理用户提交的内容，以防止XSS攻击。
 输出整洁的HTML。
 jsoup旨在处理发现所有格式有差异的HTML; 从原始和验证，到无效的标签; jsoup将创建一个明智的解析树。

 * */
