package com.fhi.accept.action ;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.fhi.accept.condition.AcceptCondition;
import com.fhi.accept.service.AcceptIn;
import com.fhi.accept.vo.FhiOaAccept;
import com.fhi.accept.vo.FhiOaAcceptBordereauxExcelBean;
import com.fhi.accept.vo.FhiOaAcceptInspectionRecords;
import com.fhi.accept.vo.FhiOaAcceptInspectionRecordsExcelBean;
import com.fhi.accept.vo.FhiOaRetmoney;
import com.fhi.custom.service.CustomIn;
import com.fhi.custom.vo.FhiOaCustom;
import com.fhi.framework.config.FhiConfig;
import com.fhi.framework.tools.SelfToken;
import com.fhi.framework.upload.UploadIn;
import com.fhi.framework.utils.ExportExcel;
import com.fhi.framework.utils.NumUtils;
import com.fhi.framework.utils.date.DataUtils;
import com.fhi.system.sysmsg.vo.SysMsgSetup;
import com.fhi.user.vo.FhiUser;
import com.heavenlake.wordapi.Document;

public class AcceptAction extends DispatchAction  { 
	
	private static Logger logger = Logger.getLogger(AcceptAction.class);	
	private AcceptIn acceptService;
	private AcceptCondition acceptCondition;
	private FhiUser user; 
	private HttpSession session ;
	private UploadIn uploadService;
	private CustomIn customService;
	private AcceptForm accForm;
	
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		accForm = (AcceptForm)form;
		session = request.getSession(true) ; 
		user = (FhiUser) request.getSession().getAttribute(FhiConfig.USER_NAME);
		if (user == null) {
			return mapping.findForward("login");
		}   
		return super.execute(mapping, form, request, response);
	}
	
	public ActionForward preAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception { 
		session.setAttribute("self.accept.Token",java.util.UUID.randomUUID().toString());
		return mapping.findForward("addaccept") ;
	}
	
	public ActionForward preAddMoney(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {  
		String index = request.getParameter("index") ;  
		if(index!=null && !index.trim().equals("")){
			List<FhiOaRetmoney>   moneylist = (List<FhiOaRetmoney>)session.getAttribute("moneylist");  
			request.setAttribute("money", moneylist.get(Integer.parseInt(index.trim()))) ; 
			request.setAttribute("index", index) ; 
		}  
		session.setAttribute("divshowitems", "item_2") ;
		return mapping.findForward("addmoney") ;
	}
	
	public ActionForward preAddEmergence(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {  
		String index = request.getParameter("index") ;  
		if(index!=null && !index.trim().equals("")){
			List<SysMsgSetup>   emergencelist = (List<SysMsgSetup>)session.getAttribute("emergencelist");  
			request.setAttribute("emergnce", emergencelist.get(Integer.parseInt(index))) ;  
			request.setAttribute("index", index) ; 
		} 
		session.setAttribute("divshowitems", "item_1") ;
		return mapping.findForward("addemergence") ;
	}
	
	
	public ActionForward preEditAccept(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception { 
		
		String id = request.getParameter("id") ;
		request.setAttribute("accept", acceptService.getAcceptById(id)) ;
		if(session.getAttribute("moneylist")==null)
		   session.setAttribute("moneylist", acceptService.getRetMoneyByAcceptId(id.trim())) ; 
		if(session.getAttribute("emergencelist")==null) 
		   session.setAttribute("emergencelist", acceptService.getEmergenceByAcceptId(id.trim())) ;
		session.setAttribute("self.accept.Token",java.util.UUID.randomUUID().toString());
		return mapping.findForward("addaccept") ;
	}
	
	/**
	 * 工作单下载
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward download(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception { 
		
		FhiOaAccept accept = acceptService.getAcceptById(accForm.getAccept().getId());
		
		if(accept!=null){
			FhiOaCustom custom = customService.getCustomById(accept.getCustomerId());		
			if(custom!=null){
				Document tempWordFile = new Document();
				String tempFileName = uploadService.getFilePath()+"\\"+java.util.UUID.randomUUID()+".tmp";
				
				tempWordFile.open(uploadService.getFilePath()+"\\WorkTemplate.doc");
				tempWordFile.saveAs(tempFileName);
				
				tempWordFile.replaceAll("$交单日期$", DataUtils.format(accept.getSendOrderDate(),"yyyy-MM-dd"));
				tempWordFile.replaceAll("$内部编码$", accept.getInnerCode());
				tempWordFile.replaceAll("$客户公司名称$", custom.getFullName());
				tempWordFile.replaceAll("$客户公司地址$", custom.getAddress());
				tempWordFile.replaceAll("$联系人$", custom.getContactPerson());
				tempWordFile.replaceAll("$电话$", custom.getPhone());
				//System.out.println(accept.getSubOrderCode()+"+"+accept.getMainOrderCode());
				tempWordFile.replaceAll("$提单号$", (accept.getSubOrderCode()==null||"".equals(accept.getSubOrderCode()))?accept.getMainOrderCode():accept.getSubOrderCode());
				tempWordFile.replaceAll("$件数$", accept.getPiece());
				tempWordFile.replaceAll("$重量$", accept.getWeight());
				
				tempWordFile.replaceAll("$付汇方式$", accept.getPayStyleStr());
				tempWordFile.replaceAll("$合同号$", accept.getContractCode());
				tempWordFile.replaceAll("$币种$", accept.getMoneyUnit());
				tempWordFile.replaceAll("$货值$", accept.getMateriaMoney().toString());
				
				tempWordFile.replaceAll("$入账日期$", DataUtils.format(accept.getRealMoneyDate(),"yyyy-MM-dd"));
				tempWordFile.replaceAll("$入账金额$", NumUtils.formatNumPoint(accept.getMayMoney()));
				tempWordFile.replaceAll("$入账说明$", accept.getMoneyComment());
				tempWordFile.replaceAll("$成本总计$", NumUtils.formatNumPoint(accept.getCost()));
				tempWordFile.replaceAll("$服务费$", NumUtils.formatNumPoint(accept.getServiceMoney()));
				tempWordFile.replaceAll("$利润$", NumUtils.formatNumPoint(accept.getProfit()));
				
				tempWordFile.close(true);
				
				// 文件提取
				File file = new File(tempFileName);
				if (!file.exists()) {
					logger.error("文件不存在，下载失败！filePath:" + tempFileName);
					request.setAttribute("message", "文件不存在或已删除！");
					return mapping.findForward("message");
				}
				response.reset();// 清空输出流

					response.setContentType("application/msword");
					response.setHeader("Content-disposition", "inline; filename="
							+ URLEncoder.encode(accept.getInnerCode()+".doc", "UTF-8"));

				logger.debug("工作单文件查找成功。Path:" + file.getPath());
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
			}
		}
		return null;
	}
	
	public ActionForward addEmergence(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {  

		String index = request.getParameter("index") ; 
        List<SysMsgSetup>   emergencelist = (List<SysMsgSetup>)session.getAttribute("emergencelist");
        if(emergencelist==null)  emergencelist = new ArrayList() ;
        
        SysMsgSetup emergence = accForm.getEmergnce() ; 
        
        int emergnceItem = accForm.getEmergnceItem() ;
        int emergnceType =accForm.getEmergnceType() ;  
        
        if(emergnceItem==1 && emergnceType==4){
        	emergence.setSysClass(1) ;
        	emergence.setSysClassName("应收结帐款到期报警") ;
        }else if(emergnceItem==1 && emergnceType==5){
        	emergence.setSysClass(2) ;
        	emergence.setSysClassName("应收结帐款到期预警") ;
        }else if(emergnceItem==2 && emergnceType==4){
        	emergence.setSysClass(3) ;
        	emergence.setSysClassName("货款到期报警") ;
        }else if(emergnceItem==2 && emergnceType==5){
        	emergence.setSysClass(4) ;
        	emergence.setSysClassName("货款到期预警") ;
        }else if(emergnceItem==3 && emergnceType==4){
        	emergence.setSysClass(5) ;
        	emergence.setSysClassName("税金到期报警") ;
        }else if(emergnceItem==3 && emergnceType==5){
        	emergence.setSysClass(6) ;
        	emergence.setSysClassName("税金到期预警") ;
        } 
        
        emergence.setSysType(1) ; 
        emergence.setStartDate(DataUtils.getDateByString(accForm.getEMstartTime())) ; 
        if(accForm.getEMendTime()!=null && !accForm.getEMendTime().trim().equals(""))
           emergence.setDeadlineDate(DataUtils.getDateByString(accForm.getEMendTime())) ; 
        emergence.setUserId(user.getUserId()) ;
        emergence.setCreateDate(new Date()) ;
        emergence.setContent(emergence.getSysClassName()) ;
        emergence.setTitle(emergence.getSysClassName()) ;
        emergence.setUrl("") ; 
        
	    emergencelist.add(emergence) ;
	    
	    if(index!=null && !index.trim().equals("")){
	    	emergencelist.remove(Integer.parseInt(index.trim())) ;
	    }
	    session.setAttribute("emergencelist", emergencelist) ; 
		session.setAttribute("divshowitems", "item_1") ;
		return mapping.findForward("emmidpage") ;
	}
	
	
	public ActionForward preDeleteMoney(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {  
		request.setAttribute("index", request.getParameter("index")) ; 
		return mapping.findForward("delmoney") ;
	}
	
	public ActionForward preDeleteEmergence(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {  
		request.setAttribute("index", request.getParameter("index")) ; 
		return mapping.findForward("delem") ;
	}
	
	
	public ActionForward deleteMoney(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {  
		 String index = request.getParameter("index") ; 
		 List<FhiOaRetmoney>   moneylist = (List<FhiOaRetmoney>)session.getAttribute("moneylist"); 
		 List<FhiOaRetmoney> moneylist_del = (List<FhiOaRetmoney>)session.getAttribute("moneylist_del"); 
		 if(moneylist_del==null) moneylist_del = new ArrayList() ; 
		 FhiOaRetmoney money = moneylist.get(Integer.parseInt(index.trim()));
		 if(money.getId()!=null && !money.getId().trim().equals("")){
			 moneylist_del.add(money) ;
			 moneylist.remove(money) ;
		 }else{ 
			 moneylist.remove(money) ;
		 } 
		 session.setAttribute("moneylist", moneylist) ; 
		 session.setAttribute("moneylist_del", moneylist_del) ;  
		 session.setAttribute("divshowitems", "item_2") ;
		 return mapping.findForward("moneymidpage") ;
	}  
	public ActionForward deleteEmergence(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception { 
		String index = request.getParameter("index") ; 
		 List<SysMsgSetup>   emergencelist = (List<SysMsgSetup>)session.getAttribute("emergencelist"); 
		 List<SysMsgSetup> emergencelist_del = (List<SysMsgSetup>)session.getAttribute("emergencelist_del"); 
		 if(emergencelist_del==null) emergencelist_del = new ArrayList() ; 
		 SysMsgSetup em = emergencelist.get(Integer.parseInt(index.trim()));
		 if(em.getId()!=null && !em.getId().trim().equals("")){
			 emergencelist_del.add(em) ;
			 emergencelist.remove(em) ;
		 }else{ 
			 emergencelist.remove(em) ;
		 } 
		 session.setAttribute("emergencelist", emergencelist) ; 
		 session.setAttribute("emergencelist_del", emergencelist_del) ;  
		 session.setAttribute("divshowitems", "item_1") ;
		 return mapping.findForward("emmidpage") ; 
	}
	
	
	public ActionForward addMoney(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {  

		String index = request.getParameter("index") ; 
        List<FhiOaRetmoney>   moneylist = (List<FhiOaRetmoney>)session.getAttribute("moneylist");
        if(moneylist==null)  moneylist = new ArrayList() ;
	    FhiOaRetmoney money = accForm.getMoney() ;
	    money.setRetDate(DataUtils.getDateByString(accForm.getPayTime())) ;
	    money.setCreator(user.getUserId()) ;
	    money.setCreateDate(new Date()) ;
	    moneylist.add(money) ;
	    if(index!=null && !index.trim().equals("")){
	    	moneylist.remove(Integer.parseInt(index.trim())) ;
	    }
	    session.setAttribute("moneylist", moneylist) ; 
		session.setAttribute("divshowitems", "item_2") ;
		return mapping.findForward("moneymidpage") ;
	}
	
	
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		session.removeAttribute("moneylist") ;
		session.removeAttribute("divshowitems") ;
		session.removeAttribute("emergencelist") ;
		session.removeAttribute("emergencelist_del") ;
		session.removeAttribute("moneylist_del") ; 
		
		List list =null ;
		try{
		    list = acceptService.getAccepts(acceptCondition.setAcceptHql(accForm,user)) ;
		}catch(Exception e){
			logger.error("接单表信息查询错误",e) ;
		} 
		request.setAttribute("list", list) ;
		request.setAttribute("form", accForm) ;
		request.setAttribute(FhiConfig.PAGE_INFO_NAME, acceptCondition.getPageInfo()) ;
		session.setAttribute("self.accept.Token",java.util.UUID.randomUUID().toString());
		return mapping.findForward("list") ;
	} 
	
	
	public ActionForward addAccept(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		if(!SelfToken.isValid(request, "self.accept.Token")){ 
			session.setAttribute("self.accept.Token",java.util.UUID.randomUUID().toString());  
			return mapping.findForward("relist") ;
		} 
		 
		try{ 
		FhiOaAccept accept = accForm.getAccept() ;
		//accept.setUsername(user.getEmployeeClass().getName()) ; 
		accept.setEexpectMoneyDate(DataUtils.getDateByString(accForm.getEexpectMoneyDate())) ;
		accept.setRealMoneyDate(DataUtils.getDateByString(accForm.getRealMoneyDate())) ;
		accept.setExcpectMatMoneyDate(DataUtils.getDateByString(accForm.getExcpectMatMoneyDate())) ;
		accept.setRealMatMoneyDate(DataUtils.getDateByString(accForm.getRealMatMoneyDate())) ;
		accept.setPayExchangeDate(DataUtils.getDateByString(accForm.getPayExchangeDate())) ;
		accept.setExpectTaxDate(DataUtils.getDateByString(accForm.getExpectTaxDate())) ;
		accept.setRealTaxDate(DataUtils.getDateByString(accForm.getRealTaxDate())) ; 
		accept.setEndDate(DataUtils.getDateByString(accForm.getEndDate())) ;
		accept.setGetOrderDate(DataUtils.getDateByString(accForm.getGetOrderDate())) ;
		List<FhiOaRetmoney> listmoney = (List<FhiOaRetmoney>)session.getAttribute("moneylist") ;
		List<SysMsgSetup> listemergence = (List<SysMsgSetup>)session.getAttribute("emergencelist") ; 
		
		
			if(accept.getId()==null || accept.getId().trim().equals("")){ 
				 accept.setId(java.util.UUID.randomUUID().toString()) ; 
				 accept.setUsername(user.getEmployeeClass().getName()) ; 
				 	//设置警报信息
				 for(int i=0 ; listemergence!=null && i<listemergence.size() ;i++){ 
						SysMsgSetup em = (SysMsgSetup)listemergence.get(i) ;
						em.setContent(em.getContent()+" <a href='accept.do?method=detail&id="+accept.getId()+"'>内部号："
			    				+accept.getInnerCode()+"</a>") ;
						em.setUrl("accept.do?method=detail&id="+accept.getId()) ;
						if(em.getDeadlineDate()!=null)
							em.setContent(em.getContent()+"  到期时间："+em.getDeadlineDate().toString()) ;
						else 
							em.setContent(em.getContent()+"  到期时间："+new Date(em.getStartDate().getTime()+24*3600*1000).toString()) ; 
					}

				 accept.setCreateDate(new Date()) ;
				 accept.setCreator(user.getUserId()) ; 
				 acceptService.addAccept(accept, listemergence, listmoney) ;
			}else{  
				List<FhiOaRetmoney> listmoney_del = (List<FhiOaRetmoney>)session.getAttribute("moneylist_del") ;
				List<SysMsgSetup> listemergence_del = (List<SysMsgSetup>)session.getAttribute("emergencelist_del") ;
				
				//设置警报信息
				for(int i=0 ; listemergence!=null && i<listemergence.size() ;i++){ 
					SysMsgSetup em = (SysMsgSetup)listemergence.get(i) ;
					em.setContent(em.getContent()+" <a href='accept.do?method=detail&id="+accept.getId()+"'>内部号："
		    				+accept.getInnerCode()+"</a>") ;
					em.setUrl("accept.do?method=detail&id="+accept.getId()) ;
					if(em.getDeadlineDate()!=null)
						em.setContent(em.getContent()+"  到期时间："+em.getDeadlineDate().toString()) ;
					else 
						em.setContent(em.getContent()+"  到期时间："+new Date(em.getStartDate().getTime()+24*3600*1000).toString()) ; 
				}

				accept.setUpdateDate(new Date()) ;
				accept.setUpdatePerson(user.getUserId()) ; 
				accept.setCreateDate(DataUtils.getDateByString(accForm.getCreateDate())) ;
				acceptService.updateAccept(accept, listemergence, listmoney,listemergence_del ,listmoney_del) ;
			}  
			session.setAttribute("acceptResult","操作成功"); 
		}catch(Exception e){
			logger.error("操作接单表失败",e) ;
			session.setAttribute("acceptResult","操作失败");  
		} 
		session.setAttribute("self.accept.Token",java.util.UUID.randomUUID().toString());  
		return mapping.findForward("relist") ;
	}
	   
	
	
	public ActionForward delAccept(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
 
		if(!SelfToken.isValid(request, "self.accept.Token")){  
			session.setAttribute("self.accept.Token",java.util.UUID.randomUUID().toString());  
			return mapping.findForward("relist") ;
		}  
		String ids [] = accForm.getIds() ;
		String id = request.getParameter("id") ;
		if(id!=null && !id.trim().equals("")){
			ids = new String[1] ;
			ids[0] = id ;
		}
 
		try{
			acceptService.deleteAccepts(ids) ; 
			session.setAttribute("acceptResult","删除成功"); 
		}catch(Exception e){
			logger.error("删除失败",e) ;
			session.setAttribute("acceptResult","删除失败"); 
		}  
		session.setAttribute("self.accept.Token",java.util.UUID.randomUUID().toString()); 
		return mapping.findForward("relist") ;
	}
	
	
	public ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id") ;
		request.removeAttribute("detailone");
		request.removeAttribute("moneylist");
		request.setAttribute("detailone", acceptService.getAcceptById(id)) ;
		request.setAttribute("moneylist", acceptService.getRetMoneyByAcceptId(id)) ; 
		return mapping.findForward("detail") ;
	}  
	
	public ActionForward copyAccept(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id") ;
		FhiOaAccept accept  = acceptService.getAcceptById(id) ;
		FhiOaAccept accept1 = new FhiOaAccept() ;
		accept1.setCustomer(accept.getCustomer()) ;
		accept1.setCustomerId(accept.getCustomerId()) ;
		accept1.setManager(accept.getManager()) ;
		accept1.setStartOrEndPort(accept.getStartOrEndPort()) ;
		accept1.setProductName(accept.getProductName()) ;
		request.setAttribute("accept", accept1) ;
		return mapping.findForward("addaccept") ;
	}
	
	/**
	 * 报检记录单列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward inspectionIndex(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 提示信息的接收 页面传值 后续处理
		String info = (String) request.getSession().getAttribute(
				FhiConfig.Inspection_Info);
		String id = (String) request.getSession().getAttribute(
				FhiConfig.Inspection_Alter_Id);
		request.setAttribute(FhiConfig.Inspection_Info, info);
		request.setAttribute(FhiConfig.Inspection_Alter_Id, id);
		request.getSession().removeAttribute(FhiConfig.Inspection_Info);
		request.getSession().removeAttribute(FhiConfig.Inspection_Alter_Id);
		
		
		acceptCondition.queryInspection(accForm,user);
		accForm.setList(acceptService.query(acceptCondition));		
		
		request.setAttribute(FhiConfig.PAGE_INFO_NAME, acceptCondition.getPageInfo());
		request.setAttribute("form", accForm);
		return mapping.findForward("inspectionIndex") ;
	}
	/**
	 * 报检记录单编辑
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward inspectionLoad(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			
		accForm.setInspection((FhiOaAcceptInspectionRecords) acceptService.load(FhiOaAcceptInspectionRecords.class, accForm.getInspection().getId()));
		
		request.setAttribute("form", accForm);
		return mapping.findForward("inspectionLoad") ;
	}
	
	/**
	 * 报检记录单保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward inspectionSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			
		FhiOaAcceptInspectionRecords inspection = accForm.getInspection();		
		
		FhiOaAcceptInspectionRecords inspection_old = (FhiOaAcceptInspectionRecords) acceptService.load(FhiOaAcceptInspectionRecords.class, inspection.getId());
		inspection_old.setUpdateDate(new Date());
		inspection_old.setUpdatePerson(user.getUserId());
		inspection_old.update(inspection);
		
		if(acceptService.update(inspection_old)){
			request.getSession().setAttribute(FhiConfig.Inspection_Alter_Id,inspection.getId());
			request.getSession().setAttribute(FhiConfig.Inspection_Info,"报检记录单保存成功！");
			logger.debug("用户"+user.getUserId()+"成功修改报检记录单"+inspection.getId());
			return mapping.findForward("inspectionMain");
		}
		else{
			logger.error("用户"+user.getUserId()+"成功保存报检记录单"+inspection.getId()+"失败");
		}
		return inspectionLoad(mapping,form,request,response);
	}
	
	/**
	 * 报表导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward downloadExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try{
			String fname = "Business";//Excel文件名
		      OutputStream os = response.getOutputStream();//取得输出流
		      response.reset();//清空输出流
		      response.setHeader("Content-disposition", "attachment; filename=" + fname + ".xls");//设定输出文件头
		      response.setContentType("application/msexcel");//定义输出类型
			 
		      String hql = acceptCondition.getAcceptDownloadExcelHql(accForm,user);// 查询条件组装
		      
		      
		      List<FhiOaAcceptBordereauxExcelBean> dataset = acceptService.query(hql);
		      
		      //为合计添加数据
		      FhiOaAcceptBordereauxExcelBean subtotal = new FhiOaAcceptBordereauxExcelBean();
		      //加入序号为小计计算数据
		      for (int i=0;i<dataset.size();i++) {
		    	  FhiOaAcceptBordereauxExcelBean temp = dataset.get(i);
		    	  temp.setSerial(Integer.toString(i+1));
		    	  //货值
		    	  //System.out.println(temp.getMateriaMoney());
		    	  subtotal.setMateriaMoney(add(subtotal.getMateriaMoney(),temp.getMateriaMoney()));
		    	  //入账金额
		    	  subtotal.setMayMoney(add(subtotal.getMayMoney(),temp.getMayMoney()));
			    	  //成本
		    	  subtotal.setCost(add(subtotal.getCost(),temp.getCost()));
			    	//服务费
		    	  subtotal.setServiceMoney(add(subtotal.getServiceMoney(),temp.getServiceMoney()));
			    	//利润
		    	  subtotal.setProfit(add(subtotal.getProfit(),temp.getProfit()));
			    	//预计提成
		    	  subtotal.setTiCheng(add(subtotal.getTiCheng(),temp.getTiCheng()));
		    	  
		      }
		      //加入空行
		      dataset.add(new FhiOaAcceptBordereauxExcelBean());
		      //加入合计
		      subtotal.setSerial("小计");
		      dataset.add(subtotal);
			  
			  logger.debug("查询接单数据条数："+dataset.size());
			  
		      ExportExcel<FhiOaAcceptBordereauxExcelBean> ee=new ExportExcel<FhiOaAcceptBordereauxExcelBean>();

		      
		      String[] headers={"序号","客户名称","内部号","合同号/订单号","货值","入账金额","成本","服务费","利润","预计提成","备注"};
		      Short[] widths={10,50,30,30,10,10,10,10,10,10,50};
		      ee.exportExcel(fname, headers, widths, dataset, os);
		      
		    }catch(Exception e){
		    	logger.error("生成电子表格错误！", e);
		    } 
		    return mapping.findForward("print") ;
	}
	
	private BigDecimal add(BigDecimal a,BigDecimal b){
		if(a==null){
			a=BigDecimal.ZERO;
		}
		if(b==null){
			b=BigDecimal.ZERO;
		}
		return a.add(b);
	}
	
	/**
	 * 报检记录单报表导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward inspectionDownload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try{
			String fname = "Inspection";//Excel文件名
		      OutputStream os = response.getOutputStream();//取得输出流
		      response.reset();//清空输出流
		      response.setHeader("Content-disposition", "attachment; filename=" + fname + ".xls");//设定输出文件头
		      response.setContentType("application/msexcel");//定义输出类型
			 
		      String hql = acceptCondition.queryInspectionDownload(accForm, user);// 查询条件组装
			  
		      List<FhiOaAcceptInspectionRecordsExcelBean> dataset = acceptService.query(hql);
			  
			  logger.debug("查询报检记录单报表数据条数："+dataset.size());
			  
		      ExportExcel<FhiOaAcceptInspectionRecordsExcelBean> ee=new ExportExcel<FhiOaAcceptInspectionRecordsExcelBean>();

		      
		      String[] headers={"内部号","客户名称","接单时间","接单承办人","报检流水号","报检号","电子报检日期","电子报检承办人","现场申报日期","现场申报承办人","收费金额","收费日期","查验日期","查验承办人","放行日期","签收日期"};
		      Short[] widths={30,40,15,10,15,20,15,10,15,10,15,15,15,10,15,15};
		      ee.exportExcel(fname, headers, widths, dataset, os);
		      
		    }catch(Exception e){
		    	logger.error("生成报检记录单报表错误！", e);
		    } 
		    return mapping.findForward("print") ;
	}
	
	/**
	 * 弹窗接单表查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward selectWindow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		    return mapping.findForward("selectWindow") ;
	}
	
	

	public void setAcceptCondition(AcceptCondition acceptCondition) {
		this.acceptCondition = acceptCondition;
	}


	public void setUploadService(UploadIn uploadService) {
		this.uploadService = uploadService;
	}

	public void setAcceptService(AcceptIn acceptService) {
		this.acceptService = acceptService;
	}

	public void setCustomService(CustomIn customService) {
		this.customService = customService;
	} 
	
}