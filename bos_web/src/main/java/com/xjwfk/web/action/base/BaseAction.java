package com.xjwfk.web.action.base;




import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
	protected T model;
	@Override
	public T getModel() {
		// TODO Auto-generated method stub
		return model;
	}

	public BaseAction() {
		ParameterizedType superclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		Type[] typeArguments = superclass.getActualTypeArguments();
		Class<T> entiryClass =  (Class<T>) typeArguments[0];
		try {	//运行时反射获得实体类对象
			model = entiryClass.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
