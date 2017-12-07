import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Recv implements Runnable
{
	Login login = null;
	MainWindow mainwindow = null;
	String Username = null;
	public Recv(Login login)
	{
		this.login = login;
	}
	public void run()
	{
		try
		{
			InputStream in = Connect.socket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			while(true)
			{
				String data = br.readLine();
				String Mark = MapString.StringToMap(data).get("Mark");
				switch(Mark)
				{
				case "Sign Up Ok":login.signup.dispose();break;
				case "Sign Up Failed":login.signup.MailError.setText("邮箱已注册");break;
				case "Login Ok":login.dispose();
							Username = MapString.StringToMap(data).get("Username");
							mainwindow = new MainWindow(Username);break;
				case "Login Failed":login.Error.setText("用户名或密码错误");break;
				case "FileList":new Thread(new AddFileList(mainwindow,Connect.socket,data)).start();
								break;
				case "UserInfo":new Thread(new UserInfo(mainwindow,data)).start();break;
				case "DoneFileList":new Thread(new DoneFileList(mainwindow,data)).start();
				}
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
