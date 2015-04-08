package com.spt.ftb.service.user.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.spt.ftb.data.user.dao.UserDao;
import com.spt.ftb.data.user.po.User;
import com.spt.ftb.framework.app.data.HibernateDao;
import com.spt.ftb.framework.app.service.AbstractService;
import com.spt.ftb.framework.app.web.page.Page;
import com.spt.ftb.framework.util.BeanUtil;
import com.spt.ftb.framework.util.MD5Utils;
import com.spt.ftb.service.user.UserService;
import com.spt.ftb.web.user.vo.UserVO;

/**
 * 
 * @ClassName: UserServiceImpl
 * @Description: 用户管理接口实现类
 * @author zq
 * @date 2014年1月6日 上午10:43:17
 */
@Service("userService")
public class UserServiceImpl extends AbstractService<User, UserVO> implements UserService<User, UserVO>
{
	
	@Resource
	private UserDao<User, Integer> userDao;

	@Override
	protected HibernateDao<User, Integer> getDao()
	{
		return userDao;
	}

	@Override
	protected User copyVO2PO(UserVO vo)
	{
		User user = new User();
		BeanUtil.copyProperties(user, vo);
		return user;
	}

	@Override
	protected UserVO copyPO2VO(User po)
	{
		UserVO vo = new UserVO();
		BeanUtil.copyProperties(vo, po);
		return vo;
	}

	@Override
	public void add(UserVO vo)
	{
		vo.setPassword(MD5Utils.getInstance().getMD5ofStr(vo.getPassword()));
		super.add(vo);
	}


	@Override
	public void delete(int id)
	{
		UserVO vo = new UserVO();
		vo.setId(id);
		delete(vo);
	}

	@Override
	public void update(UserVO vo)
	{
		super.update(vo);
	}

	@Override
	public Page<UserVO> queryDatasByWhere(String name, String account, int state, int pageSize, int curpage,int org_id)
	{
		Criteria criteria = userDao.getCriteria(User.class);
		buildWhere(criteria, name, account, state,org_id);
		Page<UserVO> pageVO = queryPage(pageSize, curpage, criteria);
		return pageVO;
	}

	private void buildWhere(Criteria criteria, String name, String account, int state,int org_id)
	{
		if (!"".equals(name))
		{
			criteria.add(Restrictions.like("name", "%" + name + "%"));
		}
		if (-1 != state)
		{
			criteria.add(Restrictions.eq("state", state));
		}
	}

	@Override
	public UserVO getDataByID(int id)
	{
		return super.getDataByID(id);
	}

	@Override
	public UserVO getUserByName(String name)
	{
		User user = getDao().get("login_name", name);
		return copyPO2VO(user);
	}
	
	@Override
	public List<UserVO> query()
	{
		return super.query();
	}
	
}
