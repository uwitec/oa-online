package com.fhi.information.document.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.fhi.framework.config.FhiConfig;
import com.fhi.information.document.condition.DocumentCondition;
import com.fhi.information.document.form.DocumentForm;
import com.fhi.information.document.service.DocumentIn;
import com.fhi.information.document.vo.FhiDocument;
import com.fhi.permission.service.PermissionIn;
import com.fhi.system.classification.service.ClassIn;
import com.fhi.system.service.SystemIn;
import com.fhi.user.vo.FhiUser;

public class DocumentAction extends DispatchAction {

//	private WmsUser user;
	private static Logger logger = Logger.getLogger(DocumentAction.class);
	private DocumentForm documentForm;
	private DocumentIn documentService;
	private FhiUser user;
	private DocumentCondition documentCondition;
	private ClassIn classService;
	private SystemIn systemService;
	private PermissionIn permissionImple;
	
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
		
		
		documentForm = (DocumentForm) form;
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
				FhiConfig.Document_Info);
		String id = (String) request.getSession().getAttribute(
				FhiConfig.Document_Alter_Id);
		request.setAttribute(FhiConfig.Document_Info, info);
		request.setAttribute(FhiConfig.Document_Alter_Id, id);
		request.getSession().removeAttribute(FhiConfig.Document_Info);
		request.getSession().removeAttribute(FhiConfig.Document_Alter_Id);
		
		documentCondition.queryManagerIndex(documentForm, user);
		documentForm.setList(documentService.query(documentCondition));
		
		//传递子分类信息
		request.setAttribute("classList",classService.queryChild(FhiConfig.Class_Document, documentForm.getDocm().getClassId()));
		//传递当前位置信息
		request.setAttribute("currentList",classService.queryParent(FhiConfig.Class_Document, documentForm.getDocm().getClassId()));
		
		request.setAttribute(FhiConfig.PAGE_INFO_NAME, documentCondition.getPageInfo());
		request.setAttribute("form", documentForm);
		return mapping.findForward("index");
	}
	
	
	/**
	 * 文档查询入口
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
		// 提示信息的接收 页面传值 后续处理
		String info = (String) request.getSession().getAttribute(
				FhiConfig.Document_Info);
		String id = (String) request.getSession().getAttribute(
				FhiConfig.Document_Alter_Id);
		request.setAttribute(FhiConfig.Document_Info, info);
		request.setAttribute(FhiConfig.Document_Alter_Id, id);
		request.getSession().removeAttribute(FhiConfig.Document_Info);
		request.getSession().removeAttribute(FhiConfig.Document_Alter_Id);
		
		documentCondition.queryIndex(documentForm, user);
		documentForm.setList(documentService.query(documentCondition));
		
		//传递子分类信息
		request.setAttribute("classList",classService.queryChild(FhiConfig.Class_Document, documentForm.getDocm().getClassId()));
		//传递当前位置信息
		request.setAttribute("currentList",classService.queryParent(FhiConfig.Class_Document, documentForm.getDocm().getClassId()));
		
		request.setAttribute(FhiConfig.PAGE_INFO_NAME, documentCondition.getPageInfo());
		request.setAttribute("form", documentForm);
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
		FhiDocument docm = documentForm.getDocm();
		docm.setAutoCode(systemService.isAutoCode(FhiConfig.AutoCode_Document));		
		if(docm.getId()!=null){
			documentForm.setDocm((FhiDocument) documentService.load(FhiDocument.class,docm.getId()));
		}
		documentForm.setRoleList(permissionImple.getAllRole());
		
		request.setAttribute("form", documentForm);
		return mapping.findForward("load");
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
	public ActionForward showLoad(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		FhiDocument docm = documentForm.getDocm();
		
		if(docm.getId()!=null){
			documentForm.setDocm((FhiDocument) documentService.load(FhiDocument.class,docm.getId()));
		}
		
		request.setAttribute("form", documentForm);
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
		FhiDocument docm = documentForm.getDocm();
		docm.setCreater(user.getUserId());
		docm.setCreateDate(new Date());
		docm.setCreatorName(user.getEmployeeClass().getName());
		docm.setDepName(user.getEmployeeClass().getDepName());
		if(docm.getId().isEmpty()){
			logger.info("添加新文档信息，用户："+user.getUserId()+",标题："+docm.getTitle());
			docm.setId(java.util.UUID.randomUUID().toString());
			if(systemService.isAutoCode(FhiConfig.AutoCode_Document)){
				docm.setCode(systemService.getCode(FhiConfig.AutoCode_Document, "FhiDocument", "code"));
			}
			documentService.save(docm);
		}else{
			logger.info("修改文档信息，用户："+user.getUserId()+",标题："+docm.getTitle());
			documentService.update(docm);
		}
		request.getSession().setAttribute(FhiConfig.Document_Alter_Id,docm.getId());
		request.getSession().setAttribute(FhiConfig.Document_Info,"文档信息保存成功！");
		return mapping.findForward("main");
	}

	public void setDocumentService(DocumentIn documentService) {
		this.documentService = documentService;
	}

	public void setDocumentCondition(DocumentCondition documentCondition) {
		this.documentCondition = documentCondition;
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
}
