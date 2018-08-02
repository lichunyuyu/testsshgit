package com.one.swing.exampleSwing;

import javax.swing.*;
import java.awt.*;

/**
 * Created by vtstar on 2018/1/11.
 */
public class JPanelTest extends JFrame {

    public JPanelTest(){
        //设置一个容器
        Container container = getContentPane();
        //将整个容器设置为2行1列的网格布局    GridLayout网格布局管理器 x, y 代表行 和 列
        container.setLayout(new GridLayout(2,1,10,10));

        //初始化一个面板，设置 1行 3列 的网格布局
        JPanel panel1 = new JPanel(new GridLayout(1,3,10,10));
        JPanel panel2 = new JPanel(new GridLayout(1,2,10,10));
        JPanel panel3 = new JPanel(new GridLayout(1,2,10,10));
        JPanel panel4 = new JPanel(new GridLayout(2,1,10,10));

        // 在 面板中添加按钮
        panel1.add(new JButton("button1")) ;
        panel1.add(new JButton("button2")) ;
        panel1.add(new JButton("button3")) ;
        panel2.add(new JButton("button4")) ;
        panel2.add(new JButton("button5")) ;
        panel3.add(new JButton("button6")) ;
        panel3.add(new JButton("button7")) ;
        panel4.add(new JButton("button8")) ;
        panel4.add(new JButton("button9")) ;

        //最重要的一步，将面板实例添加到容器中
        container.add(panel1);
        container.add(panel2);
        container.add(panel3);
        container.add(panel4);

        //将容器外部特性实例化
        setTitle("JPanel面板的案例");
        setSize(400,250);//设置窗体大小
        setVisible(true);// 设置窗体的可视化

        // 设置窗体的关闭方式
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        JPanelTest jt=new JPanelTest();
    }


}
