package com.fhi.framework.page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListTableCourier {
	//返回数据列表
	private List dataBaseList = null;
	//列表设置
	private Map<String,String> settingMap = null;
	//查询数据
	private Map<String,String> searchData = null;
	//查询设置
	private List<Map> searchSettings = null;
	//表格列设置
	private List<Map> theadSttings = null;
	//按钮设置
	private List<Map> buttonSettings = null;
	//分页信息
	private PageInfo pageInfo = null;	
	//提示信息
	private String message;
	
	public List getDataBaseList() {
		if(dataBaseList==null){
			dataBaseList=new ArrayList();
		}
		return dataBaseList;
	}

	public Map<String, String> getSearchData() {
		if(searchData==null){
			searchData=new HashMap<String,String>();
		}
		return searchData;
	}

	public void setSearchData(Map<String, String> searchData) {
		this.searchData = searchData;
	}

	public void setDataBaseList(List dataBaseList) {
		this.dataBaseList = dataBaseList;
	}

	public Map<String,String> getSettingMap() {
		if(settingMap==null){
			settingMap=new HashMap<String,String>();
		}
		return settingMap;
	}

	public void setSettingMap(Map<String,String> settingMap) {
		this.settingMap = settingMap;
	}
	

	public List<Map> getSearchSettings() {
		if(searchSettings==null){
			searchSettings=new ArrayList<Map>();
		}
		return searchSettings;
	}

	public void setSearchSettings(List<Map> searchSettings) {
		this.searchSettings = searchSettings;
	}

	public PageInfo getPageInfo() {
//		if(pageInfo==null){
//			pageInfo=new PageInfo();
//		}
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	/**
	 * 获取列表设置Map
	 * @return
	 */
	public List<Map> getTheadSttings() {
		if(theadSttings==null){
			theadSttings=new ArrayList<Map>();
		}
		return theadSttings;
	}

	public void setTheadSttings(List<Map> theadSttings) {
		this.theadSttings = theadSttings;
	}

	public List<Map> getButtonSettings() {
		if(buttonSettings==null){
			buttonSettings=new ArrayList<Map>();
		}
		return buttonSettings;
	}

	public void setButtonSettings(List<Map> buttonSettings) {
		this.buttonSettings = buttonSettings;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}	
}
