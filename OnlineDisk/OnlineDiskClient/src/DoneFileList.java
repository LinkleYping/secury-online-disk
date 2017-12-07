import java.util.HashMap;

public class DoneFileList implements Runnable
{
	MainWindow mainwindow = null;
	String data = null;
	public DoneFileList(MainWindow mainwindow,String data)
	{
		this.mainwindow = mainwindow;
		this.data = data;
	}
	public void run()
	{
		HashMap<String,String> map = MapString.StringToMap(data);
		map.remove("Mark");
		for(String Filename:map.keySet())
		{
			mainwindow.AddOneDoneFile(Filename, map.get(Filename));
		}
	}
}
