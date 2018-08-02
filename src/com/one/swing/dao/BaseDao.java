package com.one.swing.dao;

import com.one.swing.db.DButils;
import com.one.swing.entity.simpleEntityImpl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Created by vtstar on 2017/12/19.
 */

public class BaseDao<T> {

    private simpleEntityImpl simpleEntity;

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    //private  Class<T> entityClass = null;

    /**
     * 获取  类 对象
     * */
    public Object _getBaseDaoobj(){
        Class<T> entityClass = null;
        Class c = getClass();
        System.out.println("Class=c="+c);
        Type t = c.getGenericSuperclass();
        if(t instanceof ParameterizedType){
            Type[] p = ((ParameterizedType) t).getActualTypeArguments();
            entityClass = (Class<T>) p[0];
        }
        Object obj  = entityClass.getSimpleName();
        System.out.println("entityClass="+entityClass);  // class com.one.swing.entity.User
        System.out.println("entityClass.getName="+entityClass.getName());// com.one.swing.entity.User
        System.out.println("entityClass.getSimpleName="+entityClass.getSimpleName());// User
        return obj;
    }
    // 获得类名子
    public String _getBaseDao(){
        Class<T> entityClass = null;
        Class c = getClass();
        System.out.println("Class=c="+c);
        Type t = c.getGenericSuperclass();
        if(t instanceof ParameterizedType){
            Type[] p = ((ParameterizedType) t).getActualTypeArguments();
            entityClass = (Class<T>) p[0];
        }
        System.out.println("entityClass="+entityClass);  // class com.one.swing.entity.User
        System.out.println("entityClass.getName="+entityClass.getName());// com.one.swing.entity.User
        System.out.println("entityClass.getSimpleName="+entityClass.getSimpleName());// User
//        //获取实体类的所有属性 ，返回Field数组
//        Field[] fields = entityClass.getDeclaredFields();

//        System.out.println("fields="+fields);
//        // fields[0] =  private java.lang.String com.one.swing.entity.User.id
//        String methodName = fieldsName.substring(0,1).toUpperCase()+fieldsName.substring(1);

        // 截取最后一个点 . 后的值  entityClass = class com.one.swing.entity.User
//        ①、int index = str.LastIndexOf(".");
//②、string value=str.SubString(index,str.Length);
        String StringentityClass = entityClass.toString();
        int index = StringentityClass.lastIndexOf(".");
        String entityNameM = StringentityClass.substring(index+1,StringentityClass.length());
        //首字母转换 为 小写 （为了表名）
        String  entityNameR = entityNameM.substring(0,1).toLowerCase()+entityNameM.substring(1);
        System.out.println("entityNameR="+entityNameR);
        return entityNameR;
    }

    /**
     * 获取类名字
     * */
    public String _getEntityName(){
        return simpleEntity._getEntityName();
    }
    /**
     * 获取表名
     * */
    public String _getTableName(){
        //return simpleEntity._getTableName();
        return "t_"+_getBaseDao();
    }
    public Class<T>  _getEntityClass(){
        Class<T> entityClass = null;
        Class c = getClass();
        System.out.println("Class=c="+c);
        Type t = c.getGenericSuperclass();
        if(t instanceof ParameterizedType){
            Type[] p = ((ParameterizedType) t).getActualTypeArguments();
            entityClass = (Class<T>) p[0];
        }
        System.out.println("entityClass="+entityClass);
        return entityClass;
    }

    /**
     * 查询
     * */
    public List<Map> _queryAll(){
       // Class<T> entityClass =  _getEntityClass();
        List<Map> list = new ArrayList<Map>();
       // List a = new ArrayList();
        String sql = "select * from "+_getTableName();
        try{
            conn = DButils.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            System.out.println("ps.tostring="+ps.toString());
            //获取实体类的所有属性 ，返回Field数组
//            Field[] fields = entityClass.getDeclaredFields();

            while(rs.next()){
                Map rowData = new HashMap();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), rs.getObject(i));
//                    if(fields[i].getName().equals(md.getColumnName(i))){
//
//                    }
                }
                list.add((Map) rowData);
                //a.add(rowData);
                System.out.println("list="+list);
                //System.out.println("a="+a);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return list;
    }

    /**
     * 插入
     * */
    public void _add(Map map){
        Class<T> entityClass = null;
        entityClass = _getEntityClass();
        //获取实体类的所有属性 ，返回Field数组
        Field[] fields = entityClass.getDeclaredFields();
//        for(Object keyName:map.keySet()){
//            String keyname = (String) keyName;
//        }
        for (Iterator i = map.keySet().iterator(); i.hasNext();)
        {
            String key = (String) i.next();
            //首字母转大写
            String upperKey = key.substring(0,1).toUpperCase()+key.substring(1);
            String value = (String) map.get(key);
            System.out.println("key="+key);
            //text+=key + " = " + value;
           for(int j=0;j<fields.length;j++){
               String type = fields[j].getGenericType().toString();
               //好傻
               //int index = fields[j].toGenericString().lastIndexOf(".");
               //String fieldsj = fields[j].toGenericString().substring(index+1,fields[j].toGenericString().length());
               String fieldsj = fields[j].getName();
               // 加上判断
               if(key.equals(fieldsj)){
                   try{
                       //如果type 是 类 类型，则前面包含“class" ,后面跟类名
                       if(type.equals("class java.lang.String")){
                           Method m = entityClass.getMethod("set"+upperKey,String.class);
                           System.out.println("String=m="+m);
                           m.invoke(this,value);
                       }
                       if(type.equals("class java.lang.Integer")){
                           Method m = entityClass.getMethod("set"+upperKey,Integer.class);
                           m.invoke(this,Integer.valueOf(value));
                       }
                       if(type.equals("class java.lang.boolean")){
                           Method m = entityClass.getMethod("set"+upperKey,Boolean.class);
                           m.invoke(this, Boolean.valueOf(value));
                       }
                       if(type.equals("class java.util.Date")){
                           Method m = entityClass.getMethod("set"+upperKey, Date.class);
                           m.invoke(this,new Date(value));
                       }
                   }catch(Exception e){
                       System.out.println("泛型类baseDao--混淆泛型造成的应用逻辑问题getGenericType()");
                       e.printStackTrace();
                   }
               }
           }
        }

    }

}
