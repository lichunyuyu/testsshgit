package com.one.javatest.exportExcel.oneMain;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by vtstar on 2018/4/2.
 */
public class main {

    public  static void main(String[] args) throws IOException {
        int width = 178;
        int height = 133;
        // 创建BufferedImage对象
        BufferedImage image = new BufferedImage(width, height,     BufferedImage.TYPE_INT_RGB);
        // 获取Graphics2D
        Graphics2D g2d = image.createGraphics();

        // 设置透明度
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0f));  // 1.0f为透明度 ，值从0-1.0，依次变得不透明

// ----------  增加下面的代码使得背景透明  start-----------------
        image = g2d.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
         g2d.dispose();
        g2d = image.createGraphics();
// ----------  背景透明代码结束  -----------------
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
        //设置透明  end
        g2d.setFont(new Font("微软雅黑", Font.PLAIN, 36)); //设置字体
        g2d.setColor(Color.ORANGE); //设置颜色
        g2d.drawRect(0, 0, width - 1, height - 1); //画边框
        g2d.drawString("欢迎访问我的博客", width/2-36*"欢迎访问我的博客".length()/2,36); //输出文字（中文横向居中）

// 画图
        g2d.setColor(new Color(255,0,0));
        g2d.setStroke(new BasicStroke(1));
        //g2d.draw
//释放对象
        g2d.dispose();
 //保存文件
        ImageIO.write(image, "png", new File("../SSH/src/com/one/javatest/exportExcel/image/透明签名.png"));

    }

}
