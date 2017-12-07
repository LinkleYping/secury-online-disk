package handlethread;

import java.io.File;
import java.net.Socket;
import java.util.HashMap;

public class NewFolder implements Runnable
{
	String Username = null;
	String Foldername = null;
	String Route = null;
	String root = "g:\\Õ¯≈Ã¥Ê¥¢";
	Socket socket = null;
	public NewFolder(Socket socket,String data)
	{
		HashMap<String,String> map = MapString.StringToMap(data);
		this.socket = socket;
		this.Username = map.get("Username");
		this.Foldername = map.get("Foldername");
		this.Route = map.get("Route");
		if(Route == null)
			Route = "";
	}
	public void run()
	{
		File file = new File(root + "\\" + Username + Route + "\\" + Foldername);
		file.mkdir();
		new Thread(new FileList(socket,Username,Route)).start();;
	}
}
