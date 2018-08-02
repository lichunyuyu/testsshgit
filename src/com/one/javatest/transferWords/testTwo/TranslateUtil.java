package com.one.javatest.transferWords.testTwo;

/**
 * Created by vtstar on 2018/3/6.
 */
/**
 * Copyright (c) blackbear, Inc All Rights Reserved.
 */


import org.apache.commons.io.IOUtils;
import org.apache.http.client.HttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.InputStream;
import java.net.URLEncoder;
import java.text.MessageFormat;

// import org.bb.util.io.IOUtil;
//import org.bb.util.net.http.HttpClientUtil;

//由於Google translate API要收錢
//        因此想了一個偷機的方法
//
//        1. 用HttpClient發送一個request給http://translate.google.com
//        2. 再用Jsoup來parse html, 並取出翻譯後的文字
/**
 * TranslateUtil
 *
 * <pre>翻譯工具
 * PS: 透過google translate
 * </pre>
 *
 * @author catty
 * @version 1.0, Created on 2011/9/2
 */

      public class TranslateUtil {

        protected static final String URL_TEMPLATE ="http://translate.google.cn/?langpair={0}&text={1}";
        protected static final String ID_RESULTBOX ="result_box";

        protected static final String ENCODING ="UTF-8";
        protected static final String TAIWAN ="zh-TW";// 繁中
        protected static final String CHINA ="zh-CN";// 简中
        protected static final String ENGLISH ="en"; // 英
        protected static final String JAPAN ="ja";  // 日
        protected static final String KOREA = "ko"; // 韩
        protected static final String FRANCE = "fr";// 法
        protected static final String AUTO = "auto"; // google自動判斷來源語系

        protected static HttpClient httpclient;

        /**
         * <pre>Google翻譯</pre>
         *
         * @param text
         * @param src_lang 來源語系
         * @param target_lang 目標語系
         * @return
         * @throws Exception
         */
        public static String translate(final String text,final String src_lang,final String target_lang)throws Exception {
        InputStream is =null;
        Document doc =null;
        Element ele =null;
        try{
        // create URL string
        String url = MessageFormat.format(URL_TEMPLATE,
        URLEncoder.encode(src_lang +"|"+ target_lang, ENCODING),
        URLEncoder.encode(text, ENCODING));

        // connect & download html
        is = HttpClientUtil.downloadAsStream(url);

        // parse html by Jsoup
        doc = Jsoup.parse(is, ENCODING,"");
        ele = doc.getElementById(ID_RESULTBOX);
        System.out.println("ele is "+ele);// <span id="result_box" class="short_text"><span title="这样" onmouseover="this.style.backgroundColor='#ebeff9'" onmouseout="this.style.backgroundColor='#fff'">這樣</span></span>
        String result = ele.text();

        return result;

        }finally{
         // IOUtil.closeQuietly(is);
        IOUtils.closeQuietly(is); // 用  commons-io-2.1.jar  代替
        is =null;
        doc =null;
        ele =null;
        }
        }

        /**
         * <pre>Google翻譯: 簡中-->繁中</pre>
         *
         * @param text
         * @return
         * @throws Exception
         */
        public static String cn2tw(final String text)throws Exception {
        return translate(text, CHINA, TAIWAN);
        }

        /**
         * <pre>Google翻譯: 繁中-->簡中</pre>
         *
         * @param text
         * @return
         * @throws Exception
         */
        public static String tw2cn(final String text)throws Exception {
        return translate(text, TAIWAN, CHINA);
        }

        /**
         * <pre>Google翻譯: 英文-->繁中</pre>
         *
         * @param text
         * @return
         * @throws Exception
         */
        public static String en2tw(final String text)throws Exception {
        return translate(text, ENGLISH, TAIWAN);
        }

        /**
         * <pre>Google翻譯: 繁中-->英文</pre>
         *
         * @param text
         * @return
         * @throws Exception
         */
        public static String tw2en(final String text)throws Exception {
        return translate(text, TAIWAN, ENGLISH);
        }

        /**
         * <pre>Google翻譯: 日文-->繁中</pre>
         *
         * @param text
         * @return
         * @throws Exception
         */
        public static String jp2tw(final String text)throws Exception {
        return translate(text, JAPAN, TAIWAN);
        }

        /**
         * <pre>Google翻譯: 繁中-->日</pre>
         *
         * @param text
         * @return
         * @throws Exception
         */
        public static String tw2jp(final String text)throws Exception {
        return translate(text, TAIWAN, JAPAN);
        }

    /**
       * <pre>Google翻譯: 简中-->韩</pre>
     *
    * @param text
    * @return
    * @throws Exception
    */
    public static String cn2ko(final String text)throws Exception {
      return translate(text, CHINA, KOREA);
    }

  /**
   * <pre>Google翻譯: 韩-->简中</pre>
   *
   * @param text
   * @return
   * @throws Exception
   */
  public static String ko2cn(final String text)throws Exception {
    return translate(text,KOREA, CHINA);
  }

    /**
    * <pre>Google翻译：简中-->法语 </>
     * @param text
     * @return
     * @throws Exception
    */
    public static String cn2fr(final String text)throws Exception{
      return translate(text,CHINA,FRANCE);
    }
  /**
   * <pre>Google翻译：简中-->英语 </>
   * @param text
   * @return
   * @throws Exception
   */
  public static String cn2en(final String text)throws Exception{
    return translate(text,CHINA,ENGLISH);
  }
  /**
   * <pre>Google翻译：简中-->日语 </>
   * @param text
   * @return
   * @throws Exception
   */
  public static String cn2ja(final String text)throws Exception{
    return translate(text,CHINA,JAPAN);
  }

  /**
   * <pre>Google翻译：自动判断语言-->简中 </>
   * @param text
   * @return
   * @throws Exception
   */
  public static String auto(final String text)throws Exception{
    return translate(text,AUTO,CHINA);
  }
  /**
   * <pre>Google翻译：自动判断语言-->英文 </>
   * @param text
   * @return
   * @throws Exception
   */
  public static String auto2cn(final String text)throws Exception{
    return translate(text,AUTO,ENGLISH);
  }
  /**
   * <pre>Google翻译：自动判断语言-->韩语 </>
   * @param text
   * @return
   * @throws Exception
   */
  public static String auto2ko(final String text)throws Exception{
    return translate(text,AUTO,KOREA);
  }
  /**
   * <pre>Google翻译：自动判断语言-->日语 </>
   * @param text
   * @return
   * @throws Exception
   */
  public static String auto2jp(final String text)throws Exception{
    return translate(text,AUTO,JAPAN);
  }
  /**
   * <pre>Google翻译：自动判断语言-->中文(繁体) </>
   * @param text
   * @return
   * @throws Exception
   */
  public static String auto2tw(final String text)throws Exception{
    return translate(text,AUTO,TAIWAN);
  }



    }
