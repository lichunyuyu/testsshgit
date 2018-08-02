package com.one.smallJApplet;

import javax.swing.*;
import java.awt.*;
import java.util.GregorianCalendar;

/**
 * Created by vtstar on 2018/1/11.
 */
//java  小应用程序
public class MyApplet extends JApplet {

    private Color cl = new Color(255,204,102);
    private String lastTime = "";
    //初始化Applet的方法
    @Override
    public void init(){
        setBackground(Color.black);

        setLayout(new FlowLayout());
        setLayout(null);//   设置为null 后单独设置其他组件
        JButton jButtonx = new JButton("XXX");
        jButtonx.setBounds(100,100,60,40);
        add(jButtonx);
        JButton jButtony = new JButton("YYY");
        jButtony.setBounds(200,100,60,40);
        add(jButtony);
//        add(new JButton("XXX"));
//        add(new JButton("YYY"));
        System.out.println("MyApplet-init");
    }
    //启动Applet的方法
    @Override
    public void start()
    {
        System.out.println("start方法");
    }
    //绘制Applet界面的方法
    @Override
    public void paint(Graphics graphics){
        super.paint(graphics);  //加 XXX  YYY
        Graphics2D screen2D = (Graphics2D)graphics;
        Font fontType = new Font("Monospaced",Font.ITALIC,20);
        screen2D.setFont(fontType);
        GregorianCalendar day = new GregorianCalendar();
        String time = day.getTime().toString();
        screen2D.setColor(Color.black);
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

        System.out.println("MyApplet-init-lastTime"+lastTime);
        repaint();   // 不加  不会加载时间   （显示时间变化）
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
        JApplet panel = new MyApplet();
        panel.setPreferredSize(new Dimension(500, 400));
        //显式调用HelloApplet对象的init、start方法
        panel.init();
        panel.start();
        jf.add(panel);
        jf.pack();
        jf.setVisible(true);

    }
}
