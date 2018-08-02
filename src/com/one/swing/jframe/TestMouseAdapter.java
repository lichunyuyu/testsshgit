package com.one.swing.jframe;

/**
 * Created by vtstar on 2018/1/15.
 */

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.TimerTask;

/**
 * 用定时器延时单击事件实现鼠标双击事件,单击和双击事件互不影响!
 * */
public class TestMouseAdapter extends MouseAdapter{

    private boolean flag = false;  // 用来判断是否已经执行双击事件
    private int clickNum = 0;//用来判断 是否 该 执行双击事件

    @Override
    public void mouseClicked(MouseEvent mouseEvent){
        // 事件源
        final MouseEvent me1 = mouseEvent;
        // 每次点击鼠标初始化 双击事件 执行标志位false
        this.flag = false;
        // 当 clickNum == 1 时 执行双击事件
        if(this.clickNum == 1){
            this.mouseDoubleClicked(mouseEvent); //执行双击事件
            this.clickNum = 0; // 初始化双击事件执行标志为0
            this.flag = true; //双击事件已执行,事件标志为true
            return;
        }

         //* 定义定时器
        java.util.Timer timer = new java.util.Timer();

        //定时器开始执行，延时0.2秒后 确定是否执行单击事件
        timer.schedule(new TimerTask() {
            private int n = 0; //记录定时器执行次数
            @Override
            public void run() {
                // 如果双击事件已经执行，那么直接取消单击执行
                if(flag){
                    n = 0;
                    clickNum = 0;
                    this.cancel();
                    return;
                }
                // 定时器等待 0.2秒后，双击事件仍未发生，执行单击事件
                if(n == 1){
                     mouseSingleClicked(mouseEvent);
                     flag = true;
                     clickNum = 0;
                     n = 0;
                     this.cancel();
                     return;
                }
                clickNum++;
                n++;
            }
        },new Date(),200); // 设置延迟时间
    }


    /**
     * 鼠标单击事件
     * @param mouseEvent 事件源参数
     * */
    public void mouseSingleClicked(MouseEvent mouseEvent){
        System.out.println("Single Clicked");
    }
    /**
     * 鼠标双击事件
     * @param mouseEvent 事件源参数
     * */
    public void mouseDoubleClicked(MouseEvent mouseEvent){
        System.out.println("Double Clicked");
    }
}



/**
 *使用ScheduledExecutorService代替Timer吧 less... (Ctrl+F1)

 多线程并行处理定时任务时，Timer运行多个TimeTask时，只要其中之一没有捕获抛出的异常，其它任务便会自动终止运行，使用ScheduledExecutorService则没有这个问题。

 //org.apache.commons.lang3.concurrent.BasicThreadFactory
 ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
 new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());
 executorService.scheduleAtFixedRate(new Runnable() {
@Override
public void run() {
//do something
}
},initialDelay,period, TimeUnit.HOURS);
 * */
