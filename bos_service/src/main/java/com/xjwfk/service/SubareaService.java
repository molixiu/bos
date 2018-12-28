package com.xjwfk.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.xjwfk.domain.PageBean;
import com.xjwfk.domain.Subarea;

public interface SubareaService {

	public void save(Subarea model);

	public void pageQuery(PageBean pageBean);

	public List<Subarea> findAll();

	public List<Subarea> findList_notAssociate(DetachedCriteria detachedCriteria);

	public List<Subarea> findListByDecidedzoneId(DetachedCriteria detachedCriteria);

}
