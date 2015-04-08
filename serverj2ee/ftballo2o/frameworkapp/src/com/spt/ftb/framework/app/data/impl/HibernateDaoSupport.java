package com.spt.ftb.framework.app.data.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.spt.ftb.framework.app.data.HibernateDao;
import com.spt.ftb.framework.app.data.FtbSqlAccess;
import com.spt.ftb.framework.app.web.page.Page;
import com.spt.ftb.framework.app.web.page.PaginationUtils;

/**
 * 
 * @ClassName: HibernateDaoSupport
 * @Description: 数据持久层CRUD实现类
 * @author zq
 * @date 2014年1月6日 下午4:21:07
 * @param <T>
 *            所操作对应的PO类
 * @param <ID>所操作对应PO类的ID
 */
public class HibernateDaoSupport<T, ID extends Serializable> implements HibernateDao<T, ID>
{
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Autowired
	@Qualifier("sqlAccess")
	private FtbSqlAccess sqlAccess;

	protected Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	protected Class<T> getEntityClass()
	{
		return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	protected String getEntityName()
	{
		return getEntityClass().getSimpleName();
	}

	@SuppressWarnings("unchecked")
	public List<T> find()
	{
		return getSession().createQuery(buildHQLByClass(this.getEntityClass())).list();
	}

	@SuppressWarnings("unchecked")
	public List<T> findWithWhere(String where)
	{
		return getSession().createQuery(buildHQLByClass(this.getEntityClass()) + " where" + where).list();
	}

	@SuppressWarnings("unchecked")
	public List<T> findByHQL(String hql)
	{
		return getSession().createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	public T get(ID id)
	{
		return (T) getSession().get(this.getEntityClass(), id);
	}

	public void delete(ID... ids)
	{
		for (ID id : ids)
		{
			this.delete(this.get(id));
		}
	}

	@SuppressWarnings("unchecked")
	public ID save(T entity)
	{
		return (ID) getSession().save(entity);
	}

	@Override
	public void delete(List<T> entityList)
	{
		for (T t : entityList)
		{
			delete(t);
		}
	}

	@Override
	public void delete(T entity)
	{
		getSession().delete(entity);
	}

	@Override
	public void update(T entity)
	{
		getSession().update(entity);
	}
	
	@Override
	public void merge(T entity)
	{
		getSession().merge(entity);
	}

	@Override
	public Object findUniqueBy(Class<?> entityClass, String propertyName, Object value)
	{
		Criteria criteria = getSession().createCriteria(entityClass);
		criteria.add(Restrictions.eq(propertyName, value));
		return criteria.uniqueResult();
	}

	protected Query setParameters(Query query, Object... params)
	{
		int i = 0;
		for (Object object : params)
		{
			query.setParameter(i, object);
		}
		return query;
	}


	@SuppressWarnings("unchecked")
	public List<T> findPageBySql(String sql, Object... params)
	{
		if (PaginationUtils.exist())
		{
			sql += " order by " + PaginationUtils.getSorter() + " " + PaginationUtils.getOrder();
		}
		SQLQuery query = createSQLQuery(sql, params);
		if (PaginationUtils.exist())
		{
			SQLQuery countQuery = createSQLQuery("select count(1)" + removeSelect(sql), params);
			int total = Integer.valueOf(countQuery.uniqueResult().toString());
			PaginationUtils.setTotal(total);
			query.setFirstResult(PaginationUtils.getStart()).setMaxResults(PaginationUtils.getLimit());
		}
		return query.list();
	}

	public SQLQuery createSQLQuery(String sql, Object... params)
	{
		return (SQLQuery) setParameters(getSession().createSQLQuery(sql), params);
	}

	public Query createHQLQuery(String hql, Object... params)
	{
		return setParameters(getSession().createQuery(hql), params);
	}

	public static String buildHQLByClass(Class<?> clazz)
	{
		return "from " + clazz.getSimpleName() + " t";
	}

	public static String removeSelect(String str)
	{
		int pos = str.toLowerCase().indexOf("from");
		if (pos != -1)
		{
			str.substring(pos);
		}
		return str;
	}

	@Override
	public Page<T> getQueryPage(Criteria cri, Page<T> page)
	{
		int count = ((Long) cri.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		cri.setProjection(null);
		page.setSumcount(count);// 总记录数
		int pagenum = count / page.getPagesize();
		if (count % page.getPagesize() != 0)
		{
			pagenum += 1;
		}
		page.setPagecount(pagenum);// 总页数
		int startNo = (page.getCurpage() - 1) * page.getPagesize();
		cri.setFirstResult(startNo);
		cri.setMaxResults(page.getPagesize());
		page.setResult(cri.list());
		return page;
	}

	@Override
	public Page<T> queryPages(Page<T> page, Class cla)
	{
		Criteria cri = getSession().createCriteria(cla);
		return getQueryPage(cri, page);
	}


	@SuppressWarnings("unchecked")
	@Override
	public T get(String str, String value)
	{
		List<T> list = getSession().createCriteria(this.getEntityClass()).add(Restrictions.eq(str, value)).list();
		return list.get(0);
	}

	@Override
	public List<Map<String, Object>> findPageBySql(String sql)
	{
		return sqlAccess.executeQuery(sql, new Object[]{});
	}
	
	@SuppressWarnings({ "unchecked"})
	public List<T> findPageByHql(final String sql,final int offset, final int pageSize)
	{
		List<T> result = getSession().createSQLQuery(sql).setFirstResult(offset).setMaxResults(pageSize).list();
		return result;
	}

	@Override
	public int findSum(String tableName)
	{
		String sql = "select count(*) createtime from "+tableName;
		List<Map<String, Object>> result = sqlAccess.executeQuery(sql, new Object[]{});
		return Integer.valueOf(String.valueOf(result.get(0).get("createtime")));
	}

	@Override
	public Criteria getCriteria(Class cla)
	{
		return getSession().createCriteria(cla);
	}
}
