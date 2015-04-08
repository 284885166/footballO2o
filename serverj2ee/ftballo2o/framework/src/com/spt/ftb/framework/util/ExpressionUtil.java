package com.spt.ftb.framework.util;

import java.util.Map;

import fr.expression4j.basic.impl.RealImpl;
import fr.expression4j.core.Expression;
import fr.expression4j.core.Parameters;
import fr.expression4j.core.exception.EvalException;
import fr.expression4j.core.exception.ParsingException;
import fr.expression4j.factory.ExpressionFactory;

/**
 * 
 * @ClassName: ExpressionUtil
 * @Description: 表达式函数工具类
 * @author zq
 * @date 2014年1月16日 上午10:49:55
 */
public class ExpressionUtil
{
	public static final String DEFAULT_LEFT_EXPRESSION = "f(x)";

	public static final String DEFAULT_INDEPENDENT_VARIABLE = "x";

	public static final String EXPRESSION_DIVIDE = "=";

	public static String getRightExpression(String expression)
	{
		if ((expression == null) || (!expression.contains("=")))
		{
			throw new RuntimeException("expression invalid!" + expression);
		}
		String[] strs = expression.split("[=]");
		if (strs.length < 2)
			throw new RuntimeException("right expression invalid!" + expression);
		return strs[1];
	}

	public static String analyzeExpression(String rightExp, Map<String, String> expressionMap)
			throws StackOverflowError
	{
		for (String key : expressionMap.keySet())
		{
			if (rightExp.contains(key))
			{
				String exp = getRightExpression((String) expressionMap.get(key));
				rightExp = rightExp.replace(key, exp);
				return analyzeExpression(rightExp, expressionMap);
			}
		}
		return rightExp;
	}

	public static String getExpValue(String expression, double x) throws ParsingException, EvalException
	{
		Expression ex;
		try
		{
			ex = ExpressionFactory.createExpression(expression);
		}
		catch (Exception e)
		{
			ex = ExpressionFactory.createExpression("f(x)=" + expression);
		}
		Parameters parameters = ExpressionFactory.createParameters();
		RealImpl param = new RealImpl(0.0D);
		parameters.addParameter("x", param);
		param.setRealValue(x);
		double value = ex.evaluate(parameters).getRealValue();
		return String.valueOf((double)Math.round(value * 10) / 10);
	}
}