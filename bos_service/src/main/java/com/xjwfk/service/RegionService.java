package com.xjwfk.service;

import java.util.List;

import com.xjwfk.domain.PageBean;
import com.xjwfk.domain.Region;

public interface RegionService {

	public void saveBatch(List<Region> regions);

	public void pageQuery(PageBean pageBean);

	public List<Region> findAll();

	public List<Region> findBy_Keyword(String q);	//根据关键字查询
	
}
