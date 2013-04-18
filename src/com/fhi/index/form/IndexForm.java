package com.fhi.index.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.fhi.system.sysmsg.vo.SysMsg;
import com.fhi.user.form.AddUserForm;
import com.fhi.user.vo.FhiUser;


@SuppressWarnings("serial")
public class IndexForm extends ActionForm{

	private AddUserForm addUserForm;	
	
	private Integer informCount;
	
	private Integer planCount;
	
	private FhiUser wu = new FhiUser();
	
	private List<SysMsg> sysMsgList=null;
	
	private List<SysMsg> sysMsgListWork=null;
	
	public AddUserForm getAddUserForm() {
		return addUserForm;
	}

	public void setAddUserForm(AddUserForm addUserForm) {
		this.addUserForm = addUserForm;
	}

	public FhiUser getWu() {
		return wu;
	}

	public void setWu(FhiUser wu) {
		this.wu = wu;
	}

	public List<SysMsg> getSysMsgList() {
		if(sysMsgList==null){
			sysMsgList=new ArrayList<SysMsg>();
		}
		return sysMsgList;
	}

	public void setSysMsgList(List<SysMsg> sysMsgList) {
		this.sysMsgList = sysMsgList;
	}

	public List<SysMsg> getSysMsgListWork() {
		if(sysMsgListWork==null){
			sysMsgListWork=new ArrayList<SysMsg>();
		}
		return sysMsgListWork;
	}

	public void setSysMsgListWork(List<SysMsg> sysMsgListWork) {
		this.sysMsgListWork = sysMsgListWork;
	}

	public Integer getInformCount() {
		return informCount;
	}

	public void setInformCount(Integer informCount) {
		this.informCount = informCount;
	}

	public Integer getPlanCount() {
		return planCount;
	}

	public void setPlanCount(Integer planCount) {
		this.planCount = planCount;
	}	
}
