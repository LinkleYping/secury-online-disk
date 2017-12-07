import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.security.*;

public class Upload implements Runnable
{
	String data = null;
	public Upload(String Username,String Route,String RemoteRoute)
	{
		HashMap<String,String> map = new HashMap<>();
		map.put("Mark", "Upload");
		map.put("Username",Username);
		map.put("Route", Route);
		map.put("RemoteRoute", RemoteRoute);//服务器文件路径
		File file = new File(Route);//客户端文件路径
		map.put("Filename", file.getName());
		map.put("FileSize", GetSize(file));
		
		//计算文件hash值
		try
		{
			FileInputStream in ;
			if(GetSize(file).equals("-"))
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
			        in= new FileInputStream(zip);
			}
			else
				in = new FileInputStream(file);
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			//FileInputStream in = new FileInputStream(file);
			int length = 0;
			byte [] buffer = new byte[1024];
			while((length = in.read(buffer)) != -1)//读取文件并加入流
				md5.update(buffer);
			String FileHash = new String(bytes2hex(md5.digest()));
			map.put("FileHash", FileHash);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		data = MapString.MapToString(map);
	}
	public void run()
	{
		Send.send(data);
	}
	private String GetSize(File file)
	{
		double FileSize = 0;
		String Filesize = null;
		if(file.isDirectory())//如果是文件夹
			Filesize = "-";
		else if(file.length() < 1024) // < 1KB
		{
			FileSize = (double)Math.round(file.length()*100)/100;
			Filesize =  FileSize + " B";
		}
		else if(file.length() > 1024 && file.length() < 1024 * 1024)// >1KB <1MB
		{
			FileSize = (double)Math.round(file.length() / (double)1024*100)/100;
			Filesize = FileSize + " KB";
		}
		else if(file.length() > 1024 * 1024 && 
				file.length() < 1024 * 1024 * 1024)// >1MB <1GB
		{
			FileSize = (double)Math.round(file.length() / (double)(1024 *1024)*100)/100;
			Filesize =  FileSize + " MB";
		}
		else
		{
			FileSize = (double)Math.round(file.length() / (double)(1024 *1024*1024)*100)/100;
			Filesize =  FileSize + " GB";
		}
		return Filesize;
	}
	 public static String bytes2hex(byte[] bytes)  
	    {  
	        /** 
	         * 第一个参数的解释，记得一定要设置为1 
	         *  signum of the number (-1 for negative, 0 for zero, 1 for positive). 
	         */  
	        BigInteger bigInteger = new BigInteger(1, bytes);  
	        return bigInteger.toString(16);  
	    }  
}
