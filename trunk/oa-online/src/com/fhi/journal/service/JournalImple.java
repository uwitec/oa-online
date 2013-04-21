package com.fhi.journal.service;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.fhi.framework.abstractservice.AbstractServiceImple;
import com.fhi.framework.config.FhiConfig;
import com.fhi.journal.vo.FhiJournalVote;
import com.fhi.user.vo.FhiUser;

public class JournalImple extends AbstractServiceImple implements JournalIn {

	private static Logger logger = Logger.getLogger(JournalImple.class);

	
	//投票处理DWR -1未登录 1 投票成功 0重复投票
	public int voteAction(FhiJournalVote vote,HttpServletRequest request){
		FhiUser user = (FhiUser) request.getSession().getAttribute(FhiConfig.USER_NAME);
		if(user==null){
			logger.debug("用户未登录，投票失败！");
			return -1;
		}
		if(!user.getUserId().equals(vote.getCreater())){
			logger.debug("用户ID有差异，投票失败！登录用户名："+user.getUserId()+"，获取用户名："+vote.getCreater());
			return -1;
		}
		else{
			if(!vote.inspection()){
				logger.debug("投票数量异常，投票失败！");
				return -1;
			}
			int c = this.dbDao.count("from FhiJournalVote where creater='" + user.getUserId() + "' and code='" + vote.getCode() + "'");
			if(c!=0){
				logger.debug("重复投票，投票失败！");
				return 0;
			}
			vote.setCreatorName(user.getEmployeeClass().getName());
			vote.setCreateDate(new Date());
			return this.save(vote)?1:0;
		}
	}
	
	/**
	 * 计算当前票数
	 * @param code
	 * @return
	 */
	public FhiJournalVote getCount(String code,String userId){
		try {
			List list = this.dbDao.queryObjects("select new com.fhi.journal.vo.FhiJournalVote(sum(vote.good),sum(vote.ordinary),sum(vote.noGood)) from FhiJournalVote vote where code='" + code + "'");
			if(list.size()==1){
				FhiJournalVote vote_ = (FhiJournalVote) list.get(0);
				int c = this.dbDao.count("from FhiJournalVote where code='" + code + "' and creater='"+userId+"'");
				if(c==0){
					vote_.setCode(code);
				}				
				return vote_;
			}			
		} catch (Exception e) {
			logger.error("投票统计查询失败，Code："+code, e);
		}
		return null;
	}
}
