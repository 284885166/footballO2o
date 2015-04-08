/**   
 * @Title: IspMethodBeforeAdvice.java 
 * @Package com.iot.isp.framework.common.logmanager 
 * @Description: 方法开始时打印日志，这里只打印增删改的日志，通过spring AOP代理实现
 * @author xumingsen 
 * @date 2014年1月15日 上午10:52:14 
 * @version V1R1  
 */
package com.spt.ftb.framework.common.log;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;

/**
 * @ClassName: IspMethodBeforeAdvice
 * @Description: 方法开始时打印日志，这里只打印增删改的日志，通过spring AOP代理实现
 * @author xumingsen
 * @date 2014年1月15日 上午10:52:14
 */
public class IspMethodBeforeAdvice
{
	// 方法名称
	private String methodName = null;

	// 类名
	private String className = null;

	// 方法参数
	private Object[] args = null;

	private Logger logger = Logger.getLogger("SYSTEM_RUNTIME");

	/**
	 * 
	 * @Description: 打印方法开始时的日志
	 * @param jp
	 *            设定文件
	 */
	public void logBefore(JoinPoint jp)
	{
		try
		{
			methodName = jp.getSignature().getName();
			className = jp.getTarget().getClass().getSimpleName();
			args = jp.getArgs();
			logger.info(getLogInfo());
		}
		catch (Exception e)
		{
			logger.error("记录运行日志错误", e);
		}
	}

	/**
	 * @Description: 组装系统日志
	 * @return String 返回日志描述信息
	 */
	private String getLogInfo()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("系统日志： ");
		sb.append(className);
		sb.append(" 中的方法 ");
		sb.append(methodName);
		sb.append(" 被调用， ");
		sb.append("参数为：");
		sb.append(getArgs());
		return sb.toString();
	}

	// 得到方法调用中的参数
	private String getArgs()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		if (null != args)
		{
			for (Object obj : args)
			{
				sb.append(obj.toString());
			}
		}
		sb.append("]");
		return sb.toString();
	}
}
