package com.fhi.system.service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.fhi.framework.abstractservice.AbstractServiceImple;
import com.fhi.framework.utils.date.DataUtils;
import com.fhi.system.vo.SysAutoCode;
import com.fhi.system.vo.SysConfig;

public class SystemImple extends AbstractServiceImple implements SystemIn {

	private static Logger logger = Logger.getLogger(SystemImple.class);

	protected SysConfig sysConfig;

	/**
	 * 获取系统配置
	 * 
	 * @return
	 */
	public SysConfig getSysConfig() {
		try {
			if (sysConfig == null) {
				List<SysConfig> list = dbDao.queryObjects("from SysConfig");
				if (list.size() == 1) {
					sysConfig = list.get(0);
				}
				else{
					sysConfig = new SysConfig();
					this.dbDao.addObject(sysConfig);
				}
			}
		} catch (Exception e) {
			logger.error("载入系统配置失败！", e);
		}
		return sysConfig;
	}

	/**
	 * 配置信息保存
	 * 
	 * @param wmsConfig
	 * @return
	 */
	public int saveSysConfig(SysConfig wmsConfig) {
		try {
			SysConfig wmsConfig_t = (SysConfig) dbDao.queryObjectById(
					SysConfig.class, wmsConfig.getId());
			wmsConfig_t.setRowsPerpage(wmsConfig.getRowsPerpage());
			wmsConfig_t.setSessionInactiveInterval(wmsConfig
					.getSessionInactiveInterval());
			
			dbDao.updateObject(wmsConfig_t);
			this.sysConfig = wmsConfig_t;
			return 1;
		} catch (Exception e) {
			logger.error("系统配置保存失败！", e);
		}
		return 0;
	}

	/**
	 * 查询自动编号列表
	 * 
	 * @return
	 */
	public List<SysAutoCode> queryAutoCode() {
		try {
			return dbDao.queryObjects(" from SysAutoCode");
		} catch (Exception e) {
			logger.error("自动编号列表查询失败！", e);
		}
		return null;
	}

	/**
	 * 获取自动编码
	 * 
	 * @param acd
	 * @return
	 */
	private String getCode(SysAutoCode acd,boolean nextTrue) {
		if (acd.getStart().intValue() == 0) {
			return "";
		}
		try {
			String[] strs = acd.getCodeConfig().split("\\+");
			StringBuilder retStr = new StringBuilder();
			for (String string : strs) {
				logger.debug("string==:" + string);
				if (Pattern.compile("^[\"|'|\\(]\\S+[\"|'|\\)]$").matcher(
						string).find()) {
					retStr.append(string.substring(1, string.length() - 1));
				} else if (Pattern
						.compile("^Date\\([yy|yyyy|MM|dd|HH|mm]+\\)$").matcher(
								string).find()) {
					SimpleDateFormat dateformat1 = new SimpleDateFormat(string
							.substring(5, string.length() - 1));
					retStr.append(dateformat1.format(new Date()));
				} else if (Pattern.compile("^Counter\\([0|#]+\\)$").matcher(
						string).find()) {
					int t = string.indexOf(")", 9);
					DecimalFormat df = new DecimalFormat(string.substring(8, t));
					// 判断计数器重置 * 计数器重置标识 0 按年 1 按月 2 按日
					switch (acd.getReset()) {
					case 0:
						logger.debug("按年重置");
						if (DataUtils.getYear(new Date()) != DataUtils
								.getYear(acd.getCreateDate())) {
							acd.setCount(1);
						}
						break;
					case 1:
						logger.debug("按月重置");
						if (DataUtils.getYear(new Date()) != DataUtils
								.getYear(acd.getCreateDate())
								|| DataUtils.getMonth(new Date()) != DataUtils
										.getMonth(acd.getCreateDate())) {
							acd.setCount(1);
						}
						break;
					case 2:
						logger.debug("按天重置");
						if (DataUtils.getYear(new Date()) != DataUtils
								.getYear(acd.getCreateDate())
								|| DataUtils.getMonth(new Date()) != DataUtils
										.getMonth(acd.getCreateDate())
								|| DataUtils.getDay(new Date()) != DataUtils
										.getDay(acd.getCreateDate())) {
							acd.setCount(1);
						}
						break;
					case 3:
						logger.debug("按小时重置");
						if (DataUtils.getYear(new Date()) != DataUtils
								.getYear(acd.getCreateDate())
								|| DataUtils.getMonth(new Date()) != DataUtils
										.getMonth(acd.getCreateDate())
								|| DataUtils.getDay(new Date()) != DataUtils
										.getDay(acd.getCreateDate())
								|| DataUtils.getHour(new Date()) != DataUtils
										.getHour(acd.getCreateDate())) {
							acd.setCount(1);
						}
						break;
					}
					// 格式化输出序号
					retStr.append(df.format(acd.getCount()));
					
					
					// 设置下一次计数
					acd.setCount(acd.getCount() + acd.getStep());
					// 更新取数时间
					acd.setCreateDate(new Date());
					
				}
			}
			//判断是否自动更新
			if(nextTrue){
				dbDao.updateObject(acd);
			}
			logger.info("输出自动单号：" + retStr);
			return retStr.toString();
		} catch (Exception e) {
			logger.error("更新自动编号失败！", e);
		}
		return "";
	}

