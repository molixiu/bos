package com.xjwfk.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xjwfk.dao.UserDao;
import com.xjwfk.dao.base.impl.BaseDaoImpl;
import com.xjwfk.domain.User;
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public User getUser_ByusernameAndpassword(String username, String password) {
		String hql = "From User where username=? and password=?";
		List<User> find = (List<User>) this.getHibernateTemplate().find(hql, username,password);
		if (find !=null && find.size() > 0) {	
			return find.get(0);
		}
		return null;
	}

}
