package com.fhi.framework.tools;

import javax.servlet.http.HttpServletRequest;
public class SelfToken {

	/*
	 * 判断token是否合法
	 */
	public static boolean isValid(HttpServletRequest request,String attr){
		
		String requestTokenValue = request.getParameter(attr) ;
		if(requestTokenValue==null) requestTokenValue = "" ;
		Object obj = request.getSession().getAttribute(attr) ;
		String sessionTokenValue = "" ;
		if(obj!=null)  
			sessionTokenValue = obj.toString() ; 
		if(!sessionTokenValue.equals("") && !requestTokenValue.equals("")){
			if(sessionTokenValue.trim().equals(requestTokenValue.trim())){
				return true ;
			}
		}else{
			request.getSession().setAttribute(attr,"") ;
			return false ;
		} 
		request.getSession().setAttribute(attr,"") ;
		return false ;
	}
	
    public static boolean isValid(HttpServletRequest request,String attr,boolean reset){
		
		String requestTokenValue = request.getParameter(attr) ;
		if(requestTokenValue==null) requestTokenValue = "" ;
		Object obj = request.getSession().getAttribute(attr) ;
		String sessionTokenValue = "" ;
		if(obj!=null)  
			sessionTokenValue = obj.toString() ; 
		if(!sessionTokenValue.equals("") && !requestTokenValue.equals("")){
			if(sessionTokenValue.trim().equals(requestTokenValue.trim())){
				request.getSession().setAttribute(attr,java.util.UUID.randomUUID().toString()) ;
				return true ; 
			}
		}else{
			request.getSession().setAttribute(attr,"") ;
			return false ;
		} 
		request.getSession().setAttribute(attr,"") ;
		return false ;
	}
}
