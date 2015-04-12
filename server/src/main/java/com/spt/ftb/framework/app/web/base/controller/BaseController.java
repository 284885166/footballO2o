/**   
 * @Title: BaseService.java 
 * @Package com.iot.isp.web.base.controller 
 * @Description: (用一句话描述该文件做什么) 
 * @author mazenghui
 * @date Jan 14, 2014 2:47:16 PM 
 * @version V1R1  
 */
package com.spt.ftb.framework.app.web.base.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

/**
 * @ClassName: BaseService
 * @Description: (这里用一句话描述这个类的作用)
 * @author mazenghui
 * @date Jan 14, 2014 2:47:16 PM
 */
public interface BaseController<T>
{
	List<T> list();

	String add(T t);

	String update(T t);

	String delete(int[] deleteIds);

	T get(int id);

	T get(String name);

	String list(Integer offset, Integer pageSize, Integer page,HttpSession session);
}
