package com.fhi.framework.dao;

import java.sql.Types;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.fhi.framework.page.HQuery;
import com.fhi.framework.page.Paras;

public class DbDaoImple extends HibernateDaoSupport implements DbDao {
	/**
	 * 日志记录
	 */
	private static final Logger logger = Logger.getLogger(DbDaoImple.class);

	/**
	 * 数据源
	 */
	private DataSource dataSource;

	public List queryObjects(String hql) throws Exception {
		try {
			return getHibernateTemplate().find(hql);
		} catch (DataAccessException e) {
			logger.error("查询数据库表中记录错误.", e);
			throw new DbDaoException("查询数据库表中记录错误.");
		}
	}
	
	/**
	 * HQL查询Top count 条数据
	 * @param hql
	 * @param count
	 * @return
	 * @throws Exception
	 */
	public List queryObjects(String hql,Integer count) throws Exception {
		try {
			Session session = getHibernateTemplate().getSessionFactory().openSession();			
			Query query = session.createQuery(hql);
			query.setMaxResults(count.intValue());			
			return query.list();
		} catch (DataAccessException e) {
			logger.error("查询数据库表中记录错误.", e);
			throw new DbDaoException("查询数据库表中记录错误.");
		}
	}

	public void refresh(final Object entity) throws Exception {
		// TODO Auto-generated method stub
		try {
			getHibernateTemplate().execute(new HibernateCallback() {
				public Object doInHibernate(Session session)
						throws HibernateException {
					return null;
				}

			});
		} catch (DataAccessException e) {
			logger.error("向数据库表中刷新对象错误.", e);
			throw new DbDaoException("向数据库表中刷新对象错误.");
		}
	}

	public boolean updateObject(Object obj) throws Exception {
		boolean bln = false;
		try {
			getHibernateTemplate().saveOrUpdate(obj);
			bln = true;
		} catch (DataAccessException e) {
			logger.error("更新数据库表中记录错误.", e);
			throw new DbDaoException("更新数据库表中记录错误.");
		}
		return bln;
	}

	public boolean deleteObject(Object obj) throws Exception {
		boolean bln = false;
		try {
			getHibernateTemplate().delete(obj);
			bln = true;
		} catch (DataAccessException e) {
			logger.error("删除数据库表中记录错误.", e);
			throw new DbDaoException("删除数据库表中记录错误.");
		}
		return bln;

	}

	/**
	 * 批量添加数据 事务处理
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public boolean addObjects(List list) throws Exception {
		if (list == null || list.size() == 0)
			return false;
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			for (int i = 0; i < list.size(); i++) {
				this.getHibernateTemplate().save(list.get(i));
			}
			session.flush();
			tx.commit();
			return true;
		} catch (Exception e) {
			logger.error("批量添加数据库表中记录错误.", e);
			tx.rollback();
			return false;
		}finally{
			session.close();
		}
	}

	/**
	 * 批量更新数据 事务处理
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public boolean updateObjects(List list) throws Exception {
		if (list == null || list.size() == 0)
			return false;
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			for (int i = 0; i < list.size(); i++) {
				this.getHibernateTemplate().update(list.get(i));
			}
			session.flush();
			tx.commit();
			return true;
		} catch (Exception e) {
			logger.error("批量更新数据库表中记录错误.", e);
			tx.rollback();
			return false;
		}finally{
			session.close();
		}
	}
	/**
	 * 批量更新数据 事务处理
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public boolean saveOrUpdateObjects(List list) throws Exception {
		if (list == null || list.size() == 0)
			return false;
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			for (int i = 0; i < list.size(); i++) {
				this.getHibernateTemplate().saveOrUpdate(list.get(i));
			}
			session.flush();
			tx.commit();
			return true;
		} catch (Exception e) {
			logger.error("批量更新数据库表中记录错误.", e);
			return false;
		}finally{
			if(session!=null)
				session.close();
		}
	}


	/**
	 * 数据批量处理 事务处理
	 * @param addList
	 * @param updateList
	 * @param delList
	 * @return
	 * @throws Exception
	 */
	public boolean updateDbObjs(List addList, List updateList, List delList)throws Exception {
		Session session = getHibernateTemplate().getSessionFactory()
				.openSession();
		Transaction tx = session.beginTransaction();
		try { 
			if(addList!=null&&addList.size()>0){
				for (Object object : addList) {
					session.save(object);
				}				
			}
			if(updateList!=null&&updateList.size()>0){
				for (Object object : updateList) {
					session.update(session.merge(object));
				}				
			}
			if(delList!=null&&delList.size()>0){
				for (Object object : delList) {
					session.delete(object);
				}				
			}			
			session.flush();
			tx.commit();
			return true;
		} 
		catch (Exception e) {
			logger.error("批量处理数据库表中记录错误.", e);
			tx.rollback();
			return false;
		}finally{
			if(session!=null)
				session.close();
		}
	}

