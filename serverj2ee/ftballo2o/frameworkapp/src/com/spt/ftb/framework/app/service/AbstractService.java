/**   
 * @Title: AbstractService.java 
 * @Package com.iot.isp.data.system 
 * @Description: (用一句话描述该文件做什么) 
 * @author zq 
 * @date 2014年11月13日 下午4:23:40 
 * @version V1R1  
 */
package com.spt.ftb.framework.app.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;

import com.spt.ftb.framework.app.data.HibernateDao;
import com.spt.ftb.framework.app.web.page.Page;
import com.spt.ftb.framework.util.BeanUtil;
import com.spt.ftb.framework.util.StringUtil;

/**
 * @ClassName: AbstractService
 * @Description: 业务操作抽象类
 * @author zq
 * @date 2014年11月13日 下午4:23:40
 */
public abstract class AbstractService<PO, VO> implements IService<PO, VO>
{
	/**
	 * 
	 * @Description: 获取DAO操作类
	 * @param @return 设定文件
	 * @return HibernateDao<PO,Integer> 返回类型
	 * @throws AbstractService
	 */
	protected abstract HibernateDao<PO, Integer> getDao();

	/**
	 * 
	 * @Description: VO数据复制到PO类中
	 * @param @param vo
	 * @param @return 设定文件
	 * @return PO 返回类型
	 * @throws AbstractService
	 */
	protected abstract PO copyVO2PO(VO vo);

	/**
	 * 
	 * @Description: PO数据复制到VO类中
	 * @param @param vo
	 * @param @return 设定文件
	 * @return VO 返回类型
	 * @throws AbstractService
	 */
	protected abstract VO copyPO2VO(PO po);

	@Override
	public void add(VO vo)
	{
		getDao().save(copyVO2PO(vo));
	}

	@Override
	public void delete(int id)
	{
	}

	@Override
	public void delete(VO vo)
	{
		getDao().delete(copyVO2PO(vo));
	}

	@Override
	public void update(VO vo)
	{
		getDao().update(copyVO2PO(vo));
	}
	
	public void merge(VO vo)
	{
		getDao().merge(copyVO2PO(vo));
	}

	@Override
	public VO getDataByID(int id)
	{
		return copyPO2VO(getDao().get(id));
	}

	@Override
	public List<VO> query()
	{
		List<PO> pos = getDao().find();
		return copePOs2VOs(pos);
	}

	protected List<VO> copePOs2VOs(List<PO> pos)
	{
		List<VO> vos = new ArrayList<VO>();
		for (PO po : pos)
		{
			vos.add(copyPO2VO(po));
		}
		return vos;
	}

	/**
	 * 分页查询
	 */
	@Override
	public Page<VO> queryPage(int pageSize, int currentPage, Criteria criteria)
	{
		Page<PO> page = new Page<PO>();
		page.setPagesize(pageSize);
		page.setCurpage(currentPage);
		page = getDao().getQueryPage(criteria, page);
		Page<VO> pageVO = new Page<VO>();
		List<VO> vos = new ArrayList<VO>();
		BeanUtil.copyProperties(pageVO, page);
		List<PO> pos = page.getResult();
		for (int i = 0; i < pos.size(); i++)
		{
			vos.add(copyPO2VO(pos.get(i)));
		}
		pageVO.setResult(vos);
		return pageVO;
	}
	
	@Override
	public String deleteAll(Integer[] ids)
	{
		try
		{
			for (int i = 0; i < ids.length; i++)
			{
				if( null != ids[i])
				{
					delete(ids[i]);
				}
			}
			return StringUtil.SUCCESS;
		}
		catch (Exception e)
		{
			return null;
		}
	}
}
