package com.one.service;

import com.one.dao.IUserDAO;
import com.one.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by vtstar on 2017/12/7.
 */

/**
 * @Service("userService")注解是告诉Spring，当Spring要创建UserServiceImpl的的实例时，
 * bean的名字必须叫做"userService"，这样当Action需要使用UserServiceImpl的的实例时,就可以由Spring创建好的"userService"，然后注入给Action。
 * */

@Service
public class UserManager implements IUserManager {

    @Autowired
    private IUserDAO userDao;

    public void setUserDao(IUserDAO userDao) {
        this.userDao = userDao;
    }

    //登录方法
    @Override
    public User getLogin(User user){
        return userDao.getLogin(user);
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }


    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }


    @Override
    public boolean delUser(String id) {
        return userDao.delUser(id);
    }


    @Override
    public User getUser(String id) {
        return userDao.getUser(id);
    }


    @Override
    public boolean updateUser(User user) {
        return userDao.updateUser(user);
    }
}
