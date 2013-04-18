package com.fhi.information.picture.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.fhi.framework.config.FhiConfig;
import com.fhi.framework.upload.UploadIn;
import com.fhi.information.picture.condition.PictureCondition;
import com.fhi.information.picture.form.PictureForm;
import com.fhi.information.picture.service.PictureIn;
import com.fhi.information.picture.vo.FhiPicture;
import com.fhi.permission.service.PermissionIn;
import com.fhi.system.classification.service.ClassIn;
import com.fhi.system.service.SystemIn;
import com.fhi.user.vo.FhiUser;

public class PictureAction extends DispatchAction {

//	private WmsUser user;
	private static Logger logger = Logger.getLogger(PictureAction.class);
	private PictureForm pictureForm;
	private PictureIn pictureService;
	private FhiUser user;
	private PictureCondition pictureCondition;
	private ClassIn classService;
	private SystemIn systemService;
	private PermissionIn permissionImple;
	private UploadIn uploadService;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		user = (FhiUser) request.getSession().getAttribute(FhiConfig.USER_NAME);
		if (user == null) {
			return mapping.findForward("login");
		}
		
//		user=new FhiUser();
//		user.setName("管理员");
//		user.setUserId("Admin");
//		Map<String, FhiRole>  map=new HashMap<String, FhiRole> ();
//		FhiRole role=new FhiRole();
//		role.setId("402881a9260671b6012606723ecc0003");
//		role.setPid("4028818d2602da21012602daa7350001");
//		role.setRoleName("软件开发员");
//		map.put(role.getId(), role);	
//		user.setRole(map);
//		logger.debug("手动设置角色ID："+role.getId());
//		request.getSession().setAttribute(FhiConfig.USER_NAME, user);
		
		
		pictureForm = (PictureForm) form;
		//禁止网页缓存
		response.setHeader("progma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html;charset=utf-8");
		
		return super.execute(mapping, form, request, response);
	}

	/**
	 * 文档管理入口
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward index(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 提示信息的接收 页面传值 后续处理
		String info = (String) request.getSession().getAttribute(
				FhiConfig.Picture_Info);
		String id = (String) request.getSession().getAttribute(
				FhiConfig.Picture_Alter_Id);
		request.setAttribute(FhiConfig.Picture_Info, info);
		request.setAttribute(FhiConfig.Picture_Alter_Id, id);
		request.getSession().removeAttribute(FhiConfig.Picture_Info);
		request.getSession().removeAttribute(FhiConfig.Picture_Alter_Id);
		
		pictureCondition.queryManagerIndex(pictureForm, user);
		pictureForm.setList(pictureService.query(pictureCondition,18));
		
		//传递子分类信息
		request.setAttribute("classList",classService.queryChild(FhiConfig.Class_Picture, pictureForm.getPic().getClassId()));
		//传递当前位置信息
		request.setAttribute("currentList",classService.queryParent(FhiConfig.Class_Picture, pictureForm.getPic().getClassId()));
		
		request.setAttribute(FhiConfig.PAGE_INFO_NAME, pictureCondition.getPageInfo());
		request.setAttribute("form", pictureForm);
		return mapping.findForward("index");
	}
	
	/**
	 * 相册展示入口
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showIndex(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		pictureCondition.queryIndex(pictureForm, user);
		pictureForm.setList(pictureService.query(pictureCondition,18));
		//传递子分类信息
		request.setAttribute("classList",classService.queryChild(FhiConfig.Class_Picture, pictureForm.getPic().getClassId()));
		//传递当前位置信息
		request.setAttribute("currentList",classService.queryParent(FhiConfig.Class_Picture, pictureForm.getPic().getClassId()));
		
		request.setAttribute(FhiConfig.PAGE_INFO_NAME, pictureCondition.getPageInfo());
		request.setAttribute("form", pictureForm);
		return mapping.findForward("showIndex");
	}
	
	/**
	 * 文档管理载入页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward load(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		FhiPicture pic = pictureForm.getPic();
		pic.setAutoCode(systemService.isAutoCode(FhiConfig.AutoCode_Picture));		
		if(pic.getId()!=null){
			pictureForm.setPic((FhiPicture) pictureService.load(FhiPicture.class,pic.getId()));
		}
		pictureForm.setRoleList(permissionImple.getAllRole());
		
		request.setAttribute("form", pictureForm);
		return mapping.findForward("load");
	}
	/**
	 * 相册展示页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showLoad(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		FhiPicture pic = pictureForm.getPic();
		if(pic.getId()!=null){
			pictureForm.setPic((FhiPicture) pictureService.load(FhiPicture.class,pic.getId()));
		}		
		request.setAttribute("form", pictureForm);
		return mapping.findForward("showLoad");
	}
	
	
	/**
	 * 
	 * 文档管理保存页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FhiPicture pic = pictureForm.getPic();
		pic.setCreater(user.getUserId());
		pic.setCreateDate(new Date());
		pic.setCreatorName(user.getEmployeeClass().getName());
		//pic.setCover(uploadService.photoTag(pic.getCover(),80,80));
		if(pic.getId().isEmpty()){
			logger.info("添加新相册，用户："+user.getUserId()+",标题："+pic.getTitle());
			pic.setId(java.util.UUID.randomUUID().toString());
			if(systemService.isAutoCode(FhiConfig.AutoCode_Picture)){
				pic.setCode(systemService.getCode(FhiConfig.AutoCode_Picture, "FhiPicture", "code"));
			}
			pictureService.save(pic);
		}else{
			logger.info("修改相册，用户："+user.getUserId()+",标题："+pic.getTitle());
			pictureService.update(pic);
		}		
		
		
		request.getSession().setAttribute(FhiConfig.Picture_Alter_Id,pic.getId());
		request.getSession().setAttribute(FhiConfig.Picture_Info,"文档信息保存成功！");
		return mapping.findForward("main");
	}	

	public void setPictureService(PictureIn pictureService) {
		this.pictureService = pictureService;
	}

	public void setPictureCondition(PictureCondition pictureCondition) {
		this.pictureCondition = pictureCondition;
	}

	public void setClassService(ClassIn classService) {
		this.classService = classService;
	}

	public void setSystemService(SystemIn systemService) {
		this.systemService = systemService;
	}

	public void setPermissionImple(PermissionIn permissionImple) {
		this.permissionImple = permissionImple;
	}

	public void setUploadService(UploadIn uploadService) {
		this.uploadService = uploadService;
	}	
}
