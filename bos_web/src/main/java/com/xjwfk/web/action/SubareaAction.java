package com.xjwfk.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xjwfk.domain.Subarea;
import com.xjwfk.service.SubareaService;
import com.xjwfk.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {
	@Autowired
	private SubareaService subareaService;

	public String save() {
		subareaService.save(model);
		return "save_sucess";
	}
}
