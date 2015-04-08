/**   
 * @Title: User.java 
 * @Package com.spt.ftb.data.user.po 
 * @Description: 用户VO实体类
 * @author zq 
 * @date 2015年4月5日 下午4:10:25 
 * @version V1R1  
 */ 
package com.spt.ftb.web.user.vo;


import com.spt.ftb.framework.app.web.base.vo.BaseVO;

/** 
 * @ClassName: UserVO 
 * @Description: 用户实体类 
 * @author zq
 * @date 2015年4月5日 下午4:10:25  
 */
public class UserVO extends BaseVO
{

	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private String password;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
	
}
