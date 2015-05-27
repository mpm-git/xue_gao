/**
 * 
 */
package org.cz.project.jpa_base_service;


/**
 * @ClassName: QueryResult
 * @Description:this class use to store the db Query source.
 * @author leo.huang
 * @date 2013-11-29 13:51:13
 * 
 */
public class QueryResult<T> {

	private long total;

	private long page_index;

	private long page_size;

	private long page_total;
 
	private T result;

	/**
	 * @param total >=0 
	 * @param page [1..n] if(page>this.pageSize||page<=0) page=1;
	 * @param row [1..n] if row<=0 than Math.abs(row==0?1:row)
	 * @param result
	 */
	public QueryResult(long total, long pageIndex, long pageSize, T result) {
		super();
		this.total = Math.abs(total);
		if(this.total==0)
		{
			this.page_size=0;
			this.page_total=0;
		}
		else
		{
			this.page_size = Math.abs(pageSize==0?this.total:pageSize);
			this.page_total=(long)Math.ceil(this.total/(this.page_size+0.0));
			if(pageIndex>this.page_total)
			{
				this.page_index=this.page_total;
			}
			else if(pageIndex<=0)
			{
				this.page_index=1;
			}
			else
			{
				this.page_index = pageIndex;
			}
			this.result=result;
		}
	}

	public Long getTotal() {
		return total;
	}

	public long getPage_index() {
		return page_index;
	}

	public long getPage_size() {
		return page_size;
	}

	public long getPage_sotal() {
		return page_total;
	}

	public T getResult() {
		return result;
	}

}
