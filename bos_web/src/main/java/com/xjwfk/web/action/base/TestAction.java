package com.xjwfk.web.action.base;

import java.lang.reflect.Type;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xjwfk.domain.User;
@Controller
public class TestAction<T> extends BaseAction<User> {
	
	

}
