/*
 * Created on 2004-10-30
 */
package  com.fhi.framework.page;

import java.sql.Types;

/**
 * 
 * <p>
 * Title:定义一个sql语句的条件参数类
 * </p>
 * <p>
 * Description: 可以使用有序的参数集合传送给sql/hql语句
 * </p>
 */

public class Paras {
	/**
	 * 参数名称
	 */
	private Object pName;

	/**
	 * 参数类型编码，于java.sql.types中的类型保持一致
	 */
	private int typeNo;
	
	public Paras(){
		
	}
	
	public Paras(Object pName,int typeNo){
		this.pName = pName;
		this.typeNo = typeNo;
	}
	/**
	 * 默认字符串参数
	 * @param pName
	 */
	public Paras(String pName){
		this.pName = pName;
		this.typeNo = Types.VARCHAR;
	}

	public Object getPName() {
		return pName;
	}

	public void setPName(Object pName) {
		this.pName = pName;
	}

	public int getTypeNo() {
		return typeNo;
	}

	public void setTypeNo(int typeNo) {
		this.typeNo = typeNo;
	}
}
