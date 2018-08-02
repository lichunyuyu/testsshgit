package com.one.javatest.bytep;

/**
 * Created by vtstar on 2018/1/2.
 */

import java.util.Random;

/**
 * 用数组模拟双色球
 *
 *  模拟双色球生成
 * 1.从1到16中产生一个篮球的随机数
 * 2.从1到33中产生出6个红色的球的随机数
 * */
public class DoubleBar {

    /**
     * 产生6个红球的随机数
     * */
    public static int[] redBar(){
        int[] a = new int[6];
        Random random = new Random();
        //把产生的数字保存到数组中
        for(int i=0;i<6;i++){
            // random.nextInt(33) 随机产生一个大于等于0，小于33的整形数
            a[i] = random.nextInt(33)+1;
            //自己写 如果是2的倍数就固定一个值
            if(i!=0 && i%2==0){
                a[i] = 8;
            }
        }
        return a;
    }
    /**
     * 产生一个篮球的随机数
     * */
    public static int blurBar(){
        Random random = new Random();
        return random.nextInt(16)+1;
    }

    public static void main(String[] args){
        int[] a = redBar();

        System.out.println("产生的双色球数字是：");
        for(int i=0;i<a.length;i++){
            System.out.print(a[i]+" ");
            //System.out.println(blurBar());
        }
        System.out.println(blurBar());


        String aa="1";
        String bb="2";
        String cc="3";
        if(aa.equals("1") && (bb.equals("4") || cc.equals("3"))){
            System.out.println("dd");
        }
    }
}
