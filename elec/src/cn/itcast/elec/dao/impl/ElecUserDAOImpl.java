package cn.itcast.elec.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import cn.itcast.elec.dao.IElecCommonMsgDAO;
import cn.itcast.elec.dao.IElecUserDAO;
import cn.itcast.elec.domain.ElecCommonMsg;
import cn.itcast.elec.domain.ElecUser;

@Repository(IElecUserDAO.SERVICE_NAME)
public class ElecUserDAOImpl extends CommonDAOImpl<ElecUser> implements IElecUserDAO{

}
