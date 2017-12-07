import java.io.*;
import java.net.*;
import java.util.HashMap;

import javax.swing.JProgressBar;

public class RecvFile implements Runnable
{
	static int BufLen = 1024;
	Socket socket = null;
	String Username = null;
	String Filename = null;
	String FileSize = null;
	String FileSizeByte = null;
	String Route = null;
	public String DefaultDownloadRoute = "g:\\OnlineDiskLadyDownload";
	MainWindow mainwindow = null;
	JProgressBar bar = null;
	
	public RecvFile(Socket socket,MainWindow mainwindow,String data)
	{
		this.socket = socket;
		this.mainwindow = mainwindow;
		
		HashMap<String,String> map = MapString.StringToMap(data);
		Username = map.get("Username");
		Filename = map.get("Filename");
		FileSize = map.get("FileSize");
		FileSizeByte = map.get("FileSizeByte");
		
		bar = mainwindow.AddOneDownloadFile(Filename, FileSize);
	}
	@Override
	public void run()
	{
		Recv();
	}
	// 接收文件
	public void Recv()
	{
		String Route = DefaultDownloadRoute + "\\"  + Filename;
		
		try
		{
			// 创建文件，获取输出流
			File file = new File(Route);
			FileOutputStream fout = new FileOutputStream(file);
			// 创建读取的缓冲区
			byte[] RecvByte = new byte[BufLen];
			// 发送
			int length = 0;
			long total = 0;
			// 获取输入流
			InputStream in = socket.getInputStream();
			RC4 rc = new RC4();
			while ((length = in.read(RecvByte, 0, RecvByte.length)) > 0)
			{
				RecvByte = rc.rc4(RecvByte,"123");
				fout.write(RecvByte, 0, length);
				total = total + length;
				int percent = (int)(100 * ((double)total / Double.valueOf(FileSizeByte).doubleValue()));
				bar.setValue(percent);
				bar.setString(percent + " %");
			}
			System.out.println("已接收文件:" + file.getName() + "共" + total + "字节");
			// 关闭输出流
			//接收完成之后将文件的路径信息保存在文件中
			String filepath = "G:/OnlineDiskLadyDownload/RouteList.txt";
            File routefile = new  File(filepath);
            if (routefile.exists()){
                //首次创建
                //System.out.println("已经存在该文件");
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
                content = content + this.Username + "|" +this.Route + "|" +this.FileSize +"\n";//用户+路径+文件大小
                System.out.println("记录的传输文件信息为："+content);
                fw.write(content);  
                fw.close();  
            }  
			fout.close();
			if(Route.indexOf(".zip")>0)
			{
			        File zipFile = new File(Route);  
			        conzip tempzip=new conzip();
			        try {
						tempzip.unZipFiles(zipFile, DefaultDownloadRoute);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
			        //System.out.println(file.delete());
			        file.delete();
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
