/**   
 * @Title: StringUtil.java 
 * @Package com.iot.isp.framework.util 
 * @Description: (用一句话描述该文件做什么) 
 * @author mazenghui
 * @date 2014-2-20 下午02:18:36 
 * @version V1R1  
 */
package com.spt.ftb.framework.util;

import java.io.UnsupportedEncodingException;


/**
 * @ClassName: StringUtil
 * @Description: (这里用一句话描述这个类的作用)
 * @author mazenghui
 * @date 2014-2-20 下午02:18:36
 */
public class StringUtil
{
	public static String SUCCESS = "{success:true}";

	public static String FAIL = "{success:false}";
	
	/**
	 * controller返回错误信息
	 * 
	 * @param msg
	 *            信息
	 */
	public static String getReturnMsg(boolean isSuccess, String msg)
	{
		StringBuilder sb = new StringBuilder("{\"success\":");
		sb.append(isSuccess);
		sb.append(",\"msg\":\"");
		sb.append(msg);
		sb.append("\"}");
		return sb.toString();
	}

	/**
	 * 字符串是否为空
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param str
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws ObjectUtil
	 */
	public static boolean isNull(String str)
	{
		if (null == str || ("").equals(str))
		{
			return true;
		}
		return false;
	}

	/**
	 * 连接字符串
	 * 
	 * @Description: 连接字符串
	 * @param str
	 *            字符串列表
	 * @return String 返回类型
	 */
	public static String contanctStrings(String... strs)
	{
		if (null == strs || strs.length == 0)
		{
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (String str : strs)
		{
			sb.append(str);
		}
		return sb.toString();
	}

	/**
	 * 
	 * @Description: 按指定长度分割字符串,通常用于短信
	 * @param @param msg
	 * @param @param num
	 * @param @return 设定文件
	 * @return String[] 返回类型
	 * @throws StringUtil
	 */
	public static String[] split(String msg, int num)
	{
		int len = msg.length();
		if (len <= num)
		{
			return new String[] { msg };
		}
		int count = len / (num - 1);
		count += len > (num - 1) * count ? 1 : 0;
		String[] result = new String[count];
		int pos = 0;
		int splitLen = num - 1;
		for (int i = 0; i < count; i++)
		{
			if (i == count - 1)
			{
				splitLen = len - pos;
			}
			result[i] = msg.substring(pos, pos + splitLen);
			pos += splitLen;
		}
		return result;
	}
	
	public static String getUrlStr(String str)
	{
		if(!isNull(str))
		{
			try
			{
				return new String(str.getBytes("ISO-8859-1"), "UTF-8");
			}
			catch (UnsupportedEncodingException e)
			{
				e.printStackTrace();
			}
		}
		return str;
	}
}