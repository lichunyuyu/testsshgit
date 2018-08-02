package com.one.javatest.exportExcel.oneMain;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by vtstar on 2018/4/2.
 */

/**  成功 的  */
public class main2 {
    public  static void main(String[] args) throws IOException {

        int width = 178;
        int height = 133;
        // 创建BufferedImage对象
        BufferedImage image = new BufferedImage(width, height,  BufferedImage.TYPE_INT_RGB);
// 获取Graphics2D
        Graphics2D g2d = image.createGraphics();
//        // 设置透明度
//        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0f));  // 1.0f为透明度 ，值从0-1.0，依次变得不透明

        image = g2d.getDeviceConfiguration().createCompatibleImage(image.getWidth(null), image.getHeight(null), Transparency.TRANSLUCENT);
        g2d.dispose();
        g2d = image.createGraphics();
// 画图
        g2d.setColor(new Color(255,0,0));
        g2d.setStroke(new BasicStroke(1));
        g2d.drawImage(image, 0, 0, null);
//释放对象   // 释放图形上下文使用的系统资源
        g2d.dispose();
        //保存文件
        ImageIO.write(image, "png", new File("../SSH/src/com/one/javatest/exportExcel/image/透明签名4.png"));

    }
}
