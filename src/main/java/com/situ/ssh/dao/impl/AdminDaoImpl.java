package com.situ.ssh.dao.impl;

import java.util.List;

import org.hibernate.dialect.pagination.SQL2008StandardLimitHandler;
import org.springframework.stereotype.Repository;

import com.situ.ssh.dao.IAdminDao;
import com.situ.ssh.dao.base.impl.BaseDaoImpl;
import com.situ.ssh.entity.Admin;

@Repository
public class AdminDaoImpl extends BaseDaoImpl<Admin> implements IAdminDao{

	@Override
	public Admin login(Admin admin) {
		String hql = "FROM Admin a WHERE a.name=? AND a.password=?";
		List<Admin> list = (List<Admin>) getHibernateTemplate().find(hql, admin.getName(), admin.getPassword());
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
