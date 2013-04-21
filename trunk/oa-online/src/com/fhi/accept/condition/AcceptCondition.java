package com.fhi.accept.condition;

import org.apache.log4j.Logger;

import com.fhi.accept.action.AcceptForm;
import com.fhi.accept.vo.FhiOaAcceptInspectionRecords;
import com.fhi.framework.page.Condition;
import com.fhi.user.vo.FhiUser;

public class AcceptCondition extends Condition {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(AcceptCondition.class);	
	public AcceptCondition setAcceptHql(AcceptForm form,FhiUser user){
		
		//设置分页
		Integer pageNo = form.getPageNo();
		if (pageNo != null) { 
			this.setPageNo(pageNo);
		}
		
		//设置排序
		String orderByName = form.getOrderByName() ;
		String orderByOrder = form.getOrderByOrder() ;
		if(orderByName!=null && !orderByName.trim().equals("")){
			this.setOrderBy(" Order By "+orderByName.trim()+ " "+(orderByOrder.trim().equals("1") ? "ASC" : "DESC")+" ") ;
		}else{
			this.setOrderBy(" Order By CreateDate DESC ") ;
		}
		
		//条件组装
		StringBuilder hql = new StringBuilder(" from FhiOaAccept where (1=1) ") ;
		
		String code = form.getAccept().getInnerCode();
		if(code!=null && !code.trim().equals("")){
			hql.append(" and (innerCode like '%"+code.trim()+"%') ") ;
		}
		String customer = form.getAccept().getCustomer() ;
		if(customer!=null && !customer.trim().equals("")){
			hql.append(" and (customer like '%"+customer.trim()+"%') ") ;
		}
		String manager = form.getAccept().getManager() ;
		if(manager!=null && !manager.trim().equals("")){
			hql.append(" and (manager like '%"+manager.trim()+"%') ") ;
		}
		
		String startTime = form.getStartTime() ;
		if(startTime!=null && !startTime.trim().equals("")){
			hql.append(" and (endDate >='"+startTime.trim()+"') ");
		}
		
		String endTime = form.getEndTime();
		if(endTime!=null && !endTime.trim().equals("")){
			hql.append(" and (endDate <='"+endTime.trim()+"') ");
		} 
		
		String sendOrder_startTime = form.getSendOrder_startTime() ;
		if(sendOrder_startTime!=null && !sendOrder_startTime.trim().equals("")){
			hql.append(" and (sendOrderDate >='"+sendOrder_startTime.trim()+"') ");
		}
		
		String sendOrder_endTime = form.getSendOrder_endTime();
		if(sendOrder_endTime!=null && !sendOrder_endTime.trim().equals("")){
			hql.append(" and (sendOrderDate <='"+sendOrder_endTime.trim()+"') ");
		} 
		
		String creator = form.getAccept().getCreator() ;
		if(creator==null || creator.trim().equals(""))
			creator = "" ;
		if( (!"true".equals(user.getPermissionMap().get("accept_all")) 
			&& !"true".equals(user.getPermissionMap().get("accept_select"))) 
			|| !creator.equals("") ){
			hql.append(" and (creator='"+user.getUserId()+"' or managerId='"+user.getUserId()+"') ") ; 
		} 
 
		//设置条件
		this.setHqlString(hql.toString()) ;  
		return this ; 
	}
	/**
	 * 
	 * @param form
	 * @param user
	 * @return
	 */
public String getAcceptDownloadExcelHql(AcceptForm form,FhiUser user){
		
		
		
		
		//条件组装
		StringBuilder hql = new StringBuilder("select new com.fhi.accept.vo.FhiOaAcceptBordereauxExcelBean(accept) from FhiOaAccept accept where (1=1) ") ;
		
		String code = form.getAccept().getInnerCode();
		if(code!=null && !code.trim().equals("")){
			hql.append(" and (accept.innerCode like '%"+code.trim()+"%') ") ;
		}
		String customer = form.getAccept().getCustomer() ;
		if(customer!=null && !customer.trim().equals("")){
			hql.append(" and (accept.customer like '%"+customer.trim()+"%') ") ;
		}
		String manager = form.getAccept().getManager() ;
		if(manager!=null && !manager.trim().equals("")){
			hql.append(" and (accept.manager like '%"+manager.trim()+"%') ") ;
		}
		
		String startTime = form.getStartTime() ;
		if(startTime!=null && !startTime.trim().equals("")){
			hql.append(" and (accept.endDate >='"+startTime.trim()+"') ");
		}
		
		String endTime = form.getEndTime();
		if(endTime!=null && !endTime.trim().equals("")){
			hql.append(" and (accept.endDate <='"+endTime.trim()+"') ");
		} 
		
		String sendOrder_startTime = form.getSendOrder_startTime() ;
		if(sendOrder_startTime!=null && !sendOrder_startTime.trim().equals("")){
			hql.append(" and (accept.sendOrderDate >='"+sendOrder_startTime.trim()+"') ");
		}
		
		String sendOrder_endTime = form.getSendOrder_endTime();
		if(sendOrder_endTime!=null && !sendOrder_endTime.trim().equals("")){
			hql.append(" and (accept.sendOrderDate <='"+sendOrder_endTime.trim()+"') ");
		} 
		
		String creator = form.getAccept().getCreator() ;
		if(creator==null || creator.trim().equals(""))
			creator = "" ;
		if( (!"true".equals(user.getPermissionMap().get("accept_all")) 
			&& !"true".equals(user.getPermissionMap().get("accept_select"))) 
			|| !creator.equals("") ){
			hql.append(" and (accept.creator='"+user.getUserId()+"' or accept.managerId='"+user.getUserId()+"') ") ; 
		} 
 
		//设置排序
		String orderByName = form.getOrderByName() ;
		String orderByOrder = form.getOrderByOrder() ;
		if(orderByName!=null && !orderByName.trim().equals("")){
			hql.append(" Order By accept."+orderByName.trim()+ " "+(orderByOrder.trim().equals("1") ? "ASC" : "DESC")+" ") ;
		}else{
			hql.append(" Order By accept.createDate DESC ") ;
		} 
		
		return hql.toString() ; 
	}

