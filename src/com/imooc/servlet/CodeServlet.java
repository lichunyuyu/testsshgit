package com.imooc.servlet;

/**
 * Created by vtstar on 2018/3/19.
 */

import com.imooc.util.QRCodeUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 *  3.建立一个servlet
 */
public class CodeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest requset, HttpServletResponse response)
            throws ServletException, IOException {
        String content = "姓名:ll 电话:123687495";
        QRCodeUtils encoder = new QRCodeUtils();
        encoder.encoderQRCoder(content, response);
    }

}

