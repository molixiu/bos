package com.xjwfk.web.action;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xjwfk.domain.Workordermanage;
import com.xjwfk.service.WorkordermanageService;
import com.xjwfk.web.action.base.BaseAction;
@Controller
@Scope("prototype")
public class WorkordermanageAction extends BaseAction<Workordermanage> {
	@Autowired
	private WorkordermanageService workordermanageService;
	
	public String save() throws IOException {
		System.out.println(model);
		try {
			workordermanageService.save(model);
		} catch (Exception e) {
			ServletActionContext.getResponse().setContentType("html/text;charset=utf-8");
			ServletActionContext.getResponse().getWriter().write("0");
		}
		return null;
	}
}
