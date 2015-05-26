package per.cz.socket;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerMain implements IConnector{

	private static List<Transfer> list = new ArrayList<Transfer>();
	
	@Override
	public void connected(Socket socket) {
		try {
			Transfer transfer = new Transfer(socket);
//			ServerReceiverImp receiver = new ServerReceiverImp();
//			transfer.setReceiver(receiver);
			list.add(transfer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static List<Transfer> getList()
	{
		return list;
	}
	
	public static void main(String[] args) {
		try {
//			System.setOut(new PrintStream(new File("f:\\out.text")));;
			ServerMain sender = new ServerMain();
			Server server = new Server(13121);
			server.setConnector(sender);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
