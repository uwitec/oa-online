package com.fhi.employee.action;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.fhi.employee.vo.FhiOaEmployeeBasic;
import com.fhi.employee.vo.FhiOaEmployeeEducation;
import com.fhi.employee.vo.FhiOaEmployeeFamily;
import com.fhi.employee.vo.FhiOaEmployeeWork;
public class EmployeeForm extends ActionForm {
	
	private Integer pageNo;
	private String orderByBame;
	private String orderByOrder;
	private String timeitem ;
	private String startTime ; 
	private String endTime ; 
	private String []emids ; 
	private FormFile headpic  ; 
	private FormFile idcardPic  ; 
	private FormFile accountPic  ; 
	private FormFile degreePic  ; 
	private FormFile drivePic  ; 
	private FormFile graduatePic  ; 
	private FormFile gatewayPic  ; 
	private FormFile checkPic  ; 
	
	private String birthday ;  //生日 
	private String InWorkDate ; //入职时间
	private String realWorkDate ; //转正时间
	private String offWorkDate ;  //离职时间
	private String endDate ;  //合同到期时间
	private String nextCheck ; //下次验本时间
	private String startWrokDate ; //下次验本时间
	
	private FhiOaEmployeeBasic basic = new FhiOaEmployeeBasic() ;
	private FhiOaEmployeeEducation education = new FhiOaEmployeeEducation() ;
	private FhiOaEmployeeFamily family = new FhiOaEmployeeFamily() ;
	private FhiOaEmployeeWork work = new FhiOaEmployeeWork() ;
	
	Logger logger = Logger.getLogger(EmployeeForm.class) ;
	
	public FhiOaEmployeeBasic getBasic() {
		return basic;
	}
	public void setBasic(FhiOaEmployeeBasic basic) {
		this.basic = basic;
	}
	public FhiOaEmployeeEducation getEducation() {
		return education;
	}
	public void setEducation(FhiOaEmployeeEducation education) {
		this.education = education;
	}
	public String[] getEmids() {
		return emids;
	}
	public void setEmids(String[] emids) {
		this.emids = emids;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime; 
	}
	public FhiOaEmployeeFamily getFamily() {
		return family;
	}
	public void setFamily(FhiOaEmployeeFamily family) {
		this.family = family;
	}
	public String getOrderByBame() {
		return orderByBame;
	}
	public void setOrderByBame(String orderByBame) {
		this.orderByBame = orderByBame;
	}
	public String getOrderByOrder() {
		return orderByOrder;
	}
	public void setOrderByOrder(String orderByOrder) {
		this.orderByOrder = orderByOrder;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public FhiOaEmployeeWork getWork() {
		return work;
	}
	public void setWork(FhiOaEmployeeWork work) {
		this.work = work;
	}
	public FormFile getHeadpic() {
		return headpic;
	}
	public void setHeadpic(FormFile headpic) {
		this.headpic = headpic;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getInWorkDate() {
		return InWorkDate;
	}
	public void setInWorkDate(String inWorkDate) {
		InWorkDate = inWorkDate;
	}
	public String getNextCheck() {
		return nextCheck;
	}
	public void setNextCheck(String nextCheck) {
		this.nextCheck = nextCheck;
	}
	public String getOffWorkDate() {
		return offWorkDate;
	}
	public void setOffWorkDate(String offWorkDate) {
		this.offWorkDate = offWorkDate;
	}
	public String getRealWorkDate() {
		return realWorkDate;
	}
	public void setRealWorkDate(String realWorkDate) {
		this.realWorkDate = realWorkDate;
	}
	public String getStartWrokDate() {
		return startWrokDate;
	}
	public void setStartWrokDate(String startWrokDate) {
		this.startWrokDate = startWrokDate;
	}
	public FormFile getAccountPic() {
		return accountPic;
	}
	public void setAccountPic(FormFile accountPic) {
		this.accountPic = accountPic;
	}
	public FormFile getCheckPic() {
		return checkPic;
	}
	public void setCheckPic(FormFile checkPic) {
		this.checkPic = checkPic;
	}
	public FormFile getDegreePic() {
		return degreePic;
	}
	public void setDegreePic(FormFile degreePic) {
		this.degreePic = degreePic;
	}
	public FormFile getDrivePic() {
		return drivePic;
	}
	public void setDrivePic(FormFile drivePic) {
		this.drivePic = drivePic;
	}
	public FormFile getGatewayPic() {
		return gatewayPic;
	}
	public void setGatewayPic(FormFile gatewayPic) {
		this.gatewayPic = gatewayPic;
	}
	public FormFile getGraduatePic() {
		return graduatePic;
	}
	public void setGraduatePic(FormFile graduatePic) {
		this.graduatePic = graduatePic;
	}
	public FormFile getIdcardPic() {
		return idcardPic;
	}
	public void setIdcardPic(FormFile idcardPic) {
		this.idcardPic = idcardPic;
	}
	public String getTimeitem() {
		return timeitem;
	}
	public void setTimeitem(String timeitem) {
		this.timeitem = timeitem;
	} 
}
