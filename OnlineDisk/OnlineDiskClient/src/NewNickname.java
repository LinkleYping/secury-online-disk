import java.util.HashMap;

public class NewNickname implements Runnable
{
	String Username = null;
	String NewNickName = null;
	
	public NewNickname(String Username,String NewNickName)
	{
		this.Username = Username;
		this.NewNickName = NewNickName;
	}
	public void run()
	{
		HashMap<String,String> map = new HashMap<>();
		map.put("Mark", "NewNickname");
		map.put("Username", Username);
		map.put("Nickname", NewNickName);
		Send.send(MapString.MapToString(map));
		MainWindow.mainwindow.Nickname.setText(NewNickName);
	}
}
