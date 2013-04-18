package com.fhi.framework.utils.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

public final class DataUtils {
	private static Logger logger = Logger.getLogger(DataUtils.class);
	private static SimpleDateFormat dateFormat = null;
	static {
		// 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// 设置lenient为false.
		// 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
		dateFormat.setLenient(false);
	}

	public static final String CurrentTime() {
		String curTime = "";
		// 格式化时间开始
		SimpleDateFormat formatter;
		java.util.Date currentDate = new java.util.Date();
		formatter = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
		currentDate = Calendar.getInstance().getTime();
		// 格式化时间结束
		curTime = formatter.format(currentDate);
		return curTime;
	}

	public static final String CurrentCodeYMD() {
		String curTime = "";
		// 格式化时间开始
		SimpleDateFormat formatter;
		java.util.Date currentDate = new java.util.Date();
		formatter = new SimpleDateFormat("yyMMdd");
		currentDate = Calendar.getInstance().getTime();
		// 格式化时间结束
		curTime = formatter.format(currentDate);
		return curTime;
	}

	public static final String CurrentCodeYM() {
		String curTime = "";
		// 格式化时间开始
		SimpleDateFormat formatter;
		java.util.Date currentDate = new java.util.Date();
		formatter = new SimpleDateFormat("yyMM");
		currentDate = Calendar.getInstance().getTime();
		// 格式化时间结束
		curTime = formatter.format(currentDate);
		return curTime;
	}
//
//	public static final String CurrentYMDTime() {
//		String curTime = "";
//		String curTime1 = "";
//		// 格式化时间开始
//		SimpleDateFormat formatter;
//		SimpleDateFormat formatter1;
//
//		java.util.Date currentDate = new java.util.Date();
//
//		formatter = new SimpleDateFormat("yyyy年MM月dd日");
//		currentDate = Calendar.getInstance().getTime();
//		// 格式化时间结束
//		curTime = formatter.format(currentDate);
//
//		formatter1 = new SimpleDateFormat("yyyy-MM-dd");
//		currentDate = Calendar.getInstance().getTime();
//		// 格式化时间结束
//		curTime1 = formatter1.format(currentDate);
//		SimpleDateFormat formatYMD = new SimpleDateFormat("yyyy-MM-dd");
//		// formatYMD表示的是yyyy-MM-dd格式
//		SimpleDateFormat formatD = new SimpleDateFormat("E");
//		// "E"表示"day in week"
//		Date d = null;
//		try {
//			d = formatYMD.parse(curTime1);
//			// 将String 转换为符合格式的日期
//			curTime1 = formatD.format(d);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return curTime + " " + curTime1;
//	}

	/**
	 * 判断字符串是否为yyyy-MM-dd型日期字符串
	 * 
	 * @param s
	 * @return boolean
	 */
	public static boolean isValidDate(String s) {
		try {
			dateFormat.parse(s);
			return true;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}

	/**
	 * 日期字符串转换<br>
	 * 支持：
	 * <br>yyyy-MM-dd HH:mm:ss
	 * <br>yyyy-MM-dd HH:mm
	 * <br>yyyy-MM-dd
	 * <br>yyyy/MM/dd
	 * @param datestr
	 * @return
	 */
	public static Date getDateByString(String datestr) {
		if(datestr==null||"".equals(datestr)){
			return null;
		}
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
		SimpleDateFormat fms = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat fmss = new SimpleDateFormat("yyyy-MM-dd");		
		SimpleDateFormat fmsss = new SimpleDateFormat("yyyy/MM/dd");
		try {
			return fm.parse(datestr);
		} catch (Exception e) {
			try {
				return fms.parse(datestr);
			} catch (Exception ex) {
				try {
					return fmss.parse(datestr);
				} catch (Exception exx) {					
					try {
						return fmsss.parse(datestr);
					} catch (Exception exxx) {					
						logger.error("时间转换出错:"+datestr,exx);
					}
				}
			}
		}
		return null;
	}

	/**
	 * 格式化输出日期
	 * 
	 * @param date
	 *            日期
	 * @param format
	 *            格式
	 * @return 返回字符型日期
	 */
	public static String format(java.util.Date date, String format) {
		String result = "";
		try {
			if (date != null) {
				java.text.DateFormat df = new java.text.SimpleDateFormat(format);
				result = df.format(date);
			}
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * format:yyyy/MM/dd
	 * @param date
	 * @return
	 */
	public static String format(java.util.Date date) {
		return format(date, "yyyy/MM/dd");
	}

	/**
	 * 返回年份
	 * 
	 * @param date
	 *            日期
	 * @return 返回年份
	 */
	public static int getYear(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.YEAR);
	}

	/**
	 * 返回月份
	 * 
	 * @param date
	 *            日期
	 * @return 返回月份
	 */
	public static int getMonth(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.MONTH) + 1;
	}

	/**
	 * 返回日份
	 * 
	 * @param date
	 *            日期
	 * @return 返回日份
	 */
	public static int getDay(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.DAY_OF_MONTH);
	}

	/**
	 * 返回小时
	 * 
	 * @param date
	 *            日期
	 * @return 返回小时
	 */
	public static int getHour(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.HOUR_OF_DAY);
	}

	/**
	 * 返回分钟
	 * 
	 * @param date
	 *            日期
	 * @return 返回分钟
	 */
	public static int getMinute(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.MINUTE);
	}

	/**
	 * 返回秒钟
	 * 
	 * @param date
	 *            日期
	 * @return 返回秒钟
	 */
	public static int getSecond(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.SECOND);
	}

	/**
	 * 返回毫秒
	 * 
	 * @param date
	 *            日期
	 * @return 返回毫秒
	 */
	public static long getMillis(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}

	/**
	 * 返回字符型日期
	 * 
	 * @param date
	 *            日期
	 * @return 返回字符型日期
	 */
	public static String getDate(java.util.Date date) {
		return format(date, "yyyy/MM/dd");
	}

	/**
	 * 返回字符型时间
	 * 
	 * @param date
	 *            日期
	 * @return 返回字符型时间
	 */
	public static String getTime(java.util.Date date) {
		return format(date, "HH:mm:ss");
	}

	/**
	 * 返回字符型日期时间
	 * 
	 * @param date
	 *            日期
	 * @return 返回字符型日期时间
	 * "yyyy-MM-dd  HH:mm:ss"
	 */
	public static String getDateTime(java.util.Date date) {
		return format(date, "yyyy-MM-dd  HH:mm:ss");
	}

	/**
	 * 日期相加
	 * 
	 * @param date
	 *            日期
	 * @param day
	 *            天数
	 * @return 返回相加后的日期
	 */
	public static java.util.Date addDate(java.util.Date date, int day) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + ((long) day) * 24 * 3600 * 1000);
		return c.getTime();
	}

	/**
	 * 日期相减
	 * 
	 * @param date
	 *            日期
	 * @param date1
	 *            日期
	 * @return 返回相减后的日期
	 */
	public static int diffDate(java.util.Date date, java.util.Date date1) {
		return (int) ((getMillis(date) - getMillis(date1)) / (24 * 3600 * 1000));
	}
}
