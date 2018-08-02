package com.one.smallApplet;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Created by vtstar on 2018/1/11.
 */

// 利用随机  形成Applet  动画
public class ScApplet extends Applet {

    final String testStr = "java真好玩，学习很有趣！";
    // 用于保存字符串下一次出现的座标
    private int nextX;
    private int nextY;
    private Color nextColor;
    private Font nextFont;
    Random random = new Random(System.currentTimeMillis());

    @Override
    public void init(){
        // java.swing.Timer
        Timer timer = new Timer(200,new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                //随机生成下一次坐标、颜色、大小
                nextX = random.nextInt(400);
                nextY = random.nextInt(300);
                nextColor = new Color(random.nextInt(255) , random.nextInt(255) , random.nextInt(255));
                nextFont = new Font("Times" , Font.BOLD , random.nextInt(60));
                //强制重绘
                repaint();
            }
        });
        timer.start();
    }

    //绘制Applet 界面的方法
    @Override
    public void paint(Graphics graphics){
        graphics.setColor(nextColor);
        graphics.setFont(nextFont);
        graphics.drawString(testStr,nextX,nextY);
    }

    public static void main(String[] args){
        JFrame jFrame = new JFrame();
        ScApplet panel = new ScApplet();
        panel.setPreferredSize(new Dimension(400,300));
        panel.init();
        panel.start();
        jFrame.add(panel);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}
