package per.cz.util.http;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author HJL
 *
 */
public class HttpUtil {
	public static Map<String, String> getParameters(HttpServletRequest request,
			String encoding) {
		encoding=encoding==null?"utf-8":encoding;
		Hashtable<String, String> param = new Hashtable<String, String>();
		Object nextElement = null;
		String _encoding = request.getCharacterEncoding();
		try {
			request.setCharacterEncoding(_encoding == null ? encoding
					: _encoding);
			Enumeration parameterNames = request.getParameterNames();
			while (parameterNames.hasMoreElements()) {
				nextElement = parameterNames.nextElement();
				String parameter = request.getParameter(nextElement.toString());
				byte[] bytes4ISO = parameter
						.getBytes(_encoding == null ? "iso-8859-1" : _encoding);
				String p4Utf = new String(bytes4ISO, encoding);
//				 //System.out.println("\t"+nextElement+":"+parameter+" \tencode: "+p4Utf);
				param.put(nextElement.toString(), p4Utf);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return param;
	}

//	/**
//	 * Class<T> beanClass可以接受任何类型的javaBean,使用泛型调用者不用进行强转
//	 * 
//	 * @param <T>
//	 * @param request
//	 * @param beanClass
//	 * @return
//	 */
//	public static <T> T request2Bean(HttpServletRequest request,
//			Class<T> beanClass) {
//		try {
//			/** 创建封装数据的bean **/
//			T bean = beanClass.newInstance();
//			Map map = request.getParameterMap();
//			BeanUtils.populate(bean, map);
//			return bean;
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//	}

}
