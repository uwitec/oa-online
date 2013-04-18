package com.fhi.framework.config;

public interface FhiConfig {
	//用于设置系统管理错误信息以及提示信息 session request的KEY 
	public static String System_Info="SystemInfo";
	public static String System_Save_Id="SystemSaveId";	
	
//	基本配置信息
	public static String USER_NAME = "user";
	public static String CLIENT_USER_NAME = "ClientUser";
	public static String AGENT_USER_NAME = "AgentUser";
	
	public static String PAGE_INFO_NAME="PAGE_INFO_NAME";
	//文档		
	public static String Document_Info="Document_Info";
	public static String Document_Alter_Id="Document_Alter_Id";
	//相册
	public static String Picture_Info="Picture_Info";
	public static String Picture_Alter_Id="Picture_Alter_Id";
	//报价
	public static String Freight_Info="Freight_Info";
	public static String Freight_Alter_Id="Freight_Alter_Id";
	//工作计划
	public static String UC_WorkPlan_Info="UC_WorkPlan_Info";
	public static String UC_WorkPlan_Id="UC_WorkPlan_Id";
	//系统通知
	public static String Sys_Msg_Info="Sys_Msg_Info";
	public static String Sys_Msg_Id="Sys_Msg_Id";
	
	public static String AutoCode_Document="AutoCode_Document";
	public static String AutoCode_Picture="AutoCode_Picture";
	public static String AutoCode_Freight="AutoCode_Freight";
	public static String AutoCode_WorkPlan="AutoCode_WorkPlan";
	
	//RMAWMS自动编号
	public static String AutoCode_RmaWmsStockin="AutoCode_RmaWmsStockin";
	public static String AutoCode_RmaWmsStockout="AutoCode_RmaWmsStockout";
	
	//设置分类标识
	public static String Class_Document="Class_Document";
	public static String Class_Picture="Class_Picture";
	
	//设置报检记录
	public static String Inspection_Alter_Id = "Inspection_Alter_Id";
	public static String Inspection_Info = "Inspection_Info";
	
	
	/**
	 * 系统信息提醒设置<br>
	 * 工作任务类型
	 */
	public static Integer WorkTaskType=0;
	
	
	
}