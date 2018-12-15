package com.xjwfk.service;

import java.util.List;

import com.xjwfk.domain.Decidedzone;
import com.xjwfk.domain.PageBean;

public interface DecidedzoneService {
	/*subareaid是分区的主键,定区对分区是一对多的关系,*/
	public void add(Decidedzone decidedzone,String[] subareaid);

	public void pageQuery(PageBean pageBean);
	
}
