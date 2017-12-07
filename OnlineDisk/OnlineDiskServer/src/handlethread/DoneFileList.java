package handlethread;

import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class DoneFileList implements Runnable
{
	Socket socket = null;
	String Username = null;
	
	public DoneFileList(Socket socket,String Username)
	{
		this.socket = socket;
		this.Username = Username;
	}
	public void run()
	{
		HashMap<String,String> map = new HashMap<>();
		map.put("Mark", "DoneFileList");
		//查询传输完成的文件列表
		String statement = "select * from log where username = '" + Username + "';";
		ResultSet result = Database.Send(statement);
		try
		{
			while(result.next())
			{
				String Filename = result.getString("Filename");
				String FileSize = result.getString("FileSize");
				map.put(Filename, FileSize);
			}
			Send.send(socket, MapString.MapToString(map));
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
