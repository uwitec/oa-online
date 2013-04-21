package com.fhi.permission.vo;

public class FhiModule implements java.io.Serializable{
	
	private String id;
	private String moduleCode;
	private String moduleUrl;
	private String moduleName;
	private String moduleLevel;
	private String pid;
	private String moduleType;
	private String isPid;
	private String priority;
	private String module;
	
	public FhiModule(String id, String moduleCode, String moduleUrl,
			String moduleName, String moduleLevel, String pid,
			String moduleType, String isPid, String priority) {
		super();
		this.id = id;
		this.moduleCode = moduleCode;
		this.moduleUrl = moduleUrl;
		this.moduleName = moduleName;
		this.moduleLevel = moduleLevel;
		this.pid = pid;
		this.moduleType = moduleType;
		this.isPid = isPid;
		this.priority = priority;
	}

	public FhiModule(){
		
	}

	public String getModuleLevel() {
		return moduleLevel;
	}

	public void setModuleLevel(String moduleLevel) {
		this.moduleLevel = moduleLevel;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getModuleCode() {
		return moduleCode;
	}
	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}
	public String getModuleUrl() {
		return moduleUrl;
	}
	public void setModuleUrl(String moduleUrl) {
		this.moduleUrl = moduleUrl;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

	public String getIsPid() {
		return isPid;
	}

	public void setIsPid(String isPid) {
		this.isPid = isPid;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}
}
