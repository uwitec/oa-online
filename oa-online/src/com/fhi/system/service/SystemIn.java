package com.fhi.system.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fhi.system.vo.SysAutoCode;
import com.fhi.system.vo.SysConfig;

public interface SystemIn {
	
	/**
	 * 获取系统配置
	 * 
	 * @return
	 */
	public SysConfig getSysConfig();
	
	/**
	 * 查询自动编号列表
	 * @return
	 */
	public List<SysAutoCode> queryAutoCode();
	
	/**
	 * 获取自动编码
	 * 不做累加处理
	 * @param markCode
	 * @return
	 */
	public String getCode(String markCode);
	
	/**
	 * 加载自动编号
	 * @param markCode
	 * @return
	 */
	public SysAutoCode loadAutoCode(String markCode);
	
	/**
	 * 加载自动编号
	 * @param markCode
	 * @return
	 */
	public SysAutoCode loadAutoCodeById(String codeId);
	
	/**
	 * 保存自动编号
	 * @param wac
	 * @return
	 */
	public boolean saveAutoCode(SysAutoCode wac);
	
	/**
	 * 配置信息保存
	 * @param wmsConfig
	 * @return
	 */
	public int saveSysConfig(SysConfig wmsConfig);
	
	/**
	 * 判断是否启用自动单号
	 * @param markCode
	 * @return
	 */
	public boolean isAutoCode(String markCode);
	
	/**
	 * 获取自动编码
	 * 如果单号存在 自动顺延
	 * @param markCode
	 * @return 
	 */
	public String getCode(String markCode,String tabName,String codeMethod);
	
	/**
	 * 获取自动编码
	 * 并且判断是否存在
	 * @param markCode
	 * @return 如果存在返回 false
	 */
	public boolean isCodeHave(String autoCode,String tabName,String codeMethod);
	
	/**
	 * 获取客户IP地址
	 * @param request
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request);
}
