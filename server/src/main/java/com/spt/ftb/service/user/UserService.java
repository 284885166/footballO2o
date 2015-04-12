package com.spt.ftb.service.user;

import com.spt.ftb.framework.app.service.IService;
import com.spt.ftb.framework.app.web.page.Page;

/**
 * 
 * @ClassName: UserService
 * @Description: 用户管理接口类
 * @author zq
 * @date 2014年1月6日 上午10:42:44
 */
public interface UserService<PO, VO> extends IService<PO, VO>
{
	public Page<VO> queryDatasByWhere(String name, String account, int state, int pageSize, int curpage, int org_id);
	
	public VO getUserByName(String name);
}
