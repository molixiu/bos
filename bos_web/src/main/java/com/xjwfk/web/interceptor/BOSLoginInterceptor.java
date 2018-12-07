package com.xjwfk.web.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.xjwfk.domain.User;
import com.xjwfk.utils.BOSUtils;

public class BOSLoginInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		User user = BOSUtils.getCurrentLoginUser();
		if (user == null) {	//如果用户没登录
			return "login_redirect";
		}else {
			invocation.invoke();
		}
		return null;
	}

}
