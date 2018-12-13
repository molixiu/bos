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
import com.xjwfk.web.action.base.BaseAction;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {
	@Autowired
	private StaffService staffService;
	private int page;	//这
	private int rows;	//两个参数是分页查询的
	private String ids; //这个是批量删除或删除的

	public String save() {
		staffService.save(model);
		return "save_success";
	}

	public String pageQuery() throws IOException { // 分页查询
		PageBean pageBean = new PageBean();
		pageBean.setPage(page);
		pageBean.setPageSize(rows);
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Staff.class);
		pageBean.setDetachedCriteria(detachedCriteria);

		staffService.pageQuery(pageBean);
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] { "detachedCriteria", "page", }); // 把不需要的数据剔除
		String json = JSONObject.fromObject(pageBean, jsonConfig).toString();

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

	public void setPage(int page) {
		this.page = page;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	
}
