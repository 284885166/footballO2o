package com.spt.ftb.framework.util;

/**
 * 
 * @ClassName: MathUtil 
 * @Description: 数学工具类 
 * @author zq
 * @date 2014年1月16日 上午10:50:25
 */
public class MathUtil
{
	public static float round(int n, float value)
	{
		if ((Float.isInfinite(value)) || (Float.isNaN(value)))
			return (0.0F / 0.0F);
		float tran = (float) Math.pow(10.0D, n);
		return Math.round(value * tran) / tran;
	}

	public static double round(int n, double value)
	{
		if ((Double.isInfinite(value)) || (Double.isNaN(value)))
			return (0.0D / 0.0D);
		double tran = Math.pow(10.0D, n);
		return Math.round(value * tran) / tran;
	}
}