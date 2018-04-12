package com.situ.ssh.dao.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.situ.ssh.dao.base.IBaseDao;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {
	// 代表当前操作实体类的类型 Student.class Admin.class
	private Class<T> entityClass;

	public BaseDaoImpl() {
		// this:当前运行的类(AdminDaoImpl/StudentDaoImpl)
		// this.getClass:当前运行类的字节码:AdminDaoImpl.class/StudentDaoImpl.class
		// this.getClass().getGenericSuperclass():当前运行类的父类即为：BaseDaoImpl<Admin>
		Type type = this.getClass().getGenericSuperclass();
		// 强制转化为参数化类型BaseDaoImpl<Admin.clsss>
		ParameterizedType superClass = (ParameterizedType) type;
		// BaseDaoImpl<Admin,Student>参数可以有多个
		Type[] actualTypeArguments = superClass.getActualTypeArguments();// [Admin.class]
		// 获取数组中第一个元素
		entityClass = (Class<T>) actualTypeArguments[0];
	}

	@Override
	public void save(T entity) {
		getHibernateTemplate().save(entity);
	}

	@Override
	public void delete(T entity) {
		getHibernateTemplate().delete(entity);
	}

	@Override
	public void update(T entity) {
		getHibernateTemplate().update(entity);
	}

	@Override
	public void findById(Serializable id) {
		// getHibernateTemplate().get(Admin.class, id);
		getHibernateTemplate().get(entityClass, id);
	}

	@Override
	public List<T> findAll() {
		// "FROM Admin"
		String sql = "FROM" + entityClass.getSimpleName();
		return (List<T>) getHibernateTemplate().find(sql);
	}
}
