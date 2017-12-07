import java.io.*;
import java.net.*;
import java.util.HashMap;

public class Download implements Runnable
{
	String Username = null;
	String Filename = null;
	String FileSize = null;
	String Route = null;
	//发送下载文件请求
	public Download(String Username,String Filename,String FileSize,String Route)
	{
		this.Username = Username;
		this.Filename = Filename;
		this.FileSize = FileSize;
		this.Route = Route;
	}
	@Override
	public void run()
	{
		HashMap<String,String> map = new HashMap<>();
		map.put("Mark", "Download");
		map.put("Username", Username);
		map.put("Filename", Filename);
		map.put("FileSize", FileSize);
		map.put("Route", Route + "\\" + Filename);
		Send.send(MapString.MapToString(map));
	}
}
