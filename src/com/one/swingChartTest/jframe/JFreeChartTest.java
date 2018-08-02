package com.one.swingChartTest.jframe;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 * Created by vtstar on 2017/12/28.
 */
//实例一：比较简单的application版本的饼图
/**
 * 前提：导入需要的2个jar文件，jcommon-版本号.jar,jfreechart-版本号.jar。可以去官网下载：http://sourceforge.net/projects/jfreechart/files/

 注意：下载的Jfreechart版本不要太高，新版本对中文的显示会出问题，我自己后来下的是1.0.10的版本。
 * */
// A company's personnel organization database 某公司人员组织数据库
    //Organization data map of a company  某公司人员组织数据图
    //Management 管理人员 Market personnel  市场人员  Developer 开发人员   Other people其他人员
public class JFreeChartTest {

    public static void main(String[] args){
        //建立一个默认的饼图
        DefaultPieDataset dpd = new DefaultPieDataset();
        //输入数据
        dpd.setValue("Management",25);
        dpd.setValue("Market personnel",25);
        dpd.setValue("Developer",45);
        dpd.setValue("Other people",10);
        //ChartFactory是org.jfree.chart包中抽象类。它提供了实用方法的集合，用于生成标准的图表。
        //createPieChart 此方法使用默认设置创建一个饼图。它返回JFreeChart类型的对象。
        //可以查具体的API文档，第一个参数是标题，第二个参数是一个数据集，第三个参数表示是否显示Legend,
        // 第四个参数表示是否显示提示 ，第五个参数表示图中是否存在URL
        JFreeChart chart = ChartFactory.createPieChart("A company's personnel organization database",dpd,true,true,false);

        // chart 要放在Java 容器组件中，ChartFrame继承自java的JFrame类。该第一个参数的数据是放在窗口左上角的，不是正中间的标题
        ChartFrame chartFrame = new ChartFrame("Organization data map of a company",chart);

        //以合适的大小展现图像
        chartFrame.pack();
        //图形是否可见
        chartFrame.setVisible(true);
    }
}
