package com.fhi.permission.vo;

public class FhiRolePermission implements java.io.Serializable{
	
	private String id;
	private String roleCode;
	private String moduleCode;
	private String moduleLevel;
	private String moduleName;
	private String modulePid;
	private String moduleType;
	private String permissionCode;
	

	public FhiRolePermission(String id, String roleCode, String moduleCode,
			String moduleLevel, String moduleName, String modulePid,
			String moduleType, String permissionCode) {
		super();
		this.id = id;
		this.roleCode = roleCode;
		this.moduleCode = moduleCode;
		this.moduleLevel = moduleLevel;
		this.moduleName = moduleName;
		this.modulePid = modulePid;
		this.moduleType = moduleType;
		this.permissionCode = permissionCode;
	}

	public FhiRolePermission(){
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getModuleCode() {
		return moduleCode;
	}
	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleLevel() {
		return moduleLevel;
	}

	public void setModuleLevel(String moduleLevel) {
		this.moduleLevel = moduleLevel;
	}

	public String getModulePid() {
		return modulePid;
	}

	public void setModulePid(String modulePid) {
		this.modulePid = modulePid;
	}

	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

	public String getPermissionCode() {
		return permissionCode;
	}

	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}

}
