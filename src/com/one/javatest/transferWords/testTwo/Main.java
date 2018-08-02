package com.one.javatest.transferWords.testTwo;

/**
 * Created by vtstar on 2018/3/6.
 */
public class Main {
    public static void main(String[] args) throws Exception {

        String  result= TranslateUtil.cn2tw("喜欢");
        System.out.println("result 简-繁："+result); // 喜歡

        String  result1= TranslateUtil.tw2cn("喜歡");
        System.out.println("result1 繁中-->簡中："+result1); //喜欢

        String  result2= TranslateUtil.en2tw("like");
        System.out.println("result2 英文-->繁中："+result2); //喜歡

        String  result3= TranslateUtil.tw2en("喜歡");
        System.out.println("result3 繁中-->英文："+result3); // like

        String  result4= TranslateUtil.jp2tw("それのように");
        System.out.println("result4 日文-->繁中："+result4); // 喜歡它

        String  result5= TranslateUtil.tw2jp("喜歡");
        System.out.println("result5 繁中-->日："+result5); // それのように

        String  result6= TranslateUtil.cn2ko("喜欢");
        System.out.println("result5 简中-->韩："+result6);//

        String  result6x= TranslateUtil.ko2cn("의지의 차이");
        System.out.println("result5 韩-->简中："+result6x);// 意志的差异

        String  result7= TranslateUtil.cn2fr("喜欢");
        System.out.println("result5 简中-->法："+result7);// Comme ça

        String  result8= TranslateUtil.auto("Je t'aime");
        System.out.println("result5 自动判断-->简中："+result8);//我爱你

        String  result9= TranslateUtil.auto("나는 너를 사랑해.");
        System.out.println("result5 自动判断-->简中："+result9);// 我爱你。
    }
}
