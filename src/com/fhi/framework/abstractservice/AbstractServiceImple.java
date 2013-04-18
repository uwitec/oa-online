package com.fhi.framework.abstractservice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.fhi.framework.config.FhiConfig;
import com.fhi.framework.dao.DbDao;
import com.fhi.framework.dao.JdbcDbDao;
import com.fhi.framework.page.Condition;
import com.fhi.framework.page.HQuery;
import com.fhi.framework.page.ListTableCourier;
import com.fhi.framework.page.PageInfo;
import com.fhi.system.service.SystemIn;
import com.fhi.user.vo.FhiUser;

public abstract class AbstractServiceImple {
	protected DbDao dbDao;
	protected SystemIn systemService;
	protected JdbcDbDao jdbcDbDao ;
	
	private static Logger logger = Logger.getLogger(AbstractServiceImple.class);
	protected HQuery setPageInfo(Condition condition) {
		try {
			PageInfo pageInfo = condition.getPageInfo();
			HQuery hquery = condition.getHquery();
			// hquery.setParaslist(pl);
			// 查询记录总数
			int pagecount = 0;
			pagecount = dbDao.count(hquery);
			pageInfo.setRowCounts(pagecount);

			pageInfo.setCrossPages(Integer.parseInt(Long.toString(Math
					.round(Math.ceil(pagecount * 1.0
							/ pageInfo.getRowsPerpage())))));

			if (pageInfo.getPageNo() > pageInfo.getCrossPages()) {
				pageInfo.setPageNo(pageInfo.getCrossPages());
			}
			// 设置当前行
			int currentRow = (pageInfo.getPageNo() - 1)
					* pageInfo.getRowsPerpage();
			if (currentRow < 0) {
				currentRow = 0;
				pageInfo.setPageNo(1);
			}
			pageInfo.setCurrentRow(currentRow + 1);

			// 调用查寻方法得到第一页结果
			hquery.setPageStartNo(pageInfo.getPageNo());
			hquery.setRowsPerpage(pageInfo.getRowsPerpage());

			hquery.setOrderby(hquery.getOrderby() == null ? "" : hquery
					.getOrderby());
			hquery.setGroupby(hquery.getGroupby() == null ? "" : hquery
					.getGroupby());
			return hquery;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	/**
	 * 分页查询 从系统配置中读取每页条数
	 * @param condition
	 * @return
	 */
	public List query(Condition condition) {
		try {
			//从系统配置中读取每页条数			
			condition.getPageInfo().setRowsPerpage(systemService.getSysConfig().getRowsPerpage());
			
			return this.dbDao.queryObjectsToPages(this.setPageInfo(condition));
		} catch (Exception e) {
			HQuery hq = condition.getHquery();
			logger.error("Error HQL:"+hq.getQueryString()+hq.getOrderby()+hq.getGroupby());
			logger.error("分页查询出错", e);
		}
		return null;
	}
	
	/**
	 * 分页查询 手动设置每页条数
	 * @param condition
	 * @return
	 */
	public List query(Condition condition,int rowsPerpage) {
		try {
			//从系统配置中读取每页条数			
			condition.getPageInfo().setRowsPerpage(rowsPerpage);
			
			return this.dbDao.queryObjectsToPages(this.setPageInfo(condition));
		} catch (Exception e) {
			logger.error("分页查询出错", e);
		}
		return null;
	}
	
	/**
	 * 分页查询 用于DWR前台调用
	 * @param condition
	 * @return
	 */
	public ListTableCourier query(String hql,ListTableCourier courier) {
		try {
			HQuery hquery = new HQuery();
			hquery.setQueryString(hql);
			return this.query(hquery, courier);
		} catch (Exception e) {
			logger.error("DWR分页查询出错", e);
		}
		return null;
	}
	
	/**
	 * 分页查询 用于DWR前台调用
	 * @param condition
	 * @return
	 */
	public ListTableCourier query(HQuery hquery,ListTableCourier courier) {
		try {
			
			
			
			hquery.setOrderby(hquery.getOrderby() == null ? "" : hquery
					.getOrderby());
			hquery.setGroupby(hquery.getGroupby() == null ? "" : hquery
					.getGroupby());
			
			PageInfo pageInfo = courier.getPageInfo();
			if(pageInfo!=null){
				// hquery.setParaslist(pl);
				// 查询记录总数
				int pagecount = 0;
				pagecount = dbDao.count(hquery);
				pageInfo.setRowCounts(pagecount);
	
				pageInfo.setCrossPages(Integer.parseInt(Long.toString(Math
						.round(Math.ceil(pagecount * 1.0
								/ pageInfo.getRowsPerpage())))));
	
				if (pageInfo.getPageNo() > pageInfo.getCrossPages()) {
					pageInfo.setPageNo(pageInfo.getCrossPages());
				}
				// 设置当前行
				int currentRow = (pageInfo.getPageNo() - 1)
						* pageInfo.getRowsPerpage();
				if (currentRow < 0) {
					currentRow = 0;
					pageInfo.setPageNo(1);
				}
				pageInfo.setCurrentRow(currentRow + 1);
	
				// 调用查寻方法得到第一页结果
				hquery.setPageStartNo(pageInfo.getPageNo());
				hquery.setRowsPerpage(pageInfo.getRowsPerpage());			
			}
			courier.setDataBaseList(this.dbDao.queryObjectsToPages(hquery));
			return courier;
		} catch (Exception e) {
			logger.error("DWR分页查询出错", e);
		}
		return null;
	}

	public List query(String hqlString) {
		try {
			return this.dbDao.queryObjects(hqlString);
		} catch (Exception e) {
			logger.error("HQL查询失败！", e);
		}
		return null;
	}

	public Object load(Class obj, String obj_id) {
		try {
			return dbDao.queryObjectById(obj, obj_id);
		} catch (Exception e) {
			logger.error("单条数据查询失败！", e);
		}
		return null;
	}
	
	public boolean update(Object obj) {
		try {
			return dbDao.updateObject(obj);
		} catch (Exception e) {
			logger.error("单条数据更新失败！", e);
		}
		return false;
	}
	
	public boolean save(Object obj) {
		try {
			return dbDao.addObject(obj);
		} catch (Exception e) {
			logger.error("单条数据添加失败！", e);
		}
		return false;
	}
	
	/**
	 * 获取客户IP地址
	 * @param request
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request) {
	       String ip = request.getHeader("x-forwarded-for");
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	           ip = request.getHeader("Proxy-Client-IP");
	       }
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	           ip = request.getHeader("WL-Proxy-Client-IP");
	       }
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	           ip = request.getRemoteAddr();
	       }
	       return ip;
	   }
	
	/**
	 * 检查持久类集合中是否有与当前对象某些属性重复现象
	 * 
	 * @param hqlString
	 * @return true:有;false:无
	 * @throws Exception
	 */
	protected boolean checkRepeatObject(String hqlString) throws Exception {
		try {
			int repeat = dbDao.count(hqlString);
			if (repeat > 0)
				return true;
			return false;
		} catch (Exception e) {
			logger.error("检查重复对象异常!", e);
			throw new Exception("检查重复对象异常:" + e.getMessage());
		}
	}
	
	public FhiUser isLogin(HttpServletRequest request){
		return (FhiUser) request.getSession().getAttribute(FhiConfig.USER_NAME);
	}

	public DbDao getDbDao() {
		return dbDao;
	}

	public void setDbDao(DbDao dbDao) {
		this.dbDao = dbDao;
	}
	public void setSystemService(SystemIn systemService) {
		this.systemService = systemService;
	}

	public void setJdbcDbDao(JdbcDbDao jdbcDbDao) {
		this.jdbcDbDao = jdbcDbDao;
	}
}
