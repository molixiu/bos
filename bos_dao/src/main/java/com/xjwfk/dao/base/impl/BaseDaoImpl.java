package com.xjwfk.dao.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.xjwfk.dao.base.BaseDao;
import com.xjwfk.domain.PageBean;


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
		clazz = (Class<T>) typeArguments[0];
	}
	
	@Override
	public void executeUpdate(String executeName, Object... object) {
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.getNamedQuery(executeName);
		int index = 0;
		for(Object obj:object) {
			query.setParameter(index++, obj);
		}
		query.executeUpdate();
	}
	@Override
	public void pageQuery(PageBean pageBean) {
		int page = pageBean.getPage();
		int pageSize = pageBean.getPageSize();
		DetachedCriteria criteria = pageBean.getDetachedCriteria();
		
		criteria.setProjection(Projections.rowCount());
		List<Long> totalList = (List<Long>) this.getHibernateTemplate().findByCriteria(criteria);
		Long total = totalList.get(0);
		pageBean.setTotal(total.intValue());
		
		criteria.setProjection(null);
		/*下面这行代码表示不把关联的其他表的数据封装返回
		 * 比如表subarea里面有一外键对应表region
		 * 就不把region的数据封装返回了
		 * */
		criteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
		int firstResult = pageBean.getPage() - 1;
		int maxResults = pageBean.getPageSize();
		List rows = this.getHibernateTemplate().findByCriteria(criteria, firstResult, maxResults);
		pageBean.setRows(rows);
	}

	@Override
	public void saveORupdate(T entity) {
		this.getHibernateTemplate().saveOrUpdate(entity);
	}

	@Override
	public List<T> findByCriteria(DetachedCriteria criteria) {
		return (List<T>) this.getHibernateTemplate().findByCriteria(criteria);
	}
	
}
