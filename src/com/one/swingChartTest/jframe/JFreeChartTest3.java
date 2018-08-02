package com.one.swingChartTest.jframe;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by vtstar on 2017/12/28.
 */
//实例三：将chart图表转换成JPEG格式的图片的application
    // Organization data map of a company 某公司人员组织数据图   Organization chart of a company 某公司组织结构图

    //运行结果 ; 在该项目的 根目录下生成了JPEG格式的图片，，注意不是在web 目录下。
public class JFreeChartTest3 {

    public static void main(String[] args) throws IOException {
       // //ChartFactory是org.jfree.chart包中抽象类。它提供了实用方法的集合，用于生成标准的图表。
        JFreeChart chart = ChartFactory.createPieChart("Organization data map of a company",
                getDataset(),true,true,false);
        //可以重新设置标题，替换“Organization data map of a company” 标题
        chart.setTitle(new TextTitle("Organization chart of a company",
                new Font("宋体", Font.BOLD+Font.ITALIC,20)));
        //设置 Legend
        LegendTitle legend = chart.getLegend(0);
        legend.setItemFont(new Font("宋体",Font.BOLD,14));
        //设置Plot
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelFont(new Font("隶书",Font.BOLD,16));

        //图片是文件格式的，故要用到FileOutputStream用来输出
        OutputStream ops = new FileOutputStream("company.jpeg");
        //使用一个面向application 的工具类，将chart 转换成JPEG格式的图片。第3个参数是宽度,第四个参数是高度
        ChartUtilities.writeChartAsJPEG(ops,chart,1000,800);
        //关闭输出流
        ops.close();
        //1-11  放在指定目录下
        File file = new File("./src/com/one/swingChartTest/jframe/jpeg/company2.jpeg");
        OutputStream ops2 = new FileOutputStream(file);  //  到这一步，已生成 company2.jpeg   但can't load
        ChartUtilities.writeChartAsJPEG(ops2,chart,1000,800);  //  如果看不到，可能太小了 放大就可以
        //或者 直接 放 path + name
        OutputStream ops3 = new FileOutputStream("./src/com/one/swingChartTest/jframe/jpeg/company3.jpeg");
        ChartUtilities.writeChartAsJPEG(ops3,chart,1000,800);
        ops2.close();
        ops3.close();
    }

    private static DefaultPieDataset getDataset(){
        //建立一个默认的饼图
        DefaultPieDataset dpd = new DefaultPieDataset();
        //输入数据
        dpd.setValue("Management",25);
        dpd.setValue("Market personnel",25);
        dpd.setValue("Developer",45);
        dpd.setValue("Other people",10);
        return dpd;
    }
}
