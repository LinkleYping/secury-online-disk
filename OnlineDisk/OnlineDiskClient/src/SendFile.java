import java.io.*;
import java.net.*;
import java.util.HashMap;

import javax.swing.JProgressBar;

public class SendFile implements Runnable
{
	static int BufLen = 1024;
	String Username = null;
	String Route = null;
	String Filename = null;
	String RemoteRoute = null;
	String FileSize = null;
	String Ip = null;
	Socket socket = null;
	int Port = 0;
	MainWindow mainwindow = null;
	JProgressBar bar = null;

	public SendFile(Socket socket, String data, MainWindow mainwindow)
	{
		HashMap<String, String> map = MapString.StringToMap(data);
		this.Username = map.get("Username");
		this.Filename = map.get("Filename");
		this.FileSize = map.get("FileSize");
		this.Route = map.get("Route");
		this.RemoteRoute = map.get("RemoteRoute");
		this.socket = socket;
		this.mainwindow = mainwindow;
		if(FileSize.equals("-"))
		{
			   String zipname=Route+".zip";
			   File[] files = new File[]{new File(Route)};  
		        File zip = new File(zipname);  
		        conzip tempzip=new conzip();
		        try {
					tempzip.ZipFiles(zip,"",files);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
		        Route+=".zip";
		        Filename=Filename+".zip";
		}
	}

	public void run()
	{
		try
		{
			bar = mainwindow.AddOneUploadFile(Filename, FileSize);

			Send();// 发送文件
			socket.close();
			new Thread(new AskFileList(Username, GetFolder(RemoteRoute))).start();
		} catch (Exception e)
		{
			System.out.println("上传文件时连接服务器失败");
			e.printStackTrace();
		}
	}

	// 发送文件
	public void Send()
	{
		try
		{
			// 创建读取的缓冲区
			byte[] SendBuf = new byte[BufLen];
			// 发送长度
			int length = 0;
			int totallen = 0;
			// 获取输出流
			OutputStream out = socket.getOutputStream();
			// 创建文件，获取输入流
			File file = new File(Route);
			FileInputStream fin = new FileInputStream(file);
			RC4 rc = new RC4();
			while ((length = fin.read(SendBuf, 0, SendBuf.length)) != -1)
			{
				SendBuf = rc.rc4(SendBuf, "123");
				out.write(SendBuf, 0, length);
				out.flush();
				totallen += length;
				int percent = (int) (100 * ((double) totallen / (double) file.length()));
				bar.setValue(percent);
				bar.setString(percent + " %");
			}
			System.out.println("已发送文件:" + file.getName() + "共" + totallen + "字节");
			//将得到的路径存储在本地文件中，方便之后打开使用
			String filepath = "G:/OnlineDiskLadyDownload/RouteList.txt";
            File routefile = new  File(filepath);
            if (routefile.exists()){
                //首次创建
                System.out.println("已经存在该文件");
            }
            else{
                //创建一个
                try{
                    routefile.createNewFile();
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            synchronized (routefile) {  
                FileWriter fw = new FileWriter(filepath);
                String content = "";
                content = content + this.Username + "|" +this.Route + "|" +this.FileSize+"\n";//用户+路径+文件大小
                System.out.println("记录的传输文件信息为："+content);
                fw.write(content);  
                fw.close();  
            }  
			// 关闭输出流
			fin.close();
			if(FileSize.equals("-"))
				file.delete();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private String GetFolder(String Route)
	{
		int i;
		for (i = Route.length() - 1; i >= 0; i--)
		{
			if (Route.charAt(i) == '\\')
				break;
		}
		return Route.substring(0, i);
	}

}
