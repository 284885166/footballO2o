/**   
 * 登录过滤器，防止用户未登录就访问界面
 * @Title: LoginFilter.java 
 * @Package com.iot.isp.framework.app.web.filter 
 * @Description: 登录过滤器，防止用户未登录就访问界面
 * @author xumingsen 
 * @date 2014年2月20日 下午4:03:26 
 * @version V1R1  
 */
package com.spt.ftb.framework.app.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.spt.ftb.framework.app.web.base.vo.BaseVO;
import com.spt.ftb.framework.util.StringUtil;

/**
 * 登录过滤器，防止用户未登录就访问界面
 * 
 * @ClassName: LoginFilter
 * @Description: 登录过滤器，防止用户未登录就访问界面
 * @author xumingsen
 * @date 2014年2月20日 下午4:03:26
 */
public class LoginFilter extends HttpServlet implements Filter
{
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = -3782191836809706003L;

	public LoginFilter()
	{
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException,
			ServletException
	{
		HttpServletRequest sRequest = (HttpServletRequest) request;
		HttpSession session = sRequest.getSession(); // 获得session
		BaseVO loginUser = (BaseVO) session.getAttribute("user");
		// 获锝当前的URL
		String strURL = sRequest.getRequestURL().toString();
		if (strURL.endsWith(".js") || strURL.endsWith(".png") || strURL.endsWith(".jpg") || strURL.endsWith(".css") || strURL.endsWith("Service") || strURL.endsWith("Endpoint/"))
		{
			filterChain.doFilter(request, response);
		}
		else
		{
			// 判断用户是否登录，如果没有，
			if (loginUser == null)
			{
				// 判断是否为login.jsp
				if (strURL.endsWith("login.jsp"))
				{
					((HttpServletResponse) response).sendRedirect(sRequest.getContextPath() + "/");
				}
				else if (strURL.matches(".+/login$"))
				{
					if (StringUtil.isNull(sRequest.getParameter("login")))
					{
						((HttpServletResponse) response).sendRedirect(sRequest.getContextPath() + "/");
					}
					else
					{
						filterChain.doFilter(request, response);
					}
				}
				else
				{
//					filterChain.doFilter(request, response);
					 ((HttpServletResponse)response).sendRedirect(sRequest.getContextPath() + "/");
				}
			}
			else
			{
				filterChain.doFilter(request, response);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException
	{
	}
}
