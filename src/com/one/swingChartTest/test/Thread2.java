package com.one.swingChartTest.test;

/**
 * Created by vtstar on 2018/6/9.
 */
public class Thread2  implements Runnable{
    private String name;
    public Thread2(String name) {
        this.name=name;
    }

    public void testa(int ii){
        ii = 10;
        System.out.println("Thread2ii----"+ii);
    }
    public void testb(int j){
        int jj=j;
        System.out.println("Thread2ijj----"+jj);
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + "..Thread2运行  :  " + i);
            try {
                Thread.sleep((int) Math.random() * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
