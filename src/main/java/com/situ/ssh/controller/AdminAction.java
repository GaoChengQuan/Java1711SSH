package com.situ.ssh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.situ.ssh.entity.Admin;
import com.situ.ssh.service.IAdminService;

@Controller
@Scope("prototype")
public class AdminAction extends ActionSupport implements ModelDriven<Admin>{
	@Autowired
	private IAdminService adminService;

	private Admin admin = new Admin();

	public String login() {
		Admin adminLogin = adminService.login(admin);
		System.out.println("-------" + adminLogin);
		return "loginSuccess";
	}

	@Override
	public Admin getModel() {
		return admin;
	}
}
