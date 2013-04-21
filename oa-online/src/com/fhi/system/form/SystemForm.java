package com.fhi.system.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

import com.fhi.system.vo.SysAutoCode;
import com.fhi.system.vo.SysConfig;

public class SystemForm extends ActionForm {

	// 用于实体传值以及查询传值
	private SysConfig conf;
	private SysAutoCode wac;
	
	private List<SysAutoCode> codeList;
	
	public SysConfig getConf() {
		if (this.conf == null) {
			this.conf = new SysConfig();
		}
		return conf;
	}

	public void setConf(SysConfig conf) {
		this.conf = conf;
	}

	public SysAutoCode getWac() {
		if (this.wac == null) {
			this.wac = new SysAutoCode();
		}
		return wac;
	}

	public void setWac(SysAutoCode wac) {
		this.wac = wac;
	}

	public List<SysAutoCode> getCodeList() {
		return codeList;
	}

	public void setCodeList(List<SysAutoCode> codeList) {
		this.codeList = codeList;
	}
}
