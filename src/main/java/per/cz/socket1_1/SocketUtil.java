package per.cz.socket1_1;

import java.io.IOException;
import java.net.Socket;

public class SocketUtil {
	public static boolean test(String ip,int port)
	{
		Socket s=null;
		try {
			s = new Socket(ip, port);
		} catch (Exception e) {
			return false;
		}
		try {
			s.close();
		} catch (IOException e) {
		}
		return true;
	}
}
