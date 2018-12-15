package com.xjwfk.web.action.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xjwfk.domain.PageBean;
import com.xjwfk.domain.Staff;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
	protected T model;
	protected PageBean pageBean = new PageBean();
	protected DetachedCriteria detachedCriteria = null;

	@Override
	public T getModel() {
		// TODO Auto-generated method stub
		return model;
	}

	public BaseAction() {
		ParameterizedType superclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		Type[] typeArguments = superclass.getActualTypeArguments(); // 获得这个类的泛型的数组
		Class<T> entiryClass = (Class<T>) typeArguments[0];
		try { // 运行时反射获得实体类对象
			model = entiryClass.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		detachedCriteria = DetachedCriteria.forClass(entiryClass);
		pageBean.setDetachedCriteria(detachedCriteria);
	}

	public void setPage(int page) {
		pageBean.setPage(page);
	}

	public void setRows(int rows) {
		pageBean.setPageSize(rows);
	}
}
