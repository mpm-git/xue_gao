package per.cz.event1_0;

import java.util.Date;

public abstract class IMethod<T>
{
	public abstract void excute(DEvent<T> event);
	public long getID(){
		 return new Date().getTime();
	 }
	@Override
	public boolean equals(Object obj) {
		if(obj==null)
			return false;
		if(this==obj)
			return true;
		if(this.getID()==((IMethod)obj).getID())
			return true;
		return super.equals(obj);
	}
	
}
