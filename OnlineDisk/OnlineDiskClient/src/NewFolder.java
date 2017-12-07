import java.util.HashMap;

public class NewFolder implements Runnable
{
	private String Foldername = null;
	private String Username = null;
	private String Route = null;
	public NewFolder(String Foldername,String Username,String Route)
	{
		this.Foldername = Foldername; 
		this.Username = Username;
		this.Route = Route;
	}
	public void run()
	{
		HashMap<String,String> map = new HashMap<>();
		map.put("Mark", "NewFolder");
		map.put("Foldername", Foldername);
		map.put("Username", Username);
		map.put("Route", Route);
		Send.send(MapString.MapToString(map));
	}
}
