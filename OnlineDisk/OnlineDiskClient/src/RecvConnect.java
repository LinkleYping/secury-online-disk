import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;


public class RecvConnect implements Runnable
{
	static ArrayList<Connection> ConnectionList = new ArrayList<>();//连接列表
	static ServerSocket serversocket= null;//服务器socket
	static int serverport = 43232;//端口
	
	public void run()
	{
		try
		{
			serversocket = new ServerSocket(serverport);//创建ServerSocket
			//循环监听
			while(true)
			{
				Socket ClientSocket = serversocket.accept();
				Connection connection = new Connection(ClientSocket.getInetAddress().getHostAddress(),
						ClientSocket.getPort());
				ConnectionList.add(connection);
				String data = null;
				try
				{
					DataInputStream din = new DataInputStream(ClientSocket.getInputStream());
					data = din.readUTF();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
				HashMap<String,String> map = MapString.StringToMap(data);
				String Mark = map.get("Mark");
				switch(Mark)
				{
				case "Upload":new Thread(new SendFile(ClientSocket,data,MainWindow.mainwindow)).start();break;
				case "Download":new Thread(new RecvFile(ClientSocket,MainWindow.mainwindow,data)).start();
								break;
				}
				
				Thread.sleep(1000);
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