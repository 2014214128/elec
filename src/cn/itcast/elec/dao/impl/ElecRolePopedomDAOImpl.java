package cn.itcast.elec.dao.impl;

import org.springframework.stereotype.Repository;

import cn.itcast.elec.dao.IElecRolePopedomDAO;
import cn.itcast.elec.domain.ElecRolePopedom;

@Repository(IElecRolePopedomDAO.SERVICE_NAME)
public class ElecRolePopedomDAOImpl extends CommonDAOImpl<ElecRolePopedom> implements
		IElecRolePopedomDAO {
	
}
