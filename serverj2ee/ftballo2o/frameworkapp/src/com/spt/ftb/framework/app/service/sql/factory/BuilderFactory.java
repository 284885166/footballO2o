/**   
 * @Title: BuilderFactory.java 
 * @Package com.iot.isp.framework.app.service.sql.factory 
 * @Description: (用一句话描述该文件做什么) 
 * @author zq 
 * @date 2014年2月26日 上午9:56:28 
 * @version V1R1  
 */ 
package com.spt.ftb.framework.app.service.sql.factory;

import com.spt.ftb.framework.app.service.sql.builder.SqlBuilder;
import com.spt.ftb.framework.common.ResourceManager;
import com.spt.ftb.framework.common.log.LogManager;
import com.spt.ftb.framework.util.Constants;

/** 
 * @ClassName: BuilderFactory 
 * @Description: SQL 语句组装工厂类 
 * @author zq
 * @date 2014年2月26日 上午9:56:28  
 */
public class BuilderFactory
{
	public static SqlBuilder getSqlBuilder()
	{
		Class<?> builderClass = null;
		try
		{
			builderClass = Class.forName(getClassName(ResourceManager.getString("used.database")));
		}
		catch (ClassNotFoundException e)
		{
			LogManager.error(e, " Create class error : " + getClassName(ResourceManager.getString("used.database")));
		}
		SqlBuilder sqlBuilder = null;
		try
		{
			sqlBuilder = (SqlBuilder) builderClass.newInstance();
		}
		catch (InstantiationException e)
		{
			LogManager.error(e, "Instance SqlBuilder error.");
		}
		catch (IllegalAccessException e)
		{
			LogManager.error(e, "Instance SqlBuilder error.");
		}
		return sqlBuilder;
	}

	private static String getClassName(String name)
	{
		return Constants.CLASSPATHAPP + "." + name + Constants.CLASSSUFFIXAPP;
	}
}
