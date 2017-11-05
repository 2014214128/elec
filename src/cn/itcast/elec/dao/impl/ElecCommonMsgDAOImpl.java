package cn.itcast.elec.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import cn.itcast.elec.dao.IElecCommonMsgDAO;
import cn.itcast.elec.domain.ElecCommonMsg;

@Repository(IElecCommonMsgDAO.SERVICE_NAME)
public class ElecCommonMsgDAOImpl extends CommonDAOImpl<ElecCommonMsg> implements IElecCommonMsgDAO{

	@Override
	public List<Object[]> findElecCommonMsgListByCurrentDate(String currentDate) {
		final String sql = "SELECT o.stationRun, o.devRun " +
					"FROM elec_commonmsg o " +
					"WHERE o.createDate = '" + currentDate + "'";
		
		@SuppressWarnings("unchecked")
		List<Object[]> list = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createSQLQuery(sql);
				return query.list();
			}
		});
		return list;
	}
	
}
