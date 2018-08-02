package com.one.javatest.transferWords.testOne;

/**
 * Created by vtstar on 2018/2/28.
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.StringTokenizer;

/**文件翻译，将字节数组变为字符串，分离其中的单词，然后翻译为对应的汉字，去掉空格,变为字符串*/
public class TxtTrans {

    private Properties pps;
    public TxtTrans(){
        loadCiku();
    }
    public void loadCiku(){
        pps = new Properties();
        // 路径+ 文件
        String pathName = "../SSH/src/com/one/javatest/transferWords/testOne/ciku.txt";
        // 以字符 载入时没有乱码， 以字节载入时 出现了乱码
        try {
            FileReader fis = new FileReader(pathName);
            pps.load(fis);
            fis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace(System.out);
            System.out.println("载入词库时出错");
        }
    }

    public String trans(byte[] data){
        String srcTxt = new String(data);
        String dsTxt = srcTxt;
        String delim = " ,.!\n\t"; // 分隔符可以指定

        StringTokenizer st = new StringTokenizer(srcTxt,delim,false);
        String sub,lowerSub,newSub;
        while(st.hasMoreTokens()){
            sub = st.nextToken();  // 分割出的一个个单词
            lowerSub = sub.toLowerCase(); // 同意转换为小写，这样可以简化词库
            newSub = pps.getProperty(lowerSub);
            // 如果找到了匹配的汉字，则进行替换
            if(newSub!=null){
                //  //只替换第一个，即只替换了当前的字符串，ch i na 否则容易造成 ch我na的例子
                dsTxt = dsTxt.replaceFirst(sub,newSub);
            }
        }
        return dsTxt.replaceAll(" ",""); // 去掉空格
    }

}
