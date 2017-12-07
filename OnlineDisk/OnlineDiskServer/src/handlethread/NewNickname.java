package handlethread;

import java.util.HashMap;

public class NewNickname implements Runnable
{
	String Username = null;
	String Nickname = null;
	public NewNickname(String data)
	{
		HashMap<String,String> map = MapString.StringToMap(data);
		this.Username = map.get("Username");
		this.Nickname = map.get("Nickname");
	}
	public void run()
	{
		String statement = "update user set Nickname = '" + Nickname + "' where mail = '" 
							+ Username +"';";
		Database.Send(statement);
	}
}
