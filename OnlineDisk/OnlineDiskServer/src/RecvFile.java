import java.io.*;
import java.net.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import handlethread.Database;
import handlethread.DoneFileList;
import handlethread.UserInfo;

public class RecvFile implements Runnable
{
	static int BufLen = 1024;
	Socket socket = null;
	String Route = null;
	String root = "g:\\���̴洢";
	String Username = null;
	String Filename = null;
	String FileSize = null;
	String RemoteRoute = null;
	String Ip = null;
	int Port = 0;
	String FileHash = null;
	String data = null;
	HashMap<String,String> map = null;
	
	public RecvFile(String data,String Ip,int Port)
	{
		this.data = data;
		this.Ip = Ip;
		this.Port = Port;
		map = MapString.StringToMap(data);
		Username = map.get("Username");
		Route = map.get("Route");
		Filename = map.get("Filename");
		FileSize = map.get("FileSize");
		System.out.println(FileSize);
		RemoteRoute = map.get("RemoteRoute");
		FileHash = map.get("FileHash");
		Route = root + "\\" + Username + RemoteRoute;
	}
	public void run()
	{
		try
		{
			socket = new Socket(Ip,Port);//���ӿͻ���
			DataOutputStream dout = new DataOutputStream(socket.getOutputStream());//�����ļ���Ϣ
			dout.writeUTF(data);
			Recv();
			UpdateDatabase();
		} catch (Exception e1)
		{
			e1.printStackTrace();
		}
	}
	
	// �����ļ�
	public void Recv() throws IOException
	{
		int length = 0;//���ͳ���
		int totallen = 0;
		File file = null;
		FileOutputStream fout = null;
		if(FileSize.equals("-"))
		{
			System.out.println("wzpz");
			Route =Route+".zip";
			Filename=Filename+".zip";
		}
		try
		{
			// �����ļ�����ȡ�����
			file = new File(Route);
			fout = new FileOutputStream(file);
			// ������ȡ�Ļ�����
			byte[] RecvByte = new byte[BufLen];
			// ��ȡ������
			InputStream in = socket.getInputStream();
			while ((length = in.read(RecvByte, 0, RecvByte.length)) != -1)
			{
				fout.write(RecvByte, 0, length);
				fout.flush();
				totallen += length;
			}
			// �ر������
			fout.close();
		} catch (Exception e)
		{
			e.printStackTrace();
			if(fout != null)
				fout.close();
			System.out.println("�ѽ����ļ�:" + file.getName() + "��" + totallen + "�ֽ�");
		}
	}
	private void UpdateDatabase()
	{
		//���Ȳ�ѯ��ʹ�õ���
		String statement = "select Used,Total from capacity where mail = '" + Username + "';";
		ResultSet result = Database.Send(statement);
		long Used = 0;
		try
		{
			result.next();
			Used = Long.valueOf(result.getString("Used"));
			Used = Used + new File(Route).length();
			statement = "update capacity set used = '" + String.valueOf(Used) + "' where mail = '" +
						Username + "';";
			Database.Send(statement);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		//��������ɵı���
		statement = "insert into log values('" + Username + "','" + Filename + "','" + FileSize +"');";
		Database.Send(statement);
		//����hash����
		statement = "insert into hash values('" + Username + "','" + Filename + "','" + Route + "','"
				+ FileHash + "');";
		System.out.println(statement);
		Database.Send(statement);
	}
}
