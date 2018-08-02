package com.one.javatest.exportWord.twoMain;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

//import org.apache.poi.ss.util.ImageUtils;


/**
 * Created by vtstar on 2018/2/26.
 */
public class DocumentHandler2 {

    private Configuration configuration = null;

    public DocumentHandler2() {
        configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");
    }

    public void createDoc(){
        // 要填入模本的数据文件
        Map<String,Object> dataMap=new HashMap<String,Object>();
        getData(dataMap);
        //设置模本装置方法和路径，FreeMaker支持多种模板装载方法。可以重servlet，classpath,数据库装载
        // 这里我们的模板是放在com.php.documnet.template包下面    /com/one/javatest/exportWord/resource/freeMaker
        configuration.setClassForTemplateLoading(this.getClass(),"/com/one/javatest/exportWord/resource/freeMaker");
        Template t = null;
        // freeMakerTest.ftl为要装载的模板
        try {
            t = configuration.getTemplate("freeMakerTest2.ftl");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 输出文档路径及名称
       // File outfile = new File("../outFile.docx"); // SpringMVCDemo1/outFile.docx
        File outfile = new File("../SSH/web/WEB-INF/outFile/2outFile-"+System.currentTimeMillis()+".doc");  //  docx  不可以
        Writer out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outfile),"utf-8"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            t.process(dataMap,out);
            out.flush();
            out.close();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 注意dataMap里存放的数据Key值要与模板中的参数相对应
     * @param dataMap
     */
    private void getData(Map<String,Object> dataMap)
    {
        dataMap.put("maker", "aaaa");
        dataMap.put("remarks", "测试freemarker");
        dataMap.put("time", new Date().toString());
        //  替换图片
        try {
            URL imageUrl=   new URL("http://d.hiphotos.baidu.com/image/w%3D230/sign=d9b34028708b4710ce2ffacff3cec3b2/83025aafa40f4bfb98ff265d014f78f0f736188f.jpg");
            //URL imageUrl2 = new URL("../SSH/src/com/one/javatest/exportWord/resource/images/jiyeon2___20184498.jpg");
            //URL imageUrl2 = new URL("../SSH/out/production/SSHTest1/com/one/javatest/exportWord/resource/images/jiyeon2___20184498.jpg");
            //File imageFile = new File("../SSH/src/com/one/javatest/exportWord/resource/images/1517452695(1).jpg");
            /** 4-3 给图片 设置为  透明*/
            //transpic();
            //transPicture("../SSH/src/com/one/javatest/exportWord/resource/images/印章.jpg");
            File imageFile = new File("../SSH/src/com/one/javatest/exportWord/resource/images/word过来的透明印章.png");
            //File imageFile = new File("../SSH/src/com/one/javatest/exportWord/resource/images/白色.png");
            String imageBase64 = ImageUtils.encodeImgageToBase64(imageFile);
           // String imageBase64 = ImageUtils.encodeImgageToBase64(imageUrl2);
            //System.out.println("imageBase64==:"+imageBase64);
            dataMap.put("picture", imageBase64);
            dataMap.put("picture2", imageBase64);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**  当图片 已经 是 透明背景事， 在进行转化 会 变为 黑色背景，且 里面的内容不清楚*/
    public static void transPicture(String picpath) {
        String path = "../SSH/src/com/one/javatest/exportWord/resource/images";
        if (path == null || !new File(path).isDirectory()) {
            JOptionPane.showMessageDialog(null, "输入目录有误！");
            return;
        }
        String color = "0";
        if (isNo(color)) {
            color_range = 255 - Integer.parseInt(color);
            String pictype = picpath.substring(picpath.lastIndexOf(".")+1);
            System.out.println("pictype==="+pictype);
            if(pictype.equals("jpg")){
                convert(picpath);//
            }

            File file = new File(path);
            System.out.println("path=="+path);
            String[] files = file.list();
            for (int i = 0; i < files.length; i++) {
                String ext = files[i].substring(files[i].lastIndexOf(".") + 1);
                if (ext.equals("jpg")) {
                    System.out.println("111111111");
                    //convert(path + "//" + files[i]);
                }
            }
            JOptionPane.showMessageDialog(null, "转换完成！");
        } else {
            JOptionPane.showMessageDialog(null, "输入的数字有误！");
        }
    }

    public static void convert(String path) {
        // TODO Auto-generated constructor stub
        try {
            BufferedImage image = ImageIO.read(new File(path));
            ImageIcon imageIcon = new ImageIcon(image);
            BufferedImage bufferedImage = new BufferedImage(
                    imageIcon.getIconWidth(), imageIcon.getIconHeight(),
                    BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D g2D = (Graphics2D) bufferedImage.getGraphics();
            g2D.drawImage(imageIcon.getImage(), 0, 0,
                    imageIcon.getImageObserver());
            int alpha = 0;
            for (int j1 = bufferedImage.getMinY(); j1 < bufferedImage
                    .getHeight(); j1++) {
                for (int j2 = bufferedImage.getMinX(); j2 < bufferedImage
                        .getWidth(); j2++) {
                    int rgb = bufferedImage.getRGB(j2, j1);
                    if (colorInRange(rgb))
                        alpha = 0;
                    else
                        alpha = 255;
                    rgb = (alpha << 24) | (rgb & 0x00ffffff);
                    bufferedImage.setRGB(j2, j1, rgb);
                }
            }
            g2D.drawImage(bufferedImage, 0, 0, imageIcon.getImageObserver());
            // 生成图片为PNG
            String outFile = path.substring(0, path.lastIndexOf("."));
            System.out.println("outFile=="+outFile);
            ImageIO.write(bufferedImage, "png", new File(outFile + ".png"));
            // 释放对象
            g2D.dispose();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static boolean colorInRange(int color) {
        int red = (color & 0xff0000) >> 16;
        int green = (color & 0x00ff00) >> 8;
        int blue = (color & 0x0000ff);
        if (red >= color_range && green >= color_range && blue >= color_range)
            return true;
        return false;
    }

    public static int color_range = 210;
    public static Pattern pattern = Pattern.compile("[0-9]*");

    public static boolean isNo(String str) {
        return pattern.matcher(str).matches();
    }


    /** 另一种：没用 */
    public static void transpic() throws IOException {
        Image ima=ImageIO.read(new File("../SSH/src/com/one/javatest/exportWord/resource/images/印章.png"));
        BufferedImage bufIma=new BufferedImage(ima.getWidth(null),ima.getHeight(null),BufferedImage.TYPE_INT_BGR);

        //这里是关键部分
        Graphics2D g=bufIma.createGraphics();
        bufIma = g.getDeviceConfiguration().createCompatibleImage(ima.getWidth(null), ima.getHeight(null), Transparency.TRANSLUCENT);
        g = bufIma.createGraphics();

        g.drawImage(ima, 0, 0, null);
        ImageIO.write(bufIma, "png", new File("../SSH/src/com/one/javatest/exportWord/resource/images/印章.png"));
    }

    public  static void main(String[] args){
        DocumentHandler2 documentHandler = new DocumentHandler2();
        documentHandler.createDoc();
    }
}
