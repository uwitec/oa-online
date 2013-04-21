package com.fhi.permission.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.fhi.framework.config.FhiConfig;
import com.fhi.permission.condition.PermissionCondition;
import com.fhi.permission.form.PermissionForm;
import com.fhi.permission.service.PermissionIn;
import com.fhi.permission.vo.FhiModule;
import com.fhi.permission.vo.FhiRole;
import com.fhi.permission.vo.FhiRolePermission;
import com.fhi.user.vo.FhiUser;

public class PermissionAction extends DispatchAction implements FhiConfig{
	private static Logger logger = Logger.getLogger(PermissionAction.class);
	private FhiModule wmsmodule;
	private PermissionIn permissionImple;
	private PermissionCondition pcondition;
	
	public PermissionIn getPermissionImple() {
		return permissionImple;
	}
	public void setPermissionImple(PermissionIn permissionImple) {
		this.permissionImple = permissionImple;
	}

	public PermissionCondition getPcondition() {
		return pcondition;
	}
	public void setPcondition(PermissionCondition pcondition) {
		this.pcondition = pcondition;
	}
	
	public ActionForward newModule(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		PermissionForm per = (PermissionForm)form;
		wmsmodule = per.getLtsMo();
		if(wmsmodule.getModule()!=null&&!"".equals(wmsmodule.getModule())){
			wmsmodule.setPid(wmsmodule.getModule());
		}
		if(wmsmodule.getPid()!=null&&!"".equals(wmsmodule.getPid())){
			wmsmodule.setIsPid("2");
		}else{
			wmsmodule.setIsPid("1");
			//1 根目录
			//2 子目录
		}
		permissionImple.addModule(wmsmodule);
		return mapping.findForward("model");
	}
	
	public ActionForward getModel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
			List list = permissionImple.getAllMod(form);
			request.setAttribute("list", list);
			request.setAttribute("modList",permissionImple.getModel());
			request.setAttribute(FhiConfig.PAGE_INFO_NAME, pcondition.getPageInfo());
		return mapping.findForward("model");
	}
	
	public ActionForward getRoleId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
			if("1".equals(request.getParameter("isNew"))){
				FhiRole fr = new FhiRole();
				fr.setId(request.getParameter("id"));
				fr.setIsPid(request.getParameter("isPid"));
				request.setAttribute("roleId", fr);
				request.setAttribute("new", true);
			}else{
				FhiRole fr = (FhiRole)this.permissionImple.getRoleName(request.getParameter("id"));
				request.setAttribute("roleId", fr);
				request.setAttribute("new", false);
			}
		return mapping.findForward("addRole");
	}
	
	public ActionForward newRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		PermissionForm per = (PermissionForm)form;
		FhiRole addWmsRole = per.getLtsRo();
		if("1".equals(request.getParameter("isModify"))){
			this.permissionImple.updateRole(addWmsRole);
			response.getWriter().println("<script type='text/javascript'>");
			response.getWriter().println("window.parent.location.href = '"+request.getContextPath()+"/permission.do?method=permissionAdd&ltsRo.id="+addWmsRole.getId()+"&modify=1' ;");
			response.getWriter().println("</script>");
		}else{
			if("1".equals(addWmsRole.getIsPid())){
				permissionImple.updatePid(addWmsRole.getPid());
			}else{
				addWmsRole.setIsPid("1");
			}
			permissionImple.addRole(addWmsRole);
			response.getWriter().println("<script type='text/javascript'>");
			response.getWriter().println("window.parent.location.href = '"+request.getContextPath()+"/permission.do?method=permissionAdd&ltsRo.id="+addWmsRole.getId()+"' ;");
			response.getWriter().println("</script>");
		}
		return null;
	}
	
	public ActionForward permissionAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
			if("1".equals(request.getParameter("modify"))){
				PermissionForm per = (PermissionForm)form;
				FhiRole addWmsRole = per.getLtsRo();
				request.setAttribute("role", addWmsRole);
				/*List list = permissionImple.getAllPermissionById(addWmsRole.getId());
				request.setAttribute("list", list);*/
				request.setAttribute("modify", "1");
			}else{
				PermissionForm per = (PermissionForm)form;
				FhiRole addWmsRole = per.getLtsRo();
				request.setAttribute("role", addWmsRole);
				/*List list = permissionImple.getAllPermission();
				request.setAttribute("list", list);*/
			}
			return mapping.findForward("creatRoMo");
	}
	
	public Map getPermissionList(FhiUser ws) throws Exception{
		return permissionImple.findPermission(ws);
	}
	/*
	 * 返回模块信息生成页面
	 */
	
	public ActionForward getMod(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setAttribute("list",permissionImple.getAllMod((ActionForm)form));
		return mapping.findForward("mod");
	}
	
	
	public ActionForward getRole(ActionMapping mapping, ActionForm form,
	HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setAttribute("Role",permissionImple.getAllRole());
		return mapping.findForward("index");
	}
	
	public ActionForward delRole(ActionMapping mapping, ActionForm form,
	HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println(this.permissionImple.queryRoleUser(request.getParameter("id")));
		if(this.permissionImple.queryRoleUser(request.getParameter("id"))){
			this.permissionImple.delThisRole(request.getParameter("id"),request.getParameter("isPid"),request.getParameter("pid"));
		}else{
			System.out.println("删除失败");
		}
		request.setAttribute("Role",permissionImple.getAllRole());
		return mapping.findForward("index");
	}
	
	public ActionForward addRM(ActionMapping mapping, ActionForm form,
	HttpServletRequest request, HttpServletResponse response) throws Exception{
		PermissionForm asForm = (PermissionForm)form;
        List stocks = asForm.getStocks();
        if ("1".equals(request.getParameter("isModify"))){
        	this.permissionImple.delRolePermissionByCode(stocks);
        	for (int i=0; i<stocks.size() ;i++)
  	        {
  	        	FhiRolePermission stock = (FhiRolePermission)stocks.get(i);
  	        	permissionImple.addRM(stock);
  	        }
        }else{
        	 for (int i=0; i<stocks.size() ;i++)
 	        {
 	        	FhiRolePermission stock = (FhiRolePermission)stocks.get(i);
 	        	permissionImple.addRM(stock);
 	        }
        }
        request.setAttribute("Role",permissionImple.getAllRole());
        return mapping.findForward("index");
	}
}
