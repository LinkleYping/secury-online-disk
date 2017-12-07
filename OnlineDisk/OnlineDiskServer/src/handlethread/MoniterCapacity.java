package handlethread;

import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MoniterCapacity implements Runnable
{
	String Username = null;
	String LastUsed = null;
	String LastTotal = null;
	Socket socket = null;
	public MoniterCapacity(Socket socket,String Username)
	{
		this.socket = socket;
		this.Username = Username;
	}
	public void run()
	{
		//首次
		String statement = "select * from capacity where mail = '" + Username + "';";
		ResultSet result = Database.Send(statement);
		try
		{
			result.next();
			LastUsed = result.getString("Used");
			LastTotal = result.getString("Total");
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		//循环检查容量的变化
		while(true)
		{
			statement = "select * from capacity where mail = '" + Username + "';";
			result = Database.Send(statement);
			
			if(socket.isClosed())
				return;
			try
			{
				result.next();
				if((!LastUsed.equals(result.getString("Used")))||(!LastTotal.equals(result.getString("Total"))))//如果容量有变化
				{
					new Thread(new UserInfo(socket,Username)).start();
					LastUsed = result.getString("Used");
					LastTotal = result.getString("Total");
				}
				Thread.sleep(3000);
			} catch (SQLException | InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}
