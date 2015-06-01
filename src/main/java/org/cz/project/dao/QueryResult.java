/**
 * 
 */
package org.cz.project.dao;

import java.util.List;


/**
 * @ClassName: QueryResult
 * @Description:this class use to store the db Query source.
 * @author leo.huang
 * @date 2013-11-29 13:51:13
 * 
 */
public class QueryResult<T> {

	private long total;

	private long start;

	private long length;

	private List<T> result;

	/**
	 * @param total >=0 
	 * @param page [1..n] if(page>this.pageSize||page<=0) page=1;
	 * @param row [1..n] if row<=0 than Math.abs(row==0?1:row)
	 * @param list
	 */
	public QueryResult(long total, long start, long length, List<T> list) {
		super();
		this.length=length;
		this.start=start;
		this.total=total;
		this.result=list;
	}

	public long getTotal() {
		return total;
	}

	public long getStart() {
		return start;
	}

	public long getLength() {
		return length;
	}

	public List<T> getResult() {
		return result;
	}


}
