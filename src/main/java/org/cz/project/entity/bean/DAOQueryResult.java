/**
 * 
 */
package org.cz.project.entity.bean;

import java.util.Date;

/**
 * @ClassName: DAOQueryResult
 * @Description:this class use to store the db Query source.
 * @author Huang.Jilong
 * @date 2013-11-29 13:51:13
 * 
 */
public class DAOQueryResult<T> {

	private long total;

	private long page;

	private long rows;

	private long pageSize;
	private Date d;
	
	private String status;
	
	private String message;
	

	public void setTotal(long total) {
		this.total = total;
	}

	public Date getD() {
		return d;
	}

	public void setD(Date d) {
		this.d = d;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setPage(long page) {
		this.page = page;
	}

	public void setRows(long rows) {
		this.rows = rows;
	}

	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

	private T result;

	/**
	 * @param total >=0 
	 * @param page [1..n] if(page>this.pageSize||page<=0) page=1;
	 * @param row [1..n] if row<=0 than Math.abs(row==0?1:row)
	 */
	public DAOQueryResult(long total, long page, long row) {
		super();
		this.total = Math.abs(total);
		if(this.total==0)
		{
			this.rows=0;
			this.pageSize=0;
		}
		else
		{
			this.rows = Math.abs(row==0?this.total:row);
			this.pageSize=(long)Math.ceil(this.total/(this.rows+0.0));
			if(page>this.pageSize||page<=0)
			{
				this.page=1;
			}
			else
			{
				this.page = page;
			}
		}
	}

	public Long getTotal() {
		return total;
	}

	public long getPage() {
		return page;
	}

	public long getRows() {
		return rows;
	}

	public long getPageSize() {
		return pageSize;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public static void main(String[] args) {
		System.out.println((long)Math.ceil(7 / (9l+0.0)));
	}
	// TODO Auto-generated method stub

}
