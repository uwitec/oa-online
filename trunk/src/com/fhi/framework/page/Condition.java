
package com.fhi.framework.page;

import java.io.Serializable;


/**
 * 基本查询条件
 *  
 * @author liangwj
 */
public class Condition  implements Serializable,Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7448688547460880575L;
	
	/**
	 * 前台页面调用信息
	 */
	protected PageInfo pageInfo;
	
	/**
	 * 数据分页查询信息
	 */
	protected HQuery hquery;
	
	/**
	 * 参数列表；
	 */
	protected ParasList parasList;

	public Object clone(){
		Condition o = null;
		try{
			o = (Condition)super.clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
		}
		return o;
	}

	/**
	 * @param hqlString
	 *            The hqlString to set.
	 */
	public void setHqlString(String hqlString) {
		if(hquery==null){
			hquery=new HQuery();
		}
		this.hquery.setQueryString(hqlString);
	}
	/**
	 **设置页数
	 */
	public void setPageNo(int pageNo)
	{
		if(pageInfo==null){
			pageInfo = new PageInfo();
		}
		this.pageInfo.setPageNo(pageNo);
	}
	/**
	 *组织查询条件;
	 * @return
	 */

	public void preparedParams(){
		
	}
	/**
	 * 设置分页查询参数,注意:应该按照参数顺序进行设置
	 * @param pName
	 * @param pType
	 */
	protected void setPageParams(Object pName,int pType){
		Paras ps = new Paras();
		ps.setPName(pName);
		ps.setTypeNo(pType);		
		parasList.add(ps);

	}

	public void setGroupBy(String groupBy) {
		if(hquery==null){
			hquery=new HQuery();
		}
		this.hquery.setGroupby(groupBy);
	}

	public HQuery getHquery() {
		if(hquery==null){
			hquery=new HQuery();
		}
		this.preparedParams();
		hquery.setParaslist(parasList);
		return hquery;
	}

	public void setHquery(HQuery hquery) {
		this.hquery = hquery;
	}

	public void setOrderBy(String orderBy) {
		if(hquery==null){
			hquery=new HQuery();
		}
		this.hquery.setOrderby(orderBy);
	}

	public PageInfo getPageInfo() {
		if(pageInfo==null){
			pageInfo = new PageInfo();
		}
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

}
