package com.fhi.usercenter.worktask.vo;

import java.util.Date;

import com.fhi.framework.config.FhiConfig;
import com.fhi.system.sysmsg.vo.SysMsgSetup;

public class FhiUcWorkPlan {
	//工作ID
	private String id;	
	//工作Code
	private String code;
	
	private SysMsgSetup msgSetup;
	
	//提醒时间
	private Date remindTime;
	
	private boolean autoCode;
	
	//优先级 4低 2中 1高
	private Integer priority;
	//标题
    private String title;
    //工作内容
    private String content;
    //工作总结
    private String workSummary;
    //起始时间
    private Date startingTime;
    //状态 0未完成 1已完成
    private Integer mode;
    //发布人
    private String creater;
    //发布人 姓名
    private String creatorName;
    //发布时间
    private Date createDate;
    
    public SysMsgSetup getMsgSetup() {
		if(msgSetup==null){
			msgSetup=new SysMsgSetup();
		}
		msgSetup.setLinked(id);
		msgSetup.setTitle("您的工作计划【"+this.getTitle()+"】提醒");
		msgSetup.setContent("您为【"+this.getTitle()+"】设置的提醒，内容如下：<br>"+this.getContent());
		msgSetup.setUserId(this.getCreater());
		msgSetup.setUrl("workplan.do?method=show&ucWorkPlan.id="+id);
		msgSetup.setStartDate(this.getRemindTime());
		msgSetup.setSysType(FhiConfig.WorkTaskType);
		msgSetup.setSysClass(this.getPriority());
		msgSetup.setSysClassName("工作计划提醒 优先级【"+this.getPriorityStr()+"】");
		msgSetup.setCreateDate(new Date());
		return msgSetup;
	}
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public boolean isAutoCode() {
		return autoCode;
	}
	public void setAutoCode(boolean autoCode) {
		this.autoCode = autoCode;
	}
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public String getContentShow() {
		return content.replace("\r\n", "<br/>");
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWorkSummary() {
		return workSummary;
	}
	public String getWorkSummaryShow() {
		return workSummary.replace("\r\n", "<br/>");
	}
	public void setWorkSummary(String workSummary) {
		this.workSummary = workSummary;
	}
	public Date getStartingTime() {
		return startingTime;
	}
	public void setStartingTime(Date startingTime) {
		this.startingTime = startingTime;
	}
	public void setStartingTimeStr(String startingTime) {
		this.startingTime = com.fhi.framework.utils.date.DataUtils.getDateByString(startingTime);
	}
	public Integer getMode() {
		return mode==null?0:mode;
	}
	public String getModeStr() {
		return mode==0?"未完成":"已完成";
	}
	public void setMode(Integer mode) {
		this.mode = mode;
	}	
	public void setMsgSetup(SysMsgSetup msgSetup) {
		this.msgSetup = msgSetup;
	}
	public Integer getPriority() {
		return priority==null?1:priority;
	}
	public String getPriorityStr() {
		return this.getPriority().intValue()==1?"高":(this.getPriority().intValue()==2?"中":"低");
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Date getRemindTime() {
		return remindTime;
	}

	public void setRemindTime(Date remindTime) {
		this.remindTime = remindTime;
	}
	
	public void setRemindTimeStr(String remindTime) {
		this.remindTime = com.fhi.framework.utils.date.DataUtils.getDateByString(remindTime);
	}

}
