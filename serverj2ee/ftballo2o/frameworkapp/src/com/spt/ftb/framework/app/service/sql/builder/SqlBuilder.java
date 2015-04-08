/**   
 * @Title: SqlBuilder.java 
 * @Package com.iot.isp.service.da.realdata.createtable 
 * @Description: (用一句话描述该文件做什么) 
 * @author zq 
 * @date 2014年2月25日 上午11:12:07 
 * @version V1R1  
 */ 
package com.spt.ftb.framework.app.service.sql.builder;

import com.spt.ftb.framework.app.service.sql.table.AbstractTable;
import com.spt.ftb.framework.common.FtbEnum.SqlJoinModel;

/** 
 * @ClassName: SqlBuilder 
 * @Description: Sql构造器接口 
 * @author zq
 * @date 2014年2月25日 上午11:12:07  
 */
public interface SqlBuilder
{
	public String buildSqlTable(AbstractTable table);
	
	public String buildSql(String sql);
	
	public String buildSelectSql(Object obj);
	
	public String buildUpdateSql(Object obj,String where);
	
	public String buildInsertSql(AbstractTable table);
	
	/**
	 * 实现分页查询的sql语句 
	 * @Description: 实现分页查询的seql语句 
	 * @param tableName 表名
	 * @param keyName 使用分页的排序的key，一般是主键等唯一标示的
	 * @param pageSize 每页显示多少条记录
	 * @param currentPage 当前页数
	 * @return String 返回Sql语句 
	 */
	String buildSqlWithPages(String tableName, String keyName, int pageSize, int currentPage);
	
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
	String buildJoinSql(String tableA, String tableB, String colums, SqlJoinModel model, String on, String where);
	
	/**
	 * 
	 * @Description: 根据条件进行查询 
	 * @param @param tableName
	 * @param @param colums
	 * @param @param where
	 * @param @return    设定文件 
	 * @return String 返回类型 
	 * @throws SqlBuilder
	 */
	String buildSqlWithByWhere(String tableName,String colums,String where);
}
