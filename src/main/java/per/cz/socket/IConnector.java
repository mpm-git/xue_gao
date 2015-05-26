package per.cz.socket;

import java.net.Socket;

public interface IConnector {
	void connected(Socket socket);
}
