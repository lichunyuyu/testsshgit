package com.one.javatest.exportPDF.oneMain;

/**
 * Created by vtstar on 2018/10/16.
 */

/**
 * jacob技术，也是本文要讲解的技术。
 * 这个的实现思路是通过Java代码控制Windows系统上的office软件，进行转换。
 * （仅限Windows服务器）
 * 下载jacob的相关jar包。里面主要包含jacob.jar和jacob-1.18-x64.dll，jacob-1.18-x86.dll三个文件。
 * 2.excel转PDF的话，一般要安装savapdf的插件。去下载SaveAsPDFandXPS.exe并一步步安装。
 3.Excel转PDF，还容易报缺少打印机的错误。去Windows的本地服务里开启print spooler服务
 4.excel转PDF，还容易出现PDF显示不全的问题。解决的方法是，打开Excel，然后分页预览，将两条蓝色的边线，分别拉倒两侧，（当前文件有多宽，就设置多宽）然后保存。
 参考https://jingyan.baidu.com/article/48b558e3266ab17f38c09ac0.html
 5.准备其他需要的jar包。有poi-ooxml-3.9.jar，poi-3.9.jar，ooxml-schemas-1.1.jar，dom4j-2.0.0-RC1.jar
  --  jacob-1.10.jar

 报错  Exception in thread "main" java.lang.NoClassDefFoundError: Could not initialize class com.jacob.com.ComThread

 https://sourceforge.net/projects/jacob-project/postdownload    下载 jacob-1.19 .zip
将 C:\Program Files\Java\jdk1.8.0_131\jre\bin     放入 jacob-1.19-x64.dll 文件
  C:\Windows\System32                放入 jacob-1.19-x64.dll 文件
 https://sourceforge.net/projects/jacob-project/files/jacob-project/1.19/jacob-1.19.zip/download
 ok

 ---
 后换用  D:\10.5-office-test\jacob_jb51\jacob_jb51\jacob-1.17-M2（支持64位和32位）\jacob-1.17-M2
 jacob-1.17-M2-x64.dll  也是 可以的。

 放到tomcat之后
 1. com.jacob.com.ComFailException: VariantChangeType failed
 在C:\Windows\System32\config\systemprofile下创建文件夹Desktop即可。
 C:\Windows\SysWOW64\config\systemprofile\Desktop还有这个路径也要有
 2.com.jacob.com.ComFailException: Invoke of: ExportAsFixedFormat
 可能输出pdf路径名称不对
 * */

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.File;

public class Office2pdf {

    //static final int wdFormatPDF = 17;// PDF 格式

    /**
     * excel转PDF
     * */
    public static void excel2Pdf(String inFilePath,String outFilePath){
        ActiveXComponent ax = null;
        Dispatch excel = null;
        try{
            ComThread.InitSTA();
            ax = new ActiveXComponent("Excel.Application");
            ax.setProperty("Visible",new Variant(false));
            ax.setProperty("AutomationSecurity",new Variant(3));//禁用宏
            Dispatch excels = ax.getProperty("Workbooks").toDispatch();
            Object[] obj = new Object[]{
                    inFilePath,
                    new Variant(false),
                    new Variant(false)
            };
            excel = Dispatch.invoke(excels,"Open",Dispatch.Method,obj,new int[9]).toDispatch();
            File toFile = new File(outFilePath);
            if(toFile.exists()){
                toFile.delete();
            }
            // 替换格式
            Object[] obj2 = new Object[]{
                    new Variant(0), // PDF 格式 = 0
                    outFilePath,
                    new Variant(0) // 0 = 标准（生成的PDF图片不会变模糊）； 1 = 最小文件
            };
            Dispatch.invoke(excel,"ExportAsFixedFormat",Dispatch.Method,obj2,new int[1]);
            System.out.println("转换完毕!");
        }catch (Exception es){
            es.printStackTrace();
            throw es;
        }finally {
            if(excel!=null){
                Dispatch.call(excel,"Close",new Variant(false));
            }
            if(ax!=null){
                ax.invoke("Quit",new Variant[]{});
                ax = null;
            }
            ComThread.Release();
        }
    }

    /**
     * word 转pdf
     * */
    public static int word2Pdf(String sfileName,String toFileName){
        System.out.println("启动Word...");
        long start = System.currentTimeMillis();
        ActiveXComponent app = null;
        Dispatch doc = null;
        try{
            app = new ActiveXComponent("Word.Application");
            app.setProperty("Visible",new Variant(false));
            // 打开 word 文件
            Dispatch docs = app.getProperty("Documents").toDispatch();
            // doc = Dispatch.call(docs,  "Open" , sourceFile).toDispatch();
            doc = Dispatch.invoke(docs,"Open",Dispatch.Method,new Object[]{
               sfileName,
                    new Variant(false),
                    new Variant(false)
            },new int[1]).toDispatch();
            System.out.println("打开文档..."+sfileName);
            System.out.println("转换文档到PDF..."+toFileName);
            File tofile = new File(toFileName);
            // System.err.println(getDocPageSize(new File(sfileName)));
            if(tofile.exists()){
                tofile.delete();
            }
            //Dispatch.call(doc, "SaveAs",  destFile,  17);
            // 作为html格式保存到临时文件：：new Variant(8)其中8表示word转html;7表示word转txt;44表示Excel转html;17表示word转成pdf。
            Dispatch.invoke(doc,"SaveAs",Dispatch.Method,new Object[]{
                    toFileName,
                    new Variant(false),
                    new Variant(false),
            },new int[1]);

            long end = System.currentTimeMillis();
            System.out.println("转换完成..用时："+(end - start)+"ms.");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("========Error:文档转换失败：" + e.getMessage());
        }catch (Throwable t){
            t.printStackTrace();
        }finally {
            // 关闭word
            Dispatch.call(doc,"Close",false);
            System.out.println("关闭文档");
            if(app!=null){
                app.invoke("Quit",new Variant[]{});
            }
            //如果没有这句winword.exe进程将不会关
            ComThread.Release();
            return 1;
        }
    }


