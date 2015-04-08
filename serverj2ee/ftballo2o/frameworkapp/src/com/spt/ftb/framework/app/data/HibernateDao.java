package com.spt.ftb.framework.app.data;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;

import com.spt.ftb.framework.app.web.page.Page;

/**
 * 
 * @ClassName: HibernateDao
 * @Description: 数据持久层的CRUD接口类
 * @author zq
 * @date 2014年1月6日 下午4:22:22
 * @param <T>
 *            所操作的PO类
 * @param <ID>
 *            所操作PO类ID
 */
public interface HibernateDao<T, ID>
{
	List<T> find();

	/**
	 * 保存实体类，返回主键ID
	 * 
	 * @param <ID>
	 * @param <T>
	 * @param entity
	 *            实体类
	 * @return
	 */
	ID save(T entity);

	/**
	 * 删除集合
	 * 
	 * @param <T>
	 * @param entityList
	 */
	void delete(List<T> entityList);

	/**
	 * 删除实体类
	 * 
	 * @param <T>
	 * @param entity
	 */
	void delete(T entity);

	/**
	 * 更新实体类
	 * 
	 * @param <T>
	 * @param entity
	 */
	void update(T entity);
	
	void merge(T entity);

	T get(ID id);
	
	T get(String str,String value);

	/**
	 * @param content
	 */
	Object findUniqueBy(Class<?> entityClass, String propertyName, Object value);

	List<T> findByHQL(String hql);

	List<T> findWithWhere(String where);
	
	int findSum(final String tableName);
	
	List findPageByHql(final String hql,final int offset, final int pageSize);
	
	List<Map<String, Object>> findPageBySql(String sql);
	
	Page<T> getQueryPage(Criteria cri,Page<T> page);
	
	Page<T> queryPages(Page<T> page,Class cla);
	
	Criteria getCriteria(Class cla);
}
