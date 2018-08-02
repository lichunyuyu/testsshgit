package com.one.dao.HibernateTwo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by vtstar on 2017/12/13.
 */
public interface IHibernateService extends Serializable {

    List queryForList(String var1);

    List queryForList(String var1, int var2, int var3);

    List queryForList(String var1, int var2, int var3, Object... var4);
}
