package com.xjwfk.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xjwfk.domain.Decidedzone;
import com.xjwfk.service.DecidedzoneService;
import com.xjwfk.utils.MyJsonUtils;
import com.xjwfk.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone> {
	String[] subareaid;
	@Autowired
	private DecidedzoneService decidezoneService;
	
	public String add() {
		if (StringUtils.isBlank(model.getStaff().getId())) {	//如果不进行staff.id的空字符串判断,当staff.id为空串后面hibernate会报错
			model.setStaff(null);
		}
		decidezoneService.add(model, subareaid);
		return "list";
	}

	public String pageQuery() throws IOException {
		List<Decidedzone> decidedzones = null;
		decidezoneService.pageQuery(pageBean);
		
		String json = MyJsonUtils.OjectToJson(pageBean, new String[] {"detachedCriteria","pageSize","page","subareas","haspda","deltag","decidedzones"});
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(json);
		return null;
	}
	
	
	public void setSubareaid(String[] subareaid) {
		this.subareaid = subareaid;
	}	
}
