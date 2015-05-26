package per.cz.socket1_1;

import java.net.Socket;

public interface IConnector {
	void connected(Socket socket);
	void connectFailed(Server server);
}
