package com.one.swingChartTest.test;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hibernate.sql.InFragment.NULL;

/**
 * Created by vtstar on 2018/3/28.
 */
public class Main {



    public static boolean rangeInDefined(int current, int min, int max)
    {
        return Math.max(min, current) == Math.min(current, max);
    }

    public static boolean rangeInDefinedG(double current, double min, double max)
    {
        return Math.max(min, current) == Math.min(current, max);
    }


    private static long negativeZeroDoubleBits = Double.doubleToRawLongBits(-0.0d);

    public static double max(double a, double b) {
        if (a != a)
            return a;   // a is NaN
        if ((a == 0.0d) &&
                (b == 0.0d) &&
                (Double.doubleToRawLongBits(a) == negativeZeroDoubleBits)) {
            // Raw conversion ok since NaN can't map to -0.0.
            return b;
        }
        System.out.println("max==="+((a >=b) ? a : b));
        System.out.println("max==="+((a >b) ? a : b));
        return (a >= b) ? a : b;
    }

    public static double min(double a, double b) {
        if (a != a)
            return a;   // a is NaN
        if ((a == 0.0d) &&
                (b == 0.0d) &&
                (Double.doubleToRawLongBits(b) == negativeZeroDoubleBits)) {
            // Raw conversion ok since NaN can't map to -0.0.
            return b;
        }
       // return (a <= b) ? a : b; //  最小不等于
        System.out.println("min==="+((a <=b) ? a : b));
        System.out.println("min==="+((a <b) ? a : b));
        return (a <b) ? a : b;
    }

    public static boolean rangeInDefinedMine(double current, double min, double max)
    {
        return max(min, current) == min(current, max);
    }

