package com.one.javatest.exportExcel.excelPicture;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by vtstar on 2018/10/26.
 */
public class TestResizeImage {

    public static void main(String []args){
        try {
            resizeImage("D:\\MyGitHubProjects\\SSH\\src\\com\\one\\javatest\\exportExcel\\excelPicture\\images\\单波3.jpg"
                    , "D:\\MyGitHubProjects\\SSH\\src\\com\\one\\javatest\\exportExcel\\excelPicture\\outFiles\\单波3.jpg"
                    , 109,57);
        } catch (IOException e) {
            System.out.println("图片转换出现异常！");
        }

    }

    /***
     * 功能 :调整图片大小      （不是 图片大小   是 图片 像素）
     * @param srcImgPath 原图片路径
     * @param distImgPath  转换大小后图片路径
     * @param width   转换后图片宽度
     * @param height  转换后图片高度
     */
    public static void resizeImage(String srcImgPath, String distImgPath,
                                   int width, int height) throws IOException {

        File srcFile = new File(srcImgPath);
        Image srcImg = ImageIO.read(srcFile);
        BufferedImage buffImg = null;
        buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        buffImg.getGraphics().drawImage(
                srcImg.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0,
                0, null);

        ImageIO.write(buffImg, "JPEG", new File(distImgPath));

    }

}

/**
 * 这里的大小是 像素 ，不是图片的 宽高
 * 图片属性：
 * 分辨率 ： 72 像素/英寸
 *  1 in 英寸 = 2.54 cm 厘米
 *
 *  像素 宽 = 分辨率 * 图片 宽     即    像素宽（像素） = 72 (像素)/2.54(厘米) * 图片宽(厘米)
 *  像素 高 = 分辨率 * 图片 高     即    像素高（像素） = 72 (像素)/2.54(厘米) * 图片高(厘米)
 *
 * 故 ： 需要 宽 3.83   高 2 厘米的 图片时
 *     像素 宽 约为 108.567 = 109   像素高 约为 56.693 = 57
 *
 * 结果 ：宽 2.88 高1.51  （不对）
 * */