	/**
	 * 加载自动编号
	 * 
	 * @param markCode
	 * @return
	 */
	public SysAutoCode loadAutoCode(String markCode) {
		try {
			List<SysAutoCode> list = dbDao
					.queryObjects("from SysAutoCode where markCode='"
							+ markCode + "'");
			if (list.size() == 1) {
				return list.get(0);
			}
			else{
				logger.warn("自动编号‘"+markCode+"’不存在，系统自动创建。");
				SysAutoCode autoCode=new SysAutoCode(markCode);
				this.dbDao.addObject(autoCode);
				return autoCode;
			}
		} catch (Exception e) {
			logger.error("查询自动编号失败！", e);
		}
		return null;
	}	
	

	/**
	 * 加载自动编号
	 * 
	 * @param markCode
	 * @return
	 */
	public SysAutoCode loadAutoCodeById(String codeId) {
		try {
			return (SysAutoCode) dbDao.queryObjectById(SysAutoCode.class,
					codeId);
		} catch (Exception e) {
			logger.error("查询自动编号失败！", e);
		}
		return null;
	}

	/**
	 * 保存自动编号
	 * 
	 * @param wac
	 * @return
	 */
	public boolean saveAutoCode(SysAutoCode wac) {
		try {
			SysAutoCode wac_t = this.loadAutoCodeById(wac.getCodeId());
			wac_t.setCodeConfig(wac.getCodeConfig());
			wac_t.setCount(wac.getCount());
			wac_t.setReset(wac.getReset());
			if (wac.getStart() == null) {
				wac_t.setStart(0);
			} else {
				wac_t.setStart(wac.getStart());
			}
			wac_t.setStep(wac.getStep());
			dbDao.updateObject(wac_t);
			return true;
		} catch (Exception e) {
			logger.error("保存自动编号失败！", e);
		}
		return false;
	}

	/**
	 * 获取自动编码
	 * 不做累加处理
	 * @param markCode
	 * @return
	 */
	public String getCode(String markCode) {
		return this.getCode(this.loadAutoCode(markCode),false);
	}
	
	/**
	 * 获取自动编码
	 * 并且判断是否存在
	 * @param markCode
	 * @return 如果存在返回 false
	 */
	public boolean isCodeHave(String autoCode,String tabName,String codeMethod) {
		try {
			int t = dbDao.count("from "+tabName+" where "+codeMethod+" like '%" + autoCode + "%'");
			if(t>0){
				return true;
			}
		} catch (HibernateException e) {
			logger.error("判断自动单号是否重号错误！", e);
		}
		return false;
	}
	
	/**
	 * 获取自动编码
	 * 如果单号存在 自动顺延
	 * @param markCode
	 * @return 
	 */
	public String getCode(String markCode,String tabName,String codeMethod) {
		String autoCode = this.getCode(this.loadAutoCode(markCode),true);
		if(this.isCodeHave(autoCode, tabName, codeMethod)){
			autoCode = this.getCode(markCode, tabName, codeMethod);
		}
		return autoCode;
	}
	
	/**
	 * 判断是否启用自动单号
	 * @param markCode
	 * @return
	 */
	public boolean isAutoCode(String markCode){		
		try {
			List<SysAutoCode> list = dbDao.queryObjects("from SysAutoCode where markCode='" + markCode + "'");
			if(list.size()==0){
				logger.warn("自动编号‘"+markCode+"’不存在，系统自动创建。");
				SysAutoCode autoCode=new SysAutoCode(markCode);
				this.dbDao.addObject(autoCode);
				return false;
			}
			else{
				return list.get(0).getStart().intValue()==1?true:false;
			}
		} catch (Exception e) {
			logger.error("判断自动单号是否启动错误！",e);
		}
		return false;
	}
}
