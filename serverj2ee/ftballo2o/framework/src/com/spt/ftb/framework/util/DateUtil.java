/**   
 * @Title: DateUtil.java 
 * @Package com.iot.isp.framework.util 
 * @Description: 时间处理工具类
 * @author xumingsen 
 * @date 2014年1月16日 下午2:34:33 
 * @version V1R1  
 */
package com.spt.ftb.framework.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;

/**
 * @ClassName: DateUtil
 * @Description: 时间处理工具类
 * @author xumingsen
 * @date 2014年1月16日 下午2:34:33
 */
public class DateUtil
{
	private static String defaultDatePattern = "yyyy-MM-dd";

	private static String timePattern = "HH:mm";

	// 日期格式yyyy-MM-dd字符串常量
	private static final String DATE_FORMAT = "yyyy-MM-dd";

	// 日期格式HH:mm:ss字符串常量
	private static final String HOUR_FORMAT = "HH:mm:ss";

	// 日期格式yyyy-MM-dd HH:mm:ss字符串常量
	private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	private static SimpleDateFormat sdf_date_format = new SimpleDateFormat(DATE_FORMAT);

	private static SimpleDateFormat sdf_hour_format = new SimpleDateFormat(HOUR_FORMAT);

	private static SimpleDateFormat sdf_datetime_format = new SimpleDateFormat(DATETIME_FORMAT);

	public static Date getSimpleDate(String s)
	{
		try
		{
			return sdf_date_format.parse(s);
		}
		catch (ParseException e)
		{
			return new Date();
		}
	}
	
	/**
	 * @Description: 获得当前日期及时间，以格式为：yyyy-MM-dd HH:mm:ss的日期字符串形式返回
	 * @return String 当前日期及时间
	 */
	public static String getDateTime()
	{
		try
		{
			return sdf_datetime_format.format(Calendar.getInstance().getTime());
		}
		catch (Exception e)
		{
			return "";
		}
	}

	/**
	 * 
	 * @Description: 获得当前日期，以格式为：yyyy-MM-dd的日期字符串形式返回
	 * @return String 当前日期
	 */
	public static String getDate()
	{
		try
		{
			return sdf_date_format.format(Calendar.getInstance().getTime());
		}
		catch (Exception e)
		{
			return "";
		}
	}

	/**
	 * 将24小时转换为12小时制
	 */
	public static String get12Time(String s)
	{
		String timeStr = s.split("T")[1];
		String[] ss = timeStr.split(":");
		int hour = Integer.parseInt(ss[0]);
		String miute = ss[1];
		if (hour > 12)
		{
			return hour - 12 + ":" + miute + " PM";
		}
		else
		{
			return hour + ":" + miute + " AM";
		}
	}

	/**
	 * @Description: 获得当前时间，以格式为：HH:mm:ss的日期字符串形式返回
	 * @return String 服务器
	 */
	public static String getTime()
	{
		String temp = "";
		try
		{
			temp += sdf_hour_format.format(Calendar.getInstance().getTime());
			return temp;
		}
		catch (Exception e)
		{
			return "";
		}
	}

	/**
	 * @Description: 统计时开始日期的默认值
	 * @return String 开始日期的默认值
	 */
	public static String getStartDate()
	{
		try
		{
			return getYear() + "-01-01";
		}
		catch (Exception e)
		{
			return "";
		}
	}

	/**
	 * @Description: 统计时结束日期的默认值
	 * @return String 统计时结束日期的默认值
	 */
	public static String getEndDate()
	{
		try
		{
			return getDate();
		}
		catch (Exception e)
		{
			return "";
		}
	}

	/**
	 * @Description: 获得当前日期的年份
	 * @return String 当前日期的年份
	 */
	public static String getYear()
	{
		try
		{
			return String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		}
		catch (Exception e)
		{
			return "";
		}
	}

	/**
	 * @Description: 获得当前日期的月份
	 * @return String 当前日期的月份
	 */
	public static String getMonth()
	{
		try
		{
			java.text.DecimalFormat df = new java.text.DecimalFormat();
			df.applyPattern("00;00");
			return df.format((Calendar.getInstance().get(Calendar.MONTH) + 1));
		}
		catch (Exception e)
		{
			return "";
		}
	}

	/**
	 * @Description: 获得在当前月中天数
	 * @return String 在当前月中天数
	 */
	public static String getDay()
	{
		try
		{
			return String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
		}
		catch (Exception e)
		{
			return "";
		}
	}

	/**
	 * @Description: 比较两个日期相差的天数
	 * @param beginDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @return int 两个日期相差的天数
	 */
	public static int getMargin(String beginDate, String endDate)
	{
		try
		{
			ParsePosition pos = new ParsePosition(0);
			ParsePosition pos1 = new ParsePosition(0);
			Date dt1 = sdf_date_format.parse(beginDate, pos);
			Date dt2 = sdf_date_format.parse(endDate, pos1);
			long l = dt1.getTime() - dt2.getTime();
			int margin = (int) (l / (24 * 60 * 60 * 1000));
			return margin;
		}
		catch (Exception e)
		{
			return 0;
		}
	}

	/**
	 * @Description: 比较两个日期相差的天数
	 * @param beginDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @return double 两个日期相差的天数
	 */
	public static double getDoubleMargin(String beginDate, String endDate)
	{
		double margin;
		try
		{
			ParsePosition pos = new ParsePosition(0);
			ParsePosition pos1 = new ParsePosition(0);
			Date dt1 = sdf_datetime_format.parse(beginDate, pos);
			Date dt2 = sdf_datetime_format.parse(endDate, pos1);
			long l = dt1.getTime() - dt2.getTime();
			margin = (l / (24 * 60 * 60 * 1000.00));
			return margin;
		}
		catch (Exception e)
		{
			return 0;
		}
	}

	/**
	 * @Description: 比较两个日期相差的月数
	 * @param beginDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @return int 两个日期相差的月数
	 */
	public static int getMonthMargin(String beginDate, String endDate)
	{
		int margin;
		try
		{
			margin = (Integer.parseInt(endDate.substring(0, 4)) - Integer.parseInt(beginDate.substring(0, 4))) * 12;
			margin += (Integer.parseInt(endDate.substring(4, 7).replaceAll("-0", "-")) - Integer.parseInt(beginDate
					.substring(4, 7).replaceAll("-0", "-")));
			return margin;
		}
		catch (Exception e)
		{
			return 0;
		}
	}

	/**
	 * @Description: 返回日期加X天后的日期
	 * @param date
	 *            時間
	 * @param days
	 *            天数
	 * @return String 日期加X天后的日期
	 */
	public static String addDay(String date, int days)
	{
		try
		{
			GregorianCalendar gCal = new GregorianCalendar(Integer.parseInt(date.substring(0, 4)),
					Integer.parseInt(date.substring(5, 7)) - 1, Integer.parseInt(date.substring(8, 10)));
			gCal.add(GregorianCalendar.DATE, days);
			return sdf_date_format.format(gCal.getTime());
		}
		catch (Exception e)
		{
			return getDate();
		}
	}

	/**
	 * @Description: 返回日期加X月后的日期
	 * @param date
	 *            时间
	 * @param days
	 *            天数
	 * @return String 日期加X月后的日期
	 */
	public static String addMonth(String date, int days)
	{
		try
		{
			GregorianCalendar gCal = new GregorianCalendar(Integer.parseInt(date.substring(0, 4)),
					Integer.parseInt(date.substring(5, 7)) - 1, Integer.parseInt(date.substring(8, 10)));
			gCal.add(GregorianCalendar.MONTH, days);
			return sdf_date_format.format(gCal.getTime());
		}
		catch (Exception e)
		{
			return getDate();
		}
	}

	/**
	 * @Description: 返回日期加X年后的日期
	 * @param date
	 *            时间
	 * @param year
	 *            年
	 * @return String 日期加X年后的日期
	 */
	public static String addYear(String date, int year)
	{
		try
		{
			GregorianCalendar gCal = new GregorianCalendar(Integer.parseInt(date.substring(0, 4)),
					Integer.parseInt(date.substring(5, 7)) - 1, Integer.parseInt(date.substring(8, 10)));
			gCal.add(GregorianCalendar.YEAR, year);
			return sdf_date_format.format(gCal.getTime());
		}
		catch (Exception e)
		{
			return "";
		}
	}

	/**
	 * @Description: 返回某年某月中的最大天
	 * @param iyear
	 *            年
	 * @param imonth
	 *            月
	 * @return int 某年某月中的最大天
	 */
	public static int getMaxDay(int iyear, int imonth)
	{
		int day = 0;
		try
		{
			if (imonth == 1 || imonth == 3 || imonth == 5 || imonth == 7 || imonth == 8 || imonth == 10 || imonth == 12)
			{
				day = 31;
			}
			else if (imonth == 4 || imonth == 6 || imonth == 9 || imonth == 11)
			{
				day = 30;
			}
			else if ((0 == (iyear % 4)) && (0 != (iyear % 100)) || (0 == (iyear % 400)))
			{
				day = 29;
			}
			else
			{
				day = 28;
			}
			return day;
		}
		catch (Exception e)
		{
			return 1;
		}
	}

	/**
	 * @Description: 格式化日期
	 * @param orgDate
	 *            时间
	 * @param Type
	 *            类型
	 * @param Span
	 * @return String 日期
	 */
	public static String rollDate(String orgDate, int Type, int Span)
	{
		try
		{
			String temp = "";
			int iyear, imonth, iday;
			int iPos = 0;
			char seperater = '-';
			if (orgDate == null || orgDate.length() < 6)
			{
				return "";
			}
			iPos = orgDate.indexOf(seperater);
			if (iPos > 0)
			{
				iyear = Integer.parseInt(orgDate.substring(0, iPos));
				temp = orgDate.substring(iPos + 1);
			}
			else
			{
				iyear = Integer.parseInt(orgDate.substring(0, 4));
				temp = orgDate.substring(4);
			}
			iPos = temp.indexOf(seperater);
			if (iPos > 0)
			{
				imonth = Integer.parseInt(temp.substring(0, iPos));
				temp = temp.substring(iPos + 1);
			}
			else
			{
				imonth = Integer.parseInt(temp.substring(0, 2));
				temp = temp.substring(2);
			}
			imonth--;
			if (imonth < 0 || imonth > 11)
			{
				imonth = 0;
			}
			iday = Integer.parseInt(temp);
			if (iday < 1 || iday > 31)
				iday = 1;
			Calendar orgcale = Calendar.getInstance();
			orgcale.set(iyear, imonth, iday);
			temp = rollDate(orgcale, Type, Span);
			return temp;
		}
		catch (Exception e)
		{
			return "";
		}
	}

	/**
	 * @Description: 格式化时间
	 * @param cal
	 * @param Type
	 * @param Span
	 * @return String 时间
	 */
	public static String rollDate(Calendar cal, int Type, int Span)
	{
		try
		{
			String temp = "";
			Calendar rolcale;
			rolcale = cal;
			rolcale.add(Type, Span);
			temp = sdf_date_format.format(rolcale.getTime());
			return temp;
		}
		catch (Exception e)
		{
			return "";
		}
	}

	/**
	 * @Description: 将指定日期按默认格式进行格式代化成字符串后输出如：yyyy-MM-dd
	 * @param aDate
	 *            時間
	 * @return String 日期
	 */
	public static final String getDate(Date aDate)
	{
		SimpleDateFormat df = null;
		String returnValue = "";
		if (aDate != null)
		{
			df = new SimpleDateFormat(defaultDatePattern);
			returnValue = df.format(aDate);
		}
		return (returnValue);
	}

	/**
	 * @Description: 取得给定日期的时间字符串，格式为当前默认时间格式
	 * @param theTime
	 * @return String 默认时间
	 */
	public static String getTimeNow(Date theTime)
	{
		return getDateTime(timePattern, theTime);
	}

	/**
	 * @Description: 将日期类转换成指定格式的字符串形式
	 * @param formater
	 *            日期格式化方式
	 * @param date
	 *            时间
	 * @return String 按照formater输出的日期格式
	 */
	public static final String getDateTime(String formater, Date date)
	{
		SimpleDateFormat df = null;
		String returnValue = "";
		if (null != date)
		{
			df = new SimpleDateFormat(formater);
			returnValue = df.format(date);
		}
		return returnValue;
	}

	/**
	 * 
	 * @Description: 毫秒转换成时间 
	 * @param @param milliseconds
	 * @param @return    设定文件 
	 * @return Date 返回类型 
	 * @throws DateUtil
	 */
	public static Date convert2Date(long milliseconds)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milliseconds);
		return calendar.getTime();
	}
	
	/**
	 * @Description: 将指定的日期转换成默认格式的字符串形式
	 * @param date
	 * @return String 日期的默认格式
	 */
	public static final String convertDateToString(Date date)
	{
		return getDateTime(defaultDatePattern, date);
	}

	/**
	 * @Description: 将日期字符串按指定格式转换成日期类型
	 * @param dateFormant
	 *            日期的格式
	 * @param strDate
	 *            时间
	 * @return Date 日期
	 */
	public static final Date convertStringToDate(String dateFormant, String strDate)
	{
		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(dateFormant);
		try
		{
			date = df.parse(strDate);
		}
		catch (ParseException pe)
		{
			return new Date();
		}
		return date;
	}

	/**
	 * @Description: 将日期字符串按默认格式转换成日期类型
	 * @param strDate
	 *            日期字符串
	 * @return Date 时间
	 */
	public static Date convertStringToDate(String strDate) throws ParseException
	{
		Date aDate = null;
		try
		{
			aDate = convertStringToDate(defaultDatePattern, strDate);
		}
		catch (Exception pe)
		{
			new Date();
		}
		return aDate;
	}

	/**
	 * @Description: 返回一个JAVA简单类型的日期字符串
	 * @return String 日期字符串
	 */
	public static String getSimpleDateFormat()
	{
		SimpleDateFormat formatter = new SimpleDateFormat();
		String NDateTime = formatter.format(new Date());
		return NDateTime;
	}

	/**
	 * 
	 * @Description: 将指定字符串格式的日期与当前时间比较
	 * @param strDate
	 *            日期字符串
	 * @return int 制定日期与当前时间的差值
	 */
	public static int compareToCurTime(String strDate)
	{
		if (StringUtils.isBlank(strDate))
		{
			return -1;
		}
		Date curTime = Calendar.getInstance().getTime();
		String strCurTime = null;
		try
		{
			strCurTime = sdf_datetime_format.format(curTime);
		}
		catch (Exception e)
		{
			return -1;
		}
		if (StringUtils.isNotBlank(strCurTime))
		{
			return strCurTime.compareTo(strDate);
		}
		return -1;
	}

	/**
	 * @Description: 为查询日期添加最小时间
	 * @param dateParam
	 *            时间
	 * @return Date 时间
	 */
	@SuppressWarnings("deprecation")
	public static Date addStartTime(Date dateParam)
	{
		Date date = dateParam;
		try
		{
			date.setHours(0);
			date.setMinutes(0);
			date.setSeconds(0);
			return date;
		}
		catch (Exception ex)
		{
			return date;
		}
	}

	/**
	 * @Description: 为查询日期添加最大时间
	 * @param dateParam
	 * @return Date 时间
	 */
	@SuppressWarnings("deprecation")
	public static Date addEndTime(Date dateParam)
	{
		Date date = dateParam;
		try
		{
			date.setHours(23);
			date.setMinutes(59);
			date.setSeconds(0);
			return date;
		}
		catch (Exception ex)
		{
			return date;
		}
	}

	/**
	 * @Description: 为查询日期添加最大时间
	 * @param month
	 *            月份
	 * @return String 日期的最大时间
	 */
	@SuppressWarnings("deprecation")
	public static String getMonthLastDay(int month)
	{
		Date date = new Date();
		int[][] day = { { 0, 30, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 },
				{ 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 } };
		int year = date.getYear() + 1900;
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
		{
			return day[1][month] + "";
		}
		else
		{
			return day[0][month] + "";
		}
	}

	/**
	 * @Description: 返回指定年份中指定月份的天数
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @return String 月的天数
	 */
	public static String getMonthLastDay(int year, int month)
	{
		int[][] day = { { 0, 30, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 },
				{ 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 } };
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
		{
			return day[1][month] + "";
		}
		else
		{
			return day[0][month] + "";
		}
	}

	/**
	 * @Description: 判断是平年还是闰年
	 * @param year
	 *            年
	 * @return boolean 瑞年返回true，否則false
	 */
	public static boolean isLeapyear(int year)
	{
		if ((year % 4 == 0 && year % 100 != 0) || (year % 400) == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * @Description: 取得当前时间的日戳
	 * @return String 時間戳
	 */
	@SuppressWarnings("deprecation")
	public static String getTimestamp()
	{
		Date date = Calendar.getInstance().getTime();
		String timestamp = "" + (date.getYear() + 1900) + date.getMonth() + date.getDate() + date.getMinutes()
				+ date.getSeconds() + date.getTime();
		return timestamp;
	}

	/**
	 * @Description: 取得指定时间的日戳
	 * @param date
	 *            時間
	 * @return String 制定時間的時間戳
	 */
	@SuppressWarnings("deprecation")
	public static String getTimestamp(Date date)
	{
		String timestamp = "" + (date.getYear() + 1900) + date.getMonth() + date.getDate() + date.getMinutes()
				+ date.getSeconds() + date.getTime();
		return timestamp;
	}

	public static Date calculatingDate(Date date, int intrel)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, intrel);
		return calendar.getTime();
	}
}
