/**   
 * @Title: AbstractSqlBuilder.java 
 * @Package com.iot.isp.framework.app.service.sql.builder 
 * @Description: (用一句话描述该文件做什么) 
 * @author zq 
 * @date 2014年2月25日 下午2:32:49 
 * @version V1R1  
 */ 
package com.spt.ftb.framework.app.service.sql.builder;

import com.spt.ftb.framework.app.service.sql.table.AbstractTable;
import com.spt.ftb.framework.common.FtbEnum.SqlJoinModel;

/** 
 * @ClassName: AbstractSqlBuilder 
 * @Description: 构建Sql语句抽象类 
 * @author zq
 * @date 2014年2月25日 下午2:32:49  
 */
public abstract class AbstractSqlBuilder implements SqlBuilder
{
	@Override
	public String buildSqlTable(AbstractTable table)
	{
		return null;
	}

	@Override
	public String buildSql(String sql)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildSelectSql(Object obj)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildUpdateSql(Object obj, String where)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildInsertSql(AbstractTable table)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 实现分页查询的sql语句 
	 * @Description: 实现分页查询的seql语句 
	 * @param tableName 表名
	 * @param keyName 使用分页的排序的key，一般是主键等唯一标示的
	 * @param pageSize 每页显示多少条记录
	 * @param currentPage 当前页数
	 * @return String 返回Sql语句 
	 */
	@Override
	public String buildSqlWithPages(String tableName, String keyName, int pageSize, int currentPage)
	{
		return null;
	}
	
	/**
	 * 构造连表查询
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param tableA 连接的A表如：tablea tablea
	 * @param tableB 连接的B表如：tableb tableb
	 * @param colums 查询的列 如：tablea.columa,tableb.columb
	 * @param model 连接查询的方式
	 * @param on on的条件
	 * @param where where条件语句，可以为null
	 * @return String 组装的sql语句
	 */
	@Override
	public String buildJoinSql(String tableA, String tableB, String colums, SqlJoinModel model, String on, String where)
	{
		StringBuffer sql = new StringBuffer("select ");
		sql.append(colums);
		sql.append(" from ");
		sql.append(tableA);
		sql.append(" ");
		sql.append(model.getJoinModel());
		sql.append(" ");
		sql.append(tableB);
		sql.append(" on ");
		sql.append(on);
		if (null != where)
		{
			sql.append(" where ");
			sql.append(where);
		}
		return sql.toString();
	}

	@Override
	public String buildSqlWithByWhere(String tableName, String colums, String where)
	{
		StringBuffer sql = new StringBuffer("select ");
		sql.append(colums);
		sql.append(" from ");
		sql.append(tableName);
		sql.append(" where ");
		sql.append(where);
		return sql.toString();
	}
	
	
}
