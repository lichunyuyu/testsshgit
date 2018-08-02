package com.one.javatest.fileAbout.fileReader;

import java.io.*;

/**
 * Created by vtstar on 2018/1/8.
 */
public class FileRead {

    public static void main(String[] args) throws IOException {

        File file = new File("testFile.txt");  // 创建在根目录下  D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\testFile.txt
       //1.2 获取当前路径(放在当前目录下)
        File file2 = new File(".");
        File file3 = new File("./src/com/one/javatest/fileAbout/fileReader");     // D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\src\com\one\javatest\fileAbout\fileReader
        // //对于getCanonicalPath()函数，“."就表示当前的文件夹，而”..“则表示当前文件夹的上一级文件夹
        String path2 = file2.getCanonicalPath();
        String path = file.getCanonicalPath();
        System.out.println("path= "+path);//path=D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\testFile.txt
        System.out.println("path2= "+path2); //path2=D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH
        System.out.println("path3= "+file3.getCanonicalPath());// D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\src\com\one\javatest\fileAbout\fileReader
        ////对于getAbsolutePath()函数，则不管”.”、“..”，返回当前的路径加上你在new File()时设定的路径
        String path3 = file.getAbsolutePath();
        System.out.println("path、1= "+path3); // path3= D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\testFile.txt
        System.out.println("path、2= "+file2.getAbsolutePath()); // D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\.
        System.out.println("path、3=" +file3.getAbsolutePath()); // D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\.\src\com\one\javatest\fileAbout\fileReader
       ////至于getPath()函数，得到的只是你在new File()时设定的路径 比如当前的路径为
        String path4 = file.getPath();
        System.out.println("path--1= "+path4); //testFile.txt
        System.out.println("path--2= "+file2.getPath()); // .
        System.out.println("path--3="+file3.getPath()); // .\src\com\one\javatest\fileAbout\fileReader

        File file4 = new File("./src/com/one/javatest/fileAbout/fileReader/testFile.txt"); // 创建在 当前目录下
        // 创建文件
        file4.createNewFile();
        //创建一个FileWriter对象
        FileWriter writer = new FileWriter(file4);
        //向文件写入/内容
        writer.write("This\n is2\n an\n example\n");
        writer.flush();
        writer.close();
        File file5 = new File("./src/com/one/javatest/fileAbout/fileReader/FileReader.txt");
        //创建FileReader 对像
        FileReader fr = null;
        FileWriter fw = null;
        try{
             fr = new FileReader(file5);
            char[] a = new char[50];
            // 读取数组中的内容
//            fr.read(a);
//            for(char c : a){
//                //一个一个打印字符
//                System.out.println(c);
//            }
            //读一个字符  写一个 字符
            File filewrite = new File("./src/com/one/javatest/fileAbout/fileReader/testFilewrite.txt");
            //filewrite.createNewFile();

             fw = new FileWriter(filewrite);
             //解决乱码-----另一篇--------------------------
            //Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filewrite),"UTF-8"));
            System.out.println("fw.getEncoding();="+fw.getEncoding());  // 没有setEncoding()
            //（1）读一个字符，写一个字符方法
            System.out.println("fr.read()=="+fr.read()); // -1
            System.out.println("fr.read(a)=="+fr.read(a)); // -1
            int ch = 0;
            while ((ch = fr.read()) != -1) {
                fw.write(ch);  // 指定的字节写进去
                System.out.println("???"+ch);
            }
            //（2）读一个数组大小，写一个数组大小方法。
            int len=0;
            while((len = fr.read(a)) != -1){
                System.out.println("???"+len);
                fw.write(a, 0, len);

            }
            System.out.println("000000000000000");
        }catch (Exception e){
            System.out.println("e.."+e.toString());
            e.printStackTrace();
        }finally {
            if (fr != null){
                try {
                    fr.close();
                } catch (Exception e2) {
                    throw new RuntimeException("关闭失败！");
                }
            }
            if(fw!=null){
                try{
                    fw.close();
                }catch (IOException e){
                    throw new RuntimeException("关闭失败！");
                }
            }
        }

        FileReader fr2 = new FileReader("./src/com/one/javatest/fileAbout/fileReader/FileReader.txt");
        char[] a2 = new char[1024];
        int len=0;
        int num = fr2.read(a2,0,5);
        System.out.println("num= "+num);
        fr2.close();
    }

}
/**
 * FileReader类 是从InputStreamReader类继承而来。该类按照字符 读取 流 中数据。可以通过一下几种构造方法创建需要的对象。
 * 1.在给定 从中 读取数据的 File 的情况下 创建一个新的对象FileReader
 *      FileReader(File file)
   2.在给定 从中读取数据的 FileDescriptor 的情况下创建一个新的 FileReader.
        FileReader(FileDescriptor fd)
   3.在给定 从中读取数据的文件名的情况下创建一个新的FileReader
        FileReader(String fileName)
 创建FileReader对象成功后,可以参照一下列表里的方法操作文件
    1.读取单个字符，返回一个 int 型变量代表读取到的字符
        public int read() throws IOException
    2. 读取字符 到 cbuf 数组，返回 读取到字符的个数
        public int read(char[] cbuf ,int offset,int len)
            cbuf --目标字符缓冲区
            offset -- 偏移量开始存储字符的位置
            length -- 要读取的字符数上限值
            返回值
                该方法返回读取的字符数，否则如果流的末尾已到达返回-1。
 * */

/**
 结果：
 T
 h
 i
 s



 i
 s



 a
 n
 * */