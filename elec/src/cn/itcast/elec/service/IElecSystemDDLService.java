package cn.itcast.elec.service;

import java.util.List;

import cn.itcast.elec.domain.ElecCommonMsg;
import cn.itcast.elec.domain.ElecText;
import cn.itcast.elec.web.form.ElecCommonMsgForm;
import cn.itcast.elec.web.form.ElecSystemDDLForm;
import cn.itcast.elec.web.form.ElecTextForm;

public interface IElecSystemDDLService {
	public final static String SERVICE_NAME = "cn.itcast.elec.service.impl.ElecSystemDDLServiceImpl";

	List<ElecSystemDDLForm> findKeyWord();

	List<ElecSystemDDLForm> findElecSystemDDLByKeyWord(String keyWord);

	void saveElecSystemDDL(ElecSystemDDLForm elecSystemDDLForm);

		
}
