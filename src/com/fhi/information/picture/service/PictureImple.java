package com.fhi.information.picture.service;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.fhi.framework.abstractservice.AbstractServiceImple;
import com.fhi.information.picture.vo.FhiPicture;
import com.fhi.system.vo.SysConfig;

public class PictureImple extends AbstractServiceImple implements PictureIn {

	private static Logger logger = Logger.getLogger(PictureImple.class);

	protected SysConfig sysConfig;
	
	/**
	 * 用于DWR 判断单号是否重号
	 * @param code
	 * @return
	 */
	public boolean hasCode(String code){
		int c = this.dbDao.count("from FhiPicture where code='"+code+"'");
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
				FhiPicture pic = (FhiPicture) this.dbDao.queryObjectById(FhiPicture.class, string);
				logger.info("删除相册,ID:"+pic.getClassId()+",Title:"+pic.getTitle());
				delList.add(pic);
			}
			dbDao.updateDbObjs(null, null, delList);
			logger.info("删除成功!");
		} catch (Exception e) {
			logger.error("删除失败。", e);
			return -1;
		}
		return 1;
	}
	
}
