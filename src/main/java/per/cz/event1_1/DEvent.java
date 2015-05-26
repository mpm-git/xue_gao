package per.cz.event1_1;

import java.util.Date;

public class DEvent<T>
{
	private final String type;
	private  T target;
	private final Object obj;
	private final Date date;

	/**
	 * @param type "per.cz.xx.xx"
	 * @param target
	 * @throws Throwable 
	 */
	public DEvent(String type,T target,Object obj,Date date) 
	{
		if(type==null)
			type="";
		this.type=type;
		this.target=target;
		this.obj=obj;
		if(date==null)
			date=new Date();
		this.date=date;
	}
	public DEvent(String type,Object obj) 
	{
		this(type,null,obj, new Date());
	}
	public DEvent(String type) 
	{
		this(type,null,null,new Date());
	}
	
	protected void setTarget(T target) {
		this.target = target;
	}
	public Object getObj() {
		return obj;
	}

	public String getType()
	{
		return type;
	}
	public T getTarget()
	{
		return target;
	}
	public Date getDate() {
		return date;
	}
	@Override
	public String toString() {
		return "DEvent [type=" + type + ", target=" + target + ", obj=" + obj
				+ ", date=" + date + "]";
	}
	
}
