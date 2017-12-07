import java.net.Socket;
import java.util.HashMap;

public class AddFileList implements Runnable
{
	Socket socket = null;
	MainWindow mainwindow = null;
	HashMap<String,String> map = null;
	public AddFileList(MainWindow mainwindow,Socket socket,String data)
	{
		this.mainwindow = mainwindow;
		this.socket = socket;
		map = MapString.StringToMap(data);
	}

	public void run()
	{
		map.remove("Mark");//ȥ��Mark�ֶ�
		int FileNum = map.size();
		String [] Filename = new String[FileNum];
		String [] FileSize = new String[FileNum];
		String [] ModTime = new String[FileNum];
		//����map���ҵ�ÿ���ļ�����Ϣ
		int i = 0;
		for(String str : map.keySet())
		{
			HashMap<String,String> temp = MapString.StringToMapX(map.get(str));
			Filename[i] = str;	
			FileSize[i] = temp.get("FileSize");
			ModTime[i] = temp.get("ModTime");
			i++;
		}
		if(Filename != null && FileSize != null && ModTime != null && mainwindow != null)
			mainwindow.AddMulTableData(Filename, FileSize, ModTime, FileNum);
	}

}
