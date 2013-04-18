package com.fhi.information.document.condition;

import java.util.List;

import com.fhi.framework.page.Condition;
import com.fhi.information.document.form.DocumentForm;
import com.fhi.information.document.vo.FhiDocument;
import com.fhi.permission.service.PermissionIn;
import com.fhi.permission.vo.FhiRole;
import com.fhi.system.classification.service.ClassIn;
import com.fhi.user.vo.FhiUser;
public class DocumentCondition extends Condition{
	private ClassIn classService;
	private PermissionIn permissionImple;
	
	public void queryIndex(DocumentForm form,FhiUser user){
		// 设置当前页数
		Integer pageNoStr = form.getPageNo();
		if (pageNoStr != null) {
			int pageNo = pageNoStr;
			this.setPageNo(pageNo);
		}
		
		// 查询条件组装开始
		StringBuilder sql = new StringBuilder("from FhiDocument where 1=1");
		
		FhiDocument docm = form.getDocm();
		if (docm.getCode() != null
				&& !"".equals(docm.getCode())) {
			sql.append(" and code like '%" + docm.getCode() + "%'");
		}
		//可见性HQL拼装   如果没有查询全局的权限 则进行可见性拼装
		if("false".equals(user.getPermissionStr("isWatchDocumentAll"))){
			sql.append(" and (");
			//按角色查询
			//TODO 权限设置 如果 没有全部查看权限 进行可见性HQL拼装 
			if(true){
				FhiRole role = user.getRole().values().iterator().next();
				List<String> roleIdList = permissionImple.queryRoleParentID(role);
				sql.append("( visible like '%"+role.getId()+"%'");
				for (String string : roleIdList) {
					sql.append(" or visible like '%"+string+"%'");
				}
				sql.append(" ) ");			
			}
			//任何人可见自己发布的文档
			sql.append(" or creater='"+user.getUserId()+"')");
		}
		//按分类搜索
		if (docm.getClassId()!= null
				&& !"".equals(docm.getClassId())) {
			List<String> list = classService.queryAllChildId(docm.getClassId());
			sql.append(" and ( classId='"+docm.getClassId()+"'");
			for (String string : list) {
				sql.append(" or classId='" + string + "'");
			}
			sql.append(" ) ");
		}
		
		//展示页面查询
		if (form.getQueryText()!= null
				&& !"".equals(form.getQueryText())
				&& form.getQueryType()!= null
				&& !"".equals(form.getQueryType())) {
			
			String queryType = form.getQueryType();
			StringBuilder subHql = new StringBuilder();
			if(queryType.indexOf("0")>=0){
				subHql.append(" or code like '%" + form.getQueryText() + "%'");
			}
			if(queryType.indexOf("1")>=0){
				subHql.append(" or title like '%" + form.getQueryText() + "%'");
			}
			if(queryType.indexOf("2")>=0){
				subHql.append(" or content like '%" + form.getQueryText() + "%'");
			}
			if(queryType.indexOf("3")>=0){
				subHql.append(" or files like '%" + form.getQueryText() + "%'");
			}
			sql.append(" and (" + subHql.substring(3) + " ) ");
		}
		
		
		
		//按时间范围查询
		if (form.getStartTime() != null && !"".equals(form.getStartTime())
				&& form.getEndTime() != null && !"".equals(form.getEndTime())) {
			sql.append(" and createDate BETWEEN '" + form.getStartTime()
					+ "' AND '" + form.getEndTime() + "'");
		}
		
		// 排序传接值设置
		String orderByName = form.getOrderByName();
		String orderByOrder = form.getOrderByOrder();		
		if (!"".equals(orderByName) && orderByName != null) {
			this.setOrderBy(" Order By " + orderByName + " "
					+ ("1".equals(orderByOrder) ? "ASC" : "DESC"));
		}
		else{
			this.setOrderBy(" Order by createDate Desc");
		}
		// 查询条件组装结束
		this.setHqlString(sql.toString());
	}
	
	public void queryManagerIndex(DocumentForm form,FhiUser user){
		// 设置当前页数
		Integer pageNoStr = form.getPageNo();
		if (pageNoStr != null) {
			int pageNo = pageNoStr;
			this.setPageNo(pageNo);
		}
		
		// 查询条件组装开始
		StringBuilder sql = new StringBuilder("from FhiDocument where 1=1");
		
		FhiDocument docm = form.getDocm();
		if (docm.getCode() != null
				&& !"".equals(docm.getCode())) {
			sql.append(" and code like '%" + docm.getCode() + "%'");
		}
		//可见性HQL拼装   如果没有管理全局的权限 则进行可见性拼装
		if("false".equals(user.getPermissionStr("isManagerDocumentAll")))
		{
//			sql.append(" and (");
//			//按角色查询
//			//TODO 权限设置 如果 没有全部查看权限 进行可见性HQL拼装 
//			if(true){
//				FhiRole role = user.getRole().values().iterator().next();
//				List<String> roleIdList = permissionImple.queryRoleParentID(role);
//				sql.append("( visible like '%"+role.getId()+"%'");
//				for (String string : roleIdList) {
//					sql.append(" or visible like '%"+string+"%'");
//				}
//				sql.append(" ) ");			
//			}
//			//任何人可见自己发布的文档
			sql.append(" and creater='"+user.getUserId()+"' ");
		}
		
		//模糊查询
		if (form.getQueryText()!= null
				&& !"".equals(form.getQueryText())
				&& form.getQueryType()!= null
				&& !"".equals(form.getQueryType())) {
			
			String queryType = form.getQueryType();
			StringBuilder subHql = new StringBuilder();
			if(queryType.indexOf("0")>=0){
				subHql.append(" or code like '%" + form.getQueryText() + "%'");
			}
			if(queryType.indexOf("1")>=0){
				subHql.append(" or title like '%" + form.getQueryText() + "%'");
			}
			if(queryType.indexOf("2")>=0){
				subHql.append(" or content like '%" + form.getQueryText() + "%'");
			}
			if(queryType.indexOf("3")>=0){
				subHql.append(" or files like '%" + form.getQueryText() + "%'");
			}
			sql.append(" and (" + subHql.substring(3) + " ) ");
		}
		
		//按分类搜索
		if (docm.getClassId()!= null
				&& !"".equals(docm.getClassId())) {
			List<String> list = classService.queryAllChildId(docm.getClassId());
			sql.append(" and ( classId='"+docm.getClassId()+"'");
			for (String string : list) {
				sql.append(" or classId='" + string + "'");
			}
			sql.append(" ) ");
		}
				
		
		//按时间范围查询
		if (form.getStartTime() != null && !"".equals(form.getStartTime())
				&& form.getEndTime() != null && !"".equals(form.getEndTime())) {
			sql.append(" and createDate BETWEEN '" + form.getStartTime()
					+ "' AND '" + form.getEndTime() + "'");
		}
		
		// 排序传接值设置
		String orderByName = form.getOrderByName();
		String orderByOrder = form.getOrderByOrder();		
		if (!"".equals(orderByName) && orderByName != null) {
			this.setOrderBy(" Order By " + orderByName + " "
					+ ("1".equals(orderByOrder) ? "ASC" : "DESC"));
		}
		else{
			this.setOrderBy(" Order by createDate Desc");
		}
		// 查询条件组装结束
		this.setHqlString(sql.toString());
	}

	public void setClassService(ClassIn classService) {
		this.classService = classService;
	}

	public void setPermissionImple(PermissionIn permissionImple) {
		this.permissionImple = permissionImple;
	}	
}
