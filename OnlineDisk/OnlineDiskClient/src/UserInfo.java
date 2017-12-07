import java.util.HashMap;

public class UserInfo implements Runnable
{
	String Nickname = null;
	String UsedAmount = null;
	String TotalAmount = null;
	String Percent = null;
	MainWindow mainwindow = null;
	public UserInfo(MainWindow mainwindow,String data)
	{
		HashMap<String,String> map = MapString.StringToMap(data);
		Nickname = map.get("Nickname");
		UsedAmount = map.get("UsedCapacity");
		TotalAmount = map.get("TotalCapacity");
		Percent = map.get("Percent");
		this.mainwindow = mainwindow;
	}
	public void run()
	{
		mainwindow.Nickname.setText(Nickname);
		mainwindow.SetCapacity(UsedAmount, TotalAmount, Integer.valueOf(Percent));
	}
}
