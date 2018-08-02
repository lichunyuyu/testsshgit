<%@page language="java" contentType="text/html; chartset=GB18030"
        pageEncoding="GB18030" %>

<%@page import="org.jfree.data.general.DefaultPieDataset,
                org.jfree.chart.ChartFactory,
                org.jfree.chart.JFreeChart,
                org.jfree.chart.servlet.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
          "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; chartset=GB18030">
    <title>Insert title here</title>
</head>
<body>

    <%
        DefaultPieDataset dpd = new DefaultPieDataset();
        dpd.setValue("Management",25);
        dpd.setValue("Market personnel",25);
        dpd.setValue("Developer",45);
        dpd.setValue("Other people",10);

        JFreeChart chart = ChartFactory.createPieChart("Organization chart of a company ",dpd,true,false,false);
        // ServletUtilities 是面向 web 开发的工具类，返回一个字符串文件名，文件名自动生成，
        // 生成的图片会自动放在服务器（tomcat） 的临时文件下（temp）
        String fileName = ServletUtilities.saveChartAsPNG(chart,800,600,session);
        //根据文件名 去 临时目录下寻找图片，这里的 /DisplayChart 路径要与配置文件里 用户自定义的<url-partten> 一致
        System.out.println("22="+request.getContextPath());
        System.out.println("22="+fileName); // 22=jfreechart-1483825191631382379.png     D:\javaPage\TomcatTwo\apache-tomcat-8.5.15-windows-x64\apache-tomcat-8.5.15\temp\jfreechart-1483825191631382379.png
        String url = request.getContextPath()+"/DisplayChart?filename="+fileName;

    %>

<img src="<%=url%>" width="800" height="600">

</body>
</html>