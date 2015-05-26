package per.cz.websocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

/**
 * A simple WebSocketServer implementation. Keeps track of a "chatroom".
 */
public class WebSocketServerImpl extends WebSocketServer {

	private Map<String,List<WebSocket>> mapList;
	public WebSocketServerImpl( int port ) throws UnknownHostException {
		super( new InetSocketAddress( port ) );
	}

	public WebSocketServerImpl( InetSocketAddress address ) {
		super( address );
	}

	@Override
	public void onOpen( WebSocket conn, ClientHandshake handshake ) {
		if(mapList==null)
		{
			mapList=new HashMap<String, List<WebSocket>>();
		}
		String id = handshake.getResourceDescriptor().replace('/', ' ').trim();
		if(mapList.get(id)==null)
		{
			mapList.put(id, new ArrayList<WebSocket>());
		}
		List<WebSocket> list = mapList.get(id);
		list.add(conn);
//		this.sendToAll( "new connection: " + handshake.getResourceDescriptor() );
		System.out.println( conn.getRemoteSocketAddress().getAddress().getHostAddress() + " entered the room!" );
	}

	@Override
	public void onClose( WebSocket conn, int code, String reason, boolean remote ) {
		if(mapList==null)
		{
			mapList=new HashMap<String, List<WebSocket>>();
		}
		Collection<List<WebSocket>> values = mapList.values();
		for (List<WebSocket> list : values) {
			list.remove(conn);
		}
//		String id = handshake.getResourceDescriptor().replace('/', ' ').trim();
//		this.sendToAll( conn + " has left the room!" );
		System.out.println( conn + " has left the room!" );
	}

	@Override
	public void onMessage( WebSocket conn, String message ) {
//		this.sendToAll( message );
		System.out.println( conn + ": " + message );
	}

//	@Override
//	public void onFragment( WebSocket conn, Framedata fragment ) {
//		System.out.println( "received fragment: " + fragment );
//	}
	public synchronized void sendTo(String id, String text ) {
		if(id!=null&&id.trim().length()>0&&text!=null&&text.trim().length()>0&&mapList!=null)
		{
			Set<String> keySet = mapList.keySet();
			for (String key : keySet) {
				if(key.matches(id.trim()))
				{
					List<WebSocket> list = mapList.get(key);
					for (WebSocket webSocket : list) {
//				synchronized ( webSocket ) {
//						System.out.println(key+":"+text);
						webSocket.send(text);
//				}
					}
				}
			}
		}
	}
	public static void main( String[] args ) throws InterruptedException , IOException {
		WebSocketImpl.DEBUG = true;
		int port = 8887; // 843 flash policy port
		try {
			port = Integer.parseInt( args[ 0 ] );
		} catch ( Exception ex ) {
		}
		WebSocketServerImpl s = new WebSocketServerImpl( port );
		s.start();
		System.out.println( "ChatServer started on port: " + s.getPort() );

		BufferedReader sysin = new BufferedReader( new InputStreamReader( System.in ) );
		while ( true ) {
			String in = sysin.readLine();
//			s.sendToAll( in );
			if( in.equals( "exit" ) ) {
				s.stop();
				break;
			} else if( in.equals( "restart" ) ) {
				s.stop();
				s.start();
				break;
			}
		}
	}
	@Override
	public void onError( WebSocket conn, Exception ex ) {
		ex.printStackTrace();
		if( conn != null ) {
			// some errors like port binding failed may not be assignable to a specific websocket
		}
	}

	/**
	 * Sends <var>text</var> to all currently connected WebSocket clients.
	 * 
	 * @param text
	 *            The String to send across the network.
	 * @throws InterruptedException
	 *             When socket related I/O errors occur.
	 */
//	public void sendToAll( String text ) {
//		Collection<WebSocket> con = connections();
//		synchronized ( con ) {
//			for( WebSocket c : con ) {
//				c.send( text );
//			}
//		}
//	}
}
