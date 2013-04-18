package com.fhi.system.classification.service;

import java.util.List;

import com.fhi.framework.page.Condition;
import com.fhi.system.classification.vo.SysClassification;

public interface ClassIn {
	public List query(Condition condition);

	public List query(String hqlString);

	/**
	 * 返回本身以及所有父分类
	 * @param type
	 * @param id
	 * @return
	 */
	public List<SysClassification> queryParent(String type,String id);
	
	/**
	 * 查询子分类
	 * @param type
	 * @param id
	 * @return
	 */
	public List<SysClassification> queryChild(String type,String id);
	
	/**
	 * 获取所有子分类的ID
	 * @param id
	 * @return
	 */
	public List<String> queryAllChildId(String id);
}
