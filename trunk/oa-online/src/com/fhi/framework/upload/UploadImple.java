package com.fhi.framework.upload;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;

import com.fhi.framework.abstractservice.AbstractServiceImple;
import com.fhi.framework.upload.vo.FhiFile;

public class UploadImple extends AbstractServiceImple implements UploadIn {

	private static Logger logger = Logger.getLogger(UploadImple.class);
	private int maxPostSize = 500;
	private String saveDirectoryName = "upload";

	/**
	 * 文件保存
	 * 
	 * @param file
	 * @return
	 */
	public boolean saveFhiFile(FhiFile file) {
		try {
			return this.dbDao.addObject(file);
		} catch (Exception e) {
			logger.error("上传文件保存失败！", e);
		}
		return false;
	}

	/**
	 * 从数据库中查询文件信息
	 * 
	 * @param fileID
	 * @return
	 */
	public FhiFile getFhiFile(String fileID, boolean upDownCount) {
		try {
			FhiFile fhiFile = (FhiFile) this.dbDao.queryObjectById(
					FhiFile.class, fileID);
			if (upDownCount) {
				fhiFile.setDownloadCount(fhiFile.getDownloadCount() + 1);
				this.dbDao.updateObject(fhiFile);
			}
			return fhiFile;
		} catch (Exception e) {
			logger.error("文件查询失败！", e);
		}
		return null;
	}
	
	public String getFilesByType(String type) {
		if(type==null || type.trim().equals(""))
			return "";
		String ret = "" ;
		try { 
			String hql = " from FhiFile where fileType='"+type.trim()+"'" ;
			List<FhiFile> list = this.dbDao.queryObjects(hql);
			for(FhiFile one:list){
				ret += "{id:'"+one.getFileId()+"',name:'"+one.getFileName()+"',creator:'"+one.getCreator().trim()+"'}," ;
			} 
			if(!ret.equals("")){
				ret += "["+ret.substring(0,ret.length()-1)+"]";
			}
			return ret ;
		} catch (Exception e) {
			logger.error("文件查询失败！", e);
			return "";
		}
	}

	/**
	 * 文件删除
	 * 
	 * @param fileName
	 * @return
	 */
	public int deleteFile(String fileId) {
		try {
			FhiFile fhiFile = this.getFhiFile(fileId, false);
			//ogger.debug("删除文件：" + this.getFilePath() + fhiFile.getFileId()+fhiFile.getFileExt());
			File file = new File(this.getFilePath() + "\\" + fhiFile.getFileId()+fhiFile.getFileExt());			
			if (file.exists()) {
				if (this.dbDao.deleteObject(fhiFile)) {
					logger.debug("删除文件：" + fhiFile.getFileName());
					//return file.delete() ? 1 : 0;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * 文件存在检查
	 * 
	 * @param fileName
	 * @return
	 */
	public boolean existsFile(String fileID) {
		File file = new File(this.getFilePath() + fileID);
		return file.exists();
	}

	/**
	 * 获取允许上传大小
	 * 
	 * @return
	 */
	public int getMaxPostSize() {
		return maxPostSize;
	}

	public void setMaxPostSize(int maxPostSize) {
		this.maxPostSize = maxPostSize;
	}

	/**
	 * 获取上传文件夹名称
	 * 
	 * @return
	 */
	public String getSaveDirectoryName() {
		return this.saveDirectoryName;
	}

	/**
	 * 获取实际上传路径
	 * 
	 * @return
	 */
	public String getFilePath() {
		String filePath = System.getProperty("FhiOa.root")
				+ this.saveDirectoryName;
		logger.debug("文件上传路径：" + filePath);
		return filePath;
	}

	public void setSaveDirectoryName(String saveDirectoryName) {
		this.saveDirectoryName = saveDirectoryName;
	}
	//	
	// /**
	// * 生成缩略图
	// * @param strId
	// * @return
	// */
	// public String photoTag(String strId,Integer maxWideth,Integer maxHeight){
	// if(strId==null||"".equals(strId)){
	// return strId;
	// }
	// if(strId.indexOf("_tag")>0){
	// return strId;
	// }
	// int fileHave =
	// this.dbDao.count("from FhiFile where fileId='"+strId+"_tag'");
	// if(fileHave>0){
	// return strId+"_tag";
	// }
	// FhiFile file=this.getFhiFile(strId);
	// try {
	// JpgTag.JpgTag(this.getFilePath()+"/"+file.getFileId()+file.getFileExt(),
	// maxWideth,maxHeight);
	// file.setFileId(strId+"_tag");
	// this.dbDao.addObject(file);
	// return file.getFileId();
	// } catch (Exception e) {
	// logger.error("缩略图生成失败",e);
	// }
	// return "";
	// }
}
