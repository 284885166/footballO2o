/**   
 * @Title: ReflectUtil.java 
 * @Package com.iot.isp.util 
 * @Description: (用一句话描述该文件做什么) 
 * @author zq 
 * @date 2014年1月16日 下午7:24:16 
 * @version V1R1  
 */
package com.spt.ftb.framework.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

import com.spt.ftb.framework.common.log.LogManager;

/**
 * @ClassName: ReflectUtil
 * @Description: 反射工具类
 * @author zq
 * @date 2014年1月16日 下午7:24:16
 */
public class ReflectUtil
{
	public static Object createObj(String className)
	{
		if (null == className)
		{
			return null;
		}
		Class<?> myClass = null;
		try
		{
			myClass = Class.forName(className);
		}
		catch (ClassNotFoundException e)
		{
			LogManager.error(e, "ReflectUtil.createObj :" + e.getMessage());
		}
		Object obj = null;
		try
		{
			obj = myClass.newInstance();
		}
		catch (InstantiationException e)
		{
			LogManager.error(e, "ReflectUtil.createObj :" + e.getMessage());
		}
		catch (IllegalAccessException e)
		{
			LogManager.error(e, "ReflectUtil.createObj :" + e.getMessage());
		}
		return obj;
	}

	/**
	 * 
	 * @Description: 利用反射给对象赋值
	 * @param @param obj
	 * @param @param fieldName
	 * @param @param value 设定文件
	 * @return void 返回类型
	 * @throws ReflectUtil
	 */
	public static void reflect(Object obj, String fieldName, Object value)
	{
		if (null == obj)
		{
			return;
		}
		Method[] methods = obj.getClass().getDeclaredMethods();
		Field[] fields = obj.getClass().getDeclaredFields();
		String fieldNameStr = null;
		StringBuffer fieldSetName = new StringBuffer();
		for (Field field : fields)
		{
			fieldNameStr = field.getName();
			fieldSetName.append("set");
			fieldSetName.append(fieldNameStr.substring(0, 1).toUpperCase());
			fieldSetName.append(fieldNameStr.substring(1));
			if (!checkMet(methods, fieldSetName.toString()))
			{
				fieldSetName.delete(0, fieldSetName.length());
				continue;
			}
			if (fieldName.equalsIgnoreCase(fieldNameStr))
			{
				try
				{
					setValue(obj, value, field, fieldSetName.toString());
					return;
				}
				catch (Exception e)
				{
					LogManager.error(e, "ReflectUtil.reflect : " + e.getMessage());
				}
			}
			fieldSetName.delete(0, fieldSetName.length());
		}
	}

	/**
	 * 
	 * @Description: 获取对象字段和值
	 * @param @param obj
	 * @param @return 设定文件
	 * @return HashMap<String,String> 返回类型
	 * @throws ReflectUtil
	 */
	public static HashMap<String, String> getObjFieldNameAndValue(Object obj)
	{
		if (null == obj)
		{
			return null;
		}
		HashMap<String, String> maps = new HashMap<String, String>();
		Method[] methods = obj.getClass().getDeclaredMethods();
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields)
		{
			String fieldName = parGetName(field.getName());
			if (!checkMet(methods, fieldName))
			{
				continue;
			}
			putValue(obj, maps, fieldName, field);
		}
		return maps;
	}

	private static void putValue(Object obj, HashMap<String, String> maps, String fieldName, Field field)
	{
		Method fieldMet = null;
		try
		{
			fieldMet = obj.getClass().getMethod(fieldName);
			if (null != fieldMet.invoke(obj))
			{
				maps.put(field.getName(), fieldMet.invoke(obj).toString());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private static void setValue(Object obj, Object value, Field field, String fieldSetName) throws Exception
	{
		Method fieldSetMet = obj.getClass().getMethod(fieldSetName, field.getType());
		String fieldType = field.getType().getSimpleName();
		if (fieldType.equals("float"))
		{
			fieldSetMet.invoke(obj, Float.valueOf(value.toString()));
		}
		else if (fieldType.equals("int"))
		{
			fieldSetMet.invoke(obj, Integer.valueOf(value.toString()));
		}
		else if (fieldType.equals("String"))
		{
			fieldSetMet.invoke(obj, value.toString());
		}
	}

	/**
	 * 
	 * @Description: 拼接在某属性的 get方法
	 * @param @param fieldName
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws ReflectUtil
	 */
	private static String parGetName(String fieldName)
	{
		if (null == fieldName || "".equals(fieldName))
		{
			return null;
		}
		return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}

	/**
	 * 判断是否存在某属性的 set方法
	 * 
	 * @param methods
	 * @param fieldMet
	 * @return boolean
	 */
	private static boolean checkMet(Method[] methods, String fieldMet)
	{
		for (Method met : methods)
		{
			if (fieldMet.equals(met.getName()))
			{
				return true;
			}
		}
		return false;
	}

	public static Object invokeMethod(Object obj, String methodName, Class<?>[] parameterTypes, Object... args)
	{
		Object result = null;
		try
		{
			Method method = obj.getClass().getDeclaredMethod(methodName, parameterTypes);
			result = method.invoke(obj, args);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
}
