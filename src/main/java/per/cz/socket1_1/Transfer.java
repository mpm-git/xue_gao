package per.cz.socket1_1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

public class Transfer implements Runnable{
	private int interval=4096;
	private OutputStream outputStream;
	private InputStream inputStream;
	private final String remote;
	private final String local;
	private IReceiver receiver;
	private Socket socket;
	private Thread thread;
	public Socket getSocket() {
		return socket;
	}
	public Transfer(Socket socket) throws Exception {
		this.socket=socket;
		remote=socket.getInetAddress().getHostAddress()+":"+socket.getPort();
		local=socket.getLocalAddress()+":"+socket.getLocalPort();
		inputStream = socket.getInputStream();
		outputStream = socket.getOutputStream();
//		objectInputStream =new ObjectInputStream(inputStream);
		
		thread = new Thread(this, "transfer");
//		DispatchEvent.dispatchEvent(new DEvent<Transfer>("connection_in", this));
	}
	public void startReceiver()
	{
		if(thread!=null)		
			thread.start();
	}
	public void send(byte[] b,int off,int len) throws IOException {
			outputStream.write(b, off, len);;
			outputStream.flush();
	}
	public void send(byte[] b) throws IOException {
		if(b!=null&&b.length>0)
		{
			outputStream.write(b, 0, b.length);;
			outputStream.flush();
		}
	}
	public void send(String message) throws IOException
	{
		this.send(message.getBytes());
	}
	public void stop()
	{
		thread.interrupt();
	}
	public final void setReceiver(IReceiver receiver) {
		this.receiver = receiver;
	}

	@Override
	public void run() {
		receiver.socketHandle(this);
		receiver.socketClose(this);
	}

	public String getRemote()
	{
		return remote;
	}

	public String getLocal()
	{
		return local;
	}

	public OutputStream getOutputStream() {
		return outputStream;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

}
