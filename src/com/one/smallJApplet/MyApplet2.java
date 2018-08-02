package com.one.smallJApplet;

import javax.swing.*;
import java.awt.*;
import java.util.GregorianCalendar;

/**
 * Created by vtstar on 2018/1/11.
 */
public class MyApplet2 extends JApplet{

    private Color cl = new Color(255,204,102);
    private String lastTime = "";

    //初始化Applet的方法
    @Override
    public void init(){
        setBackground(Color.red);
        System.out.println("MyApplet2-init");
    }
    //绘制Applet界面的方法
    @Override
    public void paint(Graphics graphics){
        //super.paint(graphics);//  加上  否则 最后的变化的时间， 前一个值不会被覆盖掉， 看不清楚
        Graphics2D screen2D = (Graphics2D)graphics;
        Font fontType = new Font("Monospaced",Font.BOLD,20);

        screen2D.setFont(fontType);
        GregorianCalendar day = new GregorianCalendar();
        String time = day.getTime().toString();
        screen2D.setColor(Color.red);
        screen2D.drawString(lastTime,5,25);
        screen2D.setColor(cl);
        screen2D.drawString(lastTime,5,25);

        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            System.out.println("InterruptedException");
            e.printStackTrace();
        }
        lastTime = time;
        repaint();  // 不加  不会加载时间   （显示时间变化）
    }

    //停止Applet的方法
    @Override
    public void stop()
    {
        System.out.println("stop方法");
    }
    //销毁Applet的方法
    @Override
    public void destroy()
    {
        System.out.println("destroy方法");
    }

    //  放在网页中就不需要main
    public static void main(String[] args)
    {
        JFrame jf = new JFrame();
        JApplet panel = new MyApplet2();
        panel.setPreferredSize(new Dimension(500, 400));
        //显式调用HelloApplet对象的init、start方法
        panel.init();
        panel.start();
        jf.add(panel);
        jf.pack();
        jf.setVisible(true);

    }

}
