package com.one.dao;

import com.one.entity.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by vtstar on 2017/12/7.
 */
@Repository
public class UserDAO implements IUserDAO {

    @Autowired
    //注入已在spring-common.xml中配制好的sessionFactory
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

//    public void setSessionFactory(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }

    //登录方法
    @Override
    public User getLogin(User user){
        String hql = "from User u where u.userName=? and u.password=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);//此时使用的是hql语句
        query.setString(0, user.getUserName());
        query.setString(1,user.getPassword());
        return (User) query.uniqueResult();
    }

    @Override
    public void addUser(User user) {//添加用户
        System.out.println("userDao.addUser"+user.getUserName());
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public List<User> getAllUser() {//查找所有用户信息
        String hql = "from User";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }

    @Override
    public boolean delUser(String id) {//删除用户
        String hql = "delete User u where u.id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, id);

        return (query.executeUpdate() > 0);
    }

    @Override
    public User getUser(String id) {//根据id得到单个用户
        System.out.println("userDao.getUser(id)");
        String hql = "from User u where u.id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);//此时使用的是hql语句
        query.setString(0, id);

        return (User) query.uniqueResult();
    }

    @Override
    public boolean updateUser(User user) {//修改用户信息,hql语句
        String hql = "update User u set u.userName=?,u.age=?,u.recordDate=? where u.id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, user.getUserName());
        query.setInteger(1, user.getAge());
        query.setString(2, user.getRecordDate());
        query.setString(3, user.getId());

        return (query.executeUpdate() > 0);
    }
}
