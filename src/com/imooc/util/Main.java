package com.imooc.util;

import java.io.IOException;

/**
 * Created by vtstar on 2018/3/19.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        //String path="D:\\SwingProjectTests\\SpringProjectsTesto\\SpringMVCDemo1\\SSH\\web\\testerweima.jpg";
        //String content="hello";
        String path="D:\\SwingProjectTests\\SpringProjectsTesto\\SpringMVCDemo1\\SSH\\web\\csdn.jpg";
        //String content = "https://pan.baidu.com/s/1qlzUhyGRg9pvNKhoixiLhA";  // 网盘生成的链接  永久分享   csdn.apk
       // String content = "http://192.168.0.101:8081/fap/core/downloadFileService/stream/downloadFile.do?number=csdn.apk&name=/resource/upload/main/appFile/AppFileUpload/3.apk";
        String content = "https://fir.im/jf9w";//
        String a = null;
        System.out.println("a="+a);// a=null
        if(a==null){
            System.out.println("11");
        }
        a = "非空";
        if(a==null){
            System.out.println("22");
        }
        QRCodeUtils encoder = new QRCodeUtils();
        encoder.encoderQRCoder(content,path);

        //String netUrl

    }
}
