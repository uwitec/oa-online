package com.fhi.utils.portmanage.service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fhi.framework.abstractservice.AbstractServiceImple;
import com.fhi.framework.config.FhiConfig;
import com.fhi.framework.page.HQuery;
import com.fhi.framework.page.ListTableCourier;
import com.fhi.framework.page.Paras;
import com.fhi.framework.page.ParasList;
import com.fhi.user.vo.FhiUser;
import com.fhi.utils.portmanage.vo.FhiOsUtilsPort;

public class PortManageImple extends AbstractServiceImple implements PortManageIn {

	private static Logger logger = Logger.getLogger(PortManageImple.class);
	

	/**
	 * DWR分页查询测试
	 * @param courier
	 * @return
	 */
	public ListTableCourier getTableList(ListTableCourier courier,HttpServletRequest request){
		
		
		StringBuilder hql= new StringBuilder("from FhiOsUtilsPort where 1=1");		
	    
	    HQuery hquery = new HQuery();	    
	    ParasList pList = new ParasList();
	    Map<String,String> map = courier.getSearchData();
	    
	    if(map.get("portCode")!=null && !"".equals(map.get("portCode"))){
			hql.append(" and portCode like ? ");
			Paras p = new Paras("%"+map.get("portCode")+"%",Types.VARCHAR);
			pList.add(p);
		}
	    if(map.get("portName")!=null && !"".equals(map.get("portName"))){
			hql.append(" and portName like ? ");
			Paras p = new Paras("%"+map.get("portName")+"%",Types.VARCHAR);
			pList.add(p);
		}

		hquery.setQueryString(hql.toString());
		hquery.setParaslist(pList);
	    
	    
	    //检查是否有传递信息
	    HttpSession session = request.getSession();	    
	    String message = (String) session.getAttribute("PortManage_List_Message");
	    if(message!=null&&!"".equals(message)){
	    	courier.setMessage(message);
	    	session.setAttribute("PortManage_List_Message",null);
	    }
		return this.query(hquery, courier);
	}
	
	/**
	 * 批量删除
	 * @param eids
	 * @return
	 */
	public int delAll(String[] ids,HttpServletRequest request){
		if(ids==null){
			return 0;
		}
		FhiUser user = (FhiUser) request.getSession().getAttribute(FhiConfig.USER_NAME);
		if(user==null){			
			return -1;
		}
		List delList = new ArrayList();	
		try {
			for (String string : ids) {				
				FhiOsUtilsPort obj = (FhiOsUtilsPort) this.dbDao.queryObjectById(FhiOsUtilsPort.class, string);
				logger.info("删除港口编码。用户："+user.getUserId()+",ID:"+obj.getId());
				delList.add(obj);
			}
			if(dbDao.updateDbObjs(null, null, delList)){
				logger.info("全部删除成功!");
				return 1;
			}
			
		} catch (Exception e) {
			logger.error("删除失败。", e);
			return -1;
		}
		return 0;
	}
	
	/**
	 * 虚拟下拉菜单查询
	 * @param selectStr
	 * @return
	 */
	public List<FhiOsUtilsPort> querySelectMenu(String selectStr){
		List<FhiOsUtilsPort>  list = null;		
		try {
			HQuery hquery =new HQuery();
			hquery.setQueryString("from FhiOsUtilsPort where portCode like ? or portName like ?");
			hquery.setRowsPerpage(10);			
			hquery.setPageStartNo(1);
			
			ParasList pList = new ParasList();
			
			pList.add(new Paras(selectStr+"%",Types.VARCHAR));
			pList.add(new Paras(selectStr+"%",Types.VARCHAR));
			
			hquery.setParaslist(pList);
			list = this.dbDao.queryObjectsToPages(hquery);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 查询单个
	 * @param selectStr
	 * @return
	 */
	public FhiOsUtilsPort querySelectLoad(String selectStr){
		List<FhiOsUtilsPort>  list = null;		
		try {
			HQuery hquery =new HQuery();
			hquery.setQueryString("from FhiOsUtilsPort where portCode = ? or portName = ?");
			hquery.setRowsPerpage(10);			
			hquery.setPageStartNo(1);
			
			ParasList pList = new ParasList();
			
			pList.add(new Paras(selectStr.trim()));
			pList.add(new Paras(selectStr.trim()));
			
			hquery.setParaslist(pList);
			list = this.dbDao.queryObjectsToPages(hquery);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(list.size()==1){
			return list.get(0);
		}
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.fhi.article.service.ArticleIn#load(java.lang.String)
	 */
	public FhiOsUtilsPort load(String id){
		
		return (FhiOsUtilsPort) this.load(FhiOsUtilsPort.class, id);
	}
}
