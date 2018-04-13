package com.situ.ssh.controller;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.situ.ssh.util.PageBean;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
	// private Student student;
	protected T model;

	protected PageBean pageBean = new PageBean();
	private Integer page;// 第几页
	private Integer rows;// 每页多少条数据
	protected DetachedCriteria detachedCriteria;//离线查询
	
	protected String ids;//批量删除

	public BaseAction() {
		// this:当前运行的类(AdminDaoImpl/StudentDaoImpl)
		// this.getClass:当前运行类的字节码:AdminDaoImpl.class/StudentDaoImpl.class
		// this.getClass().getGenericSuperclass():当前运行类的父类即为：BaseDaoImpl<Admin>
		Type type = this.getClass().getGenericSuperclass();
		// 强制转化为参数化类型BaseDaoImpl<Admin.clsss>
		ParameterizedType superClass = (ParameterizedType) type;
		// BaseDaoImpl<Admin,Student>参数可以有多个
		Type[] actualTypeArguments = superClass.getActualTypeArguments();// [Admin.class]
		// 获取数组中第一个元素
		Class<T> entityClass = (Class<T>) actualTypeArguments[0];
		detachedCriteria = DetachedCriteria.forClass(entityClass);
		pageBean.setDetachedCriteria(detachedCriteria);
		try {
			// student = new Student();
			model = entityClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 将任意对象转换为json返回给浏览器
	 * @param obj
	 * @param excludes
	 */
	protected void obj2Json(Object obj, String... excludes) {
		JsonConfig jsonConfig = new JsonConfig();
		//指定哪些属性不进行json转换
		jsonConfig.setExcludes(excludes);
		String json = JSONObject.fromObject(obj, jsonConfig).toString();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/json;charset=utf-8");
		try {
			response.getWriter().println(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void obj2JsonForEasyUI() {
		obj2Json(pageBean, new String[]{"currentPage", "pageSize", "detachedCriteria"});
		//obj2Json(pageBean);
		//obj2Json(pageBean, new String[]{"currentPage"});
		//obj2Json(pageBean, "currentPage");
		//obj2Json(pageBean, "currentPage", "pageSize");
	}

	@Override
	public T getModel() {
		return model;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		//struts2注入时候王PageBean里面设置值
		pageBean.setCurrentPage(page);
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		pageBean.setPageSize(rows);
		this.rows = rows;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
}
