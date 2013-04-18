package com.fhi.information.picture.condition;

import java.util.List;

import com.fhi.framework.page.Condition;
import com.fhi.information.picture.form.PictureForm;
import com.fhi.information.picture.vo.FhiPicture;
import com.fhi.permission.service.PermissionIn;
import com.fhi.permission.vo.FhiRole;
import com.fhi.system.classification.service.ClassIn;
import com.fhi.user.vo.FhiUser;
public class PictureCondition extends Condition{
	private ClassIn classService;
	private PermissionIn permissionImple;
	
	public void queryIndex(PictureForm form,FhiUser user){
		// 设置当前页数
		Integer pageNoStr = form.getPageNo();
		if (pageNoStr != null) {
			int pageNo = pageNoStr;
			this.setPageNo(pageNo);
		}
		
		// 查询条件组装开始
		StringBuilder sql = new StringBuilder("from FhiPicture where 1=1");
		
		FhiPicture pic = form.getPic();
		if (pic.getCode() != null
				&& !"".equals(pic.getCode())) {
			sql.append(" and code like '%" + pic.getCode() + "%'");
		}
		//可见性HQL拼装
		if("false".equals(user.getPermissionStr("isWatchPhotoAll")))
		{
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
		if (pic.getClassId()!= null
				&& !"".equals(pic.getClassId())) {
			List<String> list = classService.queryAllChildId(pic.getClassId());
			sql.append(" and ( classId='"+pic.getClassId()+"'");
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
			this.setOrderBy(" Order by code Desc");
		}
		// 查询条件组装结束
		this.setHqlString(sql.toString());
	}
	
	public void queryManagerIndex(PictureForm form,FhiUser user){
		// 设置当前页数
		Integer pageNoStr = form.getPageNo();
		if (pageNoStr != null) {
			int pageNo = pageNoStr;
			this.setPageNo(pageNo);
		}
		
		// 查询条件组装开始
		StringBuilder sql = new StringBuilder("from FhiPicture where 1=1");
		
		FhiPicture pic = form.getPic();
		if (pic.getCode() != null
				&& !"".equals(pic.getCode())) {
			sql.append(" and code like '%" + pic.getCode() + "%'");
		}
		//可见性HQL拼装
		if("false".equals(user.getPermissionStr("isManagerPhotoAll")))
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
			//任何人可见自己发布的文档
			sql.append(" and creater='"+user.getUserId()+"' ");
		}
		//按分类搜索
		if (pic.getClassId()!= null
				&& !"".equals(pic.getClassId())) {
			List<String> list = classService.queryAllChildId(pic.getClassId());
			sql.append(" and ( classId='"+pic.getClassId()+"'");
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
			this.setOrderBy(" Order by code Desc");
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
