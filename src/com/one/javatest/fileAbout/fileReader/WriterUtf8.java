package com.one.javatest.fileAbout.fileReader;

import java.io.*;

/**
 * Created by vtstar on 2018/1/9.
 */
public class WriterUtf8 {

        public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
            File fileq = new File("./src/com/one/javatest/fileAbout/fileReader/FileReader.txt");
            FileInputStream fipsq = new FileInputStream(fileq);
            InputStreamReader ipsrq = new InputStreamReader(fipsq,"GBK");   //  UTF-8   还是乱码
                               LineNumberReader lnr=null;
                   int lineNumber=0;
                   int totalLine=0;
                    lnr = new LineNumberReader(ipsrq);
                   lineNumber = (int)lnr.lines().count();
                   System.out.println("lineNumber="+lineNumber);
                   totalLine = lineNumber%50==0?lineNumber/50:lineNumber/50+1;
                   System.out.println("totalLine"+totalLine);

            String fileContent = "";
            // 读取 文件
            //File file = new File("./src/com/one/javatest/fileAbout/fileReader/testFile.txt");
            File file = new File("./src/com/one/javatest/fileAbout/fileReader/FileReader.txt");
           try{
               if(file.isFile() && file.exists()){
                   FileInputStream fips = new FileInputStream(file);
                   InputStreamReader ipsr = new InputStreamReader(fips,"GBK");   //  UTF-8   还是乱码
                   BufferedReader bfr = new BufferedReader(ipsr);
                  //获取总行数       以及 每 50行 共计次    这里用的话，下面的   bfr 就没有了
                   //long lineCount = bfr.lines().count();
                   //int totalPage = (int) (lineCount%50==0?lineCount/50:lineCount/50+1);
                   //System.out.println("lineCount"+bfr.lines().count());
                   //获取总行数
//                   LineNumberReader lnr=null;
//                   int lineNumber=0;
//                   int totalLine=0;
//                    lnr = new LineNumberReader(ipsr);
//                   lineNumber = (int)lnr.lines().count();
//                   System.out.println("lineNumber="+lineNumber);
//                   totalLine = lineNumber%50==0?lineNumber/50:lineNumber/50+1;
//                   System.out.println("totalLine"+totalLine);
                   String line  ;
                   int aa=0;
                   //bfr.mark(10);
                   int filename = 0;
                   while((line= bfr.readLine())!=null){
                       //fileContent+=line; // 只有一行
                       fileContent=fileContent+line+"\n";  // 手动加
//                       //读10行之后，在重新读取
                       aa++;
//                       if(aa==10){
//                           //aa=0; 会循环
//                           bfr.reset();  //  不重置aa  只会reset() 一次 ，即重头读取一次
//                       }

                       // 每 50行 存一次
                       // 文件名

                       // 最后一个文件时  aa%50!=0 && (aa%50+1)==totalLine
                       //if(aa%50==0 || (aa%50!=0 && ((aa%50)+1)==totalLine)){
                       if(aa%50==0 || (aa%50!=0 && ((aa%50)+1)==totalLine)){
                       //if(aa%50==0 ){
                           //写入文件
                           filename+=1;
                           File file3 = new File("./src/com/one/javatest/fileAbout/fileReader/"+filename+".txt");
                           try{
                               FileOutputStream fops = new FileOutputStream(file3);
                               OutputStreamWriter opsw = new OutputStreamWriter(fops,"UTF-8");
                               BufferedWriter bfw = new BufferedWriter(opsw);
                               bfw.write(fileContent);
                               bfw.flush();
                               bfw.close();

                           }catch (Exception e){
                               System.out.println("写文件内容操作出错");
                               e.printStackTrace();
                           }
                       }
                   }


                   // 读一个数组  写一个数组    这样写 不行   BufferedReader  读取的 ， FileWriter读不到？
//                   File file2 = new File("./src/com/one/javatest/fileAbout/fileReader/testFilewrite.txt");
//                   FileOutputStream fops = new FileOutputStream(file2);
//                   OutputStreamWriter opsw = new OutputStreamWriter(fops,"UTF-8");
//                   BufferedWriter bfw = new BufferedWriter(opsw);
//                   int len=0;
//                   char[] a = new char[1024];
                   //bfr.read(a);  // 加上这句   下面在读就没了   -1
//                   while((len=bfr.read())!=-1){
//                       //System.out.println("a="+a[3]);// s
//                       bfw.write(len);//   不行？？
//                       System.out.println("???"+len);
//                   }

//                   int len =0;
//                   FileWriter fw = new FileWriter(file2);
//                   while ((len = bfr.read(a)) != -1) {
//                       fw.write(a,0,len);
//                       System.out.println("???"+len);
//                   }
//                   fw.close();
                   bfr.close();
//                   bfw.flush();
//                   bfw.close();
               }else{
                    file.createNewFile();
                   //创建一个FileWriter对象
                   FileWriter writer = new FileWriter(file);
                   //向文件写入/内容
                   writer.write("This\n is\n an\n example\n");
                   writer.flush();
                   writer.close();
               }
           }catch (Exception e){
               System.out.println("读取文件内容操作出错");
               e.printStackTrace();
           }
        System.out.println(fileContent);
           //写入文件
            File file2 = new File("./src/com/one/javatest/fileAbout/fileReader/testFilewrite.txt");
           try{
               FileOutputStream fops = new FileOutputStream(file2);
               OutputStreamWriter opsw = new OutputStreamWriter(fops,"UTF-8");
               BufferedWriter bfw = new BufferedWriter(opsw);
               bfw.write(fileContent);
               bfw.flush();
               bfw.close();

           }catch (Exception e){
               System.out.println("写文件内容操作出错");
               e.printStackTrace();
           }
        }
}
