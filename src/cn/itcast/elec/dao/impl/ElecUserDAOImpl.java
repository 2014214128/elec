package cn.itcast.elec.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import cn.itcast.elec.dao.IElecUserDAO;
import cn.itcast.elec.domain.ElecUser;

@Repository(IElecUserDAO.SERVICE_NAME)
public class ElecUserDAOImpl extends CommonDAOImpl<ElecUser> implements
		IElecUserDAO {

	@Override
	public List<Object> findElecPopedomByLoginName(final String name) {
		final String sql = "SELECT a.popedomCode as popedom FROM elec_role_popedom a "
				+ "LEFT OUTER JOIN elec_user_role b ON a.RoleID = b.RoleID "
				+ "INNER JOIN elec_user c ON b.UserID = c.UserID "
				+ "AND c.loginName = ? " + "WHERE c.isDuty = '1'";
		@SuppressWarnings("unchecked")
		List<Object> list = (List<Object>) this.getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(sql);
						query.setParameter(0, name);
						return query.list();
					}

				});
		return list;
	}

	@Override
	public List<Object[]> findElecRoleByLoginName(final String name) {
		final String sql = "SELECT b.ddlCode as code,b.ddlName as name FROM elec_user_role a "
				+ "LEFT OUTER JOIN elec_systemddl b ON a.RoleID = b.ddlCode "
				+ "AND b.keyword = '角色类型' "
				+ "INNER JOIN elec_user c ON a.UserID = c.UserID "
				+ "AND c.loginName = ? " + "WHERE c.isDuty = '1'";
		@SuppressWarnings("unchecked")
		List<Object[]> list = (List<Object[]>) this.getHibernateTemplate()
				.execute(new HibernateCallback() {

					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(sql);
						query.setParameter(0, name);
						return query.list();
					}
				});
		return list;
	}

	@Override
	public List<Object[]> findUserByChart() {
		final String sql = "SELECT b.DdlName AS jctname,COUNT(*) AS jctcount FROM elec_user a "
				+ "LEFT OUTER JOIN elec_systemddl b ON a.JctID = b.DdlCode "
				+ "AND b.Keyword = '所属单位' " + "GROUP BY a.JctID";
		@SuppressWarnings("unchecked")
		List<Object[]> list = (List<Object[]>) this.getHibernateTemplate()
				.execute(new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(sql);
						return query.list();
					}

				});
		return list;
	}

}
