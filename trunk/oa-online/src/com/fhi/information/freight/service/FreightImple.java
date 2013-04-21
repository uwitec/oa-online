package com.fhi.information.freight.service;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.fhi.framework.abstractservice.AbstractServiceImple;
import com.fhi.information.freight.vo.FhiFreight;
import com.fhi.system.vo.SysConfig;

public class FreightImple extends AbstractServiceImple implements FreightIn {

	private static Logger logger = Logger.getLogger(FreightImple.class);

	protected SysConfig sysConfig;
	
	/**
	 * 批量删除
	 * @param eids
	 * @return
	 */
	public int delAll(String[] ids){
		if(ids==null){
			return -1;
		}
		List delList = new ArrayList();
		try {
			for (String string : ids) {				
				FhiFreight freight = (FhiFreight) this.dbDao.queryObjectById(FhiFreight.class, string);
				logger.info("删除报价,ID:"+freight.getId()+",From:"+freight.getFromName()+",To:"+freight.getToName());
				delList.add(freight);
			}
			dbDao.updateDbObjs(null, null, delList);
			logger.info("删除成功!");
		} catch (Exception e) {
			logger.error("删除失败。", e);
			return -1;
		}
		return 1;
	}
	
	/**
	 * 用于DWR 判断单号是否重号
	 * @param code
	 * @return
	 */
	public boolean hasCode(String code){
		int c = this.dbDao.count("from FhiFreight where code='"+code+"'");
		return c>0?true:false;
	}
	
	/**
	 * 用于DWR 返回始发地虚拟下拉列表
	 * @param fromStr
	 * @return
	 */
	public List<FhiFreight> queryFrom(String fromStr){
		String hql = "from FhiFreight where fromCode like '"+fromStr+"%' or fromName like '"+fromStr+"%' Group By fromName";
		try {
			return this.dbDao.queryObjects(hql, 10);
		} catch (Exception e) {
			logger.error("虚拟下拉查询出错！HQL："+hql, e);
		}
		return null;
	}
	
	/**
	 * 用于DWR 返回目的地虚拟下拉列表
	 * @param fromStr
	 * @return
	 */
	public List<FhiFreight> queryTo(String toStr){
		String hql = "from FhiFreight where toCode like '"+toStr+"%' or toName like '"+toStr+"%' Group By toName";
		try {
			return this.dbDao.queryObjects(hql, 10);
		} catch (Exception e) {
			logger.error("虚拟下拉查询出错！HQL："+hql, e);
		}
		return null;
	}
	
}
