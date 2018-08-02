package com.one.javatest.transferWords.testOne;

/**
 * Created by vtstar on 2018/2/27.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**将文件载入类,将源文件的内容输出到字节数组中 */
public class FileLoad {

    public static byte[] getContent(String fileName) throws IOException {
        fileName = "../SSH/src/com/one/javatest/transferWords/testOne/"+fileName;
        File file = new File(fileName);
        if(!file.exists()){
            System.out.println("输入有误，该文件不存在");
        }
        FileInputStream fis = new FileInputStream(file);
        int length = (int)file.length();
        byte[] data = new byte[length];
        fis.read(data);
        fis.close();
        return data;
    }
}
