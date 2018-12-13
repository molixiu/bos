package com.xjwfk.web.action;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.xjwfk.domain.PageBean;
import com.xjwfk.domain.Staff;
import com.xjwfk.service.StaffService;
import com.xjwfk.utils.MyJsonUtils;
import com.xjwfk.web.action.base.BaseAction;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {
	@Autowired
	private StaffService staffService;
	private String ids; //这个是批量删除或删除的

	public String save() {
		staffService.save(model);
		return "save_success";
	}

	public String pageQuery() throws IOException { // 分页查询	
		staffService.pageQuery(pageBean);
		String json = MyJsonUtils.OjectToJson(pageBean, new String[] { "detachedCriteria", "page", });
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(json);
		return NONE;
	}
	
	public String delete() {
		staffService.delete(ids);
		return "staffList";
	}
	
	public String update() {	
		Staff staff = staffService.find_ById(model.getId());
		staff.setName(model.getName());
		staff.setTelephone(model.getTelephone());
		staff.setStation(model.getStation());
		staff.setStandard(model.getStandard());
		staff.setHaspda(model.getHaspda());	//到这里数据封装完毕
		
		staffService.update(staff);
		
		return "staffList";
	}

	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}


	public void setIds(String ids) {
		this.ids = ids;
	}
	
}
