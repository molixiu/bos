package com.xjwfk.dao;

import java.util.List;

import com.xjwfk.dao.base.BaseDao;
import com.xjwfk.domain.Region;

public interface RegionDao extends BaseDao<Region> {

	List<Region> findBy_Keywords(String q);	

}
