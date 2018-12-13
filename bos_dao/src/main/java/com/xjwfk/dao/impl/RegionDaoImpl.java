package com.xjwfk.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xjwfk.dao.RegionDao;
import com.xjwfk.dao.base.impl.BaseDaoImpl;
import com.xjwfk.domain.Region;
@Repository
public class RegionDaoImpl extends BaseDaoImpl<Region> implements RegionDao {

	@Override
	public List<Region> findBy_Keywords(String q) {
		String hql = "From Region where province like ? Or city like ? Or district like ? Or shortcode like ? Or city like ?";
		String[] params = {"%"+q+"%", "%"+q+"%", "%"+q+"%", "%"+q+"%", "%"+q+"%"};
		List<Region> find = (List<Region>) this.getHibernateTemplate().find(hql,params);
		return find;
	}

}
