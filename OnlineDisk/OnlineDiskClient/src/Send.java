import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class Send
{
	public static void send(String Command)
	{
		try
		{
			OutputStream out = Connect.socket.getOutputStream();
			PrintWriter pw = new PrintWriter(out);// ���������װ�ɴ�ӡ��
			pw.write(Command + "\n");
			pw.flush();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
