import handlethread.Database;

public class Main
{
	public static void main(String[] args)
	{
		System.out.println("服务器正在启动");
		Database.Init();//连接数据库
		new RecvConnect().run();//开始接收连接
	}
}
