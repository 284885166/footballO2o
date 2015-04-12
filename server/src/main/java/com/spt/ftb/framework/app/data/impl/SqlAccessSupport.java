/**   
 * @Title: SqlAccessSupport.java 
 * @Package com.iot.isp.framework.app.data.impl 
 * @Description: (用一句话描述该文件做什么) 
 * @author xumingsen 
 * @date 2014年2月17日 下午2:41:16 
 * @version V1R1  
 */
package com.spt.ftb.framework.app.data.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.spt.ftb.framework.app.data.FtbSqlAccess;
import com.spt.ftb.framework.app.service.sql.factory.BuilderFactory;
import com.spt.ftb.framework.util.Constants;

/**
 * @ClassName: SqlAccessSupport
 * @Description: (这里用一句话描述这个类的作用)
 * @author zq
 * @date 2014年2月17日 下午2:41:16
 */
public class SqlAccessSupport implements FtbSqlAccess
{
	private JdbcTemplate simpleJdbcTemplate;

	/**
	 * 执行sql语句的修改
	 * 
	 * @Description: 执行sql语句的修改
	 * @param sql
	 * @param params
	 * @return int 返回类型
	 */
	public int executeUpdate(String sql, Object[] params)
	{
		return this.simpleJdbcTemplate.update(sql, params);
	}

	/**
	 * @param simpleJdbcTemplate
	 *            the simpleJdbcTemplate to set
	 */
	public void setSimpleJdbcTemplate(JdbcTemplate simpleJdbcTemplate)
	{
		this.simpleJdbcTemplate = simpleJdbcTemplate;
	}

	/**
	 * 执行批量sql语句查询
	 * 
	 * @Description: 执行批量sql语句查询
	 * @param sql
	 * @param params
	 * @return int[] 返回类型
	 */
	public int[] batch(String sql, Object[][] params)
	{
		return this.simpleJdbcTemplate.batchUpdate(sql, Arrays.asList(params));
	}

	/**
	 * 执行sql语句查询操作
	 * 
	 * @Description: 执行sql语句查询操作
	 * @param sql
	 * @param params
	 * @return List<Map<String,Object>> 返回查询的数据类型
	 */
	public List<Map<String, Object>> executeQuery(String sql, Object[] params)
	{
		return this.simpleJdbcTemplate.queryForList(sql, params);
	}

	public void executeSql(String sql)
	{
		this.simpleJdbcTemplate.execute(sql);
	}

	/**
	 * sql语句实现的分页查询
	 * 
	 * @Description: sql语句实现的分页查询
	 * @param tableName
	 *            表明
	 * @param keyName
	 *            根据查询排序的列
	 * @param pageSize
	 *            页数
	 * @param currentPage
	 *            当前页
	 * @return Map<String,Object> 返回类型
	 */
	@Override
	public Map<String, Object> executeQueryWithPages(String tableName, String keyName, int pageSize, int currentPage)
	{
		// 默认每页显示10条记录
		if (pageSize == 0)
		{
			pageSize = 10;
		}
		// 默认显示第一页
		if (currentPage == 0)
		{
			currentPage = 1;
		}
		String queryAllNum = "SELECT COUNT(*) count from " + tableName;
		List<Map<String, Object>> countResult = this.executeQuery(queryAllNum, new Object[0]);
		float allNum = 0;
		if (countResult.size() > 0)
		{
			allNum = Float.parseFloat(countResult.get(0).get(Constants.COUNT).toString());
		}
		//总页数
		int pageCount = Math.round(allNum / pageSize);
		
		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put(Constants.CURRENT_PAGE, currentPage);
		obj.put(Constants.PAGESIZE, pageSize);
		obj.put(Constants.PAGE_COUNT, pageCount);
		String querySql = BuilderFactory.getSqlBuilder().buildSqlWithPages(tableName, keyName, pageSize, currentPage);
		obj.put(Constants.QUERY_RESULT, this.executeQuery(querySql, new Object[0]));
		
		return obj;
	}
}
