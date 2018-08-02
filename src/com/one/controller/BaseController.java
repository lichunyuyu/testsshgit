package com.one.controller;

import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by vtstar on 2017/12/6.
 */
@Controller
public class BaseController {

   // protected final Logger log = LogManager.getLogger();

    /**
     * 处理页面参数序列化后数据类型的问题
     *
     * @author Candy
     * @date 2014-11-3
     * @version V1.0
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
       // log.debug("initBinder");
        System.out.println("BaseController=initBinder=");
        SimpleDateFormat dataSdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dataTimesdf = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dataSdf,
                true));
        binder.registerCustomEditor(Timestamp.class, new CustomDateEditor(
                dataTimesdf, true));
        binder.registerCustomEditor(List.class, new CustomCollectionEditor(
                List.class, true));
    }

    /**
     * 获取客户端真实IP地址
     *
     * @author Candy
     * @date 2014-11-3
     * @version V1.0
     * @param request
     * @return
     */
    public String getIpAddr(HttpServletRequest request) {
        System.out.println("BaseController=getIpAddr=");
     //   log.debug("getIpAddr");
        if (request == null) {
            return null;
        }
        String ip = request.getRemoteAddr();

        System.out.println("getIpAddr="+ip);

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-forwarded-for");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        return ip;
    }
}
