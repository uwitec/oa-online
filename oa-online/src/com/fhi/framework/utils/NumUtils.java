package com.fhi.framework.utils;

import java.text.DecimalFormat;

public class NumUtils {	
	
	/**
	 * 
	 * @param obj
	 * @return format #.##
	 */
	public static String formatNum(Object obj){
		 DecimalFormat df = new DecimalFormat();
		 df.applyPattern("#.##");
		 return df.format(obj);
	}
	
	/**
	 * 
	 * @param obj
	 * @return format #.00
	 */
	public static String formatNumPoint(Object obj){
		 DecimalFormat df = new DecimalFormat();
		 df.applyPattern("0.00");
		 return df.format(obj);
	}
}
