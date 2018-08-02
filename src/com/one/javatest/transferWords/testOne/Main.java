package com.one.javatest.transferWords.testOne;

import javax.swing.*;
import java.io.IOException;

/**
 * Created by vtstar on 2018/2/28.
 */
public class Main {

    public static void main(String[] args){
        String srcFile = JOptionPane.showInputDialog("输入源文件");// srcfile.txt
        try {
            byte[] data = FileLoad.getContent(srcFile);
            TxtTrans tt = new TxtTrans();
            String dsString = tt.trans(data);
            String outfileName =  "../SSH/src/com/one/javatest/transferWords/testOne/result.txt";
            FileOutPut.output(dsString,outfileName);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "操作异常");
            System.exit(1);
        }
        JOptionPane.showMessageDialog(null,"翻译完毕");
    }
}
