package com.xjwfk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xjwfk.dao.RegionDao;
import com.xjwfk.domain.PageBean;
import com.xjwfk.domain.Region;
import com.xjwfk.service.RegionService;
@Service
@Transactional
public class RegionServiceImpl implements RegionService {
	@Autowired
	private RegionDao regionDao;
	@Override
	public void saveBatch(List<Region> regions) {
		for(Region region:regions) {
			regionDao.saveORupdate(region);
		}
	}
	
	@Override
	public void pageQuery(PageBean pageBean) {
		regionDao.pageQuery(pageBean);
	}

	@Override
	public List<Region> findAll() {
		return regionDao.findAll();
	}

	@Override
	public List<Region> findBy_Keyword(String q) {
		return regionDao.findBy_Keywords(q);
	}

	
}
