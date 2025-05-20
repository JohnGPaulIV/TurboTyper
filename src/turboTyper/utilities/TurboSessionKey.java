package turboTyper.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import static turboTyper.TurboTyper.*;

public class TurboSessionKey {
	private long sessionNum;
	private Calendar date;
	private Integer year, day, month, hour, minute, second, miliSecond;
	private static String SESSION_INFO_PATH = rb.getString("SessionInfoFile");
	private static Path sesInfoFileName = Paths.get(SESSION_INFO_PATH);
	private static String SESSION_NUM = "SessionNum";
	private static Properties props;

	public TurboSessionKey(long sessionNum, Calendar date)
	{
		this.sessionNum = sessionNum;
		this.date = date;
		this.year = getYear();
		this.day = getDayNum();
		this.month = getMonth();
		this.hour = getHour();
		this.minute = getMinute();
		this.second = getSecond();
		this.miliSecond = getMiliSecond();
	}
	
	public long getCurrSessionNum()
	{
		return sessionNum;
	}
	
	public Integer getYear()
	{
		return get(Calendar.YEAR);
	}
	
	public Integer getDayNum()
	{
		return get(Calendar.DAY_OF_MONTH);
	}
	
	public Integer getMonth()
	{
		return get(Calendar.MONTH);
	}
	
	public Integer getHour()
	{
		return get(Calendar.HOUR);
	}
	
	public Integer getMinute()
	{
		return get(Calendar.MINUTE);
	}
	
	public Integer getSecond()
	{
		return get(Calendar.SECOND);
	}
	
	public Integer getMiliSecond()
	{
		return get(Calendar.MILLISECOND);
	}
	
	private Integer get(Integer key)
	{
		return date.get(key);
	}
	
	private static String getProps(String property)
	{
		loadProps();
		return props.getProperty(property);
	}
	
	private static void loadProps()
	{
		try(InputStream in = Files.newInputStream(sesInfoFileName))
		{
			TurboSessionKey.props.load(in);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private static Long getCurrentSession()
	{
		loadProps();
		Long next = Long.parseLong(props.getProperty(SESSION_NUM, "0"));
		return next;
	}
	
	private static void changeKey(String key, String value)
	{
		loadProps();
		
		props.setProperty(key, value);
		
		try (OutputStream out = Files.newOutputStream(sesInfoFileName, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE))
		{
			props.store(out, "Updated '" + key + "'");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private static void changeKey(String key, Integer value)
	{
		changeKey(key, String.valueOf(value));
	}
	
	private static void changeKey(String key, Double value)
	{
		changeKey(key, String.valueOf(value));
	}
	
	private static void changeKey(String key, Long value)
	{
		changeKey(key, String.valueOf(value));
	}
	
	public static void incrementSession()
	{
		changeKey(SESSION_NUM, getCurrentSession() + 1);
	}
	
	private static Long getSessionNum()
	{
		incrementSession();
		return getCurrentSession();
	}
	
	public static String getSessionInstance()
	{
		return getProps(SESSION_NUM);
	}
	
	public static TurboSessionKey createkey()
	{
		TurboSessionKey.props = new Properties();
		/*try
		{
			TurboSessionKey.propFile = getPropertiesFileOnDisk();
		}
		catch (IOException | URISyntaxException e)
		{
			e.printStackTrace();
		}*/
		loadProps();
		Long sn = getSessionNum();
		Calendar c = Calendar.getInstance();
		return new TurboSessionKey(sn, c);
	}
}
