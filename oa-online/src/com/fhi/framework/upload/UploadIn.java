package com.fhi.framework.upload;

import com.fhi.framework.upload.vo.FhiFile;

public interface UploadIn {
	/**
	 * 文件删除 
	 * @param fileName
	 * @return
	 */
	public int deleteFile(String fileName);
	
	/**
	 * 文件存在检查
	 * @param fileName
	 * @return
	 */
	public boolean existsFile(String fileName);
	
	/**
	 * 获取上传文件夹名称
	 * @return
	 */
	public String getSaveDirectoryName();
	
	/**
	 * 获取实际上传路径
	 * @return
	 */
	public String getFilePath();
	
	/**
	 * 获取允许上传大小
	 * @return
	 */
	public int getMaxPostSize();
	
	/**
	 * 文件保存
	 * @param file
	 * @return
	 */
	public boolean saveFhiFile(FhiFile file);
	
	/**
	 * 从数据库中查询文件信息
	 * @param fileID
	 * @return
	 */
	public FhiFile getFhiFile(String fileID,boolean upDownCount);
	
	/**
	 * 生成缩略图
	 * @param strId
	 * @return
	 */
//	public String photoTag(String strId,Integer maxWideth,Integer maxHeight);
}
