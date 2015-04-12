package com.spt.ftb.framework.app.web.page;

import java.util.List;

/**
 * 表示分页中的一页。
 * 
 * @author Zhang Kaitao
 */
public class Page<T>
{
	private int pagesize;// 每页显示个数

	private int curpage;// 当前页

	private int pagecount;// 总页数

	private int sumcount;// 总记录数

	private List<T> result;

	public int getSumcount()
	{
		return sumcount;
	}

	public void setSumcount(int sumcount)
	{
		this.sumcount = sumcount;
	}

	public int getPagecount()
	{
		return pagecount;
	}

	public void setPagecount(int pagecount)
	{
		this.pagecount = pagecount;
	}

	public int getCurpage()
	{
		return curpage;
	}

	public void setCurpage(int curpage)
	{
		this.curpage = curpage;
	}

	public int getPagesize()
	{
		return pagesize;
	}

	public void setPagesize(int pagesize)
	{
		this.pagesize = pagesize;
	}

	public List<T> getResult()
	{
		return result;
	}

	public void setResult(List<T> result)
	{
		this.result = result;
	}
}
