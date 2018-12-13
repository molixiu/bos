package com.xjwfk.dao.base;

import java.io.Serializable;
import java.util.List;

import com.xjwfk.domain.PageBean;

public interface BaseDao<T> {
	public void save(T entity);
	public void delete(T entity);
	public void update(T entity);
	public T find_ById(Serializable id);
	public List<T> findAll();
	public void pageQuery(PageBean pageBean);	//分页查询
	public void saveORupdate(T entity);
	//根据参数执行更新的操作
	public void executeUpdate(String executeName,Object...object);
}
