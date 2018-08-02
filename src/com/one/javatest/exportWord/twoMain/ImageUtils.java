package com.one.javatest.exportWord.twoMain;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by vtstar on 2018/2/26.
 */
/**
 * base64图片工具介绍：
 1.支持 PNG、GIF、JPG、BMP、ICO 格式。
 2.将图片转换为Base64编码，可以让你很方便地在没有上传文件的条件下将图片插入其它的网页、编辑器中。 这对于一些小的图片是极为方便的，因为你不需要再去寻找一个保存图片的地方。
 3.假定生成的代码为"data:image/jpeg;base64, ....."，那么你只需要全部复制，然后在插入图片的时候，地址填写这段代码即可。
 4.CSS中使用：background-image: url("data:image/png;base64,iVBORw0KGgo=...");
 5.HTML中使用：<img src="data:image/png;base64,iVBORw0KGgo=..." />
 6.图片转换Base64，无线开发、HTML5、CSS3必备的工具，CSS DataURI Base64 工具。
 7.将图片转换成base64编码的，在web网上一般用于小图片上，不仅可以减少图片的请求数量（集合到js、css代码中），还可以防止因为一些相对路径等问题导致图片404错误。
 * */
public class ImageUtils {

    /**
     * 将网络图片进行Base64位编码
     *
     * @param imgUrl
     *            图片的url路径，如http://.....xx.jpg
     * @return
     */
    public static String encodeImgageToBase64(URL imageUrl) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        ByteArrayOutputStream outputStream = null;
        try {
            BufferedImage bufferedImage = ImageIO.read(imageUrl);
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", outputStream);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(outputStream.toByteArray());// 返回Base64编码过的字节数组字符串
    }

    /**
     * 将本地图片进行Base64位编码
     *
     * @param imgUrl
     *            图片的url路径，如file
     * @return
     */
    public static String encodeImgageToBase64(File imageFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        ByteArrayOutputStream outputStream = null;
        try {
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", outputStream);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(outputStream.toByteArray());// 返回Base64编码过的字节数组字符串
    }

    /**
     * 将Base64位编码的图片进行解码，并保存到指定目录
     *
     * @param base64
     *            base64编码的图片信息
     * @return
     */
    public static void decodeBase64ToImage(String base64, String path,
                                           String imgName) {
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            FileOutputStream write = new FileOutputStream(new File(path
                    + imgName));
            byte[] decoderBytes = decoder.decodeBuffer(base64);
            write.write(decoderBytes);
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
