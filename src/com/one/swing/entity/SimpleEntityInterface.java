package com.one.swing.entity;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by vtstar on 2017/12/19.
 */

/**
 * 实现表单的自动装配，主要是让实体增加反射赋值的方法：
 *
 * 先定义一个 装配接口
 * */
public interface SimpleEntityInterface<T extends Serializable> extends Serializable{

        public void _setValue(String fieldsName,String value);
        //class
        public String _getEntityName();
        //not class ; it's table name
        public String _getTableName();

        //单个插入  (需要 dao 层配合)
        public void _add(Map map);

        //通用 插入 方法 （泛型） （只需要entity  即可--  2. Entity --与 数据库表对应 t_entity ; 3. IFrame -- 对应实体窗体设值 Map 需要与 其对应的entity（或表） 的 字段值 对应）
        public int _addAll(Map map) throws SQLException;


}
