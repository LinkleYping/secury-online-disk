package handlethread;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Send
{
	public static void send(Socket socket,String Command)
	{
		try
		{
			OutputStream out = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(out);// 将输出流包装成打印流
			pw.write(Command + "\n");
			pw.flush();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
