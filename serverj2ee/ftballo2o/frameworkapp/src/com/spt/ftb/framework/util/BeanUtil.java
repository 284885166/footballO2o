/**   
 * @Title: BeanUtil.java 
 * @Package com.iot.isp.framework.util 
 * @Description: (用一句话描述该文件做什么) 
 * @author zq 
 * @date 2014年1月7日 下午5:51:17 
 * @version V1R1  
 */
package com.spt.ftb.framework.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

import com.spt.ftb.framework.app.web.page.Page;
import com.spt.ftb.framework.common.log.LogManager;

/**
 * @ClassName: BeanUtil
 * @Description: (这里用一句话描述这个类的作用)
 * @author zq
 * @date 2014年1月7日 下午5:51:17
 */
public class BeanUtil
{
	/**
	 * 实体类转换
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param dest 目标对象
	 * @param orig 原始对象
	 * @return void 无
	 * @throws BeanUtil
	 */
	public static void copyProperties(Object dest, Object orig)
	{
		try
		{
			PropertyUtils.copyProperties(dest, orig);
		}
		catch (IllegalAccessException e)
		{
			LogManager.error("copyProperties error.");
		}
		catch (InvocationTargetException e)
		{
			LogManager.error("copyProperties error.");
		}
		catch (NoSuchMethodException e)
		{
			LogManager.error("copyProperties error.");
		}
	}

	/**
	 * 用来拷贝page
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param destPage
	 * @param @param prigPage
	 * @return void 返回类型 
	 * @throws BeanUtil
	 */
	public static void copyPage(Page destPage,Page prigPage)
	{
		destPage.setCurpage(prigPage.getCurpage());
		destPage.setPagecount(prigPage.getPagecount());
		destPage.setPagesize(prigPage.getPagesize());
		destPage.setSumcount(prigPage.getSumcount());
	}
	
	/**
	 * 获取Object中属性值
	 * @param @param object 
	 * @param @param str 字段名称
	 * @param @param isPrivate 是否是私有字段
	 * @return String 返回类型 
	 */
	public static String getBeanPro(Object object, String str, boolean isPrivate)
	{
		try
		{
			if (isPrivate)
			{
				Field field = object.getClass().getDeclaredField(str);
				field.setAccessible(true);
				return String.valueOf(field.get(object));
			}
			else
			{
				return String.valueOf(object.getClass().getField(str));
			}
		}
		catch (Exception e)
		{
			return null;
		}
		
	}
	
	public static String getValue(Object object, String key)
	{
		Map<String, Object> ret = new HashMap<String, Object>();
		Field[] fields = object.getClass().getDeclaredFields();
		String fieldName;
		String fieldValue;
		Object obj = null;
		for (int i = 0; i < fields.length; i++)
		{
			try
			{
				Field field = fields[i];
				// 设置字段访问权限
				field.setAccessible(true);
				fieldName = field.getName();
				obj = field.get(object);
				fieldValue = obj == null ? "" : obj.toString();
				ret.put(fieldName, fieldValue);
			}
			catch (IllegalArgumentException e)
			{
				e.printStackTrace();
			}
			catch (IllegalAccessException e)
			{
				e.printStackTrace();
			}
		}
		return String.valueOf(ret.get(key));
	}
}
