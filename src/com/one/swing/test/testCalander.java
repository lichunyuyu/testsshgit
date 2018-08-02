package com.one.swing.test;

import javax.swing.*;
import java.util.Date;

/**
 * Created by vtstar on 2018/1/2.
 */

/**
 * 日历 + -
 * */
public class testCalander extends JFrame {

    public testCalander() {
        initComponents();
    }
    private void initComponents(){
        JFrame jframe = new JFrame();
        jframe.setSize(400,400);
        JPanel contentPane = new JPanel();
        //获得时间日期模型
                SpinnerDateModel model = new SpinnerDateModel();
                //获得JSPinner对象
                JSpinner year = new JSpinner(model);
                year.setValue(new Date());
                 //设置时间格式
                 JSpinner.DateEditor editor = new JSpinner.DateEditor(year,
                                "yyyy-MM-dd HH:mm:ss");
                year.setEditor(editor);
                 year.setBounds(34, 67, 219, 22);
                contentPane.add(year);
        jframe.add(contentPane);
        jframe.setVisible(true);

    }
    public static void main(String args[]) {
        testCalander jframe = new testCalander();

        // test
        for(int i=0;i<52;i++){
           // IToCoordinateY(i);
            System.out.println(IToCoordinateY(i));
        }
    }


    public static String IToCoordinateY(int i) {
        String s = "A   B   C   D   E   F   G   H   I   J   K   L   M   N   O   P   Q   R   S   T   U   V   W   X   Y   Z";
        String[] sArray = s.split("   ");
        return i < 1?"":(i / 26 == 0?sArray[i % 26 - 1]:(i % 26 == 0?IToCoordinateY(i / 26 - 1) + sArray[25]:sArray[i / 26 - 1] + sArray[i % 26 - 1]));
    }
}

