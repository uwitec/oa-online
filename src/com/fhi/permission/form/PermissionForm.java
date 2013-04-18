package com.fhi.permission.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

import com.fhi.permission.vo.FhiModule;
import com.fhi.permission.vo.FhiRoPerList;
import com.fhi.permission.vo.FhiRole;
import com.fhi.permission.vo.FhiRolePermission;
import com.fhi.permission.vo.FhiRoleUser;


public class PermissionForm extends ActionForm{

	private Integer pageNo;
	private FhiModule ltsMo = new FhiModule();
	private FhiRole ltsRo = new FhiRole();
	private FhiRoleUser LtsRoUs = new FhiRoleUser();
	private List stocks = new FhiRoPerList(FhiRolePermission.class);
	
	public Integer getPageNo() {
		if (pageNo == null || pageNo == 0) {
			pageNo = 1;
		}
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		if (pageNo == null || pageNo == 0) {
			pageNo = 1;
		}
		this.pageNo = pageNo;
	}
	
	public void setStocks(List stocks)
    {
        this.stocks.clear();
        this.stocks.addAll(stocks);
    }


    public List getStocks()
    {
        return stocks;
    }

	public FhiRoleUser getLtsRoUs() {
		return LtsRoUs;
	}

	public void setLtsRoUs(FhiRoleUser LtsRoUs) {
		this.LtsRoUs = LtsRoUs;
	}


	public FhiRole getLtsRo() {
		return ltsRo;
	}

	public void setLtsRo(FhiRole LtsRo) {
		this.ltsRo = LtsRo;
	}

	public FhiModule getLtsMo() {
		return ltsMo;
	}

	public void setLtsMo(FhiModule LtsMo) {
		this.ltsMo = LtsMo;
	}
}
