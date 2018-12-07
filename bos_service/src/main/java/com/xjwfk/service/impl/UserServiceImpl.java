package com.xjwfk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xjwfk.dao.UserDao;
import com.xjwfk.domain.User;
import com.xjwfk.service.UserService;
import com.xjwfk.utils.MD5Utils;
@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public User getUser_ByusernameAndpassword(String username, String password) {
		String password_md5 = MD5Utils.md5(password);
		return userDao.getUser_ByusernameAndpassword(username,password_md5);
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