	/**
	 * 报检记录单查询
	 * @param form
	 * @param user
	 * @return
	 */
	public void queryInspection(AcceptForm form,FhiUser user){
		
		// 设置当前页数
		Integer pageNoStr = form.getPageNo();
		if (pageNoStr != null) {
			int pageNo = pageNoStr;
			this.setPageNo(pageNo);
		}
				
		StringBuilder hql = new StringBuilder();
		FhiOaAcceptInspectionRecords inspection = form.getInspection();
		// 查询条件组装开始
		hql.append("from FhiOaAcceptInspectionRecords where 1=1 ");
		
		//内部号查询
		if(inspection.getInnerCode()!=null&&!"".equals(inspection.getInnerCode().trim())){
			hql.append(" and innerCode like '%"+inspection.getInnerCode().trim()+"%' ");
		}		
		
		//按时间范围查询
		if (form.getStartTime() != null && !"".equals(form.getStartTime())
				&& form.getEndTime() != null && !"".equals(form.getEndTime())) {			

				hql.append(" and (getOrderDate >='"+form.getStartTime().trim()+"') ");

				hql.append(" and (getOrderDate <='"+form.getEndTime().trim()+"') ");
		}
		
        //0为有效， 1 为无效 ；
		hql.append(" and (valid = 0) ");
		
		// 排序传接值设置
		String orderByName = form.getOrderByName();
		String orderByOrder = form.getOrderByOrder();		
		if (!"".equals(orderByName) && orderByName != null) {
			this.setOrderBy(" Order By " + orderByName + " "
					+ ("1".equals(orderByOrder) ? "ASC" : "DESC"));			
		}else{
			this.setOrderBy(" Order by createDate Desc");
		} 
		this.setHqlString(hql.toString());
	}
	/**
	 * 报检记录单导出查询
	 * @param form
	 * @param user
	 * @return
	 */
public String queryInspectionDownload(AcceptForm form,FhiUser user){
		
		// 设置当前页数
		Integer pageNoStr = form.getPageNo();
		if (pageNoStr != null) {
			int pageNo = pageNoStr;
			this.setPageNo(pageNo);
		}
				
		StringBuilder hql = new StringBuilder();
		FhiOaAcceptInspectionRecords inspection = form.getInspection();
		// 查询条件组装开始
		hql.append("select new com.fhi.accept.vo.FhiOaAcceptInspectionRecordsExcelBean(inspection) from FhiOaAcceptInspectionRecords inspection where 1=1 ");
		
		//内部号查询
		if(inspection.getInnerCode()!=null&&!"".equals(inspection.getInnerCode().trim())){
			hql.append(" and inspection.innerCode like '%"+inspection.getInnerCode().trim()+"%' ");
		}		
		
		//按时间范围查询
		if (form.getStartTime() != null && !"".equals(form.getStartTime())
				&& form.getEndTime() != null && !"".equals(form.getEndTime())) {			

				hql.append(" and (inspection.getOrderDate >='"+form.getStartTime().trim()+"') ");

				hql.append(" and (inspection.getOrderDate <='"+form.getEndTime().trim()+"') ");
		}
		
		//0为有效， 1 为无效 ；
		hql.append(" and (inspection.valid = 0) ");
		
		// 排序传接值设置
		String orderByName = form.getOrderByName();
		String orderByOrder = form.getOrderByOrder();		
		if (!"".equals(orderByName) && orderByName != null) {
			hql.append(" Order By inspection." + orderByName + " "
					+ ("1".equals(orderByOrder) ? "ASC" : "DESC"));			
		}
		else{
			hql.append(" Order by inspection.createDate Desc");
		}		
		return hql.toString();
	}

}
