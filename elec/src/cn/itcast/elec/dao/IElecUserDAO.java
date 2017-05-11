package cn.itcast.elec.dao;

import java.util.List;

import cn.itcast.elec.domain.ElecCommonMsg;
import cn.itcast.elec.domain.ElecUser;

public interface IElecUserDAO extends ICommonDAO<ElecUser> {
	public final static String SERVICE_NAME = "cn.itcast.elec.dao.impl.ElecUserDAOImpl";

	

}
