package com.one.dao.HibernateTwo;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by vtstar on 2017/12/13.
 */
/**
 * 存储层bean   自己加 增 、删、改 的方法。  这里只放了 查询列表
 * */
@Repository("dbService")
public class HibernateServiceSupport implements IHibernateService {

    // -servlet.xml 中 配置  name=hibernateTemplate    get set 方法
    @Autowired
    public HibernateTemplate hibernateTemplate;

//    @Autowired
//    public JdbcTemplate jdbcTemplate;

    @Override
    public List queryForList(String sql) {
        return this.queryForList(sql, 0, 0);
    }

    @Override
    public List queryForList(String sql, int pageNo, int pageSize) {
        return this.queryForList(sql, pageNo, pageSize, (Object[])null);
    }
    @Override
    public List queryForList(String sql, int pageNo, int pageSize, Object... values) {
        return this.queryForList(sql, true, (Map)null, pageNo, pageSize, values);
    }

    public List queryForList(String sql, boolean rowIsMapOrArray, Map<String, Object> parameterMap, int pageNo, int pageSize, Object... values) {
        return this.queryForDataList(sql, rowIsMapOrArray, parameterMap, pageNo, pageSize, values);
    }


    private List queryForDataList(final String sql, final boolean rowIsMapOrArray, final Map<String, Object> parameterMap, final int pageNo, final int pageSize, final Object... values) {
        Object lt = (List)this.getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                SQLQuery query = session.createSQLQuery(sql);
                if(rowIsMapOrArray) {
                    query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
                }

                if(parameterMap != null) {
                    Iterator list = parameterMap.keySet().iterator();

                    while(list.hasNext()) {
                        String key = (String)list.next();
                        query.setParameter(key, parameterMap.get(key));
                    }
                }
                int var5;
                if(values != null) {
                    for(var5 = 0; var5 < values.length; ++var5) {
                        query.setParameter(var5, values[var5]);
                    }
                }

                if(pageNo != 0 && pageSize != 0) {
                    var5 = (pageNo - 1) * pageSize;
                    query.setFirstResult(var5).setMaxResults(pageSize);
                }

                List var6 = query.list();
                return var6;
            }
        });
        if(lt == null) {
            lt = new ArrayList();
        }
        return (List)lt;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public HibernateTemplate getHibernateTemplate() {
        return this.hibernateTemplate;
    }

    public void flush() {
        this.hibernateTemplate.flush();
    }
}
