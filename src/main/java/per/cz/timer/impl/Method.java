package per.cz.timer.impl;
import java.util.Date;
import per.cz.timer.IMethod;

public class Method implements IMethod  {

	public Object excute(Object o) {
		//System.out.println("begin clean data："+new Date());
		//删除次数为0的游客
		long period=(Long)o;
		long time = new Date().getTime();
		//System.out.println("clean over!");
		
		return null;
	}


}
