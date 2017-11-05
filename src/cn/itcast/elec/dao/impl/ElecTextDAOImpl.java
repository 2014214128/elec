package cn.itcast.elec.dao.impl;

import org.springframework.stereotype.Repository;

import cn.itcast.elec.dao.IElecTextDAO;
import cn.itcast.elec.domain.ElecText;

@Repository(IElecTextDAO.SERVICE_NAME)
public class ElecTextDAOImpl extends CommonDAOImpl<ElecText> implements IElecTextDAO{
	
}
