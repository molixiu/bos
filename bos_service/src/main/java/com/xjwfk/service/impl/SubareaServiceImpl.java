package com.xjwfk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xjwfk.dao.SubareaDao;
import com.xjwfk.domain.Subarea;
import com.xjwfk.service.SubareaService;

@Service
@Transactional
public class SubareaServiceImpl implements SubareaService {
	@Autowired
	private SubareaDao subareaDao;
	@Override
	public void save(Subarea model) {
		subareaDao.save(model);
	}

}
