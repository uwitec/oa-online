package com.fhi.framework.utils;

import org.apache.struts.util.MessageResources;
import org.apache.struts.util.PropertyMessageResourcesFactory;

public class ConstantUtils {
	private static final MessageResources CONSTANT_RES = PropertyMessageResourcesFactory
			.createFactory().createResources("com.fhi.framework.resource.constant");

	public static String lookup(String key) {
		return CONSTANT_RES.getMessage(key);
	}
}
