package com.situ.ssh.controller;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.situ.ssh.common.ServerResponse;
import com.situ.ssh.entity.Student;
import com.situ.ssh.service.IStudentService;


@Controller
@Scope("prototype")
public class StudentAction extends BaseAction<Student>{
	@Autowired
	private IStudentService studentService;
	
	public StudentAction() {
		System.out.println("StudentAction.StudentAction()");
	}
	
	public String pageList() {
		//在DetachedCriteria中封装查询条件
		String name = model.getName();
		if (StringUtils.isNotEmpty(name)) {
			//添加过滤条件，根据名字关键字模糊查找
			detachedCriteria.add(Restrictions.like("name", "%" + name + "%"));
		}
		Integer age = model.getAge();
		if (age != null) {
			//添加过滤条件，根据年龄查找
			detachedCriteria.add(Restrictions.eq("age", age));
		}
		studentService.pageList(pageBean);
		//把pageBean转成json格式返回
		obj2JsonForEasyUI();
		/**
	     * The action execution was successful but do not
	     * show a view. This is useful for actions that are
	     * handling the view in another fashion like redirect.
	     */
		return NONE;//"none"
	}
	
	public String deleteBatch() {
		System.out.println(ids);
		ServerResponse serverResponse = null;
		if (studentService.deleteBatch(ids)) {
			 serverResponse = ServerResponse.createSuccess("删除成功");
		} else {
			serverResponse = ServerResponse.createError("删除失败");
		}
		obj2Json(serverResponse);
		return NONE;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
}
