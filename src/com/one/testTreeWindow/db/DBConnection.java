/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.one.testTreeWindow.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;


/**
 *
 * @author vtstar
 */
/**
 * 如果在Web中，getServletContext().getResourceAsStream("/WEB-INF/db.properties")

 props.load(new FileInputStream("db.properties")); 是读取当前目录的db.properties文件
 getClass.getResourceAsStream("db.properties"); 是读取当前类所在位置一起的db.properties文件
 getClass.getResourceAsStream("/db.properties"); 是读取ClassPath的根的db.properties文件,注意ClassPath如果是多个路径或者jar文件的,只要在任意一个路径目录下或者jar文件里的根下都可以,如果存在于多个路径下的话,按照ClassPath中的先后顺序,使用先找到的,其余忽略.
 * */
public class DBConnection {
    
     public static Connection getConnection() {
         Properties props = new Properties();
         //Properties props=System.getProperties();
         FileInputStream fis = null;
         Connection con = null;
        try {
           //fis = new FileInputStream("db.properties");
             fis = new FileInputStream("D:\\SwingProjectTests\\SpringProjectsTesto\\SpringMVCDemo1\\SSH\\src\\com\\one\\testTreeWindow\\db\\db.properties");
            //InputStream in = new BufferedInputStream(new FileInputStream("db.properties"));
             props.load(fis);
            //
             Class.forName(props.getProperty("DB_DRIVER_CLASS"));
             // 
             con = DriverManager.getConnection(props.getProperty("DB_URL"), props.getProperty("DB_USERNAME"), props.getProperty("DB_PASSWORD"));
         } catch (IOException | SQLException | ClassNotFoundException e) {
             e.printStackTrace();
         }
         return con;
     }

     // �ر�ResultSet
     public static void closeResultSet(ResultSet rs) {
         if (rs != null) {
             try {
                 rs.close();
                rs = null;
             } catch (SQLException e) {
                 e.printStackTrace();
             }
         }
     }
 
    //Statement
     public static void closeStatement(Statement stm) {
         if (stm != null) {
             try {
                stm.close();
                 stm = null;
             } catch (SQLException e) {
                 e.printStackTrace();
             }
        }
     }
 
     //PreparedStatement
     public static void closePreparedStatement(PreparedStatement pstm) {
         if (pstm != null) {
            try {
                 pstm.close();
                pstm = null;
             } catch (SQLException e) {
                 e.printStackTrace();
             }
         }
    }

     //Connection
     public static void closeConnection(Connection con) {
         if (con != null) {
             try {
                 con.close();
                 con = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
             con = null;
        }
     }
}
