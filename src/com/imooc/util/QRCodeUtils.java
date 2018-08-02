package com.imooc.util;

/**
 * Created by vtstar on 2018/3/19.
 */

import com.swetake.util.Qrcode;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * 首先要导入jar包（将jar包放到lib目录下）
 导入jar包的名称为：Qrcode_swetake

 在java中生成二维码并以流的形式输出
 * */
public class QRCodeUtils {

    public void encoderQRCoder(String content, HttpServletResponse response) {
        System.out.println("1111");
        try {
            //创建一个对象
            Qrcode handler = new Qrcode();
            //设置二维码的纠错能力
            //L 7% M 15% Q 25% H 30%
            handler.setQrcodeErrorCorrect('M');
            //以二进制的形式存储
            handler.setQrcodeEncodeMode('B');
            //设置二维码版本 40 1=21*21 2=25*25
            handler.setQrcodeVersion(7);
            System.out.println(content);
            //字符编码
            byte[] contentBytes = content.getBytes("UTF-8");
            //创建一个图像数据的缓冲区（创建一张纸）   这里的大小需要与 下面的大小相匹配，否则生成的二维码扫不出来  项目中有个png是不完整的 所以扫不出来
            /**设置的图片大小太小了，qrcode.setQrcodeVersion(7); 这个数字和你的图片宽度要匹配，需仔细看看你的二维码是不是完整的,多半就是这个原因，调整一下就好了*/
           // BufferedImage bufImg = new BufferedImage(80, 80, BufferedImage.TYPE_INT_RGB);
            BufferedImage bufImg = new BufferedImage(140, 140, BufferedImage.TYPE_INT_RGB);
            //创建一个笔
            Graphics2D gs = bufImg.createGraphics();
            //设置背景颜色为白色
            gs.setBackground(Color.WHITE);
            //填充到纸上来
            gs.clearRect(0, 0, 140, 140);

            //设定图像颜色：BLACK  //设置二维码的前景色-黑色
            gs.setColor(Color.BLACK);

            //设置偏移量  不设置肯能导致解析出错
            int pixoff = 2;
            //输出内容：二维码
            if(contentBytes.length > 0 && contentBytes.length < 124) {
                boolean[][] codeOut = handler.calQrcode(contentBytes);
                for(int i = 0; i < codeOut.length; i++) {
                    for(int j = 0; j < codeOut.length; j++) {
                        if(codeOut[j][i]) {
                            //根据不布尔数组绘制二维码矩形图
                            gs.fillRect(j * 3 + pixoff, i * 3 + pixoff,3, 3);
                        }
                    }
                }
            } else {
                System.err.println("QRCode&nbsp;content&nbsp;bytes&nbsp;length&nbsp;=&nbsp;" + contentBytes.length + "&nbsp;not&nbsp;in&nbsp;[&nbsp;0,120&nbsp;].&nbsp;");
            }

            gs.dispose();
            bufImg.flush();



            //生成二维码QRCode图片
            ImageIO.write(bufImg, "jpg", response.getOutputStream());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public void encoderQRCoder(String content, String path) {
        System.out.println("1111");
        try {
            //创建一个对象
            Qrcode handler = new Qrcode();
            //设置二维码的纠错能力
            //L 7% M 15% Q 25% H 30%
            handler.setQrcodeErrorCorrect('M');
            //以二进制的形式存储
            handler.setQrcodeEncodeMode('B');
            //设置二维码版本 40 1=21*21 2=25*25
            handler.setQrcodeVersion(7);
            System.out.println(content);
            //字符编码
            //byte[] contentBytes = content.getBytes("UTF-8");
            byte[] contentBytes= new String(content.getBytes("ISO-8859-1"),"UTF-8").getBytes();
            //创建一个图像数据的缓冲区（创建一张纸）
            BufferedImage bufImg = new BufferedImage(140, 140, BufferedImage.TYPE_INT_RGB);
            //创建一个笔
            Graphics2D gs = bufImg.createGraphics();
            //设置背景颜色为白色
            gs.setBackground(Color.WHITE);
            //填充到纸上来
            gs.clearRect(0, 0, 140, 140);

            //设定图像颜色：BLACK  //设置二维码的前景色-黑色
            gs.setColor(Color.BLACK);

            //设置偏移量  不设置肯能导致解析出错
            int pixoff = 2;
            //输出内容：二维码
            if(contentBytes.length > 0 && contentBytes.length < 124) {
                boolean[][] codeOut = handler.calQrcode(contentBytes);
                for(int i = 0; i < codeOut.length; i++) {
                    for(int j = 0; j < codeOut.length; j++) {
                        if(codeOut[j][i]) {
                            //根据不布尔数组绘制二维码矩形图
                            gs.fillRect(j * 3 + pixoff, i * 3 + pixoff,3, 3);
                        }
                    }
                }
            } else {
                System.err.println("QRCode&nbsp;content&nbsp;bytes&nbsp;length&nbsp;=&nbsp;" + contentBytes.length + "&nbsp;not&nbsp;in&nbsp;[&nbsp;0,120&nbsp;].&nbsp;");
            }

            gs.dispose();
            bufImg.flush();


            File f =new File(path);
            //生成二维码QRCode图片
            ImageIO.write(bufImg, "jpg",f);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }






//     private static final int BLACK = 0xff000000;
//     private static final int WHITE = 0xFFFFFFFF;

     /**
     * 仅生成二维码，返回二维码图片网络地址，可直接扫码访问
     * @param address：扫码地址
     * @return
     */
//    public static String getQRCode(String address){
//        String filePostfix="png";
//        //String today = DateUtils.formatDate("yyyyMMdd");//以日期分文件夹
//        String today = DateUtils.formatDate(new Date("yyyyMMdd"));
//        String fileName = UUID.randomUUID().toString().replaceAll("-", "")+ "." +filePostfix;
//        // D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web
//        String physicalPath = "D://SwingProjectTests/SpringProjectsTesto/SpringMVCDemo1/SSH/web/" + today + fileName;//保存在本地的地址
//        File file = new File(physicalPath);
//        if (!file.getParentFile().exists()) {
//            file.getParentFile().mkdirs();
//        }
//        encode(address, file, filePostfix, BarcodeFormat.QR_CODE, 500, 500, null);
//        decode(file);
//        String netUrl = SysConfig.nginxRootUrl + Globals.BAR + today + Globals.BAR + fileName;
//        return netUrl;
//    }
//
//
//    /**
//     *  生成QRCode二维码<br>
//     *  在编码时需要将com.google.zxing.qrcode.encoder.Encoder.java中的<br>
//     *  static final String DEFAULT_BYTE_MODE_ENCODING = "ISO8859-1";<br>
//     *  修改为UTF-8，否则中文编译后解析不了<br>
//     * @param contents 二维码的内容
//     * @param file 二维码保存的路径，如：C://test_QR_CODE.png
//     * @param filePostfix 生成二维码图片的格式：png,jpeg,gif等格式
//     * @param format qrcode码的生成格式
//     * @param width 图片宽度
//     * @param height 图片高度
//     * @param hints
//     */
//    public static void encode(String contents, File file,String filePostfix, BarcodeFormat format, int width, int height, Map<EncodeHintType, ?> hints) {
//        try {
//            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, format, width, height);
//            writeToFile(bitMatrix, filePostfix, file);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 生成二维码图片<br>
//     *
//     * @param matrix
//     * @param format
//     *            图片格式
//     * @param file
//     *            生成二维码图片位置
//     * @throws IOException
//     */
//    public static void writeToFile(BitMatrix matrix, String format, File file) throws IOException {
//        BufferedImage image = toBufferedImage(matrix);
//        ImageIO.write(image, format, file);
//    }
//
//    /**
//     * 生成二维码内容<br>
//     *
//     * @param matrix
//     * @return
//     */
//    public static BufferedImage toBufferedImage(BitMatrix matrix) {
//        int width = matrix.getWidth();
//        int height = matrix.getHeight();
//        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
//        for (int x = 0; x < width; x++) {
//            for (int y = 0; y < height; y++) {
//                image.setRGB(x, y, matrix.get(x, y) == true ? BLACK : WHITE);
//            }
//        }
//        return image;
//    }
//
//    /**
//     * 解析QRCode二维码
//     */
//    @SuppressWarnings("unchecked")
//    public static void decode(File file) {
//        try {
//            BufferedImage image;
//            try {
//                image = ImageIO.read(file);
//                if (image == null) {
//                    System.out.println("Could not decode image");
//                }
//                LuminanceSource source = new BufferedImageLuminanceSource(image);
//                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
//                Result result;
//                @SuppressWarnings("rawtypes")
//                Hashtable hints = new Hashtable();
//                //解码设置编码方式为：utf-8
//                hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
//                result = new MultiFormatReader().decode(bitmap, hints);
//                String resultStr = result.getText();
//                System.out.println("解析后内容：" + resultStr);
//            } catch (IOException ioe) {
//                System.out.println(ioe.toString());
//            } catch (ReaderException re) {
//                System.out.println(re.toString());
//            }
//        } catch (Exception ex) {
//            System.out.println(ex.toString());
//        }
//    }





}

