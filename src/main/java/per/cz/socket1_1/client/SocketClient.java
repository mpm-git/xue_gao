package per.cz.socket1_1.client;

import java.net.Socket;

import per.cz.socket1_1.IReceiver;
import per.cz.socket1_1.Transfer;

public abstract class SocketClient extends Transfer{

	
	private int port;
	private String host;
	private SocketClient(Socket socket) throws Exception {
		super(socket);
	}
	public SocketClient(String host,int port) throws Exception
	{
		this(new Socket(host,port));
		SocketClient.this.onClientOpen(this);
		this.host=host;
		this.port=port;
//		onClientOpen(this);
		setReceiver(new IReceiver(){

			@Override
			public void socketClose(Transfer transfer){
				SocketClient.this.onClientClose(transfer);
			}

			@Override
			public void socketHandle(Transfer transfer){
				SocketClient.this.onClientHandle(transfer);
			}

//			@Override
//			public void socketOpen(Transfer transfer) {
//				SocketClient.this.onClientOpen(transfer);
//				
//			}
		});
	}
	public int getPort() {
		return port;
	}

	public String getHost() {
		return host;
	}
	public abstract void onClientHandle(Transfer transfer) ;
	public abstract void onClientOpen(Transfer transfer);
	public abstract void onClientOpenFailed();
	public abstract void onClientClose( Transfer transfer);
}
