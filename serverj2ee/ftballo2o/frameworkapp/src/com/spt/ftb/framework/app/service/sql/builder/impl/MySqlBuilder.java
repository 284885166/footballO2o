/**   
 * @Title: MySqlBuilder.java 
 * @Package com.iot.isp.service.da.realdata.createtable 
 * @Description: (用一句话描述该文件做什么) 
 * @author zq 
 * @date 2014年2月25日 上午11:13:24 
 * @version V1R1  
 */
package com.spt.ftb.framework.app.service.sql.builder.impl;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import com.spt.ftb.framework.app.service.sql.builder.AbstractSqlBuilder;
import com.spt.ftb.framework.app.service.sql.table.AbstractTable;

/**
 * @ClassName: MySqlBuilder
 * @Description:mySql sql 语句组装类
 * @author zq
 * @date 2014年2月25日 上午11:13:24
 */
public class MySqlBuilder extends AbstractSqlBuilder
{
	@Override
	public String buildSqlTable(AbstractTable table)
	{
		StringBuffer sb = new StringBuffer("create table if not exists ");
		sb.append(table.getTableName());
		sb.append("(");
		Set<Entry<String, String>> set = table.getColMaps().entrySet();
		Iterator<Entry<String, String>> iterator = set.iterator();
		while (iterator.hasNext())
		{
			Entry<String, String> entry = (Entry<String, String>) iterator.next();
			sb.append(entry.getKey());
			sb.append("    ");
			sb.append(entry.getValue());
			sb.append(",");
		}
		sb.append(table.getSpecial());
		sb.append(");");
		return sb.toString();
	}

	@Override
	public String buildInsertSql(AbstractTable table)
	{
		StringBuffer sb = new StringBuffer("insert ignore into ");
		sb.append(table.getTableName());
		sb.append("(");
		Set<Entry<String, String>> set = table.getColMaps().entrySet();
		Iterator<Entry<String, String>> iterator = set.iterator();
		StringBuffer valusSql = new StringBuffer(" values (");
		while (iterator.hasNext())
		{
			Entry<String, String> entry = (Entry<String, String>) iterator.next();
			if ("id".equals(entry.getKey()))
			{
				continue;
			}
			append(sb, entry.getKey());
			append(valusSql, entry.getValue());
		}
		replace(sb);
		replace(valusSql);
		sb.append(valusSql);
		return sb.toString();
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
		int start = (currentPage - 1) * pageSize;
		StringBuffer sql = new StringBuffer("SELECT * FROM ");
		sql.append(tableName);
		if (null != keyName)
		{
			sql.append(" ORDER BY ");
			sql.append(keyName);
		}
		sql.append(" LIMIT ");
		sql.append(start);
		sql.append(",");
		sql.append(pageSize);
		
		return sql.toString();
	}

	private void append(StringBuffer sb, String str)
	{
		sb.append(str);
		sb.append(",");
	}

	private void replace(StringBuffer sb)
	{
		sb.replace(sb.lastIndexOf(","), sb.length(), ")");
	}
}
