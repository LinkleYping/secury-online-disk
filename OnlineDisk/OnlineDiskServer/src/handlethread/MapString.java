package handlethread;
import java.util.*;

public class MapString
{
	public MapString()
	{
		
	}
	public static String MapToString(Map map)
	{
		java.util.Map.Entry entry;
		StringBuffer sb = new StringBuffer();
		for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext();)
		{
			entry = (java.util.Map.Entry) iterator.next();
			sb.append(entry.getKey().toString()).append("¡¢")
					.append(null == entry.getValue() ? "" : entry.getValue().toString())
					.append(iterator.hasNext() ? "^" : "");
		}
		return sb.toString();
	}

	public static HashMap<String,String> StringToMap(String mapString)
	{
		HashMap map = new HashMap();
		java.util.StringTokenizer items;
		for (StringTokenizer entrys = new StringTokenizer(mapString, "^"); entrys.hasMoreTokens(); map
				.put(items.nextToken(), items.hasMoreTokens() ? ((Object) (items.nextToken())) : null))
			items = new StringTokenizer(entrys.nextToken(), "¡¢");
		return map;
	}
	public static String MapToStringX(Map map)
	{
		java.util.Map.Entry entry;
		StringBuffer sb = new StringBuffer();
		for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext();)
		{
			entry = (java.util.Map.Entry) iterator.next();
			sb.append(entry.getKey().toString()).append(";")
					.append(null == entry.getValue() ? "" : entry.getValue().toString())
					.append(iterator.hasNext() ? "|" : "");
		}
		return sb.toString();
	}

	public static HashMap<String,String> StringToMapX(String mapString)
	{
		HashMap map = new HashMap();
		java.util.StringTokenizer items;
		for (StringTokenizer entrys = new StringTokenizer(mapString, "|"); entrys.hasMoreTokens(); map
				.put(items.nextToken(), items.hasMoreTokens() ? ((Object) (items.nextToken())) : null))
			items = new StringTokenizer(entrys.nextToken(), ";");
		return map;
	}
}
