package com.fhi.system.vo;

import java.util.Date;


/**
 * WmsAutoCode generated by MyEclipse Persistence Tools
 */

public class SysAutoCode  implements java.io.Serializable {


    // Fields    

     private String codeId;
     private String codeName;
     private String markCode;
     private String codeConfig;
     //是否开启 0 关闭， 1 开启
     private Integer start;
     private Integer step;
     private Integer count;
     private Integer reset;
     private Date createDate;


    // Constructors

    /** default constructor */
    public SysAutoCode() {
    }

    public SysAutoCode(String markCode) {
		this.markCode = markCode;
		this.codeName = markCode;
		this.codeConfig = "Date(yyyyMM)+Counter(000)";
		this.start = 1;
		this.step = 1;
		this.count = 1;
		this.reset = 1;
		this.createDate = new Date();
	}

	// Property accessors

    public String getCodeId() {
        return this.codeId;
    }
    
    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    public String getCodeConfig() {
        return this.codeConfig;
    }
    
    public void setCodeConfig(String codeConfig) {
        this.codeConfig = codeConfig;
    }

    public Integer getStart() {
        return this.start;
    }
    
    public void setStart(Integer start) {
        this.start = start;
    }

    /**
     * 计数器步长
     * @return
     */
    public Integer getStep() {
        return this.step;
    }
    
    /**
     * 计数器步长
     * @param step
     */
    public void setStep(Integer step) {
        this.step = step;
    }

    public Integer getCount() {
//    	int r=this.count;    	
//    	this.count=this.count+this.step;
        return this.count;
    }
    
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 计数器重置标识 0 按年 1 按月 2 按日 3按小时
     * @return
     */
    public Integer getReset() {
        return this.reset;
    }
    
    
    /**
     * 计数器重置标识 0 按年 1 按月 2 按日 3按小时
     * @return
     */
    public void setReset(Integer reset) {
        this.reset = reset;
    }

    public Date getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

	public String getMarkCode() {
		return markCode;
	}

	public void setMarkCode(String markCode) {
		this.markCode = markCode;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
}