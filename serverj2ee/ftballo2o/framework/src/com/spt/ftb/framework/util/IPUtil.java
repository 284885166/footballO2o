/**   
 * @Title: IPUtil.java 
 * @Package com.iot.isp.framework.util 
 * @Description: (用一句话描述该文件做什么) 
 * @author zq 
 * @date 2014年5月16日 上午11:28:47 
 * @version V1R1  
 */
package com.spt.ftb.framework.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.spt.ftb.framework.common.log.LogManager;

/**
 * @ClassName: IPUtil
 * @Description: (这里用一句话描述这个类的作用)
 * @author zq
 * @date 2014年5月16日 上午11:28:47
 */
public class IPUtil
{
	public static String trimSpaces(String IP)
	{
		// 去掉IP字符串前后所有的空格
		while (IP.startsWith(" "))
		{
			IP = IP.substring(1, IP.length()).trim();
		}
		while (IP.endsWith(" "))
		{
			IP = IP.substring(0, IP.length() - 1).trim();
		}
		return IP;
	}

	public static boolean isIp(String IP)
	{
		// 判断是否是一个IP
		boolean b = false;
		IP = trimSpaces(IP);
		if (IP.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}"))
		{
			String s[] = IP.split("\\.");
			if (Integer.parseInt(s[0]) < 255)
				if (Integer.parseInt(s[1]) < 255)
					if (Integer.parseInt(s[2]) < 255)
						if (Integer.parseInt(s[3]) < 255)
							b = true;
		}
		return b;
	}

	public static String getIPbyDomain(String domain)
	{
		InetAddress addr = null;
		try
		{
			addr = InetAddress.getByName(domain);
		}
		catch (UnknownHostException e)
		{
			LogManager.error(e, e.getMessage());
			return "127.0.0.1";
		}
		return addr.getHostAddress();
	}
	
}
