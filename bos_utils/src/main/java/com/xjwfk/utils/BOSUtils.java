package com.xjwfk.utils;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.xjwfk.domain.User;

public class BOSUtils {
	
	/*
	 * 获得Sessio对象
	 * */
	public static HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}
	/*
	 * 获得当前登陆的用户对象
	 * */
	public static User getCurrentLoginUser() {
		return (User) getSession().getAttribute("user");
	}
}