    //volatile boolean flag = false;   // 原子性
    public static void main(String[] args) throws IOException {
//        在启动的多线程的时候，需要先通过Thread类的构造方法Thread(Runnable target) 构造出对象，然后调用Thread对象的start()方法来运行多线程代码。
//        实际上所有的多线程代码都是通过运行Thread的start()方法来运行的。因此，不管是扩展Thread类还是实现Runnable接口来实现多线程，最终还是通过Thread的对象的API来控制线程的，熟悉Thread类的API是进行多线程编程的基础。

        int  iii=0;
        Thread1 mTh1 = new Thread1("A");
        mTh1.testa(iii);
        Thread1 mTh2 = new Thread1("B");
        mTh2.testb(iii);
        mTh1.start();
        mTh2.start();

        // 输出 无序
//        但是start方法重复调用的话，会出现java.lang.IllegalThreadStateException异常。
//
//        Thread1 mTh1=new Thread1("A");
//        Thread1 mTh2=mTh1;
//        mTh1.start();
//        mTh2.start();
//        输出：Exception in thread "main" java.lang.IllegalThreadStateException
//        at java.lang.Thread.start(Unknown Source)
//        at com.multithread.learning.Main.main(Main.java:31)
//        A运行  :  0
//        A运行  :  1

//        new Thread(new Thread2("C")).start();
//        new Thread(new Thread2("D")).start();

        Thread2 mTh21 = new Thread2("C");
        mTh21.testa(iii);
        Thread2 mTh22 = new Thread2("D");
        mTh22.testb(iii);
        new Thread(mTh21).start();
        new Thread(mTh22).start();

        // 输出  有序
//        C运行  :  0
//        D运行  :  0
//        D运行  :  1
//        C运行  :  1


        String aaaaaaa = ",01,02,03,04,05,12,20,";
        if(aaaaaaa.indexOf(",01,")!=-1){
            System.out.println("包含");
        }
        if(",01,02,03,04,05,12,20,".indexOf(",01,")!=-1){
            System.out.println("包含");
        }



        int ii = 0;
        ii = 10;
        int jj =ii;
        System.out.println(jj); // 10;
        //  正则表达式    获取匹配的数字
        String regex = "([1-9]\\d*\\.?\\d*)|(0\\.\\d*[1-9])";
       // String teststr = "15.6箱(10瓶过保质期)";
        String teststr = "Vit A.........480,000,000.2IU";
        Pattern pattern = Pattern.compile(regex); //  import java.util.regex.Pattern;

        Matcher matcher = pattern.matcher(teststr);
        if(matcher.find()){
            matcher.group(1); // 15.6
            System.out.println(matcher.group(1));  // 组提取字符串
        }
        //获取第一个 . 的下标
        System.out.println("第一个点下标。。。。"+ teststr.indexOf("."));// 5
        System.out.println("第一个点下标。。。。"+ teststr.substring(0,5));// Vit A
        System.out.println("最后一个点下标。。。。"+ teststr.lastIndexOf("."));// 21
        teststr = teststr.substring(5,teststr.length());
        System.out.println("新teststr。。。。"+ teststr);//  .........480,000.2IU
         //创建一个字符串变量strStringType   将字符串转化为字符数组
        char[] chrCharArray; //创建一个字符数组chrCharArray
        chrCharArray = teststr.toCharArray(); //将字符串变量转换为字符数组
        //teststr= String.valueOf(chrCharArray); //将字符数组转换为字符串
        int needindex=0;
        for(int i=0;i<chrCharArray.length;i++){
            char a = chrCharArray[i];
            if(!String.valueOf(a).equals(".")){
                needindex = i;
                break;
            }
        }
        System.out.println("needindex......"+needindex); // 9
        teststr = teststr.substring(needindex,teststr.length());
        System.out.println("最终teststr。。。。"+ teststr);// 480,000.2IU

        if(teststr.indexOf(",")!=-1){
            teststr = teststr.replace(",","");
            System.out.println("最终去逗号teststr。。。。"+ teststr);// 480000.2
        }
        Pattern pattern2 = Pattern.compile(regex); //  import java.util.regex.Pattern;
        Matcher matcher2 = pattern2.matcher(teststr);
        if(matcher2.find()){
            matcher2.group(1); // 15.6
            System.out.println(matcher2.group(1));  // 组提取字符串
        }


        // 获取当前时间
        Calendar cal2 = Calendar.getInstance();
        SimpleDateFormat dft2 = new SimpleDateFormat("yyyyMMdd");
        String lastMonth2 = dft2.format(cal2.getTime());
        String repeatDate = lastMonth2;
        System.out.println(repeatDate); // 20180605
        // 获取任意时间的上一个月
        repeatDate = "20180104";
        String lastMonth = "";
        Calendar cal = Calendar.getInstance();
        //SimpleDateFormat dft = new SimpleDateFormat("yyyyMM");
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
        int year2 = Integer.parseInt(repeatDate.substring(0, 4));
        String monthsString = repeatDate.substring(4, 6);
        int month2;
        if ("0".equals(monthsString.substring(0, 1))) {
            month2 = Integer.parseInt(monthsString.substring(1, 2));
        } else {
            month2 = Integer.parseInt(monthsString.substring(0, 2));
        }
        cal.set(year2,month2-2,Calendar.DATE);
        lastMonth = dft.format(cal.getTime());
        System.out.println("lastMonth...."+lastMonth);//  20171205
        System.out.println("lastMonth...."+lastMonth);


        // 获取当前年份、月份、日期
        Calendar cale = null;
        cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH) + 1;
        int day = cale.get(Calendar.DATE);
        int hour = cale.get(Calendar.HOUR_OF_DAY);
        int minute = cale.get(Calendar.MINUTE);
        int second = cale.get(Calendar.SECOND);
        int dow = cale.get(Calendar.DAY_OF_WEEK);
        int dom = cale.get(Calendar.DAY_OF_MONTH);
        int doy = cale.get(Calendar.DAY_OF_YEAR);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // 获取当前日期字符串
        Date d = new Date();
        System.out.println("当前日期字符串1：" + format.format(d));
        System.out.println("当前日期字符串2：" + year + "/" + month + "/" + day + " "
                + hour + ":" + minute + ":" + second);

        String nowmonth = "";
        String monrhZH = "一,二,三,四,五,六,七,八,九,十,十一,十二";
        String[] montharr = monrhZH.split(",");
        for(int i=0;i<montharr.length;i++){
            if(month==(i+1)){
                nowmonth = montharr[i];
                System.out.println("当前日期月份：" + nowmonth); // 五
            }
        }

