package per.cz.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;


public class Server implements Runnable{

	private ServerSocket serverSocket;
	private boolean running;
	private Thread thread;
	private IConnector connector;

	public Server(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		
		thread = new Thread(this, "connector");
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
				SocketAddress remoteSocketAddress = socket.getRemoteSocketAddress();
				//System.out.println(remoteSocketAddress.toString());
				if (connector != null) {
					connector.connected(socket);
				}
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}
	
}
