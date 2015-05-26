package per.cz.jnative;   

import java.io.File;   
import java.io.FileOutputStream;   
import java.io.IOException;   
import java.io.InputStream;   

import org.xvolks.jnative.JNative;   
import org.xvolks.jnative.Type;   
import org.xvolks.jnative.exceptions.NativeException;   

public class AppSvrTestConnect {   

	public AppSvrTestConnect() {   

	}   

	/**  
	 * 测试应用服务器连接状态  
	 *   
	 *  TestConnect   
	 * @param ip 应用服务器IP  
	 * @param port 端口  
	 * @param intrcpt  是否采用数据压缩方式 1 :true 0:false  
	 * @return int 1 :成功 0:失败  
	 * @throws NativeException  
	 * @throws IllegalAccessException  
	 */  
	private static final int TestConnect(String ip, int port, int intrcpt)throws NativeException, IllegalAccessException {   
		JNative n = null;   
		try {              
			n = new JNative("TestAppSvr.dll", "TestConnect");   
			n.setRetVal(Type.INT);   
			int i = 0;   
			n.setParameter(i++, Type.STRING, ip);   
			n.setParameter(i++, Type.INT, "" + port);   
			n.setParameter(i++, Type.INT, "" + intrcpt);   
			n.invoke();   
			return Integer.parseInt(n.getRetVal());   
		} finally {   
			if (n != null)   
				n.dispose();   
		}   
	}   
	/**  
	 * 指定Dll文件路径,动态加载本地链接库,测试应用服务器连接状态  
	 * setDllPath  
	 * @param path Dll文件的路径,不包含DLL名称 例如：windows - d:\test\test\ unix - root/test/test/  
	 * @param ip 应用服务器IP  
	 * @param port 端口  
	 * @param intrcpt  是否采用数据压缩方式 1 :true 0:false  
	 * @return int 1 :成功 0:失败  
	 * @throws NativeException  
	 * @throws IllegalAccessException  
	 */  
	public static final int TestConnectFromDllPath(String path,String ip, int port, int intrcpt) throws NativeException, IllegalAccessException{   
		path += "TestAppSvr.dll";   
		System.load(path);   
		return TestConnect(ip,port,intrcpt);   
	}   
	/**  
	 * Dll文件放在JRE\bin目录下面，ClassLoader就能通过System.loadLibrary()动态加载本地链接库  
	 * TestConnectFromDllPath  
	 * @param ip 应用服务器IP  
	 * @param port 端口  
	 * @param intrcpt  是否采用数据压缩方式 1 :true 0:false  
	 * @return int 1 :成功 0:失败  
	 * @throws NativeException  
	 * @throws IllegalAccessException  
	 */  
	public static final int TestConnectFromDllPath(String ip, int port, int intrcpt) throws NativeException, IllegalAccessException{   
		System.loadLibrary("TestAppSvr");   
		return TestConnect(ip,port,intrcpt);   
	}  
	public int getInfo() throws NativeException, IllegalAccessException{   

		String path=getClass().getResource(File.separator).getPath();          
		path = path.substring(1,path.length());   
		//System.out.println(path);   //得到DLL文件的路径   

		String ip = "192.168.0.48"; //服务器IP   
		int port = 221;             //端口   
		int intrcpt = 1;            //数据压缩方式传送,1为采用;0为不采用   
		//方法1 传入Dll文件的路径   
		//int info = AppSvrTestConnect.TestConnectFromDllPath(path, ip, port, intrcpt);   

		//方法2 Dll文件已经放在JRE\bin目录下面   
		int info = AppSvrTestConnect.TestConnectFromDllPath(ip, port, intrcpt);   

		//1为成功，0为失败   
		if (info == 1)   
			System.out.println("应用服务器可用。");   
		else  
			System.out.println("应用服务器不可用，请检查IP地址和端口是否正确。");   

		return info;   
	}  
	public static void main(String[] args) throws Exception {
		
		String path=AppSvrTestConnect.class.getResource(File.separator).getPath();          
		//System.out.println(path);   //得到DLL文件的路径   
		path = path.substring(1,path.length());   
		//System.out.println(path);   //得到DLL文件的路径   

		String ip = "192.168.0.48"; //服务器IP   
		int port = 221;             //端口   
		int intrcpt = 1;            //数据压缩方式传送,1为采用;0为不采用   
		//方法1 传入Dll文件的路径   
		int info = AppSvrTestConnect.TestConnectFromDllPath(path, ip, port, intrcpt);   

		//方法2 Dll文件已经放在JRE\bin目录下面   
//		int info = AppSvrTestConnect.TestConnectFromDllPath(ip, port, intrcpt);   

		//1为成功，0为失败   
		if (info == 1)   
			System.out.println("应用服务器可用。");   
		else  
			System.out.println("应用服务器不可用，请检查IP地址和端口是否正确。");   
		//System.out.println(info);
	}
}  