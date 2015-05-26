package per.cz.util;

import java.lang.reflect.InvocationTargetException;

public class ClassUtil {
	public static Object invokeGet(Object o,String field)
	{
		try {
			String method="get"+(field.charAt(0)+"").toUpperCase()+field.substring(1);
			return o.getClass().getMethod(method).invoke(o, null);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
