package com.fhi.usercenter.worktask.service;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.fhi.framework.abstractservice.AbstractServiceImple;
import com.fhi.system.sysmsg.vo.SysMsgSetup;
import com.fhi.system.vo.SysConfig;
import com.fhi.usercenter.worktask.vo.FhiUcWorkPlan;

public class UcWorkPlanImple extends AbstractServiceImple implements UcWorkPlanIn {

	private static Logger logger = Logger.getLogger(UcWorkPlanImple.class);

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
				FhiUcWorkPlan ucWorkPlan = (FhiUcWorkPlan) this.dbDao.queryObjectById(FhiUcWorkPlan.class, string);
				logger.info("删除工作计划,ID:"+ucWorkPlan.getId()+",标题:"+ucWorkPlan.getTitle());
				delList.add(ucWorkPlan);
				delList.addAll(this.dbDao.queryObjects("from SysMsgSetup where linked='"+string+"'"));
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
	 * 更改工作计划状态
	 * @param ids
	 * @param mode
	 * @return
	 */
	public int editModeAll(String[] ids,Integer mode){
		if(ids==null){
			return 0;
		}
		List updateList = new ArrayList();
		try {
			for (String string : ids) {
				FhiUcWorkPlan ucWorkPlan = (FhiUcWorkPlan) this.dbDao.queryObjectById(FhiUcWorkPlan.class, string);				
				if(mode==null){
					ucWorkPlan.setMode((ucWorkPlan.getMode()+1)%2);					
				}else{
					ucWorkPlan.setMode(mode);
				}
				logger.info("更改工作计划状态为【"+ucWorkPlan.getModeStr()+"】,ID:"+ucWorkPlan.getId()+",标题:"+ucWorkPlan.getTitle());
				updateList.add(ucWorkPlan);
			}
			dbDao.updateDbObjs(null, updateList,null );
			logger.info("更改成功!");
		} catch (Exception e) {
			logger.error("更改失败。", e);
			return 0;
		}
		return 1;
	}
	
	/**
	 * 用于DWR 判断单号是否重号
	 * @param code
	 * @return
	 */
	public boolean hasCode(String code){
		int c = this.dbDao.count("from FhiUcWorkPlan where code='"+code+"'");
		return c>0?true:false;
	}
	
	/**
	 * 新建工作计划<br>
	 * 函数重写
	 * @param workPlan
	 * @return
	 */
	public boolean save(Object obj){
		FhiUcWorkPlan workPlan = (FhiUcWorkPlan) obj;
		try {			
		if(workPlan.getRemindTime()==null){
			
			return this.dbDao.addObject(workPlan);
		}
		else{
				List list = new ArrayList();
				SysMsgSetup msgSetup = workPlan.getMsgSetup();
				list.add(msgSetup);
				list.add(workPlan);
				logger.debug("用户名："+workPlan.getCreater()+"，保存工作计划单");
				return this.dbDao.addObjects(list);			
			}
		} catch (Exception e) {
			logger.error("添加工作任务出错！用户："+workPlan.getCreater());
			return false;
		}	
	}
	
	/**
	 * 更新工作计划<br>
	 * 函数重写
	 * @param workPlan
	 * @return
	 */
	public boolean update(Object obj){
		FhiUcWorkPlan workPlan = (FhiUcWorkPlan) obj;
		try {
			List<SysMsgSetup> delList = this.dbDao.queryObjects("from SysMsgSetup where linked='"+workPlan.getId()+"'");
			List<FhiUcWorkPlan> upDateList = new ArrayList<FhiUcWorkPlan>();			
			List<SysMsgSetup> addList = null;
			
			upDateList.add(workPlan);			
			if(workPlan.getRemindTime()!=null){
				addList = new ArrayList<SysMsgSetup>();
				SysMsgSetup msgSetup = workPlan.getMsgSetup();
				addList.add(msgSetup);
			}
			return this.dbDao.updateDbObjs(addList, upDateList, delList);
		} catch (Exception e) {
			logger.error("添加工作任务出错！用户："+workPlan.getCreater());
			return false;
		}	
	}
}
