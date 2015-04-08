/**   
 * @Title: UserDaoImpl.java 
 * @Package com.iot.isp.data.system.user.dao.impl 
 * @Description: (用一句话描述该文件做什么) 
 * @author mazenghui
 * @date Jan 14, 2014 2:27:19 PM 
 * @version V1R1  
 */
package com.spt.ftb.data.user.dao.impl;

import org.springframework.stereotype.Repository;

import com.spt.ftb.data.user.dao.UserDao;
import com.spt.ftb.data.user.po.User;
import com.spt.ftb.framework.app.data.impl.HibernateDaoSupport;

/**
 * @ClassName: UserDaoImpl
 * @Description: 用户DAO实现类
 * @author zq
 * @date Jan 14, 2014 2:27:19 PM
 */
@Repository("userDao")
public class UserDaoImpl extends HibernateDaoSupport<User, Integer> implements UserDao<User, Integer>
{
}
