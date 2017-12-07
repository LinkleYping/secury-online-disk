package handlethread;

import java.io.File;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class DeleteFile implements Runnable
{
	HashMap<String,String> map = new HashMap<>();
	String root = "g:\\网盘存储";
	Socket socket = null;
	String Username = null;
	String Route = null;
	boolean IsLast = false;
	public DeleteFile(Socket socket,String data)
	{
		map = MapString.StringToMap(data);
		this.socket = socket;
		this.Username = map.get("Username");
		this.Route = map.get("Route");
		if(map.get("IsLast").equals("true"))
			IsLast = true;
		else
			IsLast = false;
	}
	public void run()
	{
		File file = new File(root + "\\" + Username + Route);
		DeleteDir(file);
		if(IsLast == true)
			new Thread(new FileList(socket,Username,GetFolder(Route))).start();
	}
	private String GetFolder(String Route)
	{
		int i;
		for(i = Route.length() - 1;i >= 0;i--)
		{
			if(Route.charAt(i) == '\\')
				break;
		}
		return Route.substring(0, i);
	}
	private boolean DeleteDir(File dir) 
    {
        if (dir.isDirectory()) 
        {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i=0; i<children.length; i++) 
            {
                boolean success = DeleteDir(new File(dir, children[i]));
                if (!success) 
                {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        UpdateDatabase(dir);
        return dir.delete();
    }
	private void UpdateDatabase(File file)
	{
		//首先查询已使用的量
		String statement = "select Used,Total from capacity where mail = '" + Username + "';";
		ResultSet result = Database.Send(statement);
		long Used = 0;
		try
		{
			result.next();
			Used = Long.valueOf(result.getString("Used"));
			Used = Used - file.length();
			statement = "update capacity set used = '" + String.valueOf(Used) + "' where mail = '" +
						Username + "';";
			Database.Send(statement);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}