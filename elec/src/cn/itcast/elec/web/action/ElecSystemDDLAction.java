 package cn.itcast.elec.web.action;


import java.util.List;

import cn.itcast.elec.container.ServiceProvider;
import cn.itcast.elec.service.IElecCommonMsgService;
import cn.itcast.elec.service.IElecSystemDDLService;
import cn.itcast.elec.web.form.ElecCommonMsgForm;
import cn.itcast.elec.web.form.ElecSystemDDLForm;

import com.opensymphony.xwork2.ModelDriven;

public class ElecSystemDDLAction extends BaseAction implements ModelDriven<ElecSystemDDLForm>{
	private IElecSystemDDLService elecSystemDDLService = (IElecSystemDDLService) ServiceProvider.getService(IElecSystemDDLService.SERVICE_NAME);

	private ElecSystemDDLForm elecSystemDDLForm = new ElecSystemDDLForm();
	@Override
	public ElecSystemDDLForm getModel() {
		return elecSystemDDLForm;
	}
	/**
	 * @Name: home
	 * @Description: 查询数据类型，且去掉重复值
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: null
	 * @Return:String home 跳转到dictionaryIndex.jsp页面
	 */
	public String home() {
		List<ElecSystemDDLForm> list = elecSystemDDLService.findKeyWord();
		request.setAttribute("systemList", list);
		return "home";
	}
	/**
	 * @Name: edit
	 * @Description: 根据选中数据类型，跳转到编辑此数据类型的页面
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: null
	 * @Return:String edit 跳转到dictionaryEdit.jsp页面
	 */
	public String edit() {
		String keyWord = elecSystemDDLForm.getKeyWord();
		List<ElecSystemDDLForm> list = elecSystemDDLService.findElecSystemDDLByKeyWord(keyWord);
		request.setAttribute("systemList", list);
		return "edit";
	}
	/**
	 * @Name: save
	 * @Description: 保存数据字典
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: null
	 * @Return:String save 重定向到dictionaryIndex.jsp
	 */
	public String save() {
		elecSystemDDLService.saveElecSystemDDL(elecSystemDDLForm);
		return "save";
	}
	
}
