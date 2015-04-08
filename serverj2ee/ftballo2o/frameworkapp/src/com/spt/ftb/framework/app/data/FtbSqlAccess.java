/**   
 * @Title: SqlAccess.java 
 * @Package com.iot.isp.framework.app.data 
 * @Description: (用一句话描述该文件做什么) 
 * @author xumingsen 
 * @date 2014年2月17日 下午2:40:23 
 * @version V1R1  
 */
package com.spt.ftb.framework.app.data;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: SqlAccess
 * @Description: (这里用一句话描述这个类的作用)
 * @author zq
 * @date 2014年2月17日 下午2:40:23
 */
public interface FtbSqlAccess
{
	/**
	 * 执行sql语句的修改
	 * 
	 * @Description: 执行sql语句的修改
	 * @param sql
	 * @param params
	 * @return int 返回类型
	 */
	public int executeUpdate(String sql, Object[] params);

	/**
	 * 执行批量sql语句查询
	 * 
	 * @Description: 执行批量sql语句查询
	 * @param sql
	 * @param params
	 * @return int[] 返回类型
	 */
	public int[] batch(String sql, Object[][] params);

	/**
	 * 执行sql语句查询操作
	 * 
	 * @Description: 执行sql语句查询操作
	 * @param sql
	 * @param params
	 * @return List<Map<String,Object>> 返回查询的数据类型
	 */
	public List<Map<String, Object>> executeQuery(String sql, Object[] params);
	
	/**
	 * 
	 * @Description: 执行Sql语句
	 * @param @param sql    设定文件 
	 * @return void 返回类型 
	 * @throws FtbSqlAccess
	 */
	public void executeSql(String sql);
	
	/**
	 * sql语句实现的分页查询
	 * @Description: sql语句实现的分页查询
	 * @param tableName 表明
	 * @param keyName 根据查询排序的列
	 * @param pageSize 页数
	 * @param currentPage 当前页
	 * @return Map<String,Object> 返回类型
	 */
	Map<String, Object> executeQueryWithPages(String tableName, String keyName, int pageSize, int currentPage);
}
