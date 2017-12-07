import java.util.HashMap;

public class DeleteFile implements Runnable
{
	String Username = null;
	String Route = null;
	boolean IsLast = false;
	public DeleteFile(String Username,String Route,boolean IsLast)
	{
		this.Username = Username;
		this.Route = Route;
		this.IsLast = IsLast;
	}
	public void run()
	{
		HashMap<String,String> map = new HashMap<>();
		map.put("Mark", "Delete");
		map.put("Username", Username);
		map.put("Route", Route);
		if(IsLast == true)
			map.put("IsLast", "true");
		else
			map.put("IsLast", "false");
		Send.send(MapString.MapToString(map));
	}
	
}
