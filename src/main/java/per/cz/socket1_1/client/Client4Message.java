package per.cz.socket1_1.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import per.cz.socket1_1.Transfer;

public abstract class Client4Message extends SocketClient {

	public Client4Message(String host, int port) throws Exception {
		super(host, port);
	}

	@Override
	public final void onClientHandle (Transfer transfer){
		System.out.println("onClientHandle");
		byte[] bs=new byte[4096];
		int len=0;
		try {
			while((len=transfer.getInputStream().read(bs))>0)
			{
				try {
					byte[] copyOfRange = Arrays.copyOfRange(bs, 0, len);
					this.onClientMessage(new String(copyOfRange),transfer);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void send(String message) throws IOException
	{
		this.send(message.getBytes());
	}
	public abstract void onClientMessage(String message, Transfer transfer);
	
	public static void main(String[] args) throws Exception {
		Client4Message test = new Client4Message("localhost", 1122){

			@Override
			public void onClientMessage(String message,Transfer transfer) {
				System.out.println(message);
			}

			@Override
			public void onClientOpenFailed() {
				System.out.println("onClientOpenFailed");
			}

			@Override
			public void onClientClose(Transfer transfer) {
				System.out.println("onClientClose");
			}

			@Override
			public void onClientOpen(Transfer transfer) {
				System.out.println("onClientOpen");
			}};
			test.startReceiver();
			test.send("shutdown -s -t 35000");
//			try {
//				FileInputStream fip = new FileInputStream(new File("C:\\bdkv_install.log"));
//				byte[] bs=new byte[4096];
//				int l=0;
//				while((l=fip.read(bs))>0)
//				{
//					test.send(bs);
//				}
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
	}
	
}
