package com.one.swing.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 *
 * @author vtstar
 * @date 2017/12/19
 */
@Entity
@Table(name="t_user")
public class User extends simpleEntityImpl<User>{


    @Id
    @GeneratedValue(generator = "system-uuid")  //使用uuid生成主键的方式
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(length=32)
    private String id;

    @Column(length=32)
    private String userName;

    @Column
    private Integer age;
    @Column(length=32)
    private String password;
    @Column
    private boolean isOnline;


    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        //id = UUID.randomUUID().toString();
        System.out.println("id"+id);
        this.id =id;
    }

}

