/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public class BaseDaoImpl implements BaseDao {

    @Autowired
    HibernateTemplate hibernateTemplate;

	public Object loadById(Class<?> clazz, Serializable id) {
        return hibernateTemplate.get(clazz, id);
    }

    @SuppressWarnings("rawtypes")
	public Object loadObject(String hql) {
        final String hql1 = hql;
        Object obj = null;
        List list = hibernateTemplate.executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql1);
                return query.list();
            }
        });
        if (list.size() > 0) {
            obj = list.get(0);
        }
        return obj;
    }

	public void delById(Class<?> clazz, Serializable id) {
        hibernateTemplate.delete(hibernateTemplate.load(clazz, id));
    }

    public void saveOrUpdate(Object obj) {
        hibernateTemplate.saveOrUpdate(obj);
    }

    @SuppressWarnings("rawtypes")
	public List listAll(String clazz) {
        return hibernateTemplate.find("from " + clazz + " as c ");
    }

    @SuppressWarnings("rawtypes")
	public List listAll(String clazz, int pageNo, int pageSize) {
        final int pNo = pageNo;
        final int pSize = pageSize;
        final String hqlString = "from " + clazz + " as c order by c.id desc";
        List list = hibernateTemplate.executeFind(
                new HibernateCallback<Object>() {

                    public Object doInHibernate(Session sn) throws HibernateException, SQLException {
                        Query query = sn.createQuery(hqlString);
                        query.setMaxResults(pSize);
                        query.setFirstResult((pNo - 1) * pSize);
                        List result = query.list();
                        if (!Hibernate.isInitialized(result)) {
                            Hibernate.initialize(result);
                        }
                        return result;
                    }
                });
        return list;
    }

    public int countAll(String clazz) {
        final String hql = "select count(*) from  " + clazz + " as c ";
        Long count = (Long) hibernateTemplate.execute(new HibernateCallback<Object>() {

            public Object doInHibernate(Session sn) throws HibernateException, SQLException {
                Query query = sn.createQuery(hql);
                query.setMaxResults(1);
                return query.uniqueResult();
            }
        });
        return count.intValue();
    }

    @SuppressWarnings("rawtypes")
	public List query(String hql) {
        final String hql1 = hql;

        return hibernateTemplate.executeFind(new HibernateCallback<Object>() {

            public Object doInHibernate(Session sn) throws HibernateException, SQLException {
                Query query = sn.createQuery(hql1);
                return query.list();
            }
        });
    }

    @SuppressWarnings("rawtypes")
	public List query(String hql, int pageNo, int pageSize) {
        final int pNo = pageNo;
        final int pSize = pageSize;
        final String hqlString = hql ;
        List list = hibernateTemplate.executeFind(
                new HibernateCallback<Object>() {

                    public Object doInHibernate(Session sn) throws HibernateException, SQLException {
                        Query query = sn.createQuery(hqlString);
                        query.setMaxResults(pSize);
                        query.setFirstResult((pNo - 1) * pSize);
                        List result = query.list();
                        if (!Hibernate.isInitialized(result)) {
                            Hibernate.initialize(result);
                        }
                        return result;
                    }
                });
        return list;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public int countQuery(String hql) {
        final String hql1 = hql;
        Long count = (Long) hibernateTemplate.execute(new HibernateCallback(){

            public Object doInHibernate(Session sn) throws HibernateException, SQLException {
                  Query query = sn.createQuery(hql1);
                  query.setMaxResults(1);
                  return query.uniqueResult();
            }
            
        });
        return  count.intValue();
    }

    public int update(String hql) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

  

   
}
