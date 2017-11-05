package cn.itcast.elec.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.elec.dao.ICommonDAO;
import cn.itcast.elec.util.GenericSuperClass;
import cn.itcast.elec.util.PageInfo;

public class CommonDAOImpl<T> extends HibernateDaoSupport implements
		ICommonDAO<T> {
	@SuppressWarnings("rawtypes")
	private Class entity = GenericSuperClass.getClass(this.getClass());

	@Override
	public void save(T entity) {
		this.getHibernateTemplate().save(entity);
	}

	@Override
	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
	}

	@Resource(name = "sessionFactory")
	public final void setSessionFactoryDI(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findObjectById(Serializable id) {
		return (T) this.getHibernateTemplate().get(entity, id);
	}

	@Override
	public void deleteObjectByIds(Serializable... ids) {
		for (int i = 0; i < ids.length; i++) {
			this.getHibernateTemplate().delete(this.findObjectById(ids[i]));
		}
	}

	@Override
	public void deleteObjectByCollection(Collection<T> list) {
		this.getHibernateTemplate().deleteAll(list);
	}

	@Override
	public List<T> findCollectionByConditionNoPage(String hqlWhere,
			final Object[] params, LinkedHashMap<String, String> orderBy) {
		String hql = "from " + entity.getSimpleName() + " o where 1=1 ";
		/* 组织排序条件 */
		String hqlOrderBy = "";
		if (orderBy != null) {
			hqlOrderBy = orderByCondition(orderBy);
			hqlOrderBy.substring(0, hqlOrderBy.length() - 1);
		}
		hql = hql + hqlWhere + hqlOrderBy;
		final String qHql = hql;
		@SuppressWarnings("unchecked")
		List<T> list = (List<T>) this.getHibernateTemplate().execute(
				new HibernateCallback() {

					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(qHql);
						setParams(params, query);
						return query.list();
					}

					
				});
		return list;
	}
	

	private String orderByCondition(LinkedHashMap<String, String> orderBy) {
		StringBuilder hqlOrderBy = new StringBuilder();
		hqlOrderBy.append(" order by ");
		for (Map.Entry<String, String> map : orderBy.entrySet()) {
			hqlOrderBy.append(map.getKey() + " " + map.getValue() + ",");
		}
		hqlOrderBy.deleteCharAt(hqlOrderBy.length() - 1);
		return hqlOrderBy.toString();
	}

	@Override
	public void saveObjectByCollection(Collection<T> list) {
		this.getHibernateTemplate().saveOrUpdateAll(list);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findObjectByID(String userID) {
		return (T) this.getHibernateTemplate().get(entity, userID);
	}

	@Override
	public List<T> findCollectionByConditionWithPage(String hqlWhere,
			final Object[] params, LinkedHashMap<String, String> orderby,
			final PageInfo pageInfo) {
		String hql = "from " + entity.getSimpleName() + " o where 1=1";
		//组织排序条件
		String hqlOrderBy = this.orderByCondition(orderby);
		hql = hql + hqlWhere + hqlOrderBy;
		final String finalHql = hql;
		@SuppressWarnings("unchecked")
		List<T> list = (List<T>)this.getHibernateTemplate().execute(new HibernateCallback(){
            public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(finalHql);
				setParams(params,query);
				//添加分页功能
				pageInfo.setTotalResult(query.list().size());//通过pageInfo对象设置列表中的总记录数
				query.setFirstResult(pageInfo.getBeginResult());//当前页中的数据从第几条开始查询
				query.setMaxResults(pageInfo.getPageSize());//当前页显示几条记录
				return query.list();
			}
		});
		return list;
	}
	
	private void setParams(final Object[] params, Query query) {
		if (params == null) {
			return;
		}
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
	}
}
