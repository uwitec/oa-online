package com.fhi.framework.dao;

import java.util.List;

import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;


import com.fhi.framework.page.HQuery;

public interface DbDao {
	/**
	 * 查询数据库表中记录
	 * 
	 * @param hql
	 *            String
	 * @param hqlString
	 * @return List@throws java.lang.Exception
	 * @roseuid 431E4EAF037B
	 */

	public List queryObjects(String hqlString) throws Exception;
	
	/**
	 * HQL查询Top count 条数据
	 * @param hql
	 * @param count
	 * @return
	 * @throws Exception
	 */
	public List queryObjects(String hql,Integer count) throws Exception;

	/**
	 * 取记录总数
	 * 
	 * @return int
	 */
	public int count(final HQuery _query) throws HibernateException;

	/**
	 * 取记录总数
	 * 
	 * @return int
	 */
	public int count(String hqlString) throws HibernateException;

	/**
	 * 向数据库表中添加记录
	 * 
	 * @param obj
	 *            Object
	 * @throws java.lang.Exception
	 * @roseuid 431E4EAF0096
	 */
	public boolean addObject(Object obj) throws Exception;
																
	/**
	 * 批量添加数据 事务处理
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public boolean addObjects(List list) throws Exception;

	/**
	 * 批量删除数据库表中记录
	 * 
	 * @param obj
	 * @param ids
	 * @throws Exception
	 */
	public boolean deleteObjects(Class obj, String[] ids) throws Exception;

	public boolean deleteObjects(Class obj, Integer[] ids) throws Exception;

	/**
	 * 删除数据库表中记录
	 * 
	 * @param obj
	 *            Class
	 * @param obj_id
	 *            String
	 * @throws java.lang.Exception
	 * @roseuid 431E4EAF017C
	 */
	public boolean deleteObject(Object obj) throws Exception;

	/**
	 * 通过ID检索数据库表中记录
	 * 
	 * @param obj
	 * @param obj_id
	 * @return Object@throws java.lang.Exception
	 * @roseuid 431E4EAF0276
	 */
	public Object queryObjectById(Class obj, String obj_id) throws Exception;

	public Object queryObjectById(Class obj, Integer obj_id) throws Exception;

	/**
	 * 更新数据库表中记录
	 * 
	 * @param obj
	 *            Object
	 * @throws java.lang.Exception
	 * @roseuid 431E4EAF010E
	 */
	public boolean updateObject(Object obj) throws Exception;

	/**
	 * 批量更新数据 事务处理
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public boolean updateObjects(List list) throws Exception;

	/**
	 * 刷新对象
	 * 
	 * @param entity
	 */
	public void refresh(final Object entity) throws Exception;

	/**
	 * 分页查询
	 * 
	 * @return
	 * @throws Exception
	 */
	public List queryObjectsToPages(final HQuery hquery) throws Exception;

	public void excuteHQL(final String sql);

	/**
	 * 获得数据源
	 * 
	 * @return
	 * @roseuid 431CF2610147
	 */
	public DataSource getDataSource();

	/**
	 * 设置数据源
	 * 
	 * @param source
	 * @roseuid 431CF2610151
	 */
	public void setDataSource(DataSource source);

	/**
	 * 获取Session对象
	 * 
	 * @return
	 */
	public HibernateTemplate getHibernateTemplate();

	/**
	 * 数据批量处理 事务处理
	 * 
	 * @param addList
	 * @param updateList
	 * @param delList
	 * @return
	 * @throws Exception
	 */
	public boolean updateDbObjs(List addList, List updateList, List delList)
			throws Exception;

	/**
	 * 批量更新数据 事务处理
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public boolean saveOrUpdateObjects(List list) throws Exception;
	
	public boolean saveOrUpdateObject(Object obj) throws Exception;
}
