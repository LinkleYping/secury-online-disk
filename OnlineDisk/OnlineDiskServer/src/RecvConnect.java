import java.io.*;
import java.net.*;
import java.util.ArrayList;


public class RecvConnect implements Runnable
{
	static ArrayList<Connection> ConnectionList = new ArrayList<>();//连接列表
	static ServerSocket serversocket= null;//服务器socket
	static int serverport = 43211;//服务器开的端口
	
	public void run()
	{
		try
		{
			serversocket = new ServerSocket(serverport);//创建ServerSocket
			System.out.println("服务器已启动");
			//循环监听
			while(true)
			{
				Socket ClientSocket = serversocket.accept();
				Connection connection = new Connection(ClientSocket.getInetAddress().getHostAddress(),
						ClientSocket.getPort());
				ConnectionList.add(connection);
				//启动接收消息线程
				new Thread(new Recv(ClientSocket)).start();
				Thread.sleep(100);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}


class Connection
{
	String Username = null;
	String ClientIp = null;
	int Port = 0;
	
	public Connection(String ip,int port)
	{
		ClientIp = ip;
		Port = port;
	}
}