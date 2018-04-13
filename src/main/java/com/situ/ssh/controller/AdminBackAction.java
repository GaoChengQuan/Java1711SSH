package com.situ.ssh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.situ.ssh.entity.Admin;
import com.situ.ssh.service.IAdminService;

@Controller
@Scope("prototype")
public class AdminBackAction extends ActionSupport {
	@Autowired
	private IAdminService adminService;

	private Admin admin;

	public String login() {
		Admin adminLogin = adminService.login(admin);
		System.out.println("-------" + adminLogin);
		return "loginSuccess";
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

}
