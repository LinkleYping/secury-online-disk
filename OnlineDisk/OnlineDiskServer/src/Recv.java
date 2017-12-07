import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import handlethread.*;

public class Recv implements Runnable
{
	Socket socket = null;
	String root = "g:\\网盘存储";
	public Recv(Socket socket)
	{
		this.socket = socket;
	}
	public void run()
	{
		try
		{
			InputStream in = socket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			while(true)
			{
				String data = br.readLine();
				String Mark = MapString.StringToMap(data).get("Mark");
				//处理消息
				switch(Mark)
				{
				case "Sign Up":new Thread(new Signup(socket,data)).start();break;
				case "Login":
						if(new Login(socket,data).IsLoginOk==false)
							break;
						new Thread(new FileList(socket,MapString.StringToMap(data).get("Username"),"")).start();
						new Thread(new UserInfo(socket,MapString.StringToMap(data).get("Username"))).start();
						new Thread(new MoniterCapacity(socket,MapString.StringToMap(data).get("Username"))).start();
						new Thread(new DoneFileList(socket,MapString.StringToMap(data).get("Username"))).start();
						break;
				case "FileListAsk":
					String Route = MapString.StringToMap(data).get("Route");
					new Thread(new FileList(socket,MapString.StringToMap(data).get("Username"),Route)).start();
					break;
				case "Upload":new Thread(new RecvFile(data,socket.getInetAddress().getHostAddress(),43232)).start();break;
				case "Download":new Thread(new SendFile(data,socket.getInetAddress().getHostAddress(),43232)).start();
						break;
				case "Delete":new Thread(new DeleteFile(socket,data)).start();break;
				case "NewFolder":new Thread(new NewFolder(socket,data)).start();break;
				case "DoneFileListAsk":new Thread(new DoneFileList(socket,MapString.StringToMap(data).get("Username"))).start();break;
				case "RemoveOneDoneFile":new Thread(new RemoveOneDoneFile(data)).start();break;
				case "NewNickname":new Thread(new NewNickname(data)).start();break;
				default:System.out.println(data);
				}
				Thread.sleep(100);
			}
		} catch (IOException | InterruptedException e)
		{
			//e.printStackTrace();
		}
	}
}
