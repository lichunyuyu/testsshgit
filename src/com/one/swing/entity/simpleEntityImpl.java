package com.one.swing.entity;

import com.one.swing.db.DButils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by vtstar on 2017/12/19.
 */
/**
 * 实现类
 * */
public class simpleEntityImpl<T extends Serializable> implements SimpleEntityInterface<T>{

    private String uuid = (UUID.randomUUID().toString()).replace("-","");

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql;

    //  有序 集合-- LinkedHashMap  （为了 取出，拿出）
    Map map1 = new LinkedHashMap();


//    fieldsName=age,value=17
//    Class=c=class com.one.swing.entity.User
//    entityClass=class com.one.swing.entity.User
//    fields=[Ljava.lang.reflect.Field;@66d2e7d9
//    methodName=Age,fieldsName=age
//17
    /**
     * 获取 Class + 包名。类名   ；  class com.one.swing.entity.User
     * */
    public Class<T>  _getEntityClass(){
        Class<T> entityClass = null;
        Class c = getClass();
        //System.out.println("Class=c="+c);
        Type t = c.getGenericSuperclass();
        if(t instanceof ParameterizedType){
            Type[] p = ((ParameterizedType) t).getActualTypeArguments();
            entityClass = (Class<T>) p[0];
        }
        //System.out.println("entityClass="+entityClass);
        return entityClass;
    }
    /**
     * 获取 对象名称
     * */
    public Object _getEntityObject(){
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
    /**
     * 获取类名
     * */
    @Override
    public String _getEntityName() {
        Class<T> entityClass= _getEntityClass();
        String entityClassString = entityClass.toString();
        //取点后面的entity   用于取表名 user (即创建表t_  需要与entity对应)
        int index = entityClassString.lastIndexOf(".");
        String entityName = entityClassString.substring(index+1,entityClassString.length());
        return entityName;
    }

    /**
     * 获取表名
     * */
    @Override
    public String _getTableName() {
        Class<T> entityClass= _getEntityClass();
        String entityClassString = entityClass.toString();
        //取点后面的entity   用于取表名 user (即创建表t_  需要与entity对应)
        int index = entityClassString.lastIndexOf(".");
        String entityName = entityClassString.substring(index+1,entityClassString.length());
        //首字母转换  获取表名
        String tableName = "t_"+ entityName.substring(0,1).toLowerCase()+entityName.substring(1);
        //System.out.println("tableName="+tableName);
        return tableName;
    }

    /**
     * 获取属性值
     * */
    @Override
    public void _setValue(String fieldsName, String value) {
        //System.out.println("fieldsName="+fieldsName+",value="+value);
        Class<T> entityClass = null;
//        Class c = getClass();
//        System.out.println("Class=c="+c);
//        Type t = c.getGenericSuperclass();
//        if(t instanceof ParameterizedType){
//            Type[] p = ((ParameterizedType) t).getActualTypeArguments();
//            entityClass = (Class<T>) p[0];
//        }
        entityClass = _getEntityClass();
        System.out.println("entityClass="+entityClass);
        //获取实体类的所有属性 ，返回Field数组
        Field[] fields = entityClass.getDeclaredFields();
        System.out.println("fields="+fields);
        // fields[0] =  private java.lang.String com.one.swing.entity.User.id
        //首字母转大写
        String methodName = fieldsName.substring(0,1).toUpperCase()+fieldsName.substring(1);
        // methodName = UserName  例如：setUserName  ;   fieldsName = userName;
       System.out.println("methodName="+methodName+",fieldsName="+fieldsName);
        for(int i=0;i<fields.length;i++){
            if(fields[i].getName().equals(fieldsName)){
                String type = fields[i].getGenericType().toString();
                try{
                    //如果type 是 类 类型，则前面包含“class" ,后面跟类名
                    if(type.equals("class java.lang.String")){
                        Method m = entityClass.getMethod("set"+methodName,String.class);
                        System.out.println("String=m="+m);
                        m.invoke(this,value);
                    }
                    if(type.equals("class java.lang.Integer")){
                        Method m = entityClass.getMethod("set"+methodName,Integer.class);
                        m.invoke(this,Integer.valueOf(value));
                    }
                    if(type.equals("class java.lang.boolean")){
                        Method m = entityClass.getMethod("set"+methodName,Boolean.class);
                        m.invoke(this, Boolean.valueOf(value));
                    }
                    if(type.equals("class java.util.Date")){
                        Method m = entityClass.getMethod("set"+methodName, Date.class);
                        m.invoke(this,new Date(value));
                    }
                }catch(Exception e){
                    System.out.println("泛型类--混淆泛型造成的应用逻辑问题getGenericType()");
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 插入
     * */
    @Override
    public void _add(Map map){
        map.put("id",uuid);

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
            //System.out.println("key="+key);
            // 直接 调用 获取属性 -- 设 值 方法
            _setValue(key,value);

            //下面也可以：
//            for(int j=0;j<fields.length;j++){
//                String type = fields[j].getGenericType().toString();
//                //System.out.println("fields[j]="+fields[j]);//private java.lang.String com.one.swing.entity.User.id
//                //好傻
//                //int index = fields[j].toGenericString().lastIndexOf(".");
//                //String fieldsj = fields[j].toGenericString().substring(index+1,fields[j].toGenericString().length());
//                System.out.println("fields[j].getName()="+fields[j].getName());
//                String fieldsj = fields[j].getName();
//                // 加上判断
//                if(key.equals(fieldsj)){
//                    try{
//                        //如果type 是 类 类型，则前面包含“class" ,后面跟类名
//                        if(type.equals("class java.lang.String")){
//                            Method m = entityClass.getMethod("set"+upperKey,String.class);
//                            System.out.println("String=m="+m);
//                            m.invoke(this,value);
//                        }
//                        if(type.equals("class java.lang.Integer")){
//                            Method m = entityClass.getMethod("set"+upperKey,Integer.class);
//                            m.invoke(this,Integer.valueOf(value));
//                        }
//                        if(type.equals("class java.lang.boolean")){
//                            Method m = entityClass.getMethod("set"+upperKey,Boolean.class);
//                            m.invoke(this, Boolean.valueOf(value));
//                        }
//                        if(type.equals("class java.util.Date")){
//                            Method m = entityClass.getMethod("set"+upperKey, Date.class);
//                            m.invoke(this,new Date(value));
//                        }
//                    }catch(Exception e){
//                        System.out.println("泛型类simple--混淆泛型造成的应用逻辑问题getGenericType()");
//                        e.printStackTrace();
//                    }
//                }
//            }
        }
    }

    /**
     * 插入 通用
     * */
    @Override
    public int _addAll(Map map) throws SQLException {
        map.put("id",uuid);
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
            String value = (String) map.get(key);
            // 获取 map1
            _setMap1(key,value);
        }
        System.out.println("map1="+map1);
        try{
            conn = DButils.getConnection();
            StringBuffer sbuf = new StringBuffer();
            sbuf.append("insert into ");
            sbuf.append(_getTableName());
            sbuf.append("(");
            //放属性
            for (Iterator i = map1.keySet().iterator(); i.hasNext();)
            {
                String key = (String) i.next();
                sbuf.append(key);
                sbuf.append(",");
            }
            sbuf.setLength(sbuf.length()-1);
            //sbuf.append("id");
            sbuf.append(")");
            System.out.println("sbuf="+sbuf);
            sbuf.append(" values(");
            // 放 值
            for (Iterator i = map1.keySet().iterator(); i.hasNext();)
            {
//                String key = (String) i.next();
//                String value = (String) map.get(key);
//                sbuf.append(value);
                //放 ？ 问号的
                i.next();  // 一定要加 ，加，加 （否则会 无脑循环）
                sbuf.append("?");
                sbuf.append(",");
            }
            sbuf.setLength(sbuf.length()-1);
            //sbuf.append(uuid);
            sbuf.append(")");
            // sql = "insert into t_customer(custName,password,age,id) values(?,?,?,?)";
            sql = sbuf.toString();
            System.out.println("sql="+sql);
            ps = conn.prepareStatement(sql);
            int m = 0;
            for (Iterator i = map1.keySet().iterator(); i.hasNext();)
            {
                m+=1;
                String key = (String) i.next();
                String value = (String) map.get(key);
                //ps.setString(m, value);
                //根据属性类型  设值
                _setPsType(m,key,value);
            }
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

    /**
     * 设置 map1   （填充map1）
     * */
    public void _setMap1(String fieldsName, String value) {
        System.out.println("fieldsName="+fieldsName+",value="+value);
        Class<T> entityClass = null;
        entityClass = _getEntityClass();
        //获取实体类的所有属性 ，返回Field数组
        Field[] fields = entityClass.getDeclaredFields();
        for(int i=0;i<fields.length;i++){
            if(fields[i].getName().equals(fieldsName)){
                // 有序 存放   在这里赋值
                map1.put(fieldsName,value);
            }
        }
    }
    /**
     * 判断 fields 属性 类型
     * */
    public void _setPsType(int m,String fieldsName, String value) {
        Class<T> entityClass = null;
//        Class c = getClass();
//        System.out.println("Class=c="+c);
//        Type t = c.getGenericSuperclass();
//        if(t instanceof ParameterizedType){
//            Type[] p = ((ParameterizedType) t).getActualTypeArguments();
//            entityClass = (Class<T>) p[0];
//        }
        entityClass = _getEntityClass();
        //获取实体类的所有属性 ，返回Field数组
        Field[] fields = entityClass.getDeclaredFields();
        for(int i=0;i<fields.length;i++){
            if(fields[i].getName().equals(fieldsName)){
                String type = fields[i].getGenericType().toString();
                System.out.println("_setPsType=*****"+type);
                try{
                    //如果type 是 类 类型，则前面包含“class" ,后面跟类名
                    if(type.equals("class java.lang.String")){
                        ps.setString(m, value);
                    }
                    if(type.equals("class java.lang.Integer")){
                       ps.setInt(m,Integer.parseInt(value));
                    }
                    if(type.equals("class java.lang.boolean")){
                        ps.setBoolean(m, Boolean.parseBoolean(value));
                    }
                    if(type.equals("class java.util.Date")){
                       ps.setDate(m, java.sql.Date.valueOf(value));
                    }
                }catch(Exception e){
                    System.out.println("泛型类_setPsType--混淆泛型造成的应用逻辑问题getGenericType()");
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *  通用  -  更新
     * */

    public int _updateAll(Map map){
        System.out.println("mapupdateall="+map);
        Class<T> entityClass = null;
        entityClass = _getEntityClass();
        //获取实体类的所有属性 ，返回Field数组
        Field[] fields = entityClass.getDeclaredFields();
        for (Iterator i = map.keySet().iterator(); i.hasNext();)
        {
            String key = (String) i.next();
            String value = (String) map.get(key);
            // 获取 map1
            _setMap1(key,value);
        }
        System.out.println("updatemap1="+map1);
        try{
            conn = DButils.getConnection();
            StringBuffer sbuf = new StringBuffer();
            sbuf.append("update ");
            sbuf.append(_getTableName());
            sbuf.append(" set ");
            for (Iterator i = map1.keySet().iterator(); i.hasNext();)
            {
                String key = (String) i.next();
                String value = (String) map.get(key);
                if(key.equals("id")){
                    continue;
                }
                sbuf.append(key);
                sbuf.append("=");
                sbuf.append("'");  // 加上引号
                sbuf.append(value);
                sbuf.append("'");
                sbuf.append(",");
            }
            sbuf.setLength(sbuf.length()-1);
            sbuf.append(" where id=");
            for (Iterator i = map1.keySet().iterator(); i.hasNext();)
            {
                String key = (String) i.next();
                String value = (String) map.get(key);
             if(key.equals("id")){
                 sbuf.append("'");
                 sbuf.append(value);
                 sbuf.append("'");
             }
            }
            sql = sbuf.toString();
            // update t_user set password=123,userName=å°æ,age=21 where id='40288728604d8edd01604d8f12150000'
            System.out.println("sqlupdate="+sql);  //  userName  值  未加 双引号     -- 或者 ？ 问号（插入时）‘ Parameter index out of range (1 > number of parameters, which is 0).’
            ps = conn.prepareStatement(sql);
            int m = 0;
            for (Iterator i = map1.keySet().iterator(); i.hasNext();)
            {
                m+=1;
                String key = (String) i.next();
                String value = (String) map.get(key);
                //ps.setString(m, value);
                //根据属性类型  设值
                _setPsType(m,key,value);
            }
            return ps.executeUpdate();


        }catch (SQLException ex){
            System.out.println("更新异常--_update(Map map)");
            ex.printStackTrace();
        }

        return 0;
    }

 // 改为 用  LinkedHashMap   有序
//     try{
//        conn = DButils.getConnection();
//        StringBuffer sbuf = new StringBuffer();
//        sbuf.append("insert into ");
//        sbuf.append(_getTableName());
//        sbuf.append("(");
//        //放属性
//        for(int j=0;j<fields.length;j++){
//            sbuf.append(fields[j].getName());
//            sbuf.append(fields[j].getName()+",");
//        }
//        //sbuf.setLength(sbuf.length()-1);
//        sbuf.append("id");
//        sbuf.append(")");
//        System.out.println("sbuf="+sbuf);
//        sbuf.append(" values(");
//        for (Iterator i = map.keySet().iterator(); i.hasNext();)
//        {
//
//        }
//        //sbuf.setLength(sbuf.length()-1);
//        sbuf.append(uuid);
//        sbuf.append(")");
}
