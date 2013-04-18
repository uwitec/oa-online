package com.fhi.framework.utils;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.ArrayUtils;



public class FormatUtils {
	private static DecimalFormat decimalf;

	private static SimpleDateFormat datef;

	private static SimpleDateFormat timef;

	private static SimpleDateFormat monthf;
	static {
		decimalf = new DecimalFormat(ConstantUtils.lookup("format.decimal"));
		datef = new SimpleDateFormat(ConstantUtils.lookup("format.date"));
		timef = new SimpleDateFormat(ConstantUtils.lookup("format.time"));
		monthf = new SimpleDateFormat(ConstantUtils.lookup("format.month"));
	}

	public static String formatDecimal(BigDecimal decimal) {
		return decimalf.format(decimal);
	}

	public static String formatNumber(double d) {
		return decimalf.format(d);
	}

	public static String formatDecimal(BigDecimal decimal, String parrten) {
		DecimalFormat format = new DecimalFormat(parrten);
		return format.format(decimal);
	}

	public static String formatNumber(double d, String parrten) {
		if (Double.isNaN(d) || Double.isInfinite(d)) {
			return String.valueOf(d);
		}
		DecimalFormat format = new DecimalFormat(parrten);
		return format.format(d);
	}

	public static BigDecimal parseDecimal(String decimal) throws ParseException {
		Number n = decimalf.parse(decimal);
		return new BigDecimal(n.toString());
	}

	public static String formatDate(Date date) {
		return datef.format(date);
	}

	public static String formatDate(Date date, int field, int amount) {
		return datef.format(addDate(date, field, amount));
	}

	public static String formatDate(int field, int amount) {
		return datef.format(addDate(field, amount));
	}

	public static Date addDate(Date date, int field, int amount) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(field, amount);
		return c.getTime();
	}

	public static Date addDate(int field, int amount) {
		Calendar c = Calendar.getInstance();
		c.add(field, amount);
		return c.getTime();
	}

	public static Date parseDate(String source) throws ParseException {
		return datef.parse(source);
	}

	public static String formatTime(Date date) {
		return timef.format(date);
	}
	
	public static String formatMonth(Date date) {
		return monthf.format(date);
	}

	public static Date parseTime(String source) throws ParseException {
		return timef.parse(source);
	}

	@SuppressWarnings("unchecked")
	public static String combine(Collection coll, String splitChar) {
		if (coll == null) {
			return null;
		}
		StringBuffer s = new StringBuffer();
		Iterator iter = coll.iterator();
		if (iter.hasNext()) {
			s.append(iter.next());
			while (iter.hasNext()) {
				s.append(splitChar);
				s.append(iter.next());
			}
		}
		return s.toString();
	}

	@SuppressWarnings("unchecked")
	public static String combine(Collection coll, String property, String splitChar)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		if (coll == null) {
			return null;
		}
		StringBuffer s = new StringBuffer();
		Iterator iter = coll.iterator();
		if (iter.hasNext()) {
			s.append(PropertyUtils.getNestedProperty(iter.next(), property));
			while (iter.hasNext()) {
				s.append(splitChar);
				s.append(PropertyUtils.getNestedProperty(iter.next(),property));
			}
		}
		return s.toString();
	}

	/**
	 * 
	 * 
	 * @param coll
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static int[] toIntArray(Collection coll) {
		Integer[] a = new Integer[coll.size()];
		coll.toArray(a);
		return ArrayUtils.toPrimitive(a);
	}

	@SuppressWarnings("unchecked")
	public static long[] toLongArray(Collection coll) {
		Long[] a = new Long[coll.size()];
		coll.toArray(a);
		return ArrayUtils.toPrimitive(a);
	}

	@SuppressWarnings("unchecked")
	public static double[] toDoubleArray(Collection coll) {
		Double[] a = new Double[coll.size()];
		coll.toArray(a);
		return ArrayUtils.toPrimitive(a);
	}
	
	public static String lastWorkDate() {
		return lastWorkDate(-1);
	}
	
	public static String lastWorkDate(int datedef) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, datedef);
		int day = c.get(Calendar.DAY_OF_WEEK);
		while (day == Calendar.SATURDAY || day == Calendar.SUNDAY) {
			c.add(Calendar.DATE, -1);
			day = c.get(Calendar.DAY_OF_WEEK);
		}
		return FormatUtils.formatDate(c.getTime());
	}
	
	public static String lastWorkDate(String datestr)throws ParseException {
		Date date = FormatUtils.parseDate(datestr);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, -1);
		int day = c.get(Calendar.DAY_OF_WEEK);
		while (day == Calendar.SATURDAY || day == Calendar.SUNDAY) {
			c.add(Calendar.DATE, -1);
			day = c.get(Calendar.DAY_OF_WEEK);
		}
		return FormatUtils.formatDate(c.getTime());
	}
	
	public static Date lastWorkDate1(String datestr)throws ParseException {
		Date date = FormatUtils.parseDate(datestr);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, -1);
		int day = c.get(Calendar.DAY_OF_WEEK);
		while (day == Calendar.SATURDAY || day == Calendar.SUNDAY) {
			c.add(Calendar.DATE, -1);
			day = c.get(Calendar.DAY_OF_WEEK);
		}
		return c.getTime();
	}	
	
	 
	
	 
}
