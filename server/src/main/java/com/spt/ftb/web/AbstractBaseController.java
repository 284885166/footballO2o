/**   
 * @Title: BaseController.java 
 * @Package com.iot.isp.web 
 * @Description: (用一句话描述该文件做什么) 
 * @author zq 
 * @date 2014年11月14日 上午10:14:59 
 * @version V1R1  
 */
package com.spt.ftb.web;

import net.sf.json.JSONObject;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spt.ftb.framework.app.service.IService;
import com.spt.ftb.framework.util.StringUtil;

/**
 * @ClassName: BaseController
 * @Description: 控制器基类
 * @author zq
 * @date 2014年11月14日 上午10:14:59
 */
public abstract class AbstractBaseController<PO,VO>
{
	/**
	 * 
	 * @Description: 获取消息信息
	 * @param @param message
	 * @param @return    设定文件 
	 * @return String 返回类型 
	 * @throws AbstractBaseController
	 */
	protected String getMessage(String message)
	{
		return StringUtil.getReturnMsg(true, message);
	}
	
	protected abstract IService<PO, VO> getService();
	
	@ResponseBody
	@RequestMapping(value = "add", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String add(@ModelAttribute("form") VO vo)
	{
		getService().add(vo);
		return getMessage("添加成功！");
	}
	
	@ResponseBody
	@RequestMapping(value = "update", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String update(@ModelAttribute("form") VO vo) throws Exception
	{
		getService().update(vo);
		return getMessage("修改成功！");
	}
	
	@ResponseBody
	@RequestMapping(value = "delete", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String delete(int id)
	{
		getService().delete(id);
		return getMessage("删除成功！");
	}
	
	@ResponseBody
	@RequestMapping(value = "getDataByID", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String getDataByID(int id)
	{
		return JSONObject.fromObject(getService().getDataByID(id)).toString();
	}
}
