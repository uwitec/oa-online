package com.fhi.framework.utils;


import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.sql.Types;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.fhi.framework.form.FHIForm;

 

/**
 * 
 * 
 * 
 * @author zhangzih
 * 
 */
public class FHIUtils {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(FHIUtils.class);
	
	public static final BigDecimal ZERO_BIGDECIMAL = new BigDecimal(0);

	/**
	 * 将 form 中的属性放置到 vo 中
	 * 
	 * @param vo
	 * @param form
	 */
	public static void setVOByForm(Object vo, FHIForm form) {
		BeanWrapper vobw = new BeanWrapperImpl(vo);
		BeanWrapper formbw = new BeanWrapperImpl(form);
		PropertyDescriptor[] formpd = formbw.getPropertyDescriptors();
		for (int i = 0; i < formpd.length; i++) {
			setVOProperty(formpd[i].getName(), vobw, formbw, formpd[i]);
		}
	}
	
	
	/**
	 * 具体将一个form中的属性放置到vo中
	 * 
	 * 1.form中属性为null的数据不会拷贝到vo中; <br>
	 * 2.时间按默认的format.time格式化方式格式化成日期放到vo里面;<br>
	 * 3.数字按默认的format.decimal格式化成数字放在vo里面;<br>
	 * 4.int直接拷贝;<br>
	 * 5.当vo中为Long型时，form中空及不合法字符串转换为null,其余转换为Long <br>
	 * 6.boolean类型按 true-1 false-0的方式拷贝到vo的int字段中; <br>
	 * 7.超出以上情况以外后台会发出警报(warn级别);<br>
	 * 
	 * @param formPropertyName
	 * @param vobw
	 * @param formbw
	 * @param formpd
	 */
	@SuppressWarnings("unchecked")
	private static void setVOProperty(String formPropertyName,
			BeanWrapper vobw, BeanWrapper formbw, PropertyDescriptor formpd) {
		if (vobw.isWritableProperty(formPropertyName)) {
			Object formProperty = formbw.getPropertyValue(formPropertyName);
			if (formProperty != null) {
				Class voPropertyClass = vobw.getPropertyType(formPropertyName);
				Class formPropertyClass = formpd.getPropertyType();
				if (formPropertyClass.equals(String.class)) {
					if (voPropertyClass.equals(String.class)) {
						vobw.setPropertyValue(formPropertyName, formProperty);
					} else if (voPropertyClass.equals(Long.class)) {
						try {
							vobw.setPropertyValue(formPropertyName, new Long(
									(String) formProperty));
						} catch (NumberFormatException e) {
							if (logger.isDebugEnabled()) {
								logger.debug("form." + formPropertyName + " = "
										+ formProperty + "不能转换为Long型数据");
							}
						}
					}else if (voPropertyClass.equals(Integer.class)) {
						try {
							vobw.setPropertyValue(formPropertyName, new Integer(
									(String) formProperty));
						} catch (NumberFormatException e) {
							if (logger.isDebugEnabled()) {
								logger.debug("form." + formPropertyName + " = "
										+ formProperty + "不能转换为Integer型数据");
							}
						}
					} else if (voPropertyClass.equals(Date.class)) {
						String source = (String) formProperty;
						if(StringUtils.isNotEmpty(source)){
							try {
								Date propertyDateValue;
								if (dateType(formPropertyName) == Types.DATE) {
									propertyDateValue = FormatUtils
											.parseDate(source);
								} else {
									propertyDateValue = FormatUtils
											.parseTime(source);
								}
								vobw.setPropertyValue(formPropertyName,
										propertyDateValue);
							} catch (ParseException e) {
								if (logger.isDebugEnabled()) {
									logger.debug("form." + formPropertyName
											+ " 为不合法的日期字符串 :: " + source);
								}
							}
						}
					} else if (voPropertyClass.equals(BigDecimal.class)) {
						String source = (String) formProperty;
						try {
							vobw.setPropertyValue(formPropertyName, FormatUtils
									.parseDecimal(source));
						} catch (ParseException e) {
							if (logger.isDebugEnabled()) {
								logger.debug("form." + formPropertyName
										+ " 为不合法的数字型字符串 :: " + source);
							}
						}
					} else if (logger.isWarnEnabled()) {
						logger
								.warn("vo." + formPropertyName
										+ " voPropertyClass为不能分析处理的类型 :: "
										+ voPropertyClass.getName());
					}
				} else if (formPropertyClass.equals(int.class)) {
					vobw.setPropertyValue(formPropertyName, formProperty);
				} else if (formPropertyClass.equals(boolean.class)) {
					Boolean b = (Boolean) formProperty;
					Integer i = new Integer(b.booleanValue() ? 1 : 0);
					vobw.setPropertyValue(formPropertyName, i);
				} else if (logger.isWarnEnabled()) {
					logger.warn("form." + formPropertyName + " 为不能分析处理的类型 :: "
							+ formPropertyClass.getName());
				}

			}
		}
	}

