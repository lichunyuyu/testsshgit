package com.one.javatest.transferWords.JsoupExample;

import org.jsoup.Jsoup;  //  org.jsoup.Jsoup类  是任何Jsoup 程序的入口点，并将提供从各种来源加载和解析HTML文档的方法。
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by vtstar on 2018/3/6.
 */
/** https://www.yiibai.com/jsoup/jsoup-quick-start.html#article-start
 * Jsoup是用于解析HTML，就类似XML解析器用于解析XML。Jsoup它解析HTML成为真实世界的HTML。它与jquery选择器的语法非常相似，并且非常灵活容易使用以获得所需的结果。教程中，我们将介绍很多Jsoup的例子。

 能用Jsoup实现什么？

 从URL，文件或字符串中刮取并解析HTML
 查找和提取数据，使用DOM遍历或CSS选择器
 操纵HTML元素，属性和文本
 根据安全的白名单清理用户提交的内容，以防止XSS攻击
 输出整洁的HTML
 * */
public class FirstJsoupExample {
    public static void main(String[] args) throws IOException {
        /**获取维基百科 主页，解析为DOM ，并从新闻部分中 选择 标题 列入 元素列表*/
        Document document = Jsoup.connect("http://en.wikipedia.org/").get();
        Elements newsHeadlines = document.select("#mp-itn b a");//  String cssQuery
        System.out.println("newsHeadlines is "+newsHeadlines);
        /**提取易百教程网首页的title标签中的字符串符。*/
        String title = document.title();
        System.out.println("title is: " + title);//Wikipedia, the free encyclopedia
    }
}
/** 在维基百科首页  可以看到 f12  Elements    <div id="mp-itn"><ul><li><b><a herf=""></a></b></li></ul></div>
 * newsHeadlines is <a href="/wiki/90th_Academy_Awards" title="90th Academy Awards">Academy Awards</a>
 <a href="/wiki/2018_Baku_fire" title="2018 Baku fire"><span class="nowrap">a fire</span></a>
 <a href="/wiki/2018_Ouagadougou_attacks" title="2018 Ouagadougou attacks">Attacks</a>
 <a href="/wiki/2018_Papua_New_Guinea_earthquake" title="2018 Papua New Guinea earthquake">an earthquake</a>
 <a href="/wiki/Billy_Graham" title="Billy Graham">Billy Graham</a>
 <a href="/wiki/Portal:Current_events" title="Portal:Current events">Ongoing</a>
 <a href="/wiki/Deaths_in_2018" title="Deaths in 2018">Recent deaths</a>
 <a href="/wiki/Wikipedia:In_the_news/Candidates" title="Wikipedia:In the news/Candidates">Nominate an article</a>
 * */
