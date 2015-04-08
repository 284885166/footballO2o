/**   
 * @Title: PorpertyManager.java 
 * @Package com.iot.isp.util 
 * @Description: (用一句话描述该文件做什么) 
 * @author zq 
 * @date 2014年1月16日 下午3:40:51 
 * @version V1R1  
 */
package com.spt.ftb.framework.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import com.spt.ftb.framework.common.log.LogManager;
import com.spt.ftb.framework.util.XmlUtil;

/**
 * @ClassName: PorpertyManager
 * @Description: 资源管理工具类
 * @author zq
 * @date 2014年1月16日 下午3:40:51
 */
public class ResourceManager
{
	private final static Map<String, List<Map.Entry<String, String>>> sensorsortMap = new ConcurrentHashMap<String, List<Map.Entry<String, String>>>();

	private final static Map<String, String> resourceMap = new ConcurrentHashMap<String, String>();

	/**
	 * 
	 * @Description: 获取传感器顺序
	 * @param @return 设定文件
	 * @return List<Map.Entry<String,String>> 返回类型
	 * @throws ResourceManager
	 */
	public synchronized static List<Map.Entry<String, String>> getSensorsort(String argType)
	{
		return sensorsortMap.get(argType);
	}

	/**
	 * 
	 * @Description: 根据key获取资源文件内容
	 * @param @param key
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws ResourceManager
	 */
	public synchronized static String getString(String key)
	{
		if (!resourceMap.containsKey(key))
		{
			return "Does not exist " + key;
		}
		return resourceMap.get(key).trim();
	}

	/**
	 * 
	 * @Description: 根据key获取资源文件内容,如果无对应的值,则用默认值替代
	 * @param @param key
	 * @param @param defaultValue
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws ResourceManager
	 */
	public synchronized static String getString(String key, String defaultValue)
	{
		return resourceMap.get(key).trim().equals("") ? defaultValue : resourceMap.get(key).trim();
	}

	/**
	 * 
	 * @Description: 对hashmap进行排序
	 * @param @param map
	 * @param @return 设定文件
	 * @return List<Map.Entry<String,String>> 返回类型
	 * @throws ResourceManager
	 */
	public static List<Map.Entry<String, String>> sort4Map(HashMap<String, String> map)
	{
		List<Map.Entry<String, String>> sortValue = new ArrayList<Map.Entry<String, String>>(map.entrySet());
		// 排序
		Collections.sort(sortValue, new Comparator<Map.Entry<String, String>>()
		{
			@Override
			public int compare(Entry<String, String> o1, Entry<String, String> o2)
			{
				return (o1.getKey()).toString().compareTo(o2.getKey());
			}
		});
		return sortValue;
	}

	/**
	 * 
	 * @Description: 对hashmap进行排序(降序排列)
	 * @param @param map
	 * @param @return 设定文件
	 * @return List<Map.Entry<Date,Double>> 返回类型
	 * @throws ResourceManager
	 */
	public static List<Map.Entry<Date, Double>> sort4Map(Map<Date, Double> map)
	{
		List<Map.Entry<Date, Double>> sortValue = new ArrayList<Map.Entry<Date, Double>>(map.entrySet());
		// 排序
		Collections.sort(sortValue, new Comparator<Map.Entry<Date, Double>>()
		{
			@Override
			public int compare(Entry<Date, Double> o1, Entry<Date, Double> o2)
			{
				if (o1.getKey().getTime() < o2.getKey().getTime())
				{
					return 1;
				}
				return -1;
			}
		});
		return sortValue;
	}

	/**
	 * 
	 * @Description: 获取conf 的相对路径
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws ResourceManager
	 */
	public static String getAbsoluteConfPath()
	{
		return System.getProperty("user.dir") + File.separator + "conf" + File.separator;
	}

	public static String getConfPath(String fileName)
	{
		String confPath = getAbsoluteConfPath() + fileName;
		File file = new File(confPath);
		if (!file.exists())
		{
			confPath = getString("ispserver.path") + File.separator + fileName;
		}
		return confPath;
	}

	public static void loadCommon(String path)
	{
		Properties cprop = null;
		try
		{
			cprop = loadProperties(path);
		}
		catch (IOException e)
		{
			LogManager.error(e, "not find file : common.properties");
			return;
		}
		Enumeration<Object> ckeys = cprop.keys();
		while (ckeys.hasMoreElements())
		{
			String key = ckeys.nextElement().toString();
			resourceMap.put(key, cprop.getProperty(key));
		}
	}

	public static void loadSensorsortMap(String path)
	{
		sensorsortMap.putAll(XmlUtil.getArgMaps(path));
	}

	private static Properties loadProperties(String path) throws IOException
	{
		Properties p = new Properties();
		p.load(new FileInputStream(new File(path)));
		return p;
	}
}
