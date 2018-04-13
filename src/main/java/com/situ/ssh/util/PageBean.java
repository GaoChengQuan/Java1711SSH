package com.situ.ssh.util;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public class PageBean {
	private Integer currentPage;// 当前页码
	private Integer pageSize;// 每页记录数
	private DetachedCriteria detachedCriteria;// 离线查询
	private Integer total;// 总记录数
	private List<?> rows;// 当前页数据

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public DetachedCriteria getDetachedCriteria() {
		return detachedCriteria;
	}

	public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
		this.detachedCriteria = detachedCriteria;
	}

}
