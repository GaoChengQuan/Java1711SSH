package com.situ.ssh.service;

import com.situ.ssh.util.PageBean;

public interface IStudentService {

	void pageList(PageBean pageBean);

	boolean deleteBatch(String ids);

}
