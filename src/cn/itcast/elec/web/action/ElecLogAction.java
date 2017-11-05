package cn.itcast.elec.web.action;

import cn.itcast.elec.container.ServiceProvider;
import cn.itcast.elec.service.IElecLogService;
import cn.itcast.elec.web.form.ElecLogForm;

import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class ElecLogAction extends BaseAction implements
		ModelDriven<ElecLogForm> {
	private IElecLogService elecLogService = (IElecLogService) ServiceProvider.getService(IElecLogService.SERVICE_NAME);
	
	private ElecLogForm elecLogForm = new ElecLogForm();

	@Override
	public ElecLogForm getModel() {
		return elecLogForm;
	}
	/**
	 * @Name: home
	 * @Description: 查询日志列表信息
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: 无
	 * @Return: String home 跳转到logIndex.jsp
	 */
	public String home() {
		request.setAttribute("logList", elecLogService.findElecLogListByCondition(elecLogForm));
		return "home";
	}
	/**
	 * @Name: delete
	 * @Description: 删除日志列表信息
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: 无
	 * @Return: String delete 跳转到logIndex.jsp
	 */
	public String delete() {
		elecLogService.deleteElecLogByIDs(elecLogForm);
		return "delete";
	}
}
