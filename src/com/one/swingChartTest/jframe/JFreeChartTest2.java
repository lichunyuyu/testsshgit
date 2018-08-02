package com.one.swingChartTest.jframe;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by vtstar on 2017/12/28.
 */

//实例二：一个结构更加明晰的application版本的柱状图，将逻辑分装到各个函数中去。
//  Organization chart of a company 某公司组织结构图  Personnel distribution 人员分布
// Number of personnel 人员数量
public class JFreeChartTest2 extends ApplicationFrame{


    public JFreeChartTest2(String title) {
        super(title);
        //构造函数中自动创建 Java 的 panel面板
        this.setContentPane(createPanel());  // (Container contentPane)
    }
    //创建柱状图数据集
    public static CategoryDataset createDataset(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(10,"a","Management");
        dataset.setValue(20,"b","Market personnel");
        dataset.setValue(40,"c","Developer");
        dataset.setValue(15,"d","Other people");
        return dataset;
    }
    //用数据集创建一个图表
    public static JFreeChart createChart(CategoryDataset dateset){
        // //ChartFactory是org.jfree.chart包中抽象类。它提供了实用方法的集合，用于生成标准的图表。
        // createBarChart 参数java.lang.String categoryAxisLabel标签放置在X轴的值。该参数的java.lang.String valueAxisLabel标签放置在Y轴的数值。此方法创建一个条形图。
        JFreeChart chart = ChartFactory.createBarChart("hi","Personnel distribution",
                "Number of personnel",dateset, PlotOrientation.VERTICAL,true,true,false);
       //可以重新设置标题，替换“hi” 标题
        chart.setTitle(new TextTitle("Organization chart of a company",
                new Font("宋体",Font.BOLD+Font.ITALIC,20)));
        //获得图标中间部分，即plot
        CategoryPlot plot = (CategoryPlot)chart.getPlot();
        //获得横坐标
        CategoryAxis categoryAxis = plot.getDomainAxis();
        //设置横坐标字体
        categoryAxis.setLabelFont(new Font("微软雅黑",Font.BOLD,12));
        return chart;
    }
    public static JPanel createPanel(){
        JFreeChart chart = createChart(createDataset());
        //将chart 对象放入Panle面板中去，ChartPanel 已继承JPanel
        return new ChartPanel(chart);
    }
    public static void main(String[] args){
        JFreeChartTest2 chart = new JFreeChartTest2("Organization chart of a company");
       //以合适的大小显示
        chart.pack();
        chart.setVisible(true);
    }

}
