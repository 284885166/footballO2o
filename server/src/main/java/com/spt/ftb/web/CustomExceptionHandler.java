/**   
 * @Title: CustomExceptionHandler.java 
 * @Package com.iot.isp.web 
 * @Description: (用一句话描述该文件做什么) 
 * @author zq 
 * @date 2014年12月30日 下午3:17:28 
 * @version V1R1  
 */
package com.spt.ftb.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.spt.ftb.framework.common.log.LogManager;

/**
 * @ClassName: CustomExceptionHandler
 * @Description: 异常处理控制类
 * @author zq
 * @date 2014年12月30日 下午3:17:28
 */
public class CustomExceptionHandler extends SimpleMappingExceptionResolver
{
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex)
	{
		// Expose ModelAndView for chosen error view.
		String viewName = determineViewName(ex, request);
		if (viewName != null)
		{
			// Apply HTTP status code for error views, if specified.
			// Only apply it if we're processing a top-level request.
			Integer statusCode = determineStatusCode(request, viewName);
			if (statusCode != null)
			{
				applyStatusCodeIfPossible(request, response, statusCode);
			}
			ex.printStackTrace();
			LogManager.error(ex.getMessage());
			return getModelAndView(viewName, ex, request);
		}
		else
		{
			return null;
		}
	}
}
