package com.fhi.employee.action ;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

import com.fhi.company.service.CompanyIn;
import com.fhi.company.vo.FhiOaCompany;
import com.fhi.department.service.DepartmentIn;
import com.fhi.department.vo.FhiOaDepartment;
import com.fhi.employee.condition.EmployeeCondition;
import com.fhi.employee.service.EmployeeIn;
import com.fhi.employee.vo.FhiOaEmployeeBasic;
import com.fhi.employee.vo.FhiOaEmployeeEducation;
import com.fhi.employee.vo.FhiOaEmployeeFamily;
import com.fhi.employee.vo.FhiOaEmployeeWork;
import com.fhi.framework.config.FhiConfig;
import com.fhi.framework.tools.SelfFileUpload;
import com.fhi.framework.tools.SelfToken;
import com.fhi.framework.utils.date.DataUtils;
import com.fhi.user.vo.FhiUser;



public class EmployeeAction extends DispatchAction  { 
	
	private static Logger logger = Logger.getLogger(EmployeeAction.class);	
	private EmployeeIn employeeService;
	private DepartmentIn depService;
	private CompanyIn companyService; 
	private EmployeeCondition employeeCondition;
	private FhiUser user; 
	private HttpSession session ;
	EmployeeForm employeeForm = new EmployeeForm() ;
	
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		employeeForm = (EmployeeForm)form;
		session = request.getSession(true) ; 
		user = (FhiUser) request.getSession().getAttribute(FhiConfig.USER_NAME);
		if (user == null) {
			return mapping.findForward("login");
		} 
		return super.execute(mapping, form, request, response);
	} 
	
	
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {  
		
		List list =null ;
		List comlist =null ;
		List deplist =null ; 
		
		session.removeAttribute("oldEmHeadPic") ; 
		session.removeAttribute("editEmployee") ;
		session.removeAttribute("tempBasic") ;
		session.removeAttribute("operateItem") ;
		session.removeAttribute("headpicpath") ; 
		
		session.removeAttribute("tempFamilyList") ;
		session.removeAttribute("tempWorkList") ;
		session.removeAttribute("tempEducationList") ;
		
		String comid = request.getParameter("comid") ;
		String depid = request.getParameter("depid") ;
		
		if(comid!=null && !comid.trim().equals("")){
			FhiOaCompany com = companyService.getCompanyById(comid) ;
			employeeForm.getBasic().setCompanyName(com.getFullName()) ; 
			request.setAttribute("groupcom", com.getFullName()) ;
		}
		if(depid!=null && !depid.trim().equals("")){ 
			FhiOaDepartment dep = depService.getDepartmentById(depid);
			employeeForm.getBasic().setCompanyName(dep.getComName()) ;
			employeeForm.getBasic().setDepName(dep.getDepName()) ;
			request.setAttribute("groupcom", dep.getComName()) ;
			request.setAttribute("groupdep", dep.getDepName()) ;
			
		} 
		
		try{
		    list = employeeService.getEmployees(employeeCondition.setEmployeeHql(employeeForm)) ;   
		    comlist = companyService.getCompanies() ;
			deplist = depService.getDepartments() ;
		}catch(Exception e){
			logger.error("员工信息查询错误",e) ;
		} 
		request.setAttribute("emlist", list) ;
		request.setAttribute("form", employeeForm) ;
		request.setAttribute(FhiConfig.PAGE_INFO_NAME, employeeCondition.getPageInfo()) ;
		session.setAttribute("self.employee.Token",java.util.UUID.randomUUID().toString());  
		request.setAttribute("comlist", comlist) ;
		request.setAttribute("deplist", deplist) ;  
		return mapping.findForward("list") ;
	} 
	
	public ActionForward listsel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {  
		List list = new ArrayList() ;
		try{
		    list = employeeService.getEmployees(employeeCondition.setEmployeeHql(employeeForm)) ;  
		}catch(Exception e){
			logger.error("员工信息查询错误",e) ;
		} 
		request.setAttribute("emlist", list) ;
		request.setAttribute("item", request.getParameter("item")) ;
		request.setAttribute("form", employeeForm) ;
		request.setAttribute(FhiConfig.PAGE_INFO_NAME, employeeCondition.getPageInfo()) ;  
		return mapping.findForward("listsel") ;
	} 

	public ActionForward preAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {   
		
		session.removeAttribute("oldEmHeadPic") ; 
		session.removeAttribute("editEmployee") ;
		session.removeAttribute("tempBasic") ;
		session.removeAttribute("operateItem") ;
		session.removeAttribute("headpicpath") ; 
		
		session.removeAttribute("tempFamilyList") ;
		session.removeAttribute("tempWorkList") ;
		session.removeAttribute("tempEducationList") ;  
		session.setAttribute("comlist", companyService.getCompanies()) ;
		session.setAttribute("self.employee.Token",java.util.UUID.randomUUID().toString()); 
		return mapping.findForward("addbasic")  ;
	} 
	
	
	
	public ActionForward preEditEmployee(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {   
		
		session.setAttribute("comlist", companyService.getCompanies()) ; 
		session.setAttribute("self.employee.Token",java.util.UUID.randomUUID().toString()); 
		String id = request.getParameter("id") ; 
		
		session.setAttribute("headpicpath",employeeService.getEmployeeById(id).getHeadPic()) ;
		
		session.setAttribute("editEmployee","yes") ;  
		
		session.setAttribute("oldEmHeadPic",employeeService.getEmployeeById(id).getHeadPic()) ; 
		
		session.setAttribute("tempBasic",employeeService.getEmployeeById(id)) ;
		
		session.setAttribute("tempFamilyList",employeeService.getFamilys(id)) ; 
		session.setAttribute("tempWorkList",employeeService.getWorks(id)) ;
		session.setAttribute("tempEducationList",employeeService.getEducations(id)) ; 

		session.setAttribute("basicedit","Y") ; 
 
		return mapping.findForward("addbasic")  ;
	} 
   //---------------------------add basic information---------------------------------
	public ActionForward addBasic(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {  
		
		if(!SelfToken.isValid(request, "self.employee.Token")){
			session.setAttribute("self.employee.Token",java.util.UUID.randomUUID().toString());
			return mapping.findForward("addbasic")  ;
		} 
		
		String repath = "addbasic" ;
		String basicedit = (String)session.getAttribute("basicedit") ;
		
		FhiOaEmployeeBasic basic = employeeForm.getBasic() ; 
		FhiOaEmployeeBasic tempBasic = (FhiOaEmployeeBasic)session.getAttribute("tempBasic") ;
		if(tempBasic==null)
			tempBasic = new FhiOaEmployeeBasic() ;
		basic.setAddDate(new Date()) ;
		basic.setCreator(user.getUserId()) ; 
		
		if(basic.getId()==null || basic.getId().trim().equals(""))
		   basic.setId(UUID.randomUUID().toString()) ;

		if(basicedit==null || basicedit.trim().equals("")) 
		   basic.setId(UUID.randomUUID().toString()) ;
		basic.setInWorkDate(DataUtils.getDateByString(employeeForm.getInWorkDate())) ;
		basic.setBirthday(DataUtils.getDateByString(employeeForm.getBirthday())) ;
		basic.setEndDate(DataUtils.getDateByString(employeeForm.getEndDate())) ;
		basic.setNextCheck(DataUtils.getDateByString(employeeForm.getNextCheck())) ;
		basic.setOffWorkDate(DataUtils.getDateByString(employeeForm.getOffWorkDate())) ;
		basic.setRealWorkDate(DataUtils.getDateByString(employeeForm.getRealWorkDate())) ;
		basic.setStartWrokDate(DataUtils.getDateByString(employeeForm.getStartWrokDate())) ;  
		
		//头像文件上传.................
		
		if(employeeForm.getHeadpic()!=null && employeeForm.getHeadpic().getFileSize()>0){   
			String []rets = this.upLoadPic(employeeForm.getHeadpic(),tempBasic.getHeadPic()) ;
			if(rets[1].equals("ok")){
				basic.setHeadPic(rets[0]) ; 
				session.setAttribute("employeeret", "操作成功") ; 
			}else if(rets[1].equals("size")){ 
				session.setAttribute("employeeret", "头像文件太大(必须<=1M)，请重新上传") ; 
			}
		}else{ 
			basic.setHeadPic(tempBasic.getHeadPic()) ;
		}
		
		
		//会计证文件上传.................
		
		if(employeeForm.getAccountPic()!=null && employeeForm.getAccountPic().getFileSize()>0){   
			String []rets = this.upLoadPic(employeeForm.getAccountPic(),tempBasic.getAccountPic()) ;
			if(rets[1].equals("ok")){
				basic.setAccountPic(rets[0]) ; 
				session.setAttribute("employeeret", "操作成功") ; 
			}else if(rets[1].equals("size")){ 
				session.setAttribute("employeeret", "会计证文件太大(必须<=1M)，请重新上传") ; 
			}
		}else{ 
			basic.setAccountPic(tempBasic.getAccountPic()) ;
		}
		
		//		报检员文件上传.................
		
		if(employeeForm.getCheckPic()!=null && employeeForm.getCheckPic().getFileSize()>0){   
			String []rets = this.upLoadPic(employeeForm.getCheckPic(),tempBasic.getCheckPic()) ;
			if(rets[1].equals("ok")){
				basic.setAccountPic(rets[0]) ; 
				session.setAttribute("employeeret", "操作成功") ; 
			}else if(rets[1].equals("size")){ 
				session.setAttribute("employeeret", "报检员证文件太大(必须<=1M)，请重新上传") ; 
			}
		}else{ 
			basic.setCheckPic(tempBasic.getCheckPic()) ;
		}
		
		//		报关员证文件上传.................
		
		if(employeeForm.getGatewayPic()!=null && employeeForm.getGatewayPic().getFileSize()>0){   
			String []rets = this.upLoadPic(employeeForm.getGatewayPic(),tempBasic.getGatewayPic()) ;
			if(rets[1].equals("ok")){
				basic.setGatewayPic(rets[0]) ; 
				session.setAttribute("employeeret", "操作成功") ; 
			}else if(rets[1].equals("size")){ 
				session.setAttribute("employeeret", "报关员证文件太大(必须<=1M)，请重新上传") ; 
			}
		}else{ 
			basic.setGatewayPic(tempBasic.getGatewayPic()) ;
		}
		
		
		//		学位证文件上传.................
		
		if(employeeForm.getDegreePic()!=null && employeeForm.getDegreePic().getFileSize()>0){   
			String []rets = this.upLoadPic(employeeForm.getDegreePic(),tempBasic.getDegreePic()) ;
			if(rets[1].equals("ok")){
				basic.setDegreePic(rets[0]) ; 
				session.setAttribute("employeeret", "操作成功") ; 
			}else if(rets[1].equals("size")){ 
				session.setAttribute("employeeret", "学位证文件太大(必须<=1M)，请重新上传") ; 
			}
		}else{ 
			basic.setDegreePic(tempBasic.getDegreePic()) ;
		}
		
		
		//		毕业证文件上传.................
		
		if(employeeForm.getGraduatePic()!=null && employeeForm.getGraduatePic().getFileSize()>0){   
			String []rets = this.upLoadPic(employeeForm.getGraduatePic(),tempBasic.getGraduatePic()) ;
			if(rets[1].equals("ok")){
				basic.setGraduatePic(rets[0]) ; 
				session.setAttribute("employeeret", "操作成功") ; 
			}else if(rets[1].equals("size")){ 
				session.setAttribute("employeeret", "毕业证文件太大(必须<=1M)，请重新上传") ; 
			}
		}else{ 
			basic.setGraduatePic(tempBasic.getGraduatePic()) ;
		}
		
		//		驾驶证文件上传.................
		
		if(employeeForm.getDrivePic()!=null && employeeForm.getDrivePic().getFileSize()>0){   
			String []rets = this.upLoadPic(employeeForm.getDrivePic(),tempBasic.getDrivePic()) ;
			if(rets[1].equals("ok")){
				basic.setDrivePic(rets[0]) ; 
				session.setAttribute("employeeret", "操作成功") ; 
			}else if(rets[1].equals("size")){ 
				session.setAttribute("employeeret", "驾驶证文件太大(必须<=1M)，请重新上传") ; 
			}
		}else{ 
			basic.setDrivePic(tempBasic.getDrivePic()) ;
		}
		
		
		//		身份证文件上传................. 
		if(employeeForm.getIdcardPic()!=null && employeeForm.getIdcardPic().getFileSize()>0){    
			String []rets = this.upLoadPic(employeeForm.getIdcardPic(),tempBasic.getIdcardPic()) ;
			if(rets[1].equals("ok")){
				basic.setIdcardPic(rets[0]) ; 
				session.setAttribute("employeeret", "操作成功") ; 
			}else if(rets[1].equals("size")){ 
				session.setAttribute("employeeret", "身份证文件太大(必须<=1M)，请重新上传") ; 
			}
		}else{ 
			basic.setIdcardPic(tempBasic.getIdcardPic()) ;
		}  
		
		boolean ret ;  
		if(basicedit!=null && basicedit.trim().equals("Y"))
			ret = employeeService.updateEmployee(basic) ;
		else 
			ret = employeeService.addEmployee(basic) ;
		if(ret==true)
			session.setAttribute("employeeret", "操作成功") ;
		else
			session.setAttribute("employeeret", "操作失败") ; 
		session.setAttribute("basicedit", "Y") ; 
		session.setAttribute("tempBasic", basic) ;   
		session.setAttribute("operateItem", "basic") ; 
		session.setAttribute("self.employee.Token",java.util.UUID.randomUUID().toString()); 
		return mapping.findForward(repath)  ;
	}
	
	public String getPicPath(FormFile file){ 
		String nameuuid = UUID.randomUUID().toString() ;
		String ext = file.getFileName().substring(file.getFileName().lastIndexOf("."))  ;
		String path = session.getServletContext().getRealPath("/")+"upload\\" + nameuuid + ext ; 
		return path ;
	}
	
	public String[] upLoadPic(FormFile file,String oldFileName){  
		
		String ret[] = new String[2] ; 
		String path = this.getPicPath(file);  
		SelfFileUpload up = new SelfFileUpload() ; 
		if(path.lastIndexOf("\\")!=-1)
			ret[0] = path.substring(path.lastIndexOf("\\")+1) ;
		else
			ret[0] = path.substring(path.lastIndexOf("/")+1) ;
		
		 try{

			 logger.debug(path) ;
			 ret[1] = up.upload(file.getInputStream(),path,1024*1024,".JPG|.jpg|.GIF.gif|.PNG|.png|.BMP|.bmp") ;  
		 }catch(Exception e){
			 logger.debug("文件上传失败",e) ; 
			 ret[1] = "failed" ;
			 e.printStackTrace();
		 } 
		 try{ 
			 if(oldFileName!=null && !oldFileName.trim().equals(""))
			    up.delete(session.getServletContext().getRealPath("/")+"upload\\" + oldFileName.trim()) ;
		 }catch(Exception e){}
		 
		return  ret ; 
	}
	
	//---------------------------add education information---------------------------------
	
	public ActionForward addEducation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {  

		
		if(!SelfToken.isValid(request, "self.employee.Token")){
			session.setAttribute("self.employee.Token",java.util.UUID.randomUUID().toString());  
		    return mapping.findForward("addbasic"); 
		}  

		FhiOaEmployeeBasic tempBasic = (FhiOaEmployeeBasic)session.getAttribute("tempBasic") ;
		
		String submethod = request.getParameter("submethod") ; 
		
		FhiOaEmployeeEducation education = employeeForm.getEducation(); 
		education.setAddDate(new Date()) ;
		education.setCreator(user.getUserId()) ; 
		education.setStartDate(DataUtils.getDateByString(employeeForm.getStartTime())) ;
		education.setEndDate(DataUtils.getDateByString(employeeForm.getEndTime())) ; 
		
		List list = (List)session.getAttribute("tempEducationList") ;
		if(list==null)
			list = new ArrayList() ;
        //如果index不为空，表示编辑操作
		
		boolean ret_edu  ;
		String index = request.getParameter("index") ;  
		if(submethod!=null && submethod.trim().equals("edit")){
			list.remove(Integer.parseInt(index)) ;
			ret_edu = employeeService.updateEmployee_edu(education) ;
		}else{
			education.setId(java.util.UUID.randomUUID().toString()) ;
			education.setEmployeeId(tempBasic.getId()) ;
			ret_edu = employeeService.addEmployee_edu(education) ;
		} 
		
		if(ret_edu==true){
			list.add(education) ; 
			session.setAttribute("employeeret", "操作成功") ;
		}else{ 
			session.setAttribute("employeeret", "操作失败") ;
		}
		   
		
		if((submethod==null||submethod.trim().equals("")||submethod.trim().equals("edit")) && (ret_edu==true) )
		    request.setAttribute("index", list.size()-1) ; 
		
		session.setAttribute("tempEducationList", list) ;  
		session.setAttribute("operateItem", "education") ; 
		session.setAttribute("self.employee.Token",java.util.UUID.randomUUID().toString()); 
		
	    return mapping.findForward("addbasic")  ; 
	}
	
	
	
	public ActionForward delTempEducation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception { 
		
		if(!SelfToken.isValid(request, "self.employee.Token")){
			session.setAttribute("self.employee.Token",java.util.UUID.randomUUID().toString()); 
			return mapping.findForward("addbasic")  ; 
		} 
		
		List list = (List)session.getAttribute("tempEducationList") ;
		String index = request.getParameter("index") ; 
		String id = request.getParameter("id") ; 
		boolean ret = employeeService.deleteEmployee_edu(id) ; 
		if(ret==true){
			list.remove(Integer.parseInt(index)) ;
			session.setAttribute("employeeret", "删除成功") ; 
		}else{ 
			session.setAttribute("employeeret", "删除失败") ;
		}   
		session.setAttribute("tempEducationList", list) ;
		session.setAttribute("self.employee.Token",java.util.UUID.randomUUID().toString()); 
		session.setAttribute("operateItem", "education") ; 
		return mapping.findForward("addbasic")  ;
	}
	
	public ActionForward preEditTempEducation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {  
		 
		List list = (List)session.getAttribute("tempEducationList") ;
		String index = request.getParameter("index") ;  
		request.setAttribute("index", index) ; 
		request.setAttribute("submethod", "edit") ;
		if(list!=null && list.size()>0)
			request.setAttribute("education", list.get(Integer.parseInt(index))) ; 
		session.setAttribute("tempEducationList", list) ; 
		session.setAttribute("operateItem", "education") ; 
		return mapping.findForward("addbasic")  ;
	} 
	
	
   //---------------------------add work information---------------------------------
	
	public ActionForward addWork(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {  
		
		if(!SelfToken.isValid(request, "self.employee.Token")){
			session.setAttribute("self.employee.Token",java.util.UUID.randomUUID().toString());  
		    return mapping.findForward("addbasic"); 
		}  

		FhiOaEmployeeBasic tempBasic = (FhiOaEmployeeBasic)session.getAttribute("tempBasic") ;
		
		String submethod = request.getParameter("submethod") ; 
		
		FhiOaEmployeeWork work = employeeForm.getWork(); 
		work.setAddDate(new Date()) ;
		work.setCreator(user.getUserId()) ; 
		work.setStartDate(DataUtils.getDateByString(employeeForm.getStartTime())) ;
		work.setEndDate(DataUtils.getDateByString(employeeForm.getEndTime())) ; 
		
		List list = (List)session.getAttribute("tempWorkList") ;
		if(list==null)
			list = new ArrayList() ;
        //如果index不为空，表示编辑操作
		
		boolean ret_edu  ;
		String index = request.getParameter("index") ;  
		if(submethod!=null && submethod.trim().equals("edit")){
			list.remove(Integer.parseInt(index)) ;
			ret_edu = employeeService.updateEmployee_work(work) ;
		}else{
			work.setId(java.util.UUID.randomUUID().toString()) ;
			work.setEmployeeId(tempBasic.getId()) ;
			ret_edu = employeeService.addEmployee_work(work) ;
		}  
		if(ret_edu==true){
			list.add(work) ; 
			session.setAttribute("employeeret", "操作成功") ;
		}else{ 
			session.setAttribute("employeeret", "操作失败") ;
		} 
		if((submethod==null||submethod.trim().equals("")||submethod.trim().equals("edit")) && (ret_edu==true) )
		    request.setAttribute("index", list.size()-1) ; 
		session.setAttribute("tempWorkList", list) ;  
		session.setAttribute("operateItem", "work") ; 
		session.setAttribute("self.employee.Token",java.util.UUID.randomUUID().toString());  
	    return mapping.findForward("addbasic")  ; 
	}
	
	public ActionForward delTempWork(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {   
		
		if(!SelfToken.isValid(request, "self.employee.Token")){
			session.setAttribute("self.employee.Token",java.util.UUID.randomUUID().toString()); 
			return mapping.findForward("addbasic")  ; 
		} 
		
		List list = (List)session.getAttribute("tempWorkList") ;
		String index = request.getParameter("index") ; 
		String id = request.getParameter("id") ; 
		boolean ret = employeeService.deleteEmployee_work(id) ; 
		if(ret==true){
			list.remove(Integer.parseInt(index)) ;
			session.setAttribute("employeeret", "删除成功") ; 
		}else{ 
			session.setAttribute("employeeret", "删除失败") ;
		}   
		session.setAttribute("tempWorkList", list) ;
		session.setAttribute("self.employee.Token",java.util.UUID.randomUUID().toString()); 
		session.setAttribute("operateItem", "work") ; 
		return mapping.findForward("addbasic")  ;
	}
	
	public ActionForward preEditTempWork(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {  
		 
		List list = (List)session.getAttribute("tempWorkList") ;
		String index = request.getParameter("index") ;  
		request.setAttribute("index", index) ; 
		request.setAttribute("submethod", "edit") ;
		if(list!=null && list.size()>0)
			request.setAttribute("work", list.get(Integer.parseInt(index))) ; 
		session.setAttribute("tempWorkList", list) ; 
		session.setAttribute("operateItem", "work") ; 
		return mapping.findForward("addbasic")  ;
	}   
	
 //---------------------------add family information---------------------------------
	
	public ActionForward addFamily(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {  
        
		if(!SelfToken.isValid(request, "self.employee.Token")){
			session.setAttribute("self.employee.Token",java.util.UUID.randomUUID().toString());  
		    return mapping.findForward("addbasic"); 
		}  

		FhiOaEmployeeBasic tempBasic = (FhiOaEmployeeBasic)session.getAttribute("tempBasic") ;
		
		String submethod = request.getParameter("submethod") ; 
		
		FhiOaEmployeeFamily family = employeeForm.getFamily(); 
		family.setAddDate(new Date()) ;
		family.setCreator(user.getUserId()) ;  
		
		List list = (List)session.getAttribute("tempFamilyList") ;
		if(list==null)
			list = new ArrayList() ;
        //如果index不为空，表示编辑操作
		
		boolean ret_edu  ;
		String index = request.getParameter("index") ;  
		if(submethod!=null && submethod.trim().equals("edit")){
			list.remove(Integer.parseInt(index)) ;
			ret_edu = employeeService.updateEmployee_family(family) ;
		}else{
			family.setId(java.util.UUID.randomUUID().toString()) ;
			family.setEmployeeId(tempBasic.getId()) ;
			ret_edu = employeeService.addEmployee_family(family) ;
		}  
		if(ret_edu==true){
			list.add(family) ; 
			session.setAttribute("employeeret", "操作成功") ;
		}else{ 
			session.setAttribute("employeeret", "操作失败") ;
		} 
		if((submethod==null||submethod.trim().equals("")||submethod.trim().equals("edit")) && (ret_edu==true) )
		    request.setAttribute("index", list.size()-1) ; 
		session.setAttribute("tempFamilyList", list) ;  
		session.setAttribute("operateItem", "family") ; 
		session.setAttribute("self.employee.Token",java.util.UUID.randomUUID().toString());  
	    return mapping.findForward("addbasic")  ; 
	}
	
	public ActionForward delTempFamily(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {  
		
		if(!SelfToken.isValid(request, "self.employee.Token")){
			session.setAttribute("self.employee.Token",java.util.UUID.randomUUID().toString()); 
			return mapping.findForward("addbasic")  ; 
		} 
		List list = (List)session.getAttribute("tempFamilyList") ;
		String index = request.getParameter("index") ;  
		if(list!=null && list.size()>0)
			 list.remove(Integer.parseInt(index)) ;
		session.setAttribute("tempFamilyList", list) ;
		session.setAttribute("self.employee.Token",java.util.UUID.randomUUID().toString()); 
		session.setAttribute("operateItem", "family") ; 
		return mapping.findForward("addbasic")  ;
	}
	
	public ActionForward preEditTempFamily(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {  
		 
		List list = (List)session.getAttribute("tempFamilyList") ;
		String index = request.getParameter("index") ;  
		request.setAttribute("index", index) ; 
		request.setAttribute("submethod", "edit") ;
		if(list!=null && list.size()>0)
			request.setAttribute("family", list.get(Integer.parseInt(index))) ; 
		session.setAttribute("tempFamilyList", list) ; 
		session.setAttribute("operateItem", "family") ; 
		return mapping.findForward("addbasic")  ;
	} 
	
	
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {  
		
		session.removeAttribute("oldEmHeadPic") ; 
		session.removeAttribute("editEmployee") ;
		session.removeAttribute("tempBasic") ;
		session.removeAttribute("operateItem") ;
		session.removeAttribute("headpicpath") ; 
		
		session.removeAttribute("tempFamilyList") ;
		session.removeAttribute("tempWorkList") ;
		session.removeAttribute("tempEducationList") ;
		return mapping.findForward("relist")  ;
	} 
	
	public ActionForward complete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {   
		
		try{
			FhiOaEmployeeBasic basic = (FhiOaEmployeeBasic)session.getAttribute("tempBasic") ;
			List<FhiOaEmployeeEducation> listedu = (List<FhiOaEmployeeEducation>)session.getAttribute("tempEducationList") ;
			List<FhiOaEmployeeFamily> listfamily = (List<FhiOaEmployeeFamily>)session.getAttribute("tempFamilyList") ;
			List<FhiOaEmployeeWork> listwork = (List<FhiOaEmployeeWork>)session.getAttribute("tempWorkList") ;
			
			String editEmployee = (String)session.getAttribute("editEmployee") ; 
			
			if(editEmployee!=null && editEmployee.trim().equals("yes"))
				employeeService.editFullEmployee(basic, listedu, listfamily, listwork) ;
			else 
				employeeService.addFullEmployee(basic, listedu, listfamily, listwork) ;   
				   

			String oldfile = (String)session.getAttribute("oldEmHeadPic") ;
			String tempfile = (String)session.getAttribute("headpicpath") ;

			SelfFileUpload up = new SelfFileUpload() ;
			if(tempfile!=null && !tempfile.trim().equals("") && !tempfile.trim().equals(oldfile) )
			    up.delete(session.getServletContext().getRealPath("/")+"images\\head\\" + oldfile) ;
			
			 
			session.setAttribute("employeeret", "操作成功") ;
		}catch(Exception e){
			logger.error("操作失败",e) ;
			session.setAttribute("employeeret", "操作失败") ;
		} 
		return mapping.findForward("relist")  ;
	}  
	
	
	public ActionForward delEmployee(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {   
		try{
			String [] ids =null ;
			String  id = request.getParameter("id") ;
			if(id!=null && !id.trim().equals("")){
				ids = new String[1] ;
				ids[0] = id.trim() ;
			}else{
				ids = employeeForm.getEmids() ;
			} 
			String path =  session.getServletContext().getRealPath("/")+"upload\\";
			employeeService.deleteEmployees(ids,path) ; 
			session.setAttribute("employeeret", "删除成功") ; 
		}catch(Exception e){
            logger.error("删除员工信息失败",e) ;
			session.setAttribute("employeeret", "删除失败") ; 
		}
		return mapping.findForward("relist")  ;
	}
	
	public ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception { 

		String  id = request.getParameter("id") ;
		request.setAttribute("basic", employeeService.getEmployeeById(id)) ;  
		request.setAttribute("family", employeeService.getFamilys(id)) ; 
		request.setAttribute("work", employeeService.getWorks(id)) ; 
		request.setAttribute("edu", employeeService.getEducations(id)) ;  
		return mapping.findForward("detail")  ;
	}
	
		

	public void setCompanyService(CompanyIn companyService) {
		this.companyService = companyService;
	}  
	public void setDepService(DepartmentIn depService) {
		this.depService = depService;
	}  
	public void setEmployeeCondition(EmployeeCondition employeeCondition) {
		this.employeeCondition = employeeCondition;
	}  
	public void setEmployeeService(EmployeeIn employeeService) {
		this.employeeService = employeeService;
	} 
}