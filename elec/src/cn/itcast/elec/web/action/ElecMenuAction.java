package cn.itcast.elec.web.action;

import java.util.List;

import cn.itcast.elec.container.ServiceProvider;
import cn.itcast.elec.service.IElecCommonMsgService;
import cn.itcast.elec.web.form.ElecCommonMsgForm;
import cn.itcast.elec.web.form.ElecMenuForm;

import com.opensymphony.xwork2.ModelDriven;

public class ElecMenuAction extends BaseAction implements ModelDriven<ElecMenuForm>{
	private ElecMenuForm elecMenuForm = new ElecMenuForm();
	
	private IElecCommonMsgService elecCommonMsgService = (IElecCommonMsgService) ServiceProvider.getService(IElecCommonMsgService.SERVICE_NAME);
	@Override
	public ElecMenuForm getModel() {
		return this.elecMenuForm;
	}
	
	public String home() {
		return "home";
	}
	
	public String title() {
		return "title";
	}
	
	public String left() {
		return "left";
	}
	
	public String change1() {
		return "change1";
	}
	
	public String loading() {
		return "loading";
	}
	
	public String alermJX() {
		return "alermJX";
	}
	/**
	 * @Name: alermSB
	 * @Description: 查询设备当天运行的情况
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: 无
	 * @Return: String alermSB 跳转到alermSB.jsp
	 */
	public String alermSB() {
		List<ElecCommonMsgForm> list = elecCommonMsgService.findElecCommonMsgListByCurrentDate();
		request.setAttribute("commonList", list);
		return "alermSB";
	}
	
	public String alermXZ() {
		return "alermXZ";
	}
	
	public String alermYS() {
		return "alermYS";
	}
	/**
	 * @Name: alermZD
	 * @Description: 查询站点当天运行的情况
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: 无
	 * @Return: String alermZD 跳转到alermZD.jsp
	 */
	public String alermZD() {
		List<ElecCommonMsgForm> list = elecCommonMsgService.findElecCommonMsgListByCurrentDate();
		request.setAttribute("commonList", list);
		return "alermZD";
	}
	
	

}
