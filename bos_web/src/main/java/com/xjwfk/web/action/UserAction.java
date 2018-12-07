package com.xjwfk.web.action;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xjwfk.domain.User;
import com.xjwfk.service.UserService;
import com.xjwfk.utils.BOSUtils;
import com.xjwfk.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
	private String checkcode;
	@Autowired
	private UserService userService;

	public String login() {
		HttpSession session = BOSUtils.getSession();
		String checkcode_inSession = (String) session.getAttribute("checkcode");

		if (StringUtils.isBlank(checkcode)) { // 如果验证码为空
			this.addActionError("请输入验证码");
			return LOGIN;
		} else if (!checkcode.equals(checkcode_inSession)) { //// 如果验证码输入不正确
			this.addActionError("您输入的验证码不正确");
			System.out.println(checkcode_inSession);
			System.out.println(checkcode);
			return LOGIN;
		}

		User user = userService.getUser_ByusernameAndpassword(model.getUsername(), model.getPassword());
		if (user == null) {
			this.addActionError("用户名或密码错误");
			return LOGIN;
		}
		session.setAttribute("user", user); // 这行代码是真正的登录
		return "home";
	}

	public String quit() {
		System.out.println("111");
		ServletActionContext.getRequest().getSession().invalidate();// 这段代码彻底清空了session
		return "login_redirect";
	}

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
