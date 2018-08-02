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
        // ServletUtilities ������ web �����Ĺ����࣬����һ���ַ����ļ������ļ����Զ����ɣ�
        // ���ɵ�ͼƬ���Զ����ڷ�������tomcat�� ����ʱ�ļ��£�temp��
        String fileName = ServletUtilities.saveChartAsPNG(chart,800,600,session);
        //�����ļ��� ȥ ��ʱĿ¼��Ѱ��ͼƬ������� /DisplayChart ·��Ҫ�������ļ��� �û��Զ����<url-partten> һ��
        System.out.println("22="+request.getContextPath());
        System.out.println("22="+fileName); // 22=jfreechart-1483825191631382379.png     D:\javaPage\TomcatTwo\apache-tomcat-8.5.15-windows-x64\apache-tomcat-8.5.15\temp\jfreechart-1483825191631382379.png
        String url = request.getContextPath()+"/DisplayChart?filename="+fileName;

    %>

<img src="<%=url%>" width="800" height="600">

</body>
</html>