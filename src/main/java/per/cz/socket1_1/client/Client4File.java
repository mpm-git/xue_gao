package per.cz.socket1_1.client;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import per.cz.socket1_1.Transfer;

public abstract class Client4File extends SocketClient {

	public Client4File(String host, int port) throws Exception {
		super(host, port);
	}

	@Override
	public void onClientHandle(Transfer transfer){
		System.out.println("onClientHandle");
		byte[] bs=new byte[4096];
		int len=0;
		File file = getFile(transfer);
		if(file!=null)
		{
			try {
				BufferedOutputStream bfo;
				bfo = new BufferedOutputStream(new FileOutputStream(file));
				while((len=transfer.getInputStream().read(bs))>0)
				{
					try {
						bfo.write(bs, 0, len);
						bfo.flush();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				bfo.close();
			} catch (Exception e1) {
//				e1.printStackTrace();
			}
		}
	}
	public abstract File getFile(Transfer transfer) ;
	public static void main(String[] args) throws Exception {
//		for(int i=0;i<10;i++)
//		{
			Thread.sleep(100);
			Client4File test = new Client4File("localhost", 11221){
				@Override
				public void onClientOpenFailed() {
					System.out.println("onClientOpenFailed");
				}
				
				@Override
				public void onClientClose(Transfer transfer) {
					System.out.println("onClientClose"); 
					try {
						transfer.getOutputStream().close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				@Override
				public void onClientOpen(Transfer transfer) {
					System.out.println("onClientOpen");
				}

				@Override
				public File getFile(Transfer transfer) {
					File file = new File("f:\\imgs_c\\"+new Date().getTime()+".jpg");
					file.getParentFile().mkdirs();
					return file;
				}};
				test.startReceiver();
				test.send("hello");
//				try {
//					Map<String,Object> m=new HashMap<String, Object>();
//					BufferedImage capture = ImageUtil.getScreenCapture(m);
//					ImageUtil.saveImage(capture,"png",test.getOutputStream());
//				} catch (FileNotFoundException e) {
//					e.printStackTrace();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//		}
	}
	
}
