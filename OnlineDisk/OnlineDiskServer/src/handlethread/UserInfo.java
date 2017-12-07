package handlethread;

import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class UserInfo implements Runnable
{
	String Username = null;
	Socket socket = null;
	public UserInfo(Socket socket,String Username)
	{
		this.socket = socket;
		this.Username = Username;
	}
	@Override
	public void run()
	{
		HashMap<String,String> map = new HashMap<>();
		//查询昵称
		String statement = "select Nickname from user where mail = '" + Username + "';";
		ResultSet result = Database.Send(statement);
		map.put("Mark", "UserInfo");
		try
		{
			result.next();
			map.put("Nickname", result.getString("Nickname"));
			result.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		//查询容量
		statement = "select Used,Total from capacity where mail = '" + Username + "';";
		result = Database.Send(statement);
		//把容量转换成合适的单位
		try
		{
			result.next();
			String Used = SetCapacity(result.getString("Used"));
			String Total = SetCapacity(result.getString("Total"));
			String Percent = String.valueOf(((long)(Double.valueOf(result.getString("Used")) /
					Double.valueOf(result.getString("Total")) * 100)));
			map.put("UsedCapacity", Used);
			map.put("TotalCapacity", Total);
			map.put("Percent", Percent);
			result.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		Send.send(socket, MapString.MapToString(map));
	}
	private String SetCapacity(String Capacity)
	{
		double Amount = Double.valueOf(Capacity);
		String Result = null;
		if(Amount < 1024) // < 1KB
		{
			Amount = (double)Math.round(Amount*100)/100;
			Result = Amount + " B";
		}
		else if(Amount > 1024 && Amount < 1024 * 1024)// >1KB <1MB
		{
			Amount = (double)Math.round(Amount / (double)1024*10)/10;
			Result = Amount + " KB";
		}
		else if(Amount > 1024 * 1024 && 
				Amount < 1024 * 1024 * 1024)// >1MB <1GB
		{
			Amount = (double)Math.round(Amount / (double)(1024 *1024)*10)/10;
			Result = Amount + " MB";
		}
		else
		{
			Amount = (double)Math.round(Amount / (double)(1024 *1024*1024)*10)/10;
			Result = Amount + " GB";
		}
		return Result;
	}
}
