/**   
 * @Title: UserController.java 
 * @Package com.iot.isp.web.system.user.controller 
 * @Description: (用一句话描述该文件做什么) 
 * @author zq 
 * @date 2014年9月4日 下午4:36:52 
 * @version V1R1  
 */
package com.spt.ftb.web.user.ctrl;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spt.ftb.framework.app.data.po.BasePo;
import com.spt.ftb.framework.app.service.IService;
import com.spt.ftb.framework.app.web.page.Page;
import com.spt.ftb.service.user.UserService;
import com.spt.ftb.web.AbstractBaseController;
import com.spt.ftb.web.user.vo.UserVO;

/**
 * @ClassName: UserController
 * @Description: 用户管理类
 * @author zq
 * @date 2014年9月4日 下午4:36:52
 */
@Controller
@RequestMapping("/user")
public class UserController extends AbstractBaseController<BasePo, UserVO>
{
	@Resource
	private UserService<BasePo, UserVO> userService;

	@RequestMapping(value = "/logout.do", method = RequestMethod.POST)
	public void logout()
	{
	}

	@Override
	protected IService<BasePo, UserVO> getService()
	{
		return userService;
	}

	@ResponseBody
	@RequestMapping(value = "/add.do", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String add(UserVO vo)
	{
		return super.add(vo);
	}

	@ResponseBody
	@RequestMapping(value = "/update.do", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String update(UserVO vo) throws Exception
	{
		return super.update(vo);
	}

	@ResponseBody
	@RequestMapping(value = "/delete.do", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String delete(int id)
	{
		return super.delete(id);
	}

	@ResponseBody
	@RequestMapping(value = "/getDataByID.do", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String getDataByID(int id)
	{
		return super.getDataByID(id);
	}

	@ResponseBody
	@RequestMapping(value = "/queryByWhere.do", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String queryByWhere(String name, String account, int state, int pageSize, int curpage)
	{
		Page<UserVO> orgvos = userService.queryDatasByWhere(name, account, state, pageSize, curpage, 0);
		return JSONObject.fromObject(orgvos).toString();
	}

	@RequestMapping(value = "/all.do", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String getUsers()
	{
		List<UserVO> users = userService.query();
		return JSONArray.fromObject(users).toString();
	}
}
