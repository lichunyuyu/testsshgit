package com.one.swing.db;

import java.sql.*;

/**
 * Created by vtstar on 2017/12/19.
 */
public class DButils {

    private static final String URL = "jdbc:mysql://localhost:3306/attendance?useUnicode=true&characterEncoding=utf-8";
    private static  final String USER = "root";
    private static  final String PASSWORD  ="123";

    static {
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }
    //关闭方法
    public static void close(ResultSet rs, Statement st,Connection conn) throws SQLException {
        if(rs!=null){
            rs.close();
        }if(st!=null){
            st.close();
        }if(conn!=null){
            conn.close();
        }
    }
}
