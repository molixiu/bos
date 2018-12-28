package com.xjwfk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xjwfk.dao.WorkordermanageDao;
import com.xjwfk.domain.Workordermanage;
import com.xjwfk.service.WorkordermanageService;
@Service
@Transactional
public class WorkordermanageServiceImpl implements WorkordermanageService {
	@Autowired
	private WorkordermanageDao workordermanageDao;
	
	@Override
	public void save(Workordermanage workordermanage) {
		workordermanageDao.save(workordermanage);
	}

}
