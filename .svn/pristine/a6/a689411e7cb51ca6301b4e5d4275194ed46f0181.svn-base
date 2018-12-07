package com.xjwfk.dao.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.xjwfk.dao.base.BaseDao;


public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T>{
	public Class<T> clazz;
	
	@Autowired	//没办法，只能自定义方法进行sessionFactory的注入了
	public void setMySessionFactory(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}
	@Override
	public void save(T entity) {
		this.getHibernateTemplate().save(entity);
		
	}

	@Override
	public void delete(T entity) {
		this.getHibernateTemplate().delete(entity);
	}

	@Override
	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
	}

	@Override
	public T find_ById(Serializable id) {
		return this.getHibernateTemplate().get(clazz, id);
	}

	@Override
	public List<T> findAll() {
		String hql = "From " + clazz.getName();
		List<T> find = (List<T>) this.getHibernateTemplate().find(hql);
		if (find != null) {
			return find;
		}
		return null;
	}

	public BaseDaoImpl() {
		//三行代码获得泛型
		ParameterizedType superclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		Type[] typeArguments = superclass.getActualTypeArguments();
		Class<T> clazz = (Class<T>) typeArguments[0];
	}
	
}
