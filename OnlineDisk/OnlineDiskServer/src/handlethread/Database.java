package handlethread;
import java.sql.*;
public class Database
{
	static java.sql.Connection con;
	static PreparedStatement sql;
	static ResultSet res;
	static int port = 3306;
	static String username = "OnlineDiskServer";
	static String password = "3rf3rh93g379r";
	public static boolean Init()//�������ݿ�
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("���ݿ��������سɹ�");
		}catch(ClassNotFoundException e)
		{
			e.printStackTrace();
			System.out.println("���ݿ���������ʧ��");
			System.exit(0);;
		}
		try
		{
			con = DriverManager.getConnection("jdbc:mysql:"
					+ "//127.0.0.1:" + port + "/OnlineServerData" , username , password);
			System.out.println("���ݿ����ӳɹ�");
		}catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("���ݿ�����ʧ��");
			return false;
		}
		return true;
	}
	public static ResultSet Send(String s)//��ѯ
	{
		ResultSet result = null;
		try
		{
			Statement statement = con.createStatement();
			if(s.contains("select"))
			{
				result = statement.executeQuery(s);
			}
			else if(s.contains("insert")||s.contains("update")
					||s.contains("delete")||s.contains("create"))
			{
				statement.executeUpdate(s);
			}
			else
			{
				;
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return result;
	}
}
