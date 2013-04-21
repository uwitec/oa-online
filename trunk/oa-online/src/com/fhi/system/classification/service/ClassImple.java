package com.fhi.system.classification.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.fhi.framework.abstractservice.AbstractServiceImple;
import com.fhi.system.classification.vo.SysClassification;
import com.fhi.system.vo.SysConfig;

public class ClassImple extends AbstractServiceImple implements ClassIn {

	private static Logger logger = Logger.getLogger(ClassImple.class);

	protected SysConfig sysConfig;
	
	/**
	 * 返回本身以及所有父分类
	 * @param type
	 * @param id
	 * @return
	 */
	public List<SysClassification> queryParent(String type,String id){
		List<SysClassification> list = null;
		try {
			if(id==null||"".equals(id)){
				list = this.query("from SysClassification where type='"+type+"' and pid is null");
				if(list.size()==0){
					SysClassification class_ = new SysClassification();
					class_.setType(type);
					class_.setName(type);
					this.dbDao.addObject(class_);
					list = new ArrayList<SysClassification>();
					list.add(class_);
				}
			}
			else{
				SysClassification classObj = (SysClassification) this.dbDao.queryObjectById(SysClassification.class, id);
				if(classObj!=null){
					List<SysClassification> list_ = new ArrayList<SysClassification>();
					//迭代出所有父分类
					while (classObj.getPid()!=null) {
						list_.add(classObj);
						classObj = (SysClassification) this.dbDao.queryObjectById(SysClassification.class, classObj.getPid());
					}
					list_.add(classObj);
					//将list逆序
					list = new ArrayList<SysClassification>();
					for (int i=list_.size()-1;i>=0;i--) {
						list.add(list_.get(i));
					}
				}
			}
		} catch (Exception e) {
			logger.error("返回父分类错误！type:"+type+",id:"+id, e);
		}
		return list;
	}
	
	/**
	 * 查询子分类
	 * @param type
	 * @param id
	 * @return
	 */
	public List<SysClassification> queryChild(String type,String id){
		List<SysClassification> list=null;		
		try {
			if(id==null||"".equals(id)){
				list = this.query("from SysClassification where type='"+type+"' and pid is null");
				if(list.size()==1){
					list = this.query("from SysClassification where pid='"+list.get(0).getId()+"' Order By orderNum");
				}else{
					SysClassification class_ = new SysClassification();
					class_.setType(type);
					this.dbDao.addObject(class_);
				}
			}else{
				list = this.query("from SysClassification where pid='"+id+"' Order By orderNum");
			}
		} catch (Exception e) {
			logger.error("返回子类错误！type:"+type+",id:"+id, e);
		}
		return list;
	}	
	
	/**
	 * 查询父类含子类列表
	 * @param type
	 * @param id
	 * @return
	 */
	public List<SysClassification> queryTree(String type,String id){
		List<SysClassification> list=null;		
		try {
			if(id==null||"".equals(id)){
				list = this.query("from SysClassification where type='"+type+"' and pid is null");
				if(list.size()==0){
					SysClassification class_ = new SysClassification();
					class_.setType(type);
					class_.setName(type);
					this.dbDao.addObject(class_);
					list = new ArrayList<SysClassification>();
					class_.setChildList(this.query("from SysClassification where pid='"+class_.getId()+"' Order By orderNum"));
					list.add(class_);
				}
				else{
					for (SysClassification sysClassification : list) {
						sysClassification.setChildList(this.query("from SysClassification where pid='"+sysClassification.getId()+"' Order By orderNum"));
					}
				}
			}
			else{
				SysClassification classObj = (SysClassification) this.dbDao.queryObjectById(SysClassification.class, id);
				if(classObj!=null){
					List<SysClassification> list_ = new ArrayList<SysClassification>();
					//迭代出所有父分类
					while (classObj.getPid()!=null) {
						classObj.setChildList(this.query("from SysClassification where pid='"+classObj.getId()+"' Order By orderNum"));
						list_.add(classObj);
						classObj = (SysClassification) this.dbDao.queryObjectById(SysClassification.class, classObj.getPid());
					}
					classObj.setChildList(this.query("from SysClassification where pid='"+classObj.getId()+"' Order By orderNum"));
					list_.add(classObj);
					//将list逆序
					list = new ArrayList<SysClassification>();
					for (int i=list_.size()-1;i>=0;i--) {
						list.add(list_.get(i));
					}
				}
			}
		} catch (Exception e) {
			logger.error("返回父类列表错误！id:"+id, e);
		}
		return list;
	}
	
	/**
	 * 获取所有子分类的ID
	 * @param id
	 * @return
	 */
	public List<String> queryAllChildId(String id){
		List<String> list = new ArrayList<String>();
			List<SysClassification> list_ = this.query("from SysClassification where pid='"+id+"'");
			list.add(id);
			if(list_.size()>0){
				for (SysClassification class_ : list_) {
					list.addAll(this.queryAllChildId(class_.getId()));
				}
			}
		return list;
	}
	
	/**
	 * DWR添加分类
	 * @param class_
	 * @return
	 */
	public Boolean saveClass(SysClassification class_){
		try {
			int c = this.dbDao.count("from SysClassification where name='"+class_.getName()+"' and pid='"+class_.getPid()+"'");
			if(c==0){
				return this.dbDao.addObject(class_);
			}			
		} catch (Exception e) {
			logger.error("添加分类出错！", e);
		}
		return false;
	}
}