    public static int word2Pdf2(String sfileName,String toFileName) throws Exception{

        System.out.println("启动Word...");
        long start = System.currentTimeMillis();
        ActiveXComponent app = null;
        Dispatch doc = null;
        try {
            app = new ActiveXComponent("Word.Application");
            app.setProperty("Visible", new Variant(false));
            // 打开word文件
            Dispatch docs = app.getProperty("Documents").toDispatch();
//          doc = Dispatch.call(docs,  "Open" , sourceFile).toDispatch();
            doc = Dispatch.invoke(docs,"Open",Dispatch.Method,new Object[] {
                    sfileName, new Variant(false),new Variant(true) }, new int[1]).toDispatch();
            System.out.println("打开文档..." + sfileName);
            System.out.println("转换文档到PDF..." + toFileName);
            File tofile = new File(toFileName);
            // System.err.println(getDocPageSize(new File(sfileName)));
            if (tofile.exists()) {
                tofile.delete();
            }
//          Dispatch.call(doc, "SaveAs",  destFile,  17);
            // 作为html格式保存到临时文件：：new Variant(8)其中8表示word转html;7表示word转txt;44表示Excel转html;17表示word转成pdf。
            Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[] {
                    toFileName, new Variant(17) }, new int[1]);
            long end = System.currentTimeMillis();
            System.out.println("转换完成..用时：" + (end - start) + "ms.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("========Error:文档转换失败：" + e.getMessage());
        }catch(Throwable t){
            t.printStackTrace();
        } finally {
            // 关闭word
            Dispatch.call(doc,"Close",false);
            System.out.println("关闭文档");
            if (app != null)
                app.invoke("Quit", new Variant[] {});
        }
        //如果没有这句winword.exe进程将不会关
        ComThread.Release();
        return 1;
    }


    private static Document read(File xmlFile) throws DocumentException {
        SAXReader saxReader = new SAXReader();
        return saxReader.read(xmlFile);
    }
    public int getDocPageSize(String filePath)  throws Exception {
        XWPFDocument docx = new XWPFDocument(POIXMLDocument.openPackage(filePath));
        int pages = docx.getProperties().getExtendedProperties().getUnderlyingProperties().getPages();//总页
        int wordCount = docx.getProperties().getExtendedProperties().getUnderlyingProperties().getCharacters();// 忽略空格的字符另外还有getCharactersWithSpaces()方法获取带空格的总字数�?
        System.out.println ("pages=" + pages + " wordCount=" + wordCount);
        return pages;
    }

    // "../SSH/src/com/one/javatest/exportPDF/image/2.jpg"
    // D:\MyGitHubProjects\SSH\src\com\one\javatest\exportPDF\resources
    public static void main(String[] args) throws Exception {
        //excel2Pdf("../SSH/src/com/one/javatest/exportPDF/resources/ReportInfo-wuli.xlsx", "../SSH/src/com/one/javatest/exportPDF/outFiles/ReportInfo-wuli.pdf");

        //webapp目录下
//        String filePath = reportInfoFile0.getFilePath();// 两个一样  /resource/upload/main/pa/testreport/ReportInfoFile
//        String path0=requestContext.getRequest().getSession().getServletContext().getRealPath(filePath);
//        String templateFileName0 = path0+"\\"+saveToFileName0;
//        templateFileName0 = templateFileName0.replaceAll("\\\\","/"); //  C:/Users/vtstar/fap/webapp/resource/upload/main/pa/testreport/ReportInfoFile/9.jpg

        word2Pdf2("D:\\MyGitHubProjects\\SSH\\src\\com\\one\\javatest\\exportPDF\\resources\\ExternalReport---wj.doc"
                , "D:\\MyGitHubProjects\\SSH\\src\\com\\one\\javatest\\exportPDF\\outFiles\\ExternalReport---wj.pdf");

        word2Pdf2("D:\\MyGitHubProjects\\SSH\\src\\com\\one\\javatest\\exportPDF\\resources\\ExternalReport---wj4.docx"
                , "D:\\MyGitHubProjects\\SSH\\src\\com\\one\\javatest\\exportPDF\\outFiles\\ExternalReport---wj4.pdf");

        excel2Pdf("D:\\MyGitHubProjects\\SSH\\src\\com\\one\\javatest\\exportPDF\\resources\\ReportInfo-wthl.xlsx"
                , "D:\\MyGitHubProjects\\SSH\\src\\com\\one\\javatest\\exportPDF\\outFiles\\ReportInfo-wthl.pdf");


//        word2Pdf("D:\\MyGitHubProjects\\SSH\\src\\com\\one\\javatest\\exportPDF\\resources\\ExternalReport---wj4.docx"
//                , "D:\\MyGitHubProjects\\SSH\\src\\com\\one\\javatest\\exportPDF\\outFiles\\ExternalReport---wj4.pdf");

    }

}
