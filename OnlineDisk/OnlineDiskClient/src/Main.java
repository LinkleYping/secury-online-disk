import javax.swing.UIManager;

public class Main
{
	public static void main(String[] args) throws InterruptedException
	{
		Login login = new Login();
		new Thread(new Connect("127.0.0.1", 43211, login)).start();
		new Thread(new RecvConnect()).start();
	}
}
