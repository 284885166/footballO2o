/**   
 * @Title: IService.java 
 * @Package com.iot.isp.service.system 
 * @Description: (用一句话描述该文件做什么) 
 * @author zq 
 * @date 2014年11月13日 下午4:31:30 
 * @version V1R1  
 */ 
package com.spt.ftb.framework.app.service;

import java.util.List;

import org.hibernate.Criteria;

import com.spt.ftb.framework.app.web.page.Page;

/** 
 * @ClassName: IService 
 * @Description: (这里用一句话描述这个类的作用) 
 * @author zq
 * @date 2014年11月13日 下午4:31:30  
 */
public interface IService<PO,VO>
{
	void add(VO t);

	void delete(int id);

	void delete(VO t);

	void update(VO t);

	List<VO> query();
	
	VO getDataByID(int id);
	
	Page<VO> queryPage(int pageSize, int currentPage, Criteria criteria);
	
	String deleteAll(Integer[] ids);
}