	/**
	 * 将vo中的数据拷贝到form中
	 * 
	 * @param vo
	 * @param form
	 */
	public static void setFormByVO(Object vo, FHIForm form) {
		BeanWrapper vobw = new BeanWrapperImpl(vo);
		BeanWrapper formbw = new BeanWrapperImpl(form);
		PropertyDescriptor[] vopd = vobw.getPropertyDescriptors();
		for (int i = 0; i < vopd.length; i++) {
			setFormProperty(vopd[i].getName(), vobw, formbw, vopd[i]);
		}
	}

	/**
	 * 具体将一个vo中的属性放到form中
	 * 
	 * 1.两边都为String,int时正常拷贝<br>
	 * 2.vo中为Long时,转换为字符串拷贝到form中，当Long为空时拷贝空字符串到form中<br>
	 * 3.vo中为Date时，按照format.date格式化到form中，当Date为null时拷贝空字符串，注意当form中需要format.time的格式的时候，应该在转换后为该属性单独做处理<br>
	 * 4.
	 * 
	 * @param voPropertyname
	 * @param vobw
	 * @param formbw
	 * @param vopd
	 */
	@SuppressWarnings("unchecked")
	private static void setFormProperty(String voPropertyname,
			BeanWrapper vobw, BeanWrapper formbw, PropertyDescriptor vopd) {
		if (formbw.isWritableProperty(voPropertyname)) {
			Object voProperty = vobw.getPropertyValue(voPropertyname);
			Class voPropertyClass = vopd.getPropertyType();
			if (voPropertyClass.equals(String.class)) {
				formbw.setPropertyValue(voPropertyname, voProperty);
			} else if (voPropertyClass.equals(Long.class)) {
				if (voProperty != null) {
					formbw.setPropertyValue(voPropertyname, voProperty
							.toString());
				} else {
					formbw.setPropertyValue(voPropertyname, StringUtils.EMPTY);
				}
			}else if (voPropertyClass.equals(Integer.class)) {
				if (voProperty != null) {
					formbw.setPropertyValue(voPropertyname, voProperty
							.toString());
				} else {
					formbw.setPropertyValue(voPropertyname, StringUtils.EMPTY);
				}
			} else if (voPropertyClass.equals(int.class)) {
				Class formPropertyClass = formbw
						.getPropertyType(voPropertyname);
				if (formPropertyClass.equals(int.class)) {
					formbw.setPropertyValue(voPropertyname, voProperty);
				} else if (formPropertyClass.equals(boolean.class)) {
					Integer i = (Integer) voProperty;
					if (i != null && i.intValue() == 1) {
						formbw.setPropertyValue(voPropertyname, new Boolean(
								true));
					} else {
						formbw.setPropertyValue(voPropertyname, new Boolean(
								false));
					}
				}
			} else if (voPropertyClass.equals(Date.class)) {
				if (voProperty != null) {
					String propertyDateValue;
					if (dateType(voPropertyname) == Types.DATE) {
						propertyDateValue = FormatUtils
								.formatDate((Date) voProperty);
					} else {
						propertyDateValue = FormatUtils
								.formatTime((Date) voProperty);
					}
					formbw.setPropertyValue(voPropertyname, propertyDateValue);
				} else {
					formbw.setPropertyValue(voPropertyname, StringUtils.EMPTY);
				}
			} else if (voPropertyClass.equals(BigDecimal.class)) {
				if (voProperty != null) {
					formbw.setPropertyValue(voPropertyname, FormatUtils
							.formatDecimal((BigDecimal) voProperty));
				} else {
					formbw.setPropertyValue(voPropertyname, StringUtils.EMPTY);
				}
			} else if (logger.isWarnEnabled()) {
				logger.warn("vo." + voPropertyname + " 为不能分析处理的类型 :: "
						+ voPropertyClass.getName());
			}

		}
	}

	/**
	 * 
	 * @return
	 */
	public static int dateType(String name) {		
			String last4Char = name.substring(name.length() - 4);
			if (StringUtils.equals(last4Char, "Time")) {
				return Types.TIMESTAMP;
			} else {
				return Types.DATE;
			}
	}

	 

	 

	/**
	 * 是否为法定休息日 不同地域根据国别而定
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isWorkDay(Date date, Locale locale) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return isWorkDay(c, locale);
	}

	public static boolean isWorkDay(Calendar date, Locale locale) {
		switch (date.get(Calendar.DAY_OF_WEEK)) {
		case Calendar.SATURDAY:
		case Calendar.SUNDAY:
			return false;
		default:
			return true;
		}
	}

	/**
	 * 是否为法定休息日
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isWorkDay(Date date) {
		return isWorkDay(date, Locale.getDefault());
	}
	/**
	 * 得到当前系统时间
	 * @return
	 */
	public static Date getTime(){
		return Calendar.getInstance().getTime();
	}
}
