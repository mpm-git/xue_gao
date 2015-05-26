package per.cz.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import per.cz.event1_0.DEvent;
import per.cz.event1_0.DispatchEvent;

public class Transfer implements Runnable{
	
	private OutputStream outputStream;
	private InputStream inputStream;
	private final String remote;
	private final String local;
	private IReceiver receiver;
	private Socket socket;
	public Socket getSocket() {
		return socket;
	}

	private Map<String,Object> _store;

	public Transfer(Socket socket) throws IOException {
		this.socket=socket;
		remote=socket.getInetAddress().getHostAddress()+":"+socket.getPort();
		local=socket.getLocalAddress()+":"+socket.getLocalPort();
		inputStream = socket.getInputStream();
		outputStream = socket.getOutputStream();
//		objectInputStream =new ObjectInputStream(inputStream);
		
		new Thread(this, "transfer").start();
		DispatchEvent.dispatchEvent(new DEvent<Transfer>("connection_in", this));
	}
	public Object getValueByKey(String key)
	{
		if(_store!=null)
			return _store.get(key);
		return null;
	}
	public void setValueByKey(String key,Object value)
	{
		if(_store==null)
			_store=new HashMap<String, Object>();
		_store.put(key, value);
	}
	public void send(byte[] b,int off,int len) throws IOException {
			outputStream.write(b, off, len);;
			outputStream.flush();
	}
	
	public void setReceiver(IReceiver receiver) {
		this.receiver = receiver;
	}

	@Override
	public void run() {
		Object obj;
		try {
			byte[] bs=new byte[4096];
			inputStream.read(bs);
			int len=0;
			while ((len=inputStream.read(bs))>0) {
				if (receiver != null) {
					byte[] copyOfRange = Arrays.copyOfRange(bs, 0, len);
					receiver.handle(copyOfRange,this);
				}
			}
		} catch (IOException e) {
			DispatchEvent.dispatchEvent(new DEvent<Transfer>("connection_error", this));
//			//System.out.println(remote+"断开连接");
		//需要将断开的信息发送给上层的Transfer列表
//			e.printStackTrace();
		}
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

	public void setOutputStream(OutputStream outputStream) {
		this.outputStream = outputStream;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

}
