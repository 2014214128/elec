 package cn.itcast.elec.web.action;


import java.util.List;

import cn.itcast.elec.container.ServiceProvider;
import cn.itcast.elec.service.IElecCommonMsgService;
import cn.itcast.elec.web.form.ElecCommonMsgForm;

import com.opensymphony.xwork2.ModelDriven;

public class ElecCommonMsgAction extends BaseAction implements ModelDriven<ElecCommonMsgForm>{
	private IElecCommonMsgService elecCommonMsgService = (IElecCommonMsgService) ServiceProvider.getService(IElecCommonMsgService.SERVICE_NAME);

	private ElecCommonMsgForm elecCommonMsgForm = new ElecCommonMsgForm();
	@Override
	public ElecCommonMsgForm getModel() {
		return elecCommonMsgForm;
	}
	  
	public String home() {
		List<ElecCommonMsgForm> list = elecCommonMsgService.findElecCommonMsgList();
		request.setAttribute("commonList", list);
		return "home";
	}
	
	public String save() {
		elecCommonMsgService.saveElecCommonMsg(elecCommonMsgForm);
		return "save";
	}
	
}
