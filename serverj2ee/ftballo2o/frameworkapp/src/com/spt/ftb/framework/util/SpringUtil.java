/**   
 * @Title: SpringUtil.java 
 * @Package com.iot.framework.util 
 * @Description: Spring 工具类
 * @author zq 
 * @date 2014年1月6日 下午1:37:42 
 * @version V1R1  
 */
package com.spt.ftb.framework.util;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @ClassName: SpringUtil
 * @Description: Spring 工具类
 * @author zq
 * @date 2014年1月6日 下午1:37:42
 */
public class SpringUtil
{
	public static ApplicationContext getApplicationContext(ServletContext servletContext)
	{
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		return wac;
	}
}
