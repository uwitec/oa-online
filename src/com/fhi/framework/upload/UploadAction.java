//@version $Id: UploadAction.java,v 1.35 2011/02/21 06:32:11 he Exp $

package com.fhi.framework.upload;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.fhi.framework.config.FhiConfig;
import com.fhi.framework.upload.vo.FhiFile;
import com.fhi.framework.utils.JpgTag;
import com.fhi.user.vo.FhiUser;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

public class UploadAction extends DispatchAction {
	private static Logger logger = Logger.getLogger(UploadAction.class);
	private int maxPostSize;
	private String saveDirectoryName;
	private UploadIn uploadService;
	private FhiUser user;

	/**
	 * 初始化访问
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.maxPostSize = uploadService.getMaxPostSize();
		this.saveDirectoryName = uploadService.getSaveDirectoryName();
		user = (FhiUser) request.getSession().getAttribute(FhiConfig.USER_NAME);
		// 禁止网页缓存
		response.setHeader("progma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html;charset=utf-8");
		return super.execute(mapping, form, request, response);
	}

	/**
	 * 文件上传
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward upload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (user == null) {
			logger.warn("用户未登录，文件上传失败！");
			return mapping.findForward("login");
		}

		String fileName = "";
		String fileID = "";
		// 读取文件大小
		int dateLength = request.getContentLength();
		if (dateLength == -1) {
			logger.debug("无文件上传！");
			fileName = "0";
		} else if (dateLength > maxPostSize * 1024 * 1024) {
			logger.warn("上传文件过大！");
			fileName = "1";
		} else {
			try {
				// 拼装上传路径
				String saveDirectory = request.getSession().getServletContext()
						.getRealPath("/" + this.saveDirectoryName);
				// 如果路径不存在 创建路径
				File dir = new File(saveDirectory);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				// 每个文件最大5m,所以总文件大小为(以字节为单位)
				int maxPostSize = this.maxPostSize * 1024 * 1024;
				// response的编码为UTF-8,
				MyFileRenamePolicy filePolicy = new MyFileRenamePolicy();
				MultipartRequest multi = new MultipartRequest(request,
						saveDirectory, maxPostSize, "UTF-8", filePolicy);
				// 输出反馈信息
				Enumeration files = multi.getFileNames();

				String name = (String) files.nextElement();
				File f = multi.getFile(name);
				FhiFile fhiFile = filePolicy.getFhiFile();
				fileName = fhiFile.getFileName();

				if (f != null && fhiFile != null) {
					fileID = multi.getFilesystemName(name);
					logger.info("文件上传成功，文件名：" + fileName + "，文件路径："
							+ saveDirectory + "\\" + fileID);
					fhiFile.setCreator(user.getUserId());
					if (!uploadService.saveFhiFile(fhiFile)) {
						fileName = "-1";
						logger.error("文件数据库记录失败！ID:" + fileID + ",name:"
								+ fileName + ",user:" + user.getUserId());
					}
				} else {
					fileName = "-1";
					logger.error("名称获取失败，上传失败！ID:" + fileID + ",name:"
							+ fileName + ",user:" + user.getUserId());
				}
			} catch (IOException e) {
				fileName = "-1";
				logger.error("上传失败！ID:" + fileID + ",name:" + fileName
						+ ",user:" + user.getUserId(), e);
			}
		}
		request.setAttribute("fileName", fileName);
		request.setAttribute("fileID", fileID);
		return mapping.findForward("upload");
	}

	/**
	 * SWF文件上传 返回obj{ fileID:String fileName:String errMsg:String ---$[L]
	 * 未登录；$[E] 上传失败；$[D] 数据库添加失败；$[B] 文件过大 } 最后4位状态：$[L] 未登录；$[E] 上传失败；$[D]
	 * 数据库添加失败；$[B] 文件过大
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward swfupload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// response.reset();//清空输出流
		String fileName = "";
		String fileID = "";
		String errMsg = "";
		String mainid = request.getParameter("mainid") ;  
		if(mainid==null || mainid.trim().equals(""))
			mainid = "" ; 
		try {
			//int maxPostSize_ = Integer.parseInt(request.getParameter("maxPostSize"));
			//maxPostSize = maxPostSize_;
			if(request.getParameter("maxPostSize")!=null)
				maxPostSize = Integer.parseInt(request.getParameter("maxPostSize"));  
		} catch (Exception e1) {
			logger.error("文件上传获取大小出错！", e1);
		}

		if (user == null) {
			errMsg = "$[L]";
			logger.warn("用户未登录，文件上传失败！");
			response.getWriter().println(
					"{id:'" + fileID + "',name:'" + fileName + "',errMsg:'"
							+ errMsg + "'}");
			return null;
		}

		// 读取文件大小
		int dateLength = request.getContentLength();
		if (dateLength == -1) {
			logger.debug("无文件上传！");
			errMsg = "$[N]";
		} else if (dateLength > maxPostSize * 1024 * 1024) {
			logger.warn("上传文件过大！");
			errMsg = "$[B]";
		} else {
			try {
				// 拼装上传路径
				String saveDirectory = request.getSession().getServletContext()
						.getRealPath("/" + this.saveDirectoryName);
				// 如果路径不存在 创建路径
				File dir = new File(saveDirectory);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				// 每个文件最大5m,所以总文件大小为(以字节为单位)
				int maxPostSize = this.maxPostSize * 1024 * 1024;
				// response的编码为UTF-8,
				MyFileRenamePolicy filePolicy = new MyFileRenamePolicy();
				MultipartRequest multi = new MultipartRequest(request,
						saveDirectory, maxPostSize, "UTF-8", filePolicy);
				// 输出反馈信息
				Enumeration files = multi.getFileNames();

				String name = (String) files.nextElement();
				File f = multi.getFile(name);
				FhiFile fhiFile = filePolicy.getFhiFile();
				fileName = fhiFile.getFileName();
				fileID = fhiFile.getFileId();
				if (f != null && fhiFile != null) {
					// fileID = multi.getFilesystemName(name);
					logger.info("文件上传成功，文件名：" + fileName + "，文件路径："
							+ saveDirectory + "\\"
							+ multi.getFilesystemName(name));
					fhiFile.setCreator(user.getUserId());
					
					if(!mainid.equals("")) //将成本单主单id作为分类加入文件管理的（fileType）类型字段
						fhiFile.setFileType(mainid);
					
					if (!uploadService.saveFhiFile(fhiFile)) {
						errMsg = "$[D]";
						logger.error("文件数据库记录失败！ID:" + fileID + ",name:"
								+ fileName + ",user:" + user.getUserId());
					}
				} else {
					errMsg = "$[E]";
					logger.error("名称获取失败，上传失败！ID:" + fileID + ",name:"
							+ fileName + ",user:" + user.getUserId());
				}
			} catch (IOException e) {
				logger.error("上传失败,用户停止上传！ID:" + fileID + ",name:" + fileName
						+ ",user:" + user.getUserId());
			}
		}
		response.getWriter().println(
				"{id:'" + fileID + "',name:'" + fileName + "',errMsg:'"
						+ errMsg + "'}");
		return null;
	}

	/**
	 * 文件下载
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward download(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String fileID = request.getParameter("fileID");
		String fileName = request.getParameter("fileName");

		logger.debug("文件下载ID：" + fileID);
		if (fileID == null || "".equals(fileID)) {
			logger.debug("文件下载无ID，下载失败！");
			request.setAttribute("message", "请正确使用本系统！");
			return mapping.findForward("message");
		}
		// 获取文件数据
		FhiFile fhiFile = uploadService.getFhiFile(fileID.trim(),true);

		if (fhiFile == null) {
			logger.error("数据库查询错误，下载失败！");
			request.setAttribute("message", "文件不存在或已删除！");
			return mapping.findForward("message");
		}
		if (fileName != null && !"".equals(fileName)) {
			fileName = new String(fileName.getBytes("ISO-8859-1"), "GBK");// 文件名
		} else {
			fileName = fhiFile.getFileName();
		}

		String filePath = request.getSession().getServletContext().getRealPath(
				"/" + this.saveDirectoryName);
		filePath = new String(filePath.getBytes("ISO-8859-1"), "UTF-8");// 路径处理

		// 文件地址拼装
		if (fhiFile.getFileExt() != null) {
			filePath = filePath + "\\" + fileID + fhiFile.getFileExt();
		} else {
			filePath = filePath + "\\" + fileID;
		}
		// 文件提取
		File file = new File(filePath);
		if (!file.exists()) {
			logger.error("文件不存在，下载失败！filePath:" + filePath);
			request.setAttribute("message", "文件不存在或已删除！");
			return mapping.findForward("message");
		}
		response.reset();// 清空输出流

		if (filePath.endsWith(".doc") || filePath.endsWith(".rtf")) {
			response.setContentType("application/msword");
			response.setHeader("Content-disposition", "inline; filename="
					+ URLEncoder.encode(fileName, "UTF-8"));
		} else if (filePath.endsWith(".pdf")) {
			response.setContentType("application/pdf");
			response.setHeader("Content-disposition", "inline; filename="
					+ URLEncoder.encode(fileName, "UTF-8"));
		} else if (filePath.endsWith(".xls")) {
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "inline; filename="
					+ URLEncoder.encode(fileName, "UTF-8"));
		} else {
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-disposition", "attachment; filename="
					+ URLEncoder.encode(fileName, "UTF-8"));
		}

		logger.debug("文件查找成功,fileName:" + fileName + ",Path:" + file.getPath());
		OutputStream out = null;
		try {
			BufferedInputStream br = new BufferedInputStream(
					new FileInputStream(file));

			byte[] buf = new byte[8192];
			int length = 0;
			out = response.getOutputStream();

			while ((length = br.read(buf)) > 0) {
				out.write(buf, 0, length);
			}

		} catch (FileNotFoundException e1) {
			logger.error("文件不存在，下载失败！");
			request.setAttribute("message", "文件不存在或已删除！");
			return mapping.findForward("message");
		} catch (Exception e) {
			logger.debug("用户取消下载，下载失败！");
		} finally {
			out.close();
		}
		return null;

	}

	/**
	 * 文件下载
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward picDownload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String fileID = request.getParameter("fileID");
		String fileName = request.getParameter("fileName");

		String picWidth = request.getParameter("w");
		String picHight = request.getParameter("h");

		logger.debug("文件下载ID：" + fileID);
		if (fileID == null || "".equals(fileID)) {
			logger.debug("文件下载无ID，下载失败！");
			request.setAttribute("message", "请正确使用本系统！");
			return mapping.findForward("message");
		}
		// 获取文件数据
		FhiFile fhiFile = uploadService.getFhiFile(fileID.trim(),false);

		if (fhiFile == null) {
			logger.error("数据库查询错误，下载失败！");
			request.setAttribute("message", "文件不存在或已删除！");
			return mapping.findForward("message");
		}
		if (fileName != null && !"".equals(fileName)) {
			fileName = new String(fileName.getBytes("ISO-8859-1"), "GBK");// 文件名
		} else {
			fileName = fhiFile.getFileName();
		}

		String filePath = request.getSession().getServletContext().getRealPath(
				"/" + this.saveDirectoryName);
		filePath = new String(filePath.getBytes("ISO-8859-1"), "UTF-8");// 路径处理

		// 文件地址拼装
		if (fhiFile.getFileExt() != null) {
			filePath = filePath + "\\" + fileID + fhiFile.getFileExt();
		} else {
			filePath = filePath + "\\" + fileID;
		}
		// 文件提取
		File file = new File(filePath);
		if (!file.exists()) {
			logger.error("文件不存在，下载失败！filePath:" + filePath);
			request.setAttribute("message", "文件不存在或已删除！");
			return mapping.findForward("message");
		}
		response.reset();// 清空输出流


		response.setContentType("application/x-msdownload");
		response.setHeader("Content-disposition", "attachment; filename="
				+ URLEncoder.encode(fileName, "UTF-8"));

		logger.debug("文件查找成功,fileName:" + fileName + ",Path:" + file.getPath());
		
		File file_temp = JpgTag.downloadPic(file , picWidth, picHight);
		
		OutputStream out = null;
		try {
			BufferedInputStream br = new BufferedInputStream(
					new FileInputStream(file_temp ));

			byte[] buf = new byte[8192];
			int length = 0;
			out = response.getOutputStream();

			while ((length = br.read(buf)) > 0) {
				out.write(buf, 0, length);
			}

		} catch (FileNotFoundException e1) {
			logger.error("文件不存在，下载失败！");
			request.setAttribute("message", "文件不存在或已删除！");
			return mapping.findForward("message");
		} catch (Exception e) {
			logger.debug("用户取消下载，下载失败！");
		} finally {
			out.close();
		}
		return null;
	}

	/**
	 * 上传文件大小限制 默认5MB
	 * 
	 * @param maxPostSize
	 */
	public void setMaxPostSize(int maxPostSize) {
		this.maxPostSize = maxPostSize;
	}

	public void setUploadService(UploadIn uploadService) {
		this.uploadService = uploadService;
	}

	public void setSaveDirectoryName(String saveDirectoryName) {
		this.saveDirectoryName = saveDirectoryName;
	}

	/**
	 * 文件重命名规则
	 * 
	 * @author admin
	 * 
	 */
	class MyFileRenamePolicy implements FileRenamePolicy {
		private FhiFile fhiFile;

		public File rename(File file) {
			String fileID = (new Date()).getTime()
					+ java.util.UUID.randomUUID().toString().substring(0, 7);
			String ext = "";
			int pot = file.getName().lastIndexOf(".");
			if (pot != -1) {
				ext = file.getName().substring(pot).toLowerCase();
			}
			fhiFile = new FhiFile(fileID, file.getName(), ext);
			file = new File(file.getParent(), fileID + ext);
			return file;
		}

		public FhiFile getFhiFile() {
			return fhiFile;
		}
	}
}
