/*
 * Created on 2004-10-30
 */
package  com.fhi.framework.page;

/**
 * 
 * <p>
 * Title: HQL的语句封装类
 * </p>
 * <p>
 * Description: 该对象封装HQL的查询语句，参数集合，排序参数，分组参数，单页起始地址
 * </p>
 */

public class HQuery {

	/**
	 * HQL查询语句
	 */
	private String queryString="";

	/**
	 * 参数集合对象
	 */
	private ParasList paraslist=null;

	/**
	 * 排序字段
	 */
	private String orderby="";

	/**
	 * 分组字段
	 */
	private String groupby="";

	/**
	 * 分页起始查询地址
	 */
	private int pageStartNo=1;

	/**
	 * 每页显示记录数
	 */
	private int rowsPerpage=10;

	/**
	 * 取得一个Hibernate的Query对象
	 * 
	 * @return：Query对象
	 */
	public String getQueryString() {
		return queryString;
	}

	/**
	 * 设置一个HQL查询字符串
	 * 
	 * @param queryString：查询字符串
	 * 
	 */
	public void setQueryString(String queryString) {

		this.queryString = queryString;

	}

	/**
	 * 取得参数集合对象
	 * 
	 * @return：参数集合对象
	 */
	public ParasList getParaslist() {
		return paraslist;
	}

	/**
	 * 设置参数集合对象
	 * 
	 * @param paralist：参数集合对象
	 */
	public void setParaslist(ParasList paraslist) {
		this.paraslist = paraslist;
	}

	/**
	 * 取得排序字段
	 * 
	 * @return：排序字段
	 */
	public String getOrderby() {
		return orderby;
	}

	/**
	 * 设置排序字段
	 * 
	 * @param orderby
	 */
	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	/**
	 * 取得分组字段
	 * 
	 * @return
	 */
	public String getGroupby() {
		return groupby;
	}

	/**
	 * 设置分组字段
	 * 
	 * @param groupby
	 */
	public void setGroupby(String groupby) {
		this.groupby = groupby;
	}

	/**
	 * 取得页起始地址
	 * 
	 * @return
	 */
	public int getPageStartNo() {
		return pageStartNo;
	}

	/**
	 * 设置页起始地址
	 * 
	 * @param pageStartNo
	 */
	public void setPageStartNo(int pageStartNo) {
		this.pageStartNo = pageStartNo;
	}

	/**
	 * @return Returns the rowsPerpage.
	 */
	public int getRowsPerpage() {
		return rowsPerpage;
	}

	/**
	 * @param rowsPerpage
	 *            The rowsPerpage to set.
	 */
	public void setRowsPerpage(int rowsPerpage) {
		this.rowsPerpage = rowsPerpage;
	}
	
	public void clear(){
		this.groupby="";
		this.orderby="";
		this.pageStartNo=1;
		this.queryString="";
	}

}
