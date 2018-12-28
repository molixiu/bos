package com.xjwfk.service.impl;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.lang.ObjectUtils.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xjwfk.dao.DecidedzoneDao;
import com.xjwfk.dao.NoticebillDao;
import com.xjwfk.dao.WorkbillDao;
import com.xjwfk.domain.Decidedzone;
import com.xjwfk.domain.Noticebill;
import com.xjwfk.domain.Staff;
import com.xjwfk.domain.Workbill;
import com.xjwfk.service.CustomerService;
import com.xjwfk.service.NoticebillService;

@Service
@Transactional
public class NoticebillServiceImpl implements NoticebillService {
	@Autowired
	private NoticebillDao noticebillDao;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private DecidedzoneDao decidedzoneDao;
	@Autowired
	private WorkbillDao workbillDao;

	@Override
	public void save(Noticebill noticebill) {
		noticebillDao.save(noticebill); // 保存业务通知单
		/*
		 * 对下面代码的解释 根据客户的取件地址查询这个地址的属于哪个定区，再查这个定区是哪个取派员负责的，然后自动生成工单给这个取派员 否者就人工分单，不会生成工单
		 */
		String decidedId = customerService.findDecidedIdzoneByAddr(noticebill.getPickaddress());
		Decidedzone decidedzone = decidedzoneDao.find_ById(decidedId);
		if (decidedzone != null) {
			Staff staff = decidedzone.getStaff();
			if (staff != null) { // 自动分单 //下面是一坨的工单数据封装…(๑˃∀˂๑)
				Workbill workbill = new Workbill();
				workbill.setNoticebill(noticebill);
				workbill.setStaff(staff);
				workbill.setType(Workbill.TYPE_1);
				workbill.setPickstate(Workbill.PICKSTATE_NO);
				workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));
				workbill.setAttachbilltimes(0);
				workbill.setRemark(noticebill.getRemark());
				workbillDao.save(workbill); // 保存生成的工单
				noticebill.setOrdertype(Noticebill.ORDERTYPE_AUTO);// 设置一下业务通知单的类型为"自动分单"
				noticebillDao.update(noticebill);									// 并更新
			} else {	// 人工分单
				noticebill.setOrdertype(Noticebill.ORDERTYPE_MAN);
				noticebillDao.update(noticebill);
			}
			
		} else { // 人工分单
			noticebill.setOrdertype(Noticebill.ORDERTYPE_MAN);
			noticebillDao.update(noticebill);
		}

	}

}
