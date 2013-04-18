package com.fhi.client.service;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.fhi.client.vo.FhiClientUser;
import com.fhi.framework.abstractservice.AbstractServiceImple;
import com.fhi.framework.config.FhiConfig;
import com.fhi.system.vo.SysConfig;

public class ClientImple extends AbstractServiceImple implements ClientIn {

	private static Logger logger = Logger.getLogger(ClientImple.class);

	protected SysConfig sysConfig;
	
	/**
	 * 客户用户登录查询
	 * @param userId
	 * @param password
	 * @return
	 */
	public FhiClientUser getClient(String userId,String password){
		FhiClientUser client=null;
		try {
			List<FhiClientUser> list = this.dbDao.queryObjects("from FhiClientUser where userId = '"+userId+"' and passWord = '"+password+"'");
			if(list!=null&&list.size()==1){
				client = list.get(0);
			}
		} catch (Exception e) {
			logger.error("客户登录失败 用户名："+userId+"，密码："+password, e);
		}		
		return client;
	}
	/**
	 * DWR密码修改
	 * @param oldPassWord
	 * @param newPassWord
	 * @param request
	 * @return				
	 */						
	public int setPassWord(String oldPassWord,String newPassWord,HttpServletRequest request){
		
		if(oldPassWord==null||"".equals(oldPassWord)||newPassWord==null||"".equals(newPassWord))return 0;
		//用户未登录返回-1
		FhiClientUser clientUser = (FhiClientUser) request.getSession().getAttribute(FhiConfig.CLIENT_USER_NAME);
		if(clientUser==null)return -1;
		//旧密码与输入不服返回2
		if(!clientUser.getPassWord().equals(oldPassWord))return 2;
		
		clientUser.setPassWord(newPassWord);
		try {
			return this.getDbDao().updateObject(clientUser)?1:0;
		} catch (Exception e) {
			logger.error("客户用户修改密码错误。用户："+clientUser.getUserId()+"，新密码："+newPassWord, e);
			return 0;
		}
	}
}
