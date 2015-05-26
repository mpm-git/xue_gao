package per.cz.event1_1;

import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
public class DispatchEvent
{
	private static Hashtable<String,Hashtable<Object,IMethod>> mathodMap = new Hashtable<String, Hashtable<Object,IMethod>>();
	private static DispatchEvent global;
	static{
		global=new DispatchEvent();
	}
	private DispatchEvent(){}
	public static void dispatchEvent(DEvent event)
	{
		Hashtable<Object, IMethod> hashtable = mathodMap.get(event.getType());
		if(hashtable!=null)
		{
			Object t = event.getTarget();
			if(t==null)
				event.setTarget(global);
			IMethod method = hashtable.get(event.getTarget());
			//				IMethod method = hashtable.get(event.getInfo());
			if(method !=null)
			{
				method.excute(event);
			}
		}
			//进行遍历找到注册此事件的目标(递归),其中涉及冒泡等操作 .
	}
	public static void addEventListener(String type,IMethod method)
	{
		Hashtable<Object, IMethod> mathods = mathodMap.get(type);
		if(mathods==null)
		{
			mathods=new Hashtable<Object, IMethod>();
			mathodMap.put(type, mathods);
		}
		mathods.put(global, method);
//		mathodMap.put(type, method);
	}
	public static Hashtable<Object, IMethod> removeListenerByType(String type)
	{
		return mathodMap.remove(type);
	}
	public static Hashtable<Object, IMethod> removeListener(IMethod method)
	{
		Hashtable<Object, IMethod> res=new Hashtable<Object, IMethod>();
		Collection<Hashtable<Object, IMethod>> values = mathodMap.values();
		for (Hashtable<Object, IMethod> iMsTable : values) {
			Set<Entry<Object, IMethod>> entrySet = iMsTable.entrySet();
			for (Entry<Object, IMethod> entry : entrySet) {
				IMethod value = entry.getValue();
				Object key = entry.getKey();
				if(method==value)
				{
					res.put(key, iMsTable.remove(key));
				}
			}
		}
		if(res.size()==0)
			return null;
		return res;
	}
}
