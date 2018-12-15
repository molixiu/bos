package com.xjwfk.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xjwfk.dao.StaffDao;
import com.xjwfk.dao.base.impl.BaseDaoImpl;
import com.xjwfk.domain.PageBean;
import com.xjwfk.domain.Staff;
import com.xjwfk.service.StaffService;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {
	@Autowired
	private StaffDao staffDao;

	@Override
	public void save(Staff model) {
		staffDao.save(model);
	}

	public void setStaffDao(StaffDao staffDao) {
		this.staffDao = staffDao;
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		staffDao.pageQuery(pageBean);
	}

	@Override
	public void delete(String ids) {
		String[] ids_Arr = ids.split(",");
		for (String id : ids_Arr) {
			staffDao.executeUpdate("staff.delete", id);
		}
	}

	@Override
	public Staff find_ById(String id) {
		return staffDao.find_ById(id);
	}

	@Override
	public void update(Staff staff) {
		staffDao.update(staff);
	}

	@Override
	public List<Staff> findBy_Keyword(DetachedCriteria detachedCriteria) {
		return staffDao.findByCriteria(detachedCriteria);
	}

	@Override
	public List<Staff> findAll() {
		return staffDao.findAll();
	}
}
