import java.util.HashMap;

public class RemoveOneDoneFile implements Runnable
{
	String Filename = null;
	String Username = null;
	public RemoveOneDoneFile(String Username,String Filename)
	{
		this.Username = Username;
		this.Filename = Filename;
	}
	public void run()
	{
		HashMap<String,String> map = new HashMap<>();
		map.put("Mark", "RemoveOneDoneFile");
		map.put("Username", Username);
		map.put("Filename", Filename);
		Send.send(MapString.MapToString(map));
	}
}
