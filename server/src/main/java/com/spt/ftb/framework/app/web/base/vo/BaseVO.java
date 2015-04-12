/**   
 * @Title: BaseVO.java 
 * @Package com.iot.isp.web.base.vo 
 * @Description: (用一句话描述该文件做什么) 
 * @author mazenghui
 * @date Jan 14, 2014 2:44:25 PM 
 * @version V1R1  
 */
package com.spt.ftb.framework.app.web.base.vo;

import java.io.Serializable;

/**
 * @ClassName: BaseVO
 * @Description: (这里用一句话描述这个类的作用)
 * @author mazenghui
 * @date Jan 14, 2014 2:44:25 PM
 */
public class BaseVO implements Serializable
{
	private static final long serialVersionUID = 1L;
	protected int id;

	public BaseVO()
	{
	}

	public BaseVO(int id)
	{
		this.id = id;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		BaseVO other = (BaseVO) obj;
		if (id != other.id)
		{
			return false;
		}
		return true;
	}

	@Override
	public String toString()
	{
		return this.getClass().getName() + "VO [ID=" + id + "]";
	}
}
