package com.xjwfk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xjwfk.dao.DecidedzoneDao;
import com.xjwfk.dao.SubareaDao;
import com.xjwfk.domain.Decidedzone;
import com.xjwfk.domain.PageBean;
import com.xjwfk.domain.Subarea;
import com.xjwfk.service.DecidedzoneService;

@Service
@Transactional
public class DecidedzoneServiceImpl implements DecidedzoneService {
	
	@Autowired
	private DecidedzoneDao decidedzoneDao;
	@Autowired
	private SubareaDao subareaDao;
	@Override
	public void add(Decidedzone decidedzone, String[] subareaid) {
		decidedzoneDao.save(decidedzone);
		if (subareaid != null) {	//要不然可能会空指针异常哦
			for(String subareaiid:subareaid) {
				Subarea subarea = subareaDao.find_ById(subareaiid);
				subarea.setDecidedzone(decidedzone);	//更新对于定区的外键
				subareaDao.update(subarea);
			}
		}
	}
	@Override
	public void pageQuery(PageBean pageBean) {
		decidedzoneDao.pageQuery(pageBean);
	}

}
