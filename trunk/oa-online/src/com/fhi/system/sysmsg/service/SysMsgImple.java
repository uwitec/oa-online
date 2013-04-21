package com.fhi.system.sysmsg.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.fhi.accept.service.AcceptIn;
import com.fhi.framework.abstractservice.AbstractServiceImple;
import com.fhi.framework.utils.date.DataUtils;
import com.fhi.system.sysmsg.vo.SysMsg;
import com.fhi.system.sysmsg.vo.SysMsgSetup;
import com.fhi.user.vo.FhiUser;

public class SysMsgImple extends AbstractServiceImple implements SysMsgIn , Runnable {

	private static Logger logger = Logger.getLogger(SysMsgImple.class);
	private AcceptIn acceptService;
	//构造方法启动线程
	public SysMsgImple(){
		Thread t = new Thread(this);
		t.start();
		logger.debug("系统信息检测线程启动！");
	}
	//运行线程
	public void run() {
		try {
			Thread.sleep(1000*60);
			this.queryNowSetup();
			this.run();
		} catch (Exception e) {
		logger.error("系统信息检测线程错误！",e);
		}
	}
	
	public void queryNowSetup(){
		String hql = " from SysMsgSetup where now() BETWEEN startDate AND deadlineDate and now() < deadlineDate and startDate < deadlineDate";
		int count = this.dbDao.count(hql);
		if(count > 0){
			try {
				logger.debug("检查信息设置，找到："+count+"条待生成信息.");
				processMsgSetup(this.dbDao.queryObjects(hql));
			} catch (Exception e) {
				logger.error("系统信息查询失败！", e);
			}
		}
	}
	
	//生成系统信息
	public void processMsgSetup(List<SysMsgSetup> list){
		List<SysMsg> sysMsgList = new ArrayList<SysMsg>();
		List<SysMsgSetup> sysMsgSetupList = new ArrayList<SysMsgSetup>();
		//循环所有配置信息
		for (SysMsgSetup sysMsgSetup : list) {
			sysMsgList.addAll(sysMsgSetup.getSysMsgs());
			//更改当前配置信息启动日期  当前日期+配置时间 防止服务器停止时生成过去提醒
			String newDate = DataUtils.format(new Date(), "yyyy-MM-dd") + " " + DataUtils.format(sysMsgSetup.getStartDate(), "HH:mm:ss");
			Date d = DataUtils.getDateByString(newDate);
			d.setTime(d.getTime()+1000*60*60*24);
			sysMsgSetup.setStartDate(d);
			sysMsgSetupList.add(sysMsgSetup);
			if(sysMsgSetup.getSysType()==1){
				acceptService.setState(sysMsgSetup);
			}
		}		
		try {			
			this.dbDao.updateDbObjs(sysMsgList, sysMsgSetupList, null);
			logger.debug("成功生成"+sysMsgList.size()+"条系统信息。");
		} catch (Exception e) {
			logger.error("系统信息入库失败！", e);       
		}
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
				SysMsg sysMsg = (SysMsg) this.dbDao.queryObjectById(SysMsg.class, string);
				logger.info("删除系统消息,ID:"+sysMsg.getId()+",标题:"+sysMsg.getTitle());
				delList.add(sysMsg);
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
	 * 用于DWR前台展示信息
	 */
	public SysMsg showLoad(String id){		
		try {
			SysMsg msg = (SysMsg) this.dbDao.queryObjectById(SysMsg.class, id);
			if(msg!=null&&msg.getIsRead().intValue()==0){
				msg.setIsRead(1);
				this.dbDao.updateObject(msg);
			}
			return msg;
		} catch (Exception e) {
			logger.error("DWR查询系统消息错误！",e);
			return null;
		}
	}
	
	/**
	 * 用于DWR前台展示信息
	 */
	public Integer editRead(String[] ids){		
		if(ids==null){
			return -1;
		}
		List updateList = new ArrayList();
		try {
			for (String string : ids) {				
				SysMsg sysMsg = (SysMsg) this.dbDao.queryObjectById(SysMsg.class, string);
				logger.info("系统消息标记为未读,ID:"+sysMsg.getId()+",标题:"+sysMsg.getTitle());
				sysMsg.setIsRead(0);
				updateList.add(sysMsg);
			}
			dbDao.updateDbObjs(null, updateList, null);
			logger.info("已标记为未读!");
		} catch (Exception e) {
			logger.error("修改读取状态失败。", e);
			return -1;
		}
		return 1;
	}
	
	/**
	 * 获取未读系统信息数量
	 * @return
	 */
	public Integer getInformCount(FhiUser user){
		return this.dbDao.count(" from SysMsg where userId='"+user.getUserId().trim()+"' and sysType>0 and isRead=0");
	}
	
	/**
	 * 获取未读工作计划提醒数量
	 * @return
	 */
	public Integer getPlanCount(FhiUser user){
		return this.dbDao.count(" from SysMsg where userId='"+user.getUserId().trim()+"' and sysType=0 and isRead=0");
	}
	public void setAcceptService(AcceptIn acceptService) {
		this.acceptService = acceptService;
	}	
//	
//	/**
//	 * 接单表升级数据批量整理
//	 * @return
//	 */
//	public boolean update(){
//		try {
//			List aList = this.dbDao.queryObjects(" from FhiOaAccept ");
//			List updateList=new ArrayList();
//			List updateCustomList=new ArrayList();
//			for (int i=0;i<aList.size();i++) {
//				FhiOaAccept accept = (FhiOaAccept) aList.get(i);
//				
//				String manager = accept.getManager();
//				List employeeList = this.dbDao.queryObjects(" from FhiOaEmployeeBasic where name='"+manager+"'");
//				if(employeeList.size()==1){
//					FhiOaEmployeeBasic employee = (FhiOaEmployeeBasic) employeeList.get(0);
//					accept.setManagerId(employee.getAccount());
//					updateList.add(accept);
//					boolean t=true;
//					for (int j=0;j<updateCustomList.size()&&t;j++) {
//						FhiOaCustom custom = (FhiOaCustom) updateCustomList.get(j);
//						t=!accept.getCustomerId().equals(custom.getId());
//					}
//					if(t){
//						List customeList = this.dbDao.queryObjects(" from FhiOaCustom where id='"+accept.getCustomerId()+"'");
//						if(customeList.size()==1){
//							FhiOaCustom custom = (FhiOaCustom) customeList.get(0);
//							custom.setWorkor(employee.getName());
//							custom.setWorkorId(employee.getAccount());
//							updateCustomList.add(custom);
//						}
//					}
//					
//				}
//			}
//			updateList.addAll(updateCustomList);
//			return this.dbDao.updateObjects(updateList);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return false;		
//	}
}