        int p2=0;
            System.out.println(p2+1); // 1
            System.out.println(p2); // 0
            int current = 20;
            if(rangeInDefined(current, 0, 10)) {
                System.out.println(current + "在1——10之间"); // >1  有 10
            }
            if(rangeInDefined(current, 10, 20)){
                System.out.println(current + "在10——20之间"); // >10   ... 20
            }
            if(rangeInDefined(current, 20, 9999)){
            System.out.println(current + "在20——无尽之间"); // >10   ... 20
            }

        BigDecimal current2 = new BigDecimal(20);
        if(rangeInDefinedG(current2.doubleValue(), 0, 10)) {
            System.out.println(current2 + "g在1——10之间"); // >1  有 10
        }
        if(rangeInDefinedG(current2.doubleValue(), 10, 20)){
            System.out.println(current2 + "g在10——20之间"); // >10   ... 20
        }
        double iiii=9999;
        if(rangeInDefinedG(current2.doubleValue(), 20, iiii/0)){
            System.out.println(current2.doubleValue() + "g在20——无尽之间"); // >10   ... 20
        }

        // java  中  无穷大   用 正整数 除以 0   即  9.0/0    为 double  类型 才不会报错 。

        BigDecimal currentMine = new BigDecimal(20);
        if(rangeInDefinedMine(currentMine.doubleValue(), 0, 10)) {
            System.out.println(currentMine + "m在1——10之间"); // >1  有 10
        }
        if(rangeInDefinedMine(currentMine.doubleValue(), 10, 20)){
            System.out.println(currentMine + "m在10——20之间"); // >10   ... 20
        }
        if(rangeInDefinedMine(currentMine.doubleValue(), 20, new BigDecimal(9999).doubleValue()/0)){
            System.out.println(currentMine.doubleValue() + "m在20——无尽之间"); // >10   ... 20
        }



        String aat = NULL;
        if(aat.length()>0){
            System.out.println("aa.lngth...."+aat.length()); //  4
        }else{
            System.out.println("aa....."+aat.length()); // 0
        }
        String aaaaa= "2223";
        System.out.println(aaaaa.substring(0,aaaaa.length()-1));
        String[] result = {};
        System.out.println("result.lngth...."+result.length); // 0

        String[] arr0 = new String[]{""};
    System.out.println("arr0.lngth...."+arr0.length); // 1
    if(arr0[0]!=""){
        System.out.println("arr0[]....."+arr0[0]);
    }

        /** 数组 */
        String[] arr1 = new String[]{"1","2","3","4"}; //新的
        String[] arr2 = new String[]{"2","3","4","5"};  // 老的、
        /**交集 */
       String[] arr3 = intersect(arr1,arr2);
       System.out.println("arr3....."+arr3.length);
       for(int i=0;i<arr3.length;i++){
           System.out.println("arr3[]....."+arr3[i]);  // 2,3,4
       }
       /** 差集*/
       String[] arr4 = minus(arr2,arr3);  //  老的 与 交集   得到  不检验的
        System.out.println("arr4....."+arr4);
        for(int i=0;i<arr4.length;i++){
            System.out.println("arr4[]....."+arr4[i]);  //5
        }
        /** 差集*/
        String[] arr5 = minus(arr1,arr3);  //  新的 与 交集   得到  不检验的
        System.out.println("arr5....."+arr5);
        for(int i=0;i<arr5.length;i++){
            System.out.println("arr5[]....."+arr5[i]);  // 1
        }



//        Integer aa = Integer.valueOf("");
//        Integer aa = Integer.valueOf(null); // 抛异常
String test = "123";
System.out.println("123.length==="+ test.length()); // 3
        if(test.indexOf(",")==-1){
            System.out.println("不包含"); // 3
        }
        String checkItemsIds = "1234,234";// 检测指标ids
        String[] guids = null;
        if(checkItemsIds.indexOf(",")!=-1){
            guids = checkItemsIds.split(",");
            System.out.println("guids=="+guids[1]); // 3
        }else{
            guids = new String[1];
            guids[0] = checkItemsIds;
            System.out.println("guids[0]=="+guids[0]); // 3
        }
//        String[] guids2 = null;
//        guids2[0]="2";   错误
//        System.out.println("guids2[0]=="+guids); // 3

        String testaa = "";
        System.out.println("testaa.length==="+testaa.length()); // 3

