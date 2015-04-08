package com.spt.ftb.framework.app.data.po;


/**
 * 
 * @ClassName: BaseEntity
 * @Description: po 基类
 * @author zq
 * @date 2014年1月6日 下午3:50:42
 */
public abstract class BasePo implements java.io.Serializable
{
	private static final long serialVersionUID = 2035013017939483936L;

	protected int id;

	public BasePo()
	{
	}

	public BasePo(int id)
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
		BasePo other = (BasePo) obj;
		if (id != other.id)
		{
			return false;
		}
		return true;
	}

	@Override
	public String toString()
	{
		return this.getClass().getName() + "Entity [ID=" + id + "]";
	}
}
