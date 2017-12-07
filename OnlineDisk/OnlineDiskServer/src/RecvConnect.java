import java.io.*;
import java.net.*;
import java.util.ArrayList;


public class RecvConnect implements Runnable
{
	static ArrayList<Connection> ConnectionList = new ArrayList<>();//�����б�
	static ServerSocket serversocket= null;//������socket
	static int serverport = 43211;//���������Ķ˿�
	
	public void run()
	{
		try
		{
			serversocket = new ServerSocket(serverport);//����ServerSocket
			System.out.println("������������");
			//ѭ������
			while(true)
			{
				Socket ClientSocket = serversocket.accept();
				Connection connection = new Connection(ClientSocket.getInetAddress().getHostAddress(),
						ClientSocket.getPort());
				ConnectionList.add(connection);
				//����������Ϣ�߳�
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