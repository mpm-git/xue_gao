package per.cz.util;

public class CheckTimes {
	private long start;
	public CheckTimes() {
		start=System.currentTimeMillis();
	}
	public long getLastTimes()
	{
		
		long currentTimeMillis = System.currentTimeMillis();
		long l = currentTimeMillis-start;
		start=currentTimeMillis;
		return l;
	}
	
}
