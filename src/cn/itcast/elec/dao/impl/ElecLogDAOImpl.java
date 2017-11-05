package cn.itcast.elec.dao.impl;

import org.springframework.stereotype.Repository;

import cn.itcast.elec.dao.IElecLogDAO;
import cn.itcast.elec.domain.ElecLog;

@Repository(IElecLogDAO.SERVICE_NAME)
public class ElecLogDAOImpl extends CommonDAOImpl<ElecLog> implements
		IElecLogDAO {

}
