package com.situ.ssh.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.omg.CORBA.LongHolder;
import org.springframework.stereotype.Repository;

import com.situ.ssh.dao.IStudentDao;
import com.situ.ssh.dao.base.impl.BaseDaoImpl;
import com.situ.ssh.entity.Student;
import com.situ.ssh.util.PageBean;

@Repository
public class StudentDaoImpl extends BaseDaoImpl<Student> implements IStudentDao {

	@Override
	public void pageList(PageBean pageBean) {
		Integer currentPage = pageBean.getCurrentPage();
		Integer pageSize = pageBean.getPageSize();
		DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
		//查询total：总记录数(是带着查询条件是记录数)
		detachedCriteria.setProjection(Projections.rowCount());
		List<?> list = getHibernateTemplate().findByCriteria(detachedCriteria);
		long total = (long) list.get(0);
		pageBean.setTotal((int)total);
		//rows:当前页数据
		detachedCriteria.setProjection(null);
		int firstResult = (currentPage - 1) * pageSize;
		List<?> rows = getHibernateTemplate().findByCriteria(detachedCriteria, firstResult, pageSize);
		// String sql = "FROM Student limit ?,?";
		// getHibernateTemplate().find(sql, firstResult, pageSize)
		pageBean.setRows(rows);
	}

}
