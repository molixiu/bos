package com.xjwfk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xjwfk.dao.StaffDao;
import com.xjwfk.dao.base.impl.BaseDaoImpl;
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
}
