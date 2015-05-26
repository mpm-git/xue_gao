package per.cz.jnative;

import org.xvolks.jnative.JNative;  
import org.xvolks.jnative.exceptions.NativeException;  
import org.xvolks.jnative.Type;  
import org.xvolks.jnative.misc.basicStructures.LONG;  

public class JNativeT {  

	static JNative PegRoute = null;  

	public String init() throws NativeException, IllegalAccessException {  
		try {  
			if (PegRoute == null) {  
				// 1. 利用org.xvolks.jnative.JNative来加载DLL：参数1.PegRoute为类名  
				// 2.HCTInitEx方法名  
				PegRoute = new JNative("PegRoute", "HCTInitEx");  

				// 2.设置要调用方法中的参数：0 表示第一个以此类推  
				LONG versionLong = new LONG(10);  
				versionLong.setValue(0);  

				PegRoute.setParameter(0, Type.LONG, versionLong.toString());  
				PegRoute.setParameter(1, Type.STRING, "");  

				// 3.设置返回参数的类型  
				PegRoute.setRetVal(Type.INT);  
				// 4.执行方法  
				PegRoute.invoke();// 调用方法  
			}  
			//System.out.println("调用的DLL文件名为：" + PegRoute.getDLLName());  
			//System.out.println("调用的方法名为：" + PegRoute.getFunctionName());  
			// 5.返回值  
			return PegRoute.getRetVal();  
		} finally {  
			if (PegRoute != null) {  
				// 6.释放系统资源  
				PegRoute.dispose();  
			}  
		}  
	}  

	public static void main(String[] args) throws NativeException,  
	IllegalAccessException {  

		String mm = new JNativeT().init();  
		//System.out.println(mm);  
	}  
}  
