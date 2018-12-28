package com.xjwfk.web;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xjwfk.domain.Customer;
import com.xjwfk.domain.Noticebill;
import com.xjwfk.service.CustomerService;
import com.xjwfk.service.NoticebillService;
import com.xjwfk.utils.BOSUtils;
import com.xjwfk.utils.MyJsonUtils;
import com.xjwfk.web.action.base.BaseAction;
@Controller
@Scope("prototype")
public class NoticebillAction extends BaseAction<Noticebill> {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private NoticebillService noticebillService;
	
	public String findCustomerByTelephone() throws IOException {
		Customer customer = customerService.findCustomerByTelephone(model.getTelephone());
		String json = MyJsonUtils.OjectToJson(customer);
		ServletActionContext.getResponse().setContentType("html/text;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(json);
		return null;
	}
	
	public String save() {
		model.setUser(BOSUtils.getCurrentLoginUser());
		noticebillService.save(model);
		return "list";
	}
}
