/*package com.fhi.accept.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.fhi.accept.vo.FhiOaAccept;
import com.fhi.accept.vo.FhiOaAcceptInspectionRecords;
import com.fhi.accept.vo.FhiOaRetmoney;
import com.fhi.costsheet.costmaster.service.CostMasterIn;
import com.fhi.framework.abstractservice.AbstractServiceImple;
import com.fhi.framework.page.Condition;
import com.fhi.framework.page.HQuery;
import com.fhi.framework.page.ListTableCourier;
import com.fhi.framework.page.Paras;
import com.fhi.framework.page.ParasList;
import com.fhi.system.sysmsg.vo.SysMsgSetup;

public class AcceptImpl extends AbstractServiceImple implements AcceptIn {
	
	Logger logger = Logger.getLogger(AcceptImpl.class) ;  
	private CostMasterIn costMasterService;
	
//  查询方法
	public FhiOaAccept getAcceptById(String id){ 
		try{
			return (FhiOaAccept)this.dbDao.queryObjectById(FhiOaAccept.class, id.trim()) ;
		}catch(Exception e){
			logger.error("接单表对象查询失败",e) ;
		}
		return null ;
	}
	public FhiOaAccept getAcceptByCode(String code){
		String hql = " from FhiOaAccept where innerCode='"+code.trim()+"' " ;
		try{
			List list = this.dbDao.queryObjects(hql) ;
			if(list!=null && list.size()>0){
				return (FhiOaAccept)list.get(0) ;
			}
		}catch(Exception e){
			logger.error("接单表对象查询失败",e) ;
		}
		return null ; 
	}

	public List<SysMsgSetup> getEmergenceByAcceptId(String acceptId){
		String hql = " from SysMsgSetup where linked='"+acceptId.trim()+"' " ;
		try{
			return this.dbDao.queryObjects(hql) ; 
		}catch(Exception e){
			logger.error("提醒对象查询失败",e) ;
		}
		return null ; 
	}
	public List<FhiOaRetmoney> getRetMoneyByAcceptId(String acceptId){
		String hql = " from FhiOaRetmoney where acceptOrderId='"+acceptId.trim()+"' " ;
		try{
			return this.dbDao.queryObjects(hql) ; 
		}catch(Exception e){
			logger.error("分段还款对象查询失败",e) ;
		}
		return null ; 
	}
	
	
	public List<FhiOaAccept> getAccepts(Condition con){
		try{
			return (List<FhiOaAccept>)this.dbDao.queryObjectsToPages(this.setPageInfo(con)); 
		}catch(Exception e){
			logger.error("接单表对象查询失败",e) ;
		}
		return null ; 
	} 

	 
//  添加方法
	public boolean addAccept(FhiOaAccept accept,List<SysMsgSetup> emergences, List<FhiOaRetmoney> moneys)throws Exception{ 
	    
		BigDecimal mayMoney = new BigDecimal("0") ;
		BigDecimal taxMoney = new BigDecimal("0") ;
		BigDecimal matMoney = new BigDecimal("0") ; 
		BigDecimal otherMoney = new BigDecimal("0") ; 
	    if(emergences!=null && emergences.size()>0){
	    	for(SysMsgSetup em:emergences){
	    		em.setLinked(accept.getId()) ; 
	    		this.dbDao.addObject(em) ;
	    	}
	    }
	    if(moneys!=null && moneys.size()>0){
	    	for(FhiOaRetmoney one:moneys){
	    		one.setAcceptOrderId(accept.getId()) ;
	    		this.dbDao.addObject(one) ;
	    		if(one.getMoneyItem().trim().equals("mayMoney"))
	    			mayMoney =mayMoney.add(new BigDecimal(one.getRetCount()+""))   ;
	    		if(one.getMoneyItem().trim().equals("matMoney"))
	    			//matMoney +=one.getRetCount() ;
	    			matMoney =matMoney.add(new BigDecimal(one.getRetCount()+""))   ;
	    		if(one.getMoneyItem().trim().equals("taxMoney"))
	    			//taxMoney +=one.getRetCount() ;
	    			taxMoney =taxMoney.add(new BigDecimal(one.getRetCount()+""))   ;
	    	    if(one.getMoneyItem().trim().equals("otherMoney"))
	    	    	//otherMoney +=one.getRetCount() ;
	    	    	otherMoney =otherMoney.add(new BigDecimal(one.getRetCount()+""))   ; 
	    	}
	    } 
	    accept.setMatCount(matMoney);
	    accept.setTaxCount(taxMoney);
	    accept.setMayCount(mayMoney);
	    accept.setOtherCount(otherMoney) ;
	    
	    //判断接单表是否含有报检业务 有责创建报检单
	    if(accept.getInnerCode().toUpperCase().indexOf("JB")>=0){
	    	FhiOaAcceptInspectionRecords inspection = new FhiOaAcceptInspectionRecords(accept);	    	
	    	logger.debug("接单表"+accept.getInnerCode()+"创建报检单");
	    	this.dbDao.addObject(inspection);
	    }	    

   	    logger.info("添加接单表信息 , id="+accept.getId()+" 内部号="+accept.getInnerCode()) ;
	    this.dbDao.addObject(accept) ; 
	    costMasterService.saveCostMasterMain(accept);
		return true ; 
	}
//	修改方法
	public boolean updateAccept(FhiOaAccept accept,List<SysMsgSetup> emergences, 
			List<FhiOaRetmoney> moneys,List<SysMsgSetup> emergences_del, List<FhiOaRetmoney> moneys_del)
	       throws Exception {
		BigDecimal mayMoney = new BigDecimal("0") ;
		BigDecimal taxMoney = new BigDecimal("0") ;
		BigDecimal matMoney = new BigDecimal("0") ; 
		BigDecimal otherMoney = new BigDecimal("0") ; 
		 
			 if(emergences_del!=null && emergences_del.size()>0){
			 for(SysMsgSetup em:emergences_del){ 
					 this.dbDao.deleteObject(em) ; 
			 } }

			 if(moneys_del!=null && moneys_del.size()>0){
			 for(FhiOaRetmoney em:moneys_del){ 
				 this.dbDao.deleteObject(em) ; 
		     }}
			 

			 if(emergences!=null){
			 for(SysMsgSetup em:emergences){
				 if(em.getId()==null || em.getId().trim().equals("")){
					 em.setLinked(accept.getId()) ;
					 this.dbDao.addObject(em) ;
				 }else{
					 this.dbDao.updateObject(em) ;
				 } 
			 }}
			 

			 if(moneys!=null){
			 for(FhiOaRetmoney money:moneys){
				 if(money.getMoneyItem().trim().equals("mayMoney"))
		    			//mayMoney +=money.getRetCount() ;
				 mayMoney =mayMoney.add(new BigDecimal(money.getRetCount()+""))   ; 
		    	 if(money.getMoneyItem().trim().equals("matMoney"))
		    			//matMoney +=money.getRetCount() ;
		    	 matMoney =matMoney.add(new BigDecimal(money.getRetCount()+""))   ; 
		         if(money.getMoneyItem().trim().equals("taxMoney"))
		    			//taxMoney +=money.getRetCount() ;
		         taxMoney =taxMoney.add(new BigDecimal(money.getRetCount()+""))   ; 
		    	 if(money.getMoneyItem().trim().equals("otherMoney"))
		    	    	//otherMoney +=money.getRetCount() ;
		    	 otherMoney =otherMoney.add(new BigDecimal(money.getRetCount()+""))   ; 
				 if(money.getId()==null || money.getId().trim().equals("")){
					 money.setAcceptOrderId(accept.getId()) ;
					 this.dbDao.addObject(money) ;
				 }else{
					 this.dbDao.updateObject(money) ;
				 } 
			 } }
			 
			 accept.setMatCount(matMoney) ;
			 accept.setTaxCount(taxMoney);
			 accept.setMayCount(mayMoney);
			 accept.setOtherCount(otherMoney) ;
			 
			 
			 //判断接单表是否含有报检业务
			 //判断修改后接单表是否含有JB字符
			if(accept.getInnerCode().toUpperCase().indexOf("JB")>=0){
				//判断数据库中是否含有此报检记录单 如果有 进行修改 没有就创建
				if(this.dbDao.count("from FhiOaAcceptInspectionRecords where id='"+accept.getId()+"'")==1){
		    		FhiOaAcceptInspectionRecords inspection = (FhiOaAcceptInspectionRecords) dbDao.queryObjectById(FhiOaAcceptInspectionRecords.class, accept.getId());
		    		if(inspection.update(accept)){
		    			logger.debug("修改接单表 同时修改报检记录单信息"+accept.getInnerCode());
		    			this.dbDao.updateObject(inspection);
		    		}	    		
		    	}
				else{
					FhiOaAcceptInspectionRecords inspection = new FhiOaAcceptInspectionRecords(accept);
					logger.debug("接单表修改为含有报检业务 创建报检记录单信息"+accept.getInnerCode());
			    	this.dbDao.addObject(inspection);
				}
		    }
			else{
				//判断如果已经有此条报检记录 删除
				if(this.dbDao.count("from FhiOaAcceptInspectionRecords where id='"+accept.getId()+"'")==1){
		    		String[] delIds = {accept.getId()};
		    		logger.debug("接单表修改为不含有报检业务 删除报检记录单信息"+accept.getInnerCode());
		    		this.dbDao.deleteObjects(FhiOaAcceptInspectionRecords.class, delIds);
		    	}
			}

        	 logger.info("修改接单表信息 , id="+accept.getId()+" 内部号="+accept.getInnerCode()) ;
			 this.dbDao.updateObject(accept) ;
			 costMasterService.saveCostMasterMain(accept);
			 
			 return true ; 
	}
	
	public boolean setState(SysMsgSetup state){
		FhiOaAccept accept = this.getAcceptById(state.getLinked()) ;
		int sysclass = state.getSysClass().intValue() ;
		if(accept.getState()!=null && !accept.getState().trim().equals("moneyover")){
			if(sysclass==1 || sysclass==3 ||sysclass==5){
				accept.setState("realalarm") ;
			}else if(sysclass==2 || sysclass==4 ||sysclass==6){
				accept.setState("prealarm") ;
			}
			this.update(accept) ;
		} 
		return true ;
	}
//	删除方法
//	public boolean deleteAccept(FhiOaAccept accept)throws Exception{ 
//	    this.dbDao.deleteObject(accept) ; 
//		String sql = " delete from fhi_sys_MsgSetup where linked='"+accept.getId().trim()+"'" ;   
//	    this.jdbcDbDao.execute(sql) ;
//	    sql = " delete from fhi_oa_retmoney where acceptOrderId='"+accept.getId().trim()+"'" ; 
//	    this.jdbcDbDao.execute(sql) ;
//		return true ;
//	}
	public boolean deleteAcceptById(String id)throws Exception{  
		String sql = " delete from fhi_sys_MsgSetup where linked='"+id.trim()+"'" ;   
	    this.jdbcDbDao.execute(sql) ;
	    sql = " delete from fhi_oa_retmoney where acceptOrderId='"+id.trim()+"'" ; 
	    this.jdbcDbDao.execute(sql) ;
	    sql = " delete from fhi_oa_accept where id='"+id.trim()+"'" ;  
	    this.jdbcDbDao.execute(sql) ;
	    sql = " delete from fhi_oa_accept_InspectionRecords where id='"+id.trim()+"'" ;  
	    this.jdbcDbDao.execute(sql) ;
		return true ;
	} 
	public boolean deleteAccepts(String []ids)throws Exception{ 
        for(String id:ids){  
        	logger.info("删除接单表信息 , id="+id+" 内部号="+this.getAcceptById(id).getInnerCode()) ;
        	this.deleteAcceptById(id);
        }
        return true ;
	} 
	
	public boolean hasAccept(String code,String id){
		String hql = " from FhiOaAccept where innerCode='"+code.trim()+"'" ;
		if(id!=null && !id.trim().equals(""))
			hql += " and id!='"+id.trim()+"' " ; 
		int c = this.dbDao.count(hql) ;
		if(c>0) return true ;
		else return false ; 
	} 
	
	*//**
	 * DWR查询接单表主单
	 * @param courier
	 * @param request
	 * @return
	 *//*
	public ListTableCourier getAcceptMainList(ListTableCourier courier,HttpServletRequest request){
		
		//获取查询信息
		StringBuilder hql= new StringBuilder("from FhiOaAccept where id not in (select costMasterMain.id from FhiCostMasterMain as costMasterMain)");
	    
	    HQuery hquery = new HQuery();	    
	    ParasList pList = new ParasList();
	    Map<String,String> searchMap = courier.getSearchData();
	    
	    if(searchMap.get("innerCode")!=null && !"".equals(searchMap.get("innerCode"))){
			hql.append(" and innerCode like ? ");
			Paras p = new Paras("%"+searchMap.get("innerCode")+"%");
			pList.add(p);
	    }
	    
		if(searchMap.get("state")!=null && !"".equals(searchMap.get("state"))){
			hql.append(" and state != ? ");
			Paras p = new Paras(searchMap.get("state"));
			pList.add(p);
		}
		hquery.setQueryString(hql.toString());
		hquery.setParaslist(pList);
		hquery.setOrderby(" Order By createDate Desc");
	    
//	    //检查是否有传递信息
//	    HttpSession session = request.getSession();
//	    String message = (String) session.getAttribute("CostMaster_Message");
//	    if(message!=null&&!"".equals(message)){
//	    	courier.setMessage(message);
//	    	session.setAttribute("CostMaster_Message",null);
//	    }
		return this.query(hquery, courier);
	}
	public void setCostMasterService(CostMasterIn costMasterService) {
		this.costMasterService = costMasterService;
	}
}
*/