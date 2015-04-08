/**   
 * @Title: IspEnum.java 
 * @Package com.iot.isp.framework.common 
 * @Description: 枚举类 
 * @author xumingsen 
 * @date 2014年1月15日 下午5:29:33 
 * @version V1R1  
 */
package com.spt.ftb.framework.common;

/**
 * @ClassName: FtbEnum
 * @Description: 枚举类
 * @author zq
 * @date 2014年1月15日 下午5:29:33
 */
public class FtbEnum
{
	/**
	 * @ClassName: Status
	 * @Description: 状态枚举类
	 * @author zq
	 * @date 2014年2月19日 下午4:18:30
	 */
	public static enum Status
	{
		Close(0, "close", "关闭"), Open(1, "open", "开启"), Stop(2, "stop", "停止"), Unknown(-1, "unKnown", "未知");
		// 日志ID
		private int code;

		// 英文名称
		private String nameEn;

		// 中文名称
		private String nameZh;

		/**
		 * 
		 * @Description: 构造函数
		 * @param code
		 * @param nameEn
		 * @param nameZh
		 */
		private Status(int code, String nameEn, String nameZh)
		{
			this.code = code;
			this.nameEn = nameEn;
			this.nameZh = nameZh;
		}

		public static Status getStatus(int code)
		{
			for (Status type : values())
			{
				if (type.getCode() == code)
				{
					return type;
				}
			}
			return Unknown;
		}

		/**
		 * @Description: 枚举值
		 * @return Integer 返回类型
		 * @throws Status
		 */
		public int getCode()
		{
			return this.code;
		}

		/**
		 * @Description: 英文名称
		 * @return String 返回类型
		 */
		public String getNameEn()
		{
			return this.nameEn;
		}

		/**
		 * @Description: 中文名称
		 * @return String 返回类型
		 */
		public String getNameZh()
		{
			return this.nameZh;
		}
	}

	/**
	 * sql查询的join方式
	 * 
	 * @ClassName: SqlJoinModel
	 * @Description: (这里用一句话描述这个类的作用)
	 * @author zq
	 * @date 2014年2月26日 下午3:04:50
	 */
	public static enum SqlJoinModel
	{
		LEFT("LEFT JOIN"), RIGHT("RIGHT JOIN"), INNER("INNER JOIN");
		private String joinModel;

		/**
		 * @Description: 构造函数
		 * @param joinModel
		 *            连接列i型
		 */
		private SqlJoinModel(String joinModel)
		{
			this.joinModel = joinModel;
		}

		/**
		 * 连接查询类型
		 * 
		 * @Description: 连接查询类型
		 * @return String 返回连接查询类型
		 */
		public String getJoinModel()
		{
			return this.joinModel;
		}
	}

	public static enum TimeUnit
	{
		// 分
		MINUTE(1, "分", 1),
		// 时
		HOURS(2, "时", 60),
		// 天
		DAY(3, "天", 1440);
		private int id;

		private String name;

		private int value;

		private TimeUnit(int id, String name, int value)
		{
			this.id = id;
			this.name = name;
			this.value = value;
		}

		public static TimeUnit getTimeUnit(int id)
		{
			for (TimeUnit timeUnit : TimeUnit.values())
			{
				if (timeUnit.getId() == id)
				{
					return timeUnit;
				}
			}
			return null;
		}

		public int getId()
		{
			return id;
		}

		public void setId(int id)
		{
			this.id = id;
		}

		public String getName()
		{
			return name;
		}

		public void setName(String name)
		{
			this.name = name;
		}

		public int getValue()
		{
			return value;
		}

		public void setValue(int value)
		{
			this.value = value;
		}
	}
}
