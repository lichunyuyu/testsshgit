package com.one.swing.dao;

import com.one.swing.db.DButils;
import com.one.swing.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by vtstar on 2017/12/19.
 */
public class CustomerDao {

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql;

    /**
     * 插入
     * */
    public int add(Customer customer) throws SQLException {
//        String uuid = UUID.randomUUID().toString(); //获取UUID并转化为String对象
//        uuid = uuid.replace("-", "");
//        user.setId(uuid);
        try{
            conn = DButils.getConnection();
            sql = "insert into t_customer(custName,password,age,id) values(?,?,?,?)";
            ps = conn.prepareStatement(sql);
            //ps.setString(1, user.getId());
            ps.setString(1, customer.getCustName());
            ps.setString(2, customer.getPassword());
            ps.setInt(3, customer.getAge());
            ps.setString(4, customer.getId());
            return ps.executeUpdate();
        }
        catch (SQLException ex) {
            System.out.println("客户注册异常");
            ex.printStackTrace();
            // Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DButils.close(rs, ps, conn);
        }
        return 0;
    }
}