	/**
	 * 批量删除数据库表中记录
	 * 
	 * @param list
	 *            List
	 * @param obj
	 * @throws java.lang.Exception
	 * @roseuid 431CF2600312
	 */
	public boolean deleteObjects(Class obj, String[] ids) throws Exception {
		boolean bln = false;
		if (ids == null)
			return bln;
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction(); 
		try {			
			for (int i = 0; i < ids.length; i++) {
				String class_id = ids[i];
				if (class_id == null) continue;
				this.getHibernateTemplate().delete(this.getHibernateTemplate().load(obj, class_id));
			}
			session.flush();
			tx.commit();
			bln = true;
		} catch (Exception e) {
			logger.error("批量删除数据库表中记录失败.", e);
			tx.rollback();
			throw new Exception("批量删除数据库表中记录错误.");
		}finally{
			session.close();
		}
		return bln;
	}

	public boolean deleteObjects(Class obj, Integer[] ids) throws Exception {
		if (ids == null)
			return false;
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		
		Transaction tx = session.beginTransaction();
		try {
			for (int i = 0; i < ids.length; i++) {
				Integer class_id = ids[i];
				if (class_id == null)
					continue;
				this.getHibernateTemplate().delete(
						this.getHibernateTemplate().load(obj, class_id));
			}
			session.flush();
			tx.commit();
			session.close();
			return true;
		} catch (Exception e) {
			logger.error("批量删除数据库表中记录错误.", e);
			tx.rollback();
			return false;
		}finally{
			session.close();
		}
	}

	/**
	 * @return
	 * @roseuid 431CF2610147
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * @param source
	 * @roseuid 431CF2610151
	 */
	public void setDataSource(DataSource source) {
		dataSource = source;
	}

	/**
	 * 通过ID检索数据库表中记录
	 * 
	 * @param id
	 *            String
	 * @param obj
	 * @param obj_id
	 * @return Object@throws java.lang.Exception
	 * @roseuid 431CF260038A
	 */
	public Object queryObjectById(Class obj, String obj_id) throws Exception {
		try {
			return (Object) this.getHibernateTemplate().load(obj, obj_id);
		} catch (Exception e) {
			//logger.error("通过ID检索数据库表中记录错误", e);
			throw new Exception("通过ID检索数据库表中记录错误.");
		}
	}

