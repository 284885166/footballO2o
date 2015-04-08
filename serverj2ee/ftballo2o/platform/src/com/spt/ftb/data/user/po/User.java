/**   
 * @Title: User.java 
 * @Package com.spt.ftb.data.user.po 
 * @Description: (用一句话描述该文件做什么) 
 * @author zq 
 * @date 2015年4月5日 下午4:10:25 
 * @version V1R1  
 */ 
package com.spt.ftb.data.user.po;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.spt.ftb.framework.app.data.po.BasePo;

/** 
 * @ClassName: User 
 * @Description: 用户实体类 
 * @author zq
 * @date 2015年4月5日 下午4:10:25  
 */
@Entity
public class User extends BasePo
{

	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private String password;

	@Id
	@GeneratedValue
	@Column
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}
	
	@Column
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Column
	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
	
}
