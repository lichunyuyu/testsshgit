package com.one.swingChartTest.test;

/**
 * Created by vtstar on 2018/6/9.
 */
public class Thread1 extends Thread {

    private String name;
    public Thread1(String name){
        this.name = name;
    }

    public void testa(int ii){
        ii = 10;
        System.out.println("ii----"+ii);
    }
    public void testb(int j){
       int jj=j;
        System.out.println("ijj----"+jj);
    }
    @Override
    public void run(){
        for(int i=0;i<5;i++){
            System.out.println(name+"运行："+i);
            try{
                sleep((int)Math.random()*10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

