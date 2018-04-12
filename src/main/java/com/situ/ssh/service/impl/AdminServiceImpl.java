package com.situ.ssh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.ssh.dao.IAdminDao;
import com.situ.ssh.entity.Admin;
import com.situ.ssh.service.IAdminService;

@Service
public class AdminServiceImpl implements IAdminService{
	@Autowired
	private IAdminDao adminDao;

	@Override
	public Admin login(Admin admin) {
		return adminDao.login(admin);
	}

}
