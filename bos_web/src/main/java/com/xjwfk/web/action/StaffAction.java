package com.xjwfk.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xjwfk.domain.Staff;
import com.xjwfk.service.StaffService;
import com.xjwfk.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {
	@Autowired
	private StaffService staffService;

	public String save() {
		staffService.save(model);
		return "save_success";
	}

	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}
}