        String aub = "bbaaa.jpg";
        aub.lastIndexOf(".");
       System.out.println( aub.lastIndexOf("."));// 5
       System.out.println(aub.indexOf("."));//5
        System.out.println(aub.substring(aub.indexOf("."))); // .jpg
        System.out.println(aub.substring(aub.indexOf(".")+1)); // jpg
        System.out.println(aub.split(".")); //[Ljava.lang.String;@5c29bfd
        System.out.println(aub.substring(0,aub.indexOf("."))); // bbaaa
        Integer aa = null;
        if(aa==null){
            System.out.println("111");
        }
        if(aa!=null){
            System.out.println("2222");
        }
        String stra = "123abc4abc56ab78c9abc";
        String [] ary = ("," + stra + ",").split("abc");
        System.out.println("ABC的个数 : " + (ary.length - 1)); // 3
        String strrr = "  1234  abc56  ab78  c9abc";
        String [] aryy = ("," + strrr + ",").split("  ");
        System.out.println("双空格的个数 : " + (aryy.length - 1)); // 4


        Timestamp time= new Timestamp(new Date().getTime());
        SimpleDateFormat timefor=new SimpleDateFormat("yyyy-MM-dd");
        String str =timefor.format(time.getTime());
        String nowDate=time.toString().substring(0,10);
        String oldDate = "2018-03-29";
        String olddate1 = "2018-03-31";
        String olddate4 = "2018-03-30";
        String olddate2 = "";
        String olddate3 = "";
        if(nowDate.compareTo(olddate4)<=0){
            System.out.println("<=<=<="); // true
        }
        if(nowDate.compareTo(oldDate)>0){
            System.out.println(">>>"); // true
        }
        if(nowDate.compareTo(olddate1)>0){
            System.out.println("===<<<"); // false
        }
        if(nowDate.compareTo(olddate2)>0){
            System.out.println("空字符串"); // true
        }
        if(olddate2.compareTo(olddate3)>0){
            System.out.println("都是空字符串");//false
        }

        if(oldDate.compareTo(nowDate)>0){
            nowDate = oldDate;
        }
        if(nowDate.compareTo(olddate1)<0){
            nowDate = olddate1;
        }
        System.out.println(nowDate);
        int a = 4;
        int b = 2 ;
        int c = 3;
        int flag = a>=b?(a>=c?(b>=c?b:c):a):(a>=c?a:(b>=c?c:b));
        System.out.println("flag = "+flag); // 3
        System.out.println(a>=c?a:(b>=c?c:b));// 4
        //oldDate.compareTo(olddate1)>0?oldDate:(olddate2.compareTo(olddate1)>0?olddate1:olddate2);
    }



    //求两个数组的交集
    public static String[] intersect(String[] arr1, String[] arr2) {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        LinkedList<String> list = new LinkedList<String>();
        for (String str : arr1) {
            if (!map.containsKey(str)) {
                map.put(str, Boolean.FALSE);
            }
        }
        for (String str : arr2) {
            if (map.containsKey(str)) {
                map.put(str, Boolean.TRUE);
            }
        }

        for (Map.Entry<String, Boolean> e : map.entrySet()) {
            if (e.getValue().equals(Boolean.TRUE)) {
                list.add(e.getKey());
            }
        }
        String[] result = {};
        return list.toArray(result);
    }

    //求两个数组的差集
     public static String[] minus(String[] arr1, String[] arr2) {
                LinkedList<String> list = new LinkedList<String>();
                LinkedList<String> history = new LinkedList<String>();
                 String[] longerArr = arr1;
                 String[] shorterArr = arr2;
                 //找出较长的数组来减较短的数组
                 if (arr1.length > arr2.length) {
                        longerArr = arr2;
                       shorterArr = arr1;
                    }
                for (String str : longerArr) {
                        if (!list.contains(str)) {
                                list.add(str);
                          }
                     }
               for (String str : shorterArr) {
                        if (list.contains(str)) {
                                history.add(str);
                                list.remove(str);
                             } else {
                               if (!history.contains(str)) {
                                       list.add(str);
                                 }
                          }
                   }

                 String[] result = {};
                 return list.toArray(result);
            }

}
