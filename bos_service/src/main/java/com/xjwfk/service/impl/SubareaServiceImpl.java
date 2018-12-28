package com.xjwfk.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xjwfk.dao.SubareaDao;
import com.xjwfk.domain.PageBean;
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
	@Override
	public void pageQuery(PageBean pageBean) {
		subareaDao.pageQuery(pageBean);
	}
	@Override
	public List<Subarea> findAll() {
		return subareaDao.findAll();
	}
	@Override
	public List<Subarea> findList_notAssociate(DetachedCriteria detachedCriteria) {
		return subareaDao.findByCriteria(detachedCriteria);
	}
	@Override
	public List<Subarea> findListByDecidedzoneId(DetachedCriteria detachedCriteria) {
		return subareaDao.findByCriteria(detachedCriteria);
	}

}
