package com.one.javatest.transferWords.testOne;

/**
 * Created by vtstar on 2018/2/28.
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**将字符串输出到文件*/
public class FileOutPut {

    public static void output(String text,String fileName) throws IOException {
        File file = new File(fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(text.getBytes());
        fos.close();
    }
}
