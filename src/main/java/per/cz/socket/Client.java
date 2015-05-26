//package per.cz.socket;
//
//import java.io.BufferedInputStream;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.net.Socket;
//import java.util.ArrayList;
//import java.util.Hashtable;
//import java.util.InvalidPropertiesFormatException;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Properties;
//import java.util.Set;
//
//public class Client {
//
//	private static Hashtable<String,Integer> hostMap=new Hashtable<String, Integer>();
//	
//	
////	private ServerSocket serverSocket;
////	private boolean running;
////	private Thread thread;
//	private List<Transfer> clientList;
////	private IConnector connector;
//	private List<AcResult> selectResultList;
////	private List<Object[]> updateResultList;
//	private Hashtable<String, Object[]> updateResultTable;
//	public Client() {
//		this.selectResultList=new ArrayList<AcResult>();
//		this.clientList=new ArrayList<Transfer>();
//		this.updateResultTable=new Hashtable<String, Object[]>();
////		this.updateResultList=new ArrayList<Object[]>();
//		Set<String> keySet = hostMap.keySet();
//		Iterator<String> iterator = keySet.iterator();
//		while(iterator.hasNext())
//		{
//			String host=iterator.next();
//			int port=hostMap.get(host);
//			Socket socket;
//			Transfer transfer=null;
//			try
//			{
//				socket = new Socket(host, port);
//				transfer = new Transfer(socket);
//				updateResultTable.put(host, new Object[3]);
//			}
//			catch (Exception e)
//			{
//				//System.out.println(host+":"+port+"Connection refused: connect");
//				e.printStackTrace();
//				continue;
//			}
//			transfer.setReceiver(getReceiver());
//			clientList.add(transfer);
//		}
//	}
//	private IReceiver getReceiver()
//	{
//		ClientReceiverImp receiver = new ClientReceiverImp();
//		receiver.addEventListener(ClientReceiverImp.GETSELECTRESULT, new IMethod()
//		{
//			
//			@Override
//			public void excute(DEvent event)
//			{
//				Client.this.selectResultList.add((AcResult) event.getObject());
//			}
//		});
//		receiver.addEventListener(ClientReceiverImp.GETUPDATERESULT, new IMethod()
//		{
//			
//			@Override
//			public void excute(DEvent event)
//			{
//				Object[] objs=(Object[])event.getObject();
//				Client.this.updateResultTable.put(objs[2].toString(), objs);
//			}
//		});
//		return receiver;
//	}
//	public void sendMommand(Command method)
//	{
//		if(method.getCommandName().equals(Command.UPDATE))
//		{
//			for(int i=0;i<clientList.size();i++)
//			{
//				//System.out.println("发送update指令"+method);
//				clientList.get(i).send(method);
//			}
//		}
//		else if(method.getCommandName().equals(Command.SELECT))
//		{
//			Command[] splitCommands = getNextSplitCommands(method);
//				for(int i=0;i<clientList.size();i++)
//				{
//					//System.out.println("发送insert指令"+method);
//					clientList.get(i).send(splitCommands[i]);
//				}
//		}
//	}
//	private Command[] getNextSplitCommands(Command method)
//	{
//		Command[] commonds=new Command[this.clientList.size()];
//		for(int i=0;i<commonds.length;i++)
//		{
//			commonds[i]=new Command();
//			commonds[i].setCommandName(method.getCommandName());
//		}
//		List sqlList = method.getSQLList();
//		a:for(int i=0;i<sqlList.size();i++)
//		{
//			for(int j=0;j<clientList.size();j++,i++)
//			{
//				//System.out.println("发送insert指令"+method);
////				commands[j].setCommandName(method.getCommandName());
//				Object[] commond=null;
//				try {
//					
//					commond = (Object[]) sqlList.get(j);
//				}catch(Exception e) {
//					if(commond==null)
//						break a;
//				}
//				commonds[j].addSQL( commond);
////				clientList.get(i).send(method);
//			}
//		}
//		return null;
//	}
//	public static void main(String[] args) throws Exception
//	{
//		Client client = new Client();
//		Thread.sleep(1000);
//		Command method = new Command(Command.UPDATE,null);
//		StringBuffer buffer=new StringBuffer();
//		String code="goods3";
//		buffer.append("CREATE TABLE `"+code+"` (                                                                              \r\n" + 
//				"               `goodsId` int(10) unsigned NOT NULL AUTO_INCREMENT,                                                     \r\n" + 
//				"               `barcodeId` int(10) unsigned NOT NULL,                                                                  \r\n" + 
//				"               `goodsDate` date DEFAULT NULL,                                                                          \r\n" + 
//				"               `goodsPrice` decimal(10,2) DEFAULT NULL,                                                                \r\n" + 
//				"               `goodsTag` int(11) unsigned DEFAULT NULL,                                                               \r\n" + 
//				"               `categoryId` int(10) unsigned NOT NULL,                                                                 \r\n" + 
//				"               PRIMARY KEY (`goodsId`),                                                                                \r\n" + 
//				"               KEY `FK_"+code+"_barcode` (`barcodeId`),                                                             \r\n" + 
//				"               KEY `FK_"+code+"_category` (`categoryId`),                                                           \r\n" + 
//				"               CONSTRAINT `FK_"+code+"_barcode` FOREIGN KEY (`barcodeId`) REFERENCES `goodsbarcode` (`barcodeId`),  \r\n" + 
//				"               CONSTRAINT `FK_"+code+"_category` FOREIGN KEY (`categoryId`) REFERENCES `category` (`categoryId`)    \r\n" + 
//				"             ) ENGINE=InnoDB DEFAULT CHARSET=utf8");
//		method.addSQL(new Object[]{buffer,code});
//		client.sendMommand(method);
//	}
//	static {
//		try
//		{
//			 Properties properties=new Properties();
//			properties.loadFromXML(new BufferedInputStream(new FileInputStream("lib/PropertiesForClient.xml")));
//			if(properties!=null)
//			{
//				for(int i=1;;i++)
//				{
//					String host = (String) properties.get("host"+i);
//					String port = (String) properties.get("port"+i);
//					if(host!=null)
//						hostMap.put(host, Integer.parseInt(port));
//					else
//						break;
//					
//				}
//			}
//		}
//		catch (InvalidPropertiesFormatException e)
//		{
//			e.printStackTrace();
//		}
//		catch (FileNotFoundException e)
//		{
//			e.printStackTrace();
//		}
//		catch (IOException e)
//		{
//			e.printStackTrace();
//		}
//	}
//}