	/**
	 * 分页查询数据库表中记录
	 * 
	 * @param hquery
	 * @return List@throws net.sf.hibernate.HibernateException
	 * @roseuid 431CF26100B2
	 */
	public List queryObjectsToPages(final HQuery hquery) {
		HibernateTemplate hibernateTemplate = new HibernateTemplate(
				getSessionFactory());
		return (List) hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				List itr = null;
				StringBuilder query_str=null;
				try {
					 query_str = new StringBuilder(hquery
							.getQueryString());
					// 是否要排序
					if (hquery.getOrderby() != null) {
						query_str.append(hquery.getOrderby());
					}
					// 是否要分组
					if (hquery.getGroupby() != null) {
						query_str.append(hquery.getGroupby());
					}
					Query query = session.createQuery(query_str.toString());

					setQueryParams(hquery, query);

					// 是否存在分页，当hquery.getPageStartNo()==0是不分页

					if (hquery.getPageStartNo() != 0) {
						long pageno = hquery.getPageStartNo();
						query.setFirstResult(Integer.parseInt(Long
								.toString(pageno - 1))
								* hquery.getRowsPerpage());
						query.setMaxResults(hquery.getRowsPerpage());
					}
					itr = query.list();
				} catch (Exception e) {
					logger.error("分页查询记录失败 HQL："+query_str.toString(), e);
				}finally{
					hquery.clear();
				}
				return itr;
			}
		});
	}

	private void setQueryParams(HQuery hQuery, Query query) throws Exception {
		try {
			if (hQuery.getParaslist() != null) {
				List list = hQuery.getParaslist();
				for (int i = 0; i < list.size(); i++) {
					Paras param = (Paras) list.get(i);
					switch (param.getTypeNo()) {// 此处要根据参数类型的增加要增加相应的“case”
					case Types.VARCHAR:
						query.setString(i, param.getPName().toString());
						break;
					case Types.INTEGER:
						query.setInteger(i, ((Integer) param.getPName())
								.intValue());
						break;
					case Types.DATE:
						query
								.setTimestamp(i, (java.util.Date) param
										.getPName());
						break;
					case Types.DOUBLE:
						query.setDouble(i, ((Double) param.getPName())
								.doubleValue());
						break;
					case Types.BOOLEAN:
						query.setBoolean(i, ((Boolean) param.getPName())
								.booleanValue());
						break;
					case Types.CHAR:
						query.setCharacter(i, ((Character) param.getPName())
								.charValue());
						break;
					case Types.ARRAY:
						query.setParameterList("inParam0", (List) param
								.getPName());
						break;
					}
				}
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * 取记录总数 liuyc
	 * 
	 * @return int
	 */
	public int count(final HQuery _query) throws HibernateException {

		Session session = getSession();
		try {
			StringBuffer query_str = new StringBuffer(_query.getQueryString());
			Query query;
			Integer coun;
			if (query_str.toString().toLowerCase().indexOf("select ") < 0) {				
				query = session.createQuery("select count(*) "
						+ query_str.toString());
				
			} else {
				int i = query_str.toString().toLowerCase().indexOf("from ");
				
				query = session.createQuery("select count(*) " + query_str.toString().substring(i));
			}
			setQueryParams(_query, query);		
			Iterator it = query.iterate();
			if (!it.hasNext())
				return 0;
			coun = (Integer) it.next();
			//logger.debug("查询记录条数：count=" + coun);
			if (coun == null)
				return 0;
			return coun.intValue();
		} catch (Exception e) {
			logger.error("查询记录数出错", e);
			return 0;
		}
	}

	/**
	 * 取记录总数
	 * 
	 * @return int
	 */
	public int count(String hsqlString) throws HibernateException {
		Session session = getSession();
		try {
			Query query = session.createQuery("select count(*) " + hsqlString);
			Iterator it = query.iterate();
			if (!it.hasNext())
				return 0;
			int b = ((Integer) (it.next())).intValue();
			return b;
		} catch (Exception e) {
			logger.error("查询记录数出错", e);
			return -1;
		}
	}

	/**
	 * 向数据库表中添加记录
	 * 
	 * @param obj
	 *            Object
	 * @throws java.lang.Exception
	 * @roseuid 431CF26001FA
	 */
	public boolean addObject(Object obj) throws Exception {
		boolean bln = false;
		try {
			getHibernateTemplate().save(obj);
			getHibernateTemplate().flush();
			bln = true;
		} catch (Exception e) {
			logger.error("向数据库表中添加记录错误", e);
			throw new Exception("向数据库表中添加记录错误.");
		}
		return bln;
	}

	public Object queryObjectById(Class obj, Integer obj_id) throws Exception {
		// TODO Auto-generated method stub
		try {
			return (Object) this.getHibernateTemplate().load(obj, obj_id);
		} catch (Exception e) {
			logger.error("通过ID检索数据库表中记录错误", e);
			throw new Exception("通过ID检索数据库表中记录错误.");
		}
	}

	public void excuteHQL(final String sql) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Query query = session.createQuery(sql);
				query.executeUpdate();
				return null;
			}
		});
	}

	@Override
	public boolean saveOrUpdateObject(Object obj) throws Exception {
		boolean bln = false;
		try {
			getHibernateTemplate().saveOrUpdate(obj);
			bln = true;
		} catch (DataAccessException e) {
			logger.error("更新数据库表中记录错误.", e);
			throw new DbDaoException("更新数据库表中记录错误.");
		}
		return bln;
	}

	 
}
