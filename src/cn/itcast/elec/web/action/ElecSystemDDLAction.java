package cn.itcast.elec.web.action;

import cn.itcast.elec.container.ServiceProvider;
import cn.itcast.elec.service.IElecSystemDDLService;
import cn.itcast.elec.web.form.ElecSystemDDLForm;

import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class ElecSystemDDLAction extends BaseAction implements
		ModelDriven<ElecSystemDDLForm> {
	private IElecSystemDDLService elecSystemDDLService = (IElecSystemDDLService) ServiceProvider
			.getService(IElecSystemDDLService.SERVICE_NAME);

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
		/*
		 * 操作： 1、查询数据字典表中的数据类型，使用distinct关键字，去掉数据类型的重复值。 2、PO对象转换成VO对象
		 */
		request.setAttribute("systemList", elecSystemDDLService.findKeyWord());
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
		/*
		 * 操作： 1、获取页面传递的数据类型 2、使用数据类型查询数据字典表，获取对应数据类型的数据项的值 3、PO对象转换成VO对象
		 */
		request.setAttribute("systemList", elecSystemDDLService
				.findElecSystemDDLByKeyWord(elecSystemDDLForm.getKeyWord()));
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
