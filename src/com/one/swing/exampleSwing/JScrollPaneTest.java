package com.one.swing.exampleSwing;

import javax.swing.*;
import java.awt.*;

/**
 * Created by vtstar on 2018/1/11.
 */
/** * 1: 设置界面时，可能会遇到在一个较小的容器窗体中显示一个较大部分的内容的情况，这时
 * 可以使用JScrollPane面板
 * 2: JScrollPane面板是带滚动条的面板，它也是一个面板，但是JScrollPane只能
 * 放置一个组件，并且不可以使用布局管理器
 * 3: 如果需要在JScrollPane面板中放置多个组件，需要将多个组件放置在JPanel面板上，
 * 然后将JPanel作为一个整体组件添加到JScrollPane组件上。
 * 4: 从本实例可以得到在窗体中创建一个带滚动条的文字编辑器，首先需要初始化编辑器，
 * 并且在初始化时完成编译器的大小指定，当创建带滚动条的面板时，将编译器加入面板中
 * ，最后将带滚动条的编译器放置在容器中即可
 * */
 // JScrollPane 管理视口、可选的垂直和水平滚动条以及可选的行和列标题视口。
public class JScrollPaneTest extends JFrame {

    //定义一个构造方法
    public JScrollPaneTest(){
        //创建一个容器
        Container container = getContentPane();
        //创建文本区域组件
        JTextArea jTextArea = new JTextArea(20,50);
        //创建JScrollPane() 面板对象，并将 文本域对象添加到 面板对象
        JScrollPane jScrollPane = new JScrollPane(jTextArea);
        //将该面板添加到容器中
        container.add(jScrollPane);
        // 设置容器的外部特性
        setTitle("待滚动条的文字编辑器");// 设置窗口的标题文字
        setSize(400,400);//设置窗口的大小
        setVisible(true);//设置窗口的可视
        //设置窗口的默认关闭方式
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public static void main(String[] args){
        JScrollPaneTest jScrollPaneTest = new JScrollPaneTest();
    }
}
