package per.cz.socket1_1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;


public class Server implements Runnable{

	private ServerSocket serverSocket;
	private boolean running;
	private Thread thread;
	private IConnector connector;
	
	
	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public Thread getThread() {
		return thread;
	}

	public Server(int port) throws Exception {
		serverSocket = new ServerSocket(port);
		
		thread = new Thread(this, "connector");
	}
	public void start()
	{
		if(thread!=null)
			thread.start();
	}
	public void stop()
	{
		running = false;
		thread.interrupt();
	}
	
	public void setConnector(IConnector connector) {
		this.connector = connector; 
	}
	
	@Override
	public void run() {
		running = true;
		
		while (running) {
			try {
				Socket socket = serverSocket.accept();
				if (connector != null) {
					connector.connected(socket);
				}
			} catch (IOException e) {
				connector.connectFailed(Server.this);
				e.printStackTrace();
			}
		}
	}
	
}
