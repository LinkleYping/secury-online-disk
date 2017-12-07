import java.net.Socket;
import java.util.HashMap;

public class AskFileList implements Runnable
{
	String Username = null;
	String Route = null;
	public AskFileList(String Username,String Route)
	{
		this.Username = Username;
		this.Route = Route;
	}
	public void run()
	{
		HashMap<String,String> map = new HashMap<>();
		map.put("Mark", "FileListAsk");
		map.put("Username", Username);
		map.put("Route", Route);
		Send.send(MapString.MapToString(map));
	}
}
