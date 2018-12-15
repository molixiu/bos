package com.xjwfk.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xjwfk.domain.Staff;
import com.xjwfk.service.StaffService;
import com.xjwfk.utils.MyJsonUtils;
import com.xjwfk.web.action.base.BaseAction;


@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {
	@Autowired
	private StaffService staffService;
	private String ids; // 这个是批量删除或删除的
	private String q; // 这个是根据关键字查询取派员的参数

	public String save() {
		staffService.save(model);
	}

	public String pageQuery() throws IOException { // 分页查询
		staffService.pageQuery(pageBean);
		String json = MyJsonUtils.OjectToJson(pageBean, new String[] { "detachedCriteria", "page","decidedzones" });
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
		staff.setHaspda(model.getHaspda()); // 到这里数据封装完毕

		staffService.update(staff);

		return "staffList";
	}

	public String listAjax() throws IOException {
		List<Staff> staffs = null;
		if (StringUtils.isNotBlank(q)) {
			detachedCriteria.add(Restrictions.ne("deltag", "1"));	//过滤已经被删除的派送员
			detachedCriteria.add(Restrictions.like("name", "%"+q+"%"));
			staffs = staffService.findBy_Keyword(detachedCriteria);
		} else {
			staffs = staffService.findAll();
		}
		
		String json = MyJsonUtils.OjectToJson(staffs, new String[] {"telephone","haspda","deltag","station","standard","decidedzones"});
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(json);
		return null;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setQ(String q) {
		this.q = q;
	}
	
}
