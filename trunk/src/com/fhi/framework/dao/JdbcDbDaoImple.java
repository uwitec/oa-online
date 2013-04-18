package com.fhi.framework.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class JdbcDbDaoImple extends HibernateDaoSupport implements JdbcDbDao {
	/**
	 * 日志记录
	 */
	private static final Logger logger = Logger.getLogger(JdbcDbDaoImple.class);

	/**
	 * 数据源
	 */
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	public int update(String sql) throws Exception {
		try {
			logger.debug("JDBC执行更新  SQL：" + sql);
			return jdbcTemplate.update(sql);
		} catch (DataAccessException e) {
			logger.error("JDBC更新数据出错  SQL：" + sql, e);
			throw new DbDaoException("JDBC更新数据出错  SQL：" + sql);
		}
	}
	
	public void execute(String sql) throws Exception {
		try {
			logger.debug("JDBC执行  SQL：" + sql);
			jdbcTemplate.execute(sql);
		} catch (DataAccessException e) {
			logger.error("JDBC更新数据出错  SQL：" + sql, e);
			throw new DbDaoException("JDBC更新数据出错  SQL：" + sql);
		}
	}

	public List queryJDBCforList(String sql) throws Exception {
		List list = null;
		try {
			logger.debug("JDBC查询List  SQL：" + sql);
			list = jdbcTemplate.queryForList(sql);
		} catch (DataAccessException e) {
			logger.error("JDBC查询数据库表中记录错误 SQL:"+sql, e);
			throw new DbDaoException("查询数据库表中记录错误.");
		}
		return list;
	}

	public Map loadJDBCforMap(String sql) throws Exception {
		// TODO Auto-generated method stub
		Map map = null;
		try {
			logger.debug("JDBC查询Map  SQL：" + sql);
			map = jdbcTemplate.queryForMap(sql);
		} catch (DataAccessException e) {
			logger.error("JDBC查询数据库表中单条记录错误 SQL:"+sql, e);
			throw new DbDaoException("JDBC查询数据库表中单条记录错误.");
		}
		return map;
	}

	/**
	 * 查询记录数 已有SQL：select count(*)
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public int count(String sql) throws Exception {
		// TODO Auto-generated method stub
		Map map = null;
		try {
			map = jdbcTemplate.queryForMap("select count(*) "+sql); 
			if(map==null)
				return 0;
			return Integer.parseInt(map.values().iterator().next().toString());
		} catch (DataAccessException e) {
			logger.error("JDBC查询记录数错误 SQL:select count(*) "+sql, e);
			throw new DbDaoException("JDBC查询记录数错误.");
		}		
	}
	@Override
	public boolean deleteObjectByIds(String tableName ,String ids) throws Exception {
		this.execute("delete from "+tableName+" where id in("+ids+")");
		return true;
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

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	 

}
