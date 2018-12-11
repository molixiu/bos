package com.xjwfk.domain;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public class PageBean {
	private int page;
	private int total;
	private int pageSize;
	private List rows;
	private DetachedCriteria detachedCriteria;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	public DetachedCriteria getDetachedCriteria() {
		return detachedCriteria;
	}
	public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
		this.detachedCriteria = detachedCriteria;
	}
	
	
	@Override
	public String toString() {
		return "PageBean [page=" + page + ", total=" + total + ", pageSize=" + pageSize + ", rows=" + rows
				+ ", detachedCriteria=" + detachedCriteria + "]";
	}
	
	
	
}
