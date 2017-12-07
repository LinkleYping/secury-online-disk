package handlethread;

import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Login 
{
	HashMap<String,String> map = null;
	Socket socket = null;
	public boolean IsLoginOk = false;
	
	public Login(Socket socket,String data)
	{
		this.socket = socket;
		map = MapString.StringToMap(data);
		String statement = "select password from user where mail =" + "'" + map.get("Username") + "'";
		ResultSet result = Database.Send(statement);
		try
		{
			result.last();
			if(result.getRow() == 0)//如果用户名不存在
			{
				HashMap<String,String> map = new HashMap<>();
				map.put("Mark", "Login Failed");
				Send.send(socket, MapString.MapToString(map));
				return;
			}
			if(result.getString("password").equals(map.get("Password")))//密码正确
			{
				HashMap<String,String> map = new HashMap<>();
				map.put("Mark", "Login Ok");
				map.put("Username", this.map.get("Username"));
				Send.send(socket, MapString.MapToString(map));
				IsLoginOk = true;
				return;
			}
			else
			{
				HashMap<String,String> map = new HashMap<>();
				map.put("Mark", "Login Failed");
				Send.send(socket, MapString.MapToString(map));
				return;
			}
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
