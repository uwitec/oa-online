
package com.fhi.framework.page;

import java.io.Serializable;



/**
 * 基本查询条件
 *  
 * @author liangwj
 */
public class PageInfo  implements Serializable,Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7448688547460880575L;

	protected int pageNo;// 页号

	protected int crossPages;// 总页数

	protected int rowCounts;// 记录总数

	protected int rowsPerpage ;//每页显示记录数目

	protected int currentRow; //当前行号

	public PageInfo() {
		this.init();
	}
	
	public void init(){
		pageNo = 1;
		crossPages = 1;
		rowCounts = 0;
		currentRow = 0;
		rowsPerpage = 10;
	}
	
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getCrossPages() {
		return crossPages;
	}

	public void setCrossPages(int crossPages) {
		this.crossPages = crossPages;
	}

	public int getRowCounts() {
		return rowCounts;
	}

	public void setRowCounts(int rowCounts) {
		this.rowCounts = rowCounts;
	}

	public int getRowsPerpage() {
		return rowsPerpage;
	}

	public void setRowsPerpage(int rowsPerpage) {
		this.rowsPerpage = rowsPerpage;
	}

	public int getCurrentRow() {
		return currentRow;
	}

	public void setCurrentRow(int currentRow) {
		this.currentRow = currentRow;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
}
