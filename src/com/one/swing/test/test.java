package com.one.swing.test;

import com.one.swing.dao.UserDao;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by vtstar on 2017/12/19.
 */

public class test {

    @Test
    public void _testAutoFit(){
//        User users =new User();
//        users._setValue("userName", "user1");
//        users._setValue("age", "17");
//        System.out.println(users.getAge()+",userName="+users.getUserName());
        UserDao userDao = new UserDao();
        String entityname = userDao._getBaseDao();
        System.out.println("entityname="+entityname);
        System.out.println(userDao._queryAll());
        List<Map> list= userDao._queryAll();
        System.out.println("userDaolist="+list);
    }
}
