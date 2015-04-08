/**   
 * @Title: AbstractTable.java 
 * @Package com.iot.isp.service.da.realdata.createtable 
 * @Description: (用一句话描述该文件做什么) 
 * @author zq 
 * @date 2014年2月25日 上午10:45:09 
 * @version V1R1  
 */
package com.spt.ftb.framework.app.service.sql.table;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: AbstractTable
 * @Description: 用于封装需要组装SQL语句的数据
 * @author zq
 * @date 2014年2月25日 上午10:45:09
 */
public abstract class AbstractTable implements Table
{
	private Map<String, String> colMaps = new HashMap<String, String>();

	public void putCol(String col, String value)
	{
		colMaps.put(col, value);
	}

	public Map<String, String> getColMaps()
	{
		Map<String, String> maps = new HashMap<String, String>();
		maps.putAll(colMaps);
		return maps;
	}

	public String getValue(String key)
	{
		return colMaps.get(key);
	}

	public abstract String getSpecial();
}
