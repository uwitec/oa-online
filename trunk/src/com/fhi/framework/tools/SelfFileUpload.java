package com.fhi.framework.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;

public class SelfFileUpload {

   private static Logger logger = Logger.getLogger(SelfFileUpload.class);	
   
   public String getOperatSystem(){
	   String os = System.getProperties().getProperty("os.name").toUpperCase();
	   if(os.indexOf("WINDOWS")!=-1)
		   return "WINDOWS" ;
	   else if(os.indexOf("LINUX")!=-1)
		   return "LINUX" ;
	   else if(os.indexOf("UNIX")!=-1)
		   return "UNIX" ;
	   else 
		   return "" ;
   }
   
   public String upload(InputStream in,String path,int size,String type){
	   byte []count = new byte[6000] ; 
	   try{  
           //判断操作系统
		   String newpath = path ;
		   String dirpath = "" ;
		   String os = "" ;
		   if(this.getOperatSystem().equals("WINDOWS")){
			   os = "WINDOWS" ;
			   newpath =  newpath.replace("/", "\\") ; 
			   dirpath = newpath.substring(0,newpath.lastIndexOf("\\")) ; 
		   }else{  
			   os = "LINUX" ;
			   newpath =  newpath.replace("\\", "/") ;  
			   dirpath = newpath.substring(0,newpath.lastIndexOf("/")) ;
		   }
		   
		   //检测文件类型
		   String ext = path.substring(path.lastIndexOf(".")) ;
		   if(type.indexOf(ext)==-1)
			   return "type" ; 
		    
		   //检测文件大小
		   if(in.available()>size){ 
			   return "size" ;
		   }  
		   
		   File dir = new File(dirpath) ; 
		   if(!dir.exists()){
			   dir.mkdir() ;
		   }  
		   File directFile = new File(newpath) ;   
		   OutputStream out =  new FileOutputStream(directFile) ;
		   while(in.read(count)>0){
			   out.write(count) ;
		   }  
		   
	   }catch(Exception e){
		   System.out.println(" 文件上传错误: "+e.getMessage()) ;
		   return "wrong" ;
	   }  
	   return "ok" ; 
   } 
   
   public boolean delete(String path){ 
	   try{  
		   if(path==null || path.trim().equals(""))
			   return true ;
		   
           //判断操作系统
		   String newpath = path ; 
		   if(this.getOperatSystem().equals("WINDOWS"))
			   newpath =  newpath.replace("/", "\\") ;  
		   else
			   newpath =  newpath.replace("\\", "/") ;    
		   new File(path).delete() ;   
	   }catch(Exception e){
		   System.out.println(" 文件删除错误: "+e.getMessage()) ;
		   return false ;
	   }  
	   return true ; 
   } 
   
   public String upload(File upfile,String path,int size,String type){
	   byte []count = new byte[6000] ; 
	   try{  
           //判断操作系统
		   String newpath = path ;
		   String dirpath = "" ;
		   if(this.getOperatSystem().equals("WINDOWS")){
			   newpath =  newpath.replace("/", "\\") ; 
			   dirpath = newpath.substring(0,newpath.lastIndexOf("\\")) ; 
		   }else{ 
			   newpath =  newpath.replace("\\", "/") ;  
			   dirpath = newpath.substring(0,newpath.lastIndexOf("/")) ;
		   }
		   
		   //检测文件类型
		   String ext = path.substring(path.lastIndexOf(".")) ;
		   if(type.indexOf(ext)==-1)
			   return "type" ; 
		   
		   InputStream in = new FileInputStream(upfile) ;
		   //检测文件大小
		   if(in.available()>size){ 
			   return "size" ;
		   }  
		   
		   File dir = new File(dirpath) ; 
		   if(!dir.exists()){
			   dir.mkdir() ;
		   }  
		   File directFile = new File(newpath) ;   
		   OutputStream out =  new FileOutputStream(directFile) ;
		   while(in.read(count)>0){
			   out.write(count) ;
		   } 
	   }catch(Exception e){
		   System.out.println(" 文件上传错误: "+e.getMessage()) ;
		   return "wrong" ;
	   }  
	   return "ok" ; 
   } 
}
