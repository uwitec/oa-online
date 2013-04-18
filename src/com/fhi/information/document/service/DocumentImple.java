package com.fhi.information.document.service;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.fhi.framework.abstractservice.AbstractServiceImple;
import com.fhi.information.document.vo.FhiDocument;
import com.fhi.system.vo.SysConfig;

public class DocumentImple extends AbstractServiceImple implements DocumentIn {

	private static Logger logger = Logger.getLogger(DocumentImple.class);

	protected SysConfig sysConfig;
	
	/**
	 * 用于DWR 判断单号是否重号
	 * @param code
	 * @return
	 */
	public boolean hasCode(String code){
		int c = this.dbDao.count("from FhiDocument where code='"+code+"'");
		return c>0?true:false;
	}
	
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
				FhiDocument docu = (FhiDocument) this.dbDao.queryObjectById(FhiDocument.class, string);
				logger.info("删除文档,ID:"+docu.getClassId()+",Title:"+docu.getTitle());
				delList.add(docu);
			}
			dbDao.updateDbObjs(null, null, delList);
			logger.info("全部删除成功!");
		} catch (Exception e) {
			logger.error("删除失败。", e);
			return -1;
		}
		return 1;
	}
	
}
