package com.situ.ssh.dao;

import com.situ.ssh.dao.base.IBaseDao;
import com.situ.ssh.entity.Admin;

public interface IAdminDao extends IBaseDao<Admin>{
	public Admin login(Admin admin);
}
