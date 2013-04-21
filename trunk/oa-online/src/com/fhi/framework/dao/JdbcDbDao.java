package com.fhi.framework.dao;

import java.util.List;
import java.util.Map;

public interface JdbcDbDao {

	public List queryJDBCforList(String sql) throws Exception;

	public Map loadJDBCforMap(String sql) throws Exception;
	
	public void execute(String sql) throws Exception;
	
	public int update(String sql) throws Exception;
	
	
	/**
	 * 查询记录数 已有SQL：select count(*)
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public int count(String sql) throws Exception;
	
	/**
	 * 使用sql  in 关键字批量删除数据  根据主键删 id
	 * 
	 * 
	 * @param ids (必须用逗号分开  111,222,333)  表名
	 * @return  true  删除成功   false 删除失败 
	 * @throws Exception
	 */
	public boolean deleteObjectByIds(String tableName,String ids) throws Exception;
	
	
 
	
	 
}
