package per.cz.socket1_1;

import java.io.InputStream;



public interface IReceiver {

//	void socketOpen(Transfer transfer);
	void socketClose(Transfer transfer);
//	void socketHandle(byte[] bs, int len, Transfer transfer) throws Exception;
	void socketHandle(Transfer transfer);
}
