/**
 * @ClassName: LogManager 
 * @Description: 日志输出代理类。
 * @author xumingsen
 * @date 2014年1月14日 下午5:04:35
 */
package com.spt.ftb.framework.common.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @ClassName: LogManager
 * @Description: 日志输出代理类。
 * @author xumingsen
 * @date 2014年1月14日 下午5:04:35
 */
public class LogManager
{
	// 是否进行源代码定位，ture表示输出源代码所在类以及代码行
	private static boolean showLocSrc = true;

	// 消息所属和消息内容的分隔符
	private static final String msgSplit = "-";

	// 该类的名称，用于识别该类的堆栈
	private static final String thisClassName = LogManager.class.getName();

	// 默认输出日志的日志工具
	private static Log logger = LogFactory.getLog("");

	/**
	 * 
	 * @Description: 打印trace日志
	 * @param message
	 *            异常信息
	 */
	public static void trace(String message)
	{
		if (logger.isTraceEnabled())
		{
			logger.trace(getStackTrace(message, showLocSrc ? Thread.currentThread().getStackTrace() : null));
		}
	}

	/**
	 * 输出debug信息
	 * 
	 * @param message
	 */
	public static void debug(String message)
	{
		if (logger.isDebugEnabled())
		{
			logger.debug(getStackTrace(message, showLocSrc ? Thread.currentThread().getStackTrace() : null));
		}
	}

	/**
	 * 输出info信息
	 * 
	 * @param message
	 */
	public static void info(String message)
	{
		if (logger.isInfoEnabled())
		{
			logger.info(getStackTrace(message, showLocSrc ? Thread.currentThread().getStackTrace() : null));
		}
	}

	/**
	 * 输出warn信息
	 * 
	 * @param message
	 */
	public static void warn(String message)
	{
		if (logger.isWarnEnabled())
		{
			logger.warn(getStackTrace(message, showLocSrc ? Thread.currentThread().getStackTrace() : null));
		}
	}

	/**
	 * 输出error信息
	 * 
	 * @param message
	 */
	public static void error(String message)
	{
		if (logger.isErrorEnabled())
		{
			logger.error(getStackTrace(message, showLocSrc ? Thread.currentThread().getStackTrace() : null));
		}
	}

	/**
	 * 输出fatal信息
	 * 
	 * @param message
	 */
	public static void fatal(String message)
	{
		if (logger.isFatalEnabled())
		{
			logger.fatal(getStackTrace(message, showLocSrc ? Thread.currentThread().getStackTrace() : null));
		}
	}

	/**
	 * 
	 * @Description: 打印trace日志
	 * @param throwable
	 *            异常
	 * @param message
	 *            异常信息
	 */
	public static void trace(Throwable throwable, String message)
	{
		if (logger.isTraceEnabled())
		{
			logger.trace(getStackTrace(message, showLocSrc ? Thread.currentThread().getStackTrace() : null), throwable);
		}
	}

	/**
	 * 输出debug信息
	 * 
	 * @param message
	 */
	public static void debug(Throwable throwable, String message)
	{
		if (logger.isDebugEnabled())
		{
			logger.debug(getStackTrace(message, showLocSrc ? Thread.currentThread().getStackTrace() : null), throwable);
		}
	}

	/**
	 * 输出info信息
	 * 
	 * @param message
	 */
	public static void info(Throwable throwable, String message)
	{
		if (logger.isInfoEnabled())
		{
			logger.info(getStackTrace(message, showLocSrc ? Thread.currentThread().getStackTrace() : null), throwable);
		}
	}

	/**
	 * 输出warn信息
	 * 
	 * @param message
	 */
	public static void warn(Throwable throwable, String message)
	{
		if (logger.isWarnEnabled())
		{
			logger.warn(getStackTrace(message, showLocSrc ? Thread.currentThread().getStackTrace() : null), throwable);
		}
	}

	/**
	 * 输出error信息
	 * 
	 * @param message
	 */
	public static void error(Throwable throwable, String message)
	{
		if (logger.isErrorEnabled())
		{
			logger.error(getStackTrace(message, showLocSrc ? Thread.currentThread().getStackTrace() : null), throwable);
		}
	}

	/**
	 * 输出fatal信息
	 * 
	 * @param message
	 */
	public static void fatal(Throwable throwable, String message)
	{
		if (logger.isFatalEnabled())
		{
			logger.fatal(getStackTrace(message, showLocSrc ? Thread.currentThread().getStackTrace() : null), throwable);
		}
	}

	/**
	 * 获取堆栈信息。
	 * 
	 * @param message
	 *            日志消息
	 * @param stackTraces
	 *            堆栈信息。
	 */
	private static String getStackTrace(String message, StackTraceElement[] stackTraces)
	{
		if (stackTraces != null)
		{
			// 加入源代码定位
			message = getStackMsg(stackTraces) + msgSplit + message;
		}
		return message;
	}

	/**
	 * 根据堆栈信息得到源代码行信息
	 * 
	 * @param stackTraces
	 *            堆栈信息
	 * @return String 调用输出日志的代码所在的类.方法.代码行的相关信息
	 */
	private static String getStackMsg(StackTraceElement[] stackTraces)
	{
		if (stackTraces == null)
		{
			return null;
		}
		boolean srcFlag = false;
		for (StackTraceElement stackTrace : stackTraces)
		{
			// 如果上一行堆栈代码是本类的堆栈，则该行代码则为源代码的最原始堆栈。
			if (srcFlag)
			{
				return stackTrace == null ? "" : getClassNameAndLineNum(stackTrace);
			}
			// 定位本类的堆栈
			if (thisClassName.equals(stackTrace.getClassName()))
			{
				srcFlag = true;
			}
		}
		return null;
	}

	/**
	 * 
	 * @Description: 得到堆栈的类名
	 * @param stackTrace
	 *            堆栈
	 * @return String 堆栈的类名和行号
	 */
	private static String getClassNameAndLineNum(StackTraceElement stackTrace)
	{
		StringBuffer sb = new StringBuffer();
		if (null == stackTrace)
		{
			return sb.toString();
		}
		sb.append(stackTrace.getFileName());
		sb.append(":");
		sb.append(stackTrace.getLineNumber());
		return sb.toString();
	}
}