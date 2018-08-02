package com.one.swing.dao;

import com.one.swing.db.DButils;
import com.one.swing.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vtstar on 2017/12/19.
 */
/**  注意 ：  BaseDao<User>   不是 <UserDao>  笑哭
 * */
public class UserDao extends BaseDao<User>{

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql;

    /**
     * 查询所有用户 （User）
     * */
    public List<User> queryAllUser(){
        String sql = "select * from t_user";
        List<User> list = new ArrayList<User>();
        try{
            conn = DButils.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            System.out.println("ps.tostring="+ps.toString());
            while(rs.next()){
                User user = new User();
                user.setId(rs.getString(1));
                user.setAge(rs.getInt(2));
                user.setUserName(rs.getString(3));
                user.setPassword(rs.getString(4));
                list.add(user);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 泛型 ：  查询列表
     * */
    public List<User> queryAllUser2(){
        String sql = "select * from t_user";
        List<User> list = new ArrayList<User>();
        try{
            conn = DButils.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            System.out.println("ps.tostring="+ps.toString());
            System.out.println("rs="+rs);
            while(rs.next()){
                User user = new User();
                user._setValue("id",rs.getString(1));
                user._setValue("age", String.valueOf(rs.getInt(2)));
                user._setValue("userName",rs.getString(3));
                user._setValue("password",rs.getString(4));
                list.add(user);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 插入
     * */
    public int add(User user) throws SQLException {
//        String uuid = UUID.randomUUID().toString(); //获取UUID并转化为String对象
//        uuid = uuid.replace("-", "");
//        user.setId(uuid);
        try{
            conn = DButils.getConnection();
            sql = "insert into t_user(userName,password,age,id) values(?,?,?,?)";
            ps = conn.prepareStatement(sql);
            //ps.setString(1, user.getId());
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getAge());
            ps.setString(4, user.getId());
           return ps.executeUpdate();
        }
         catch (SQLException ex) {
             System.out.println("注册异常");
            ex.printStackTrace();
           // Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DButils.close(rs, ps, conn);
        }
        return 0;
    }

}
