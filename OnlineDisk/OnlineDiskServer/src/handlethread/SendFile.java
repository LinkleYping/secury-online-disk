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
	String root = "g:\\网盘存储";
	String Ip = null;
	Socket socket = null;
	int Port = 0;
	public SendFile(String data,String Ip,int Port) 
	{
		//获取信息
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
			socket = new Socket(Ip,Port);//建立连接
			//发送包
			DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
			dout.writeUTF(data);
			//发送文件
			Send();
			socket.close();
			//插入已完成的表里
			String statement = "insert into log values('" + Username + "','" + Filename + "','" + FileSize +"');";
			Database.Send(statement);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	// 发送文件
	public void Send()
	{
		try
		{
			// 创建文件，获取输入流
			File file = new File(Route);
			FileInputStream fin = new FileInputStream(file);
	        //创建读取的缓冲区
			byte[] SendBuf = new byte[BufLen];
			//发送
			int length = 0;
			long total = 0;
			//获取输出流
			OutputStream out = socket.getOutputStream();
	        while((length = fin.read(SendBuf, 0, SendBuf.length))>0)
	        {
	            out.write(SendBuf,0,length);
	            out.flush();
	            total = total + length;
	        }
	        System.out.println("已发送文件:" + file.getName() + "共" + total + "字节");
	        //关闭输出流
	        fin.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
