package com.xjwfk.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.xjwfk.domain.PageBean;
import com.xjwfk.domain.Staff;

public interface StaffService {

	public void save(Staff model);
	public void pageQuery(PageBean pageBean);	//分页查询
	public void delete(String ids);				//批量删除或者单个删除都可以,你喜欢就好
	public Staff find_ById(String id);			//我真是个笨蛋
	public void update(Staff staff);			//除了喜欢你
												//什么也不会
	public List<Staff> findBy_Keyword(DetachedCriteria detachedCriteria);
	public List<Staff> findAll();
}
