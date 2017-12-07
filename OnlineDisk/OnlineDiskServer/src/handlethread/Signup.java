package handlethread;

import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Signup implements Runnable
{
	HashMap<String,String> map = null;
	Socket socket = null;
	public Signup(Socket socket,String data)
	{
		this.socket = socket;
		map = MapString.StringToMap(data);
	}
	public void run()
	{
		//首先查询邮箱是否重复
		String statement = "select * from user where mail =" + "'" + map.get("Username") + "'";
		ResultSet result = Database.Send(statement);
		try
		{
			result.last();
			System.out.println(result.getRow());
			if(result.getRow() > 0)//如果已存在
			{
				HashMap<String,String> map = new HashMap<>();
				map.put("Mark", "Sign Up Failed");
				Send.send(socket, MapString.MapToString(map));
				return;
			}
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		//确认没有注册这个邮箱后
		//插入表User
		String Nickname = "'" + map.get("Nickname") + "'";
		String mail ="'" + map.get("Username") + "'";
		String password ="'" + map.get("Password") + "'";
		statement = "insert into user values (" 
				+ Nickname + "," + mail + "," + password + ")" + ";";
		Database.Send(statement);
		//插入表capacity
		statement = "insert into capacity values("
				+ mail + "," + "0" + "," + "107374182400" + ")" + ";";
		Database.Send(statement);
		//返回注册成功的消息
		HashMap<String,String> map = new HashMap<>();
		map.put("Mark", "Sign Up Ok");
		Send.send(socket, MapString.MapToString(map));
	}
}
