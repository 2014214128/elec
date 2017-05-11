package cn.itcast.elec.service;

import java.util.List;

import cn.itcast.elec.domain.ElecCommonMsg;
import cn.itcast.elec.domain.ElecText;
import cn.itcast.elec.web.form.ElecCommonMsgForm;
import cn.itcast.elec.web.form.ElecTextForm;
import cn.itcast.elec.web.form.ElecUserForm;

public interface IElecUserService {
	public final static String SERVICE_NAME = "cn.itcast.elec.service.impl.ElecUserServiceImpl";

	List<ElecUserForm> findElecUserList(ElecUserForm elecUserForm);

	void saveElecUser(ElecUserForm elecUserForm);

	ElecUserForm findElecUser(ElecUserForm elecUserForm);

}
