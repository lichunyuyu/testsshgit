package com.one.service;

import com.one.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by vtstar on 2017/12/7.
 */
@Service
public interface IUserManager {

    //登录方法
    public User getLogin(User user);

    public void addUser(User user);

    public List<User> getAllUser();

    public boolean delUser(String id);

    public User getUser(String id);

    public boolean updateUser(User user);
}
