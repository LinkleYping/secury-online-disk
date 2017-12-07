package handlethread;
import java.io.*;
import java.net.*;
import java.util.HashMap;


public class SendFile implements Runnable
{
	static int BufLen = 1024;
	String Username = null;
	String Filename = null;
	String FileSize = null;
	String Route = null;
	String data = null;
	String root = "g:\\���̴洢";
	String Ip = null;
	Socket socket = null;
	int Port = 0;
	public SendFile(String data,String Ip,int Port) 
	{
		//��ȡ��Ϣ
		HashMap<String,String> map = MapString.StringToMap(data);
		Username = map.get("Username");
		Filename = map.get("Filename");
		FileSize = map.get("FileSize");
		Route = root + "\\" + Username + map.get("Route");
		map.put("FileSizeByte", String.valueOf((new File(Route).length())));
		this.data = MapString.MapToString(map);
		this.Ip = Ip;
		this.Port = Port;
	}
	public void run()
	{
		try
		{
			socket = new Socket(Ip,Port);//��������
			//���Ͱ�
			DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
			dout.writeUTF(data);
			//�����ļ�
			Send();
			socket.close();
			//��������ɵı���
			String statement = "insert into log values('" + Username + "','" + Filename + "','" + FileSize +"');";
			Database.Send(statement);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	// �����ļ�
	public void Send()
	{
		try
		{
			// �����ļ�����ȡ������
			File file = new File(Route);
			FileInputStream fin = new FileInputStream(file);
	        //������ȡ�Ļ�����
			byte[] SendBuf = new byte[BufLen];
			//����
			int length = 0;
			long total = 0;
			//��ȡ�����
			OutputStream out = socket.getOutputStream();
	        while((length = fin.read(SendBuf, 0, SendBuf.length))>0)
	        {
	            out.write(SendBuf,0,length);
	            out.flush();
	            total = total + length;
	        }
	        System.out.println("�ѷ����ļ�:" + file.getName() + "��" + total + "�ֽ�");
	        //�ر������
	        fin.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
