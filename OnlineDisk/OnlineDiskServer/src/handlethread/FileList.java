package handlethread;

import java.io.File;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class FileList implements Runnable
{
	Socket socket = null;
	File file = null;
	String root = "g:\\网盘存储";
	public FileList(Socket socket,String Username,String Route)
	{
		if(Username == null||Username.equals("请输入用户名"))
			return;
		this.socket = socket;
		file = new File(root + "\\" + Username);
		if(file.exists() == false)
		{
			file.mkdir();
			return;
		}
		else
		{
			if(Route == null || Route.equals(""))
				file = new File(root + "\\" + Username);
			else
				file = new File(root + "\\" + Username + "\\" + Route);
		}
	}
	public void run()
	{
		if(file == null)
			return;
		File[] FileList = file.listFiles();
		HashMap<String,String> map = new HashMap<>();
		map.put("Mark", "FileList");
		for(int i = 0;i < FileList.length;i++)
		{
			HashMap<String,String> temp = new HashMap<>();
			//文件大小
			double FileSize = 0;
			if(FileList[i].isDirectory())//如果是文件夹
				temp.put("FileSize", "-");
			else if(FileList[i].length() < 1024) // < 1KB
			{
				FileSize = (double)Math.round(FileList[i].length()*100)/100;
				temp.put("FileSize", FileSize + " B");
			}
			else if(FileList[i].length() > 1024 && FileList[i].length() < 1024 * 1024)// >1KB <1MB
			{
				FileSize = (double)Math.round(FileList[i].length() / (double)1024*100)/100;
				temp.put("FileSize", FileSize + " KB");
			}
			else if(FileList[i].length() > 1024 * 1024 && 
					FileList[i].length() < 1024 * 1024 * 1024)// >1MB <1GB
			{
				FileSize = (double)Math.round(FileList[i].length() / (double)(1024 *1024)*100)/100;
				temp.put("FileSize", FileSize + " MB");
			}
			else
			{
				FileSize = (double)Math.round(FileList[i].length() / (double)(1024 *1024*1024)*100)/100;
				temp.put("FileSize", FileSize + " GB");
			}
			//修改时间
			Calendar cal = Calendar.getInstance();   
			long time = FileList[i].lastModified();
			cal.setTimeInMillis(time);
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			temp.put("ModTime",format.format(cal.getTime()));
			//打包
			map.put(FileList[i].getName(), MapString.MapToStringX(temp));
		}
		Send.send(this.socket, MapString.MapToString(map));
	}
}
