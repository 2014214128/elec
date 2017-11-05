 package cn.itcast.elec.web.action;


import cn.itcast.elec.container.ServiceProvider;
import cn.itcast.elec.service.IElecCommonMsgService;
import cn.itcast.elec.web.form.ElecCommonMsgForm;

import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class ElecCommonMsgAction extends BaseAction implements ModelDriven<ElecCommonMsgForm>{
	private IElecCommonMsgService elecCommonMsgService = (IElecCommonMsgService) ServiceProvider.getService(IElecCommonMsgService.SERVICE_NAME);

	private ElecCommonMsgForm elecCommonMsgForm = new ElecCommonMsgForm();
	@Override
	public ElecCommonMsgForm getModel() {
		return elecCommonMsgForm;
	}
	  
	public String home() {
		/*操作：
		   1、查询所有代办事宜列表，得到数据结果集对象。
		   2、将结果集中PO对象转换成VO对象
		   3、将结果集对象存放到request中
		*/
		request.setAttribute("commonList", elecCommonMsgService.findElecCommonMsgList());
		return "home";
	}
	
	public String save() {
		/*操作：
		   1、获取VO对象中属性值（站点运行情况、设备运行情况）
		   2、从VO对象转换成PO对象
		   3、执行save()
		*/
		elecCommonMsgService.saveElecCommonMsg(elecCommonMsgForm);
		return "save";
	}
	
}