/**
 * "C:\Program Files\Java\jdk1.8.0_111\bin\java" -Didea.launcher.port=7533 "-Didea.launcher.bin.path=C:\Program Files (x86)\JetBrains\IntelliJ IDEA 2016.3\bin" -Dfile.encoding=UTF-8 -classpath "C:\Program Files\Java\jdk1.8.0_111\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.8.0_111\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.8.0_111\jre\lib\ext\access-bridge-64.jar;C:\Program Files\Java\jdk1.8.0_111\jre\lib\ext\cldrdata.jar;C:\Program Files\Java\jdk1.8.0_111\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.8.0_111\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.8.0_111\jre\lib\ext\jfxrt.jar;C:\Program Files\Java\jdk1.8.0_111\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.8.0_111\jre\lib\ext\nashorn.jar;C:\Program Files\Java\jdk1.8.0_111\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.8.0_111\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.8.0_111\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.8.0_111\jre\lib\ext\sunpkcs11.jar;C:\Program Files\Java\jdk1.8.0_111\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk1.8.0_111\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.8.0_111\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0_111\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.8.0_111\jre\lib\jfxswt.jar;C:\Program Files\Java\jdk1.8.0_111\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.8.0_111\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.8.0_111\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.8.0_111\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_111\jre\lib\rt.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\out\production\SSHTest1;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\lib\spring-aspects-4.3.13.RELEASE.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\lib\spring-aop-4.3.13.RELEASE.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\lib\spring-context-support-4.3.13.RELEASE.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\lib\spring-beans-4.3.13.RELEASE.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\lib\spring-instrument-4.3.13.RELEASE.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\lib\spring-expression-4.3.13.RELEASE.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\lib\spring-instrument-tomcat-4.3.13.RELEASE.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\lib\spring-context-4.3.13.RELEASE.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\lib\spring-core-4.3.13.RELEASE.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\lib\spring-jms-4.3.13.RELEASE.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\lib\spring-oxm-4.3.13.RELEASE.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\lib\spring-messaging-4.3.13.RELEASE.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\lib\spring-orm-4.3.13.RELEASE.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\lib\spring-jdbc-4.3.13.RELEASE.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\lib\spring-tx-4.3.13.RELEASE.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\lib\commons-logging-1.2.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\lib\spring-test-4.3.13.RELEASE.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\lib\aopalliance-1.0.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\lib\spring-webmvc-portlet-4.3.13.RELEASE.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\lib\spring-websocket-4.3.13.RELEASE.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\lib\spring-web-4.3.13.RELEASE.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\lib\spring-webmvc-4.3.13.RELEASE.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\lib\classmate-1.3.0.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\lib\hibernate-commons-annotations-5.0.1.Final.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\lib\dom4j-1.6.1.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\lib\antlr-2.7.7.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\lib\hibernate-jpa-2.1-api-1.0.0.Final.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\lib\jandex-2.0.3.Final.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\lib\jboss-logging-3.3.0.Final.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\lib\jboss-transaction-api_1.2_spec-1.0.1.Final.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\lib\javassist-3.20.0-GA.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\lib\hibernate-core-5.2.12.Final.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\lib\javax.annotation.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\lib\javax.jms.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\lib\javax.resource.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\lib\javax.ejb.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\lib\javax.servlet.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\lib\javax.servlet.jsp.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\lib\javax.servlet.jsp.jstl.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\spring-webmvc-portlet-4.3.13.RELEASE.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\spring-websocket-4.3.13.RELEASE.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\spring-tx-4.3.13.RELEASE.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\spring-web-4.3.13.RELEASE.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\spring-webmvc-4.3.13.RELEASE.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\spring-test-4.3.13.RELEASE.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\spring-oxm-4.3.13.RELEASE.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\spring-orm-4.3.13.RELEASE.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\spring-messaging-4.3.13.RELEASE.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\spring-jms-4.3.13.RELEASE.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\spring-jdbc-4.3.13.RELEASE.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\spring-instrument-tomcat-4.3.13.RELEASE.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\spring-instrument-4.3.13.RELEASE.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\spring-expression-4.3.13.RELEASE.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\spring-core-4.3.13.RELEASE.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\spring-context-support-4.3.13.RELEASE.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\spring-context-4.3.13.RELEASE.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\spring-beans-4.3.13.RELEASE.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\spring-aspects-4.3.13.RELEASE.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\spring-aop-4.3.13.RELEASE.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\jboss-transaction-api_1.2_spec-1.0.1.Final.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\jboss-logging-3.3.0.Final.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\javax.servlet.jsp.jstl.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\javax.servlet.jsp.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\javax.servlet.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\javax.resource.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\javax.jms.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\javax.ejb.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\javax.annotation.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\javassist-3.20.0-GA.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\jandex-2.0.3.Final.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\hibernate-jpa-2.1-api-1.0.0.Final.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\hibernate-core-5.2.12.Final.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\hibernate-commons-annotations-5.0.1.Final.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\dom4j-1.6.1.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\commons-logging-1.2.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\classmate-1.3.0.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\aopalliance-1.0.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\antlr-2.7.7.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\mysql-connector-java-5.1.45-bin.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\aspectjrt.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\aspectjtools.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\org.aspectj.matcher.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\aspectjweaver.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\spring-tomcat-weaver.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\asm-attrs.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\asm.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\commons-collections-3.2.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\commons-lang.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\jstl-1.2.jar;C:\Program Files (x86)\JetBrains\IntelliJ IDEA 2016.3\plugins\junit\lib\junit-jupiter-api-5.0.0-M2.jar;C:\Program Files (x86)\JetBrains\IntelliJ IDEA 2016.3\plugins\junit\lib\opentest4j-1.0.0-M1.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\hamcrest-core-1.3.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\jfreechart-1.0.19-experimental.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\jcommon-1.0.23.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\jfreechart-1.0.19-swt.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\jfreechart-1.0.19.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\jfreesvg-2.0.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\orsoncharts-1.4-eval-nofx.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\orsonpdf-1.6-eval.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\servlet.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\swtgraphics2d.jar;D:\SwingProjectTests\SpringProjectsTesto\SpringMVCDemo1\SSH\web\WEB-INF\lib\oscore-2.2.2.jar;C:\Program Files (x86)\JetBrains\IntelliJ IDEA 2016.3\lib\idea_rt.jar" com.intellij.rt.execution.application.AppMain com.one.swing.test.testCalander

 A
 B
 C
 D
 E
 F
 G
 H
 I
 J
 K
 L
 M
 N
 O
 P
 Q
 R
 S
 T
 U
 V
 W
 X
 Y
 Z
 AA
 AB
 AC
 AD
 AE
 AF
 AG
 AH
 AI
 AJ
 AK
 AL
 AM
 AN
 AO
 AP
 AQ
 AR
 AS
 AT
 AU
 AV
 AW
 AX
 AY

 * */
