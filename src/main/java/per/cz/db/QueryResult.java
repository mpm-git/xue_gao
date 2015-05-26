/**
 * 
 */
package per.cz.db;


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

	private T result;

	/**
	 * @param total >=0 
	 * @param page [1..n] if(page>this.pageSize||page<=0) page=1;
	 * @param row [1..n] if row<=0 than Math.abs(row==0?1:row)
	 * @param result
	 */
	public QueryResult(long total, long start, long length, T result) {
		super();
		this.length=length;
		this.start=start;
		this.total=total;
		this.result=result;
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

	public T getResult() {
		return result;
	}


}
