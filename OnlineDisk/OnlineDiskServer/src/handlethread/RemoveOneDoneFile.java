package handlethread;

import java.util.HashMap;

public class RemoveOneDoneFile implements Runnable
{
	public String Username = null;
	public String Filename = null;
	public RemoveOneDoneFile(String data)
	{
		HashMap<String,String> map = MapString.StringToMap(data);
		Username = map.get("Username");
		Filename = map.get("Filename");
	}
	public void run()
	{
		String statement = "delete from log where username = '" + Username + "' and Filename = '"
						+ Filename + "';";
		Database.Send(statement);
	}
}
