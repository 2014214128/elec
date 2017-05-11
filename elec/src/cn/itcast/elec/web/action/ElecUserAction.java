 package cn.itcast.elec.web.action;


import java.util.List;

import cn.itcast.elec.container.ServiceProvider;
import cn.itcast.elec.domain.ElecUser;
import cn.itcast.elec.service.IElecCommonMsgService;
import cn.itcast.elec.service.IElecSystemDDLService;
import cn.itcast.elec.service.IElecUserService;
import cn.itcast.elec.web.form.ElecCommonMsgForm;
import cn.itcast.elec.web.form.ElecSystemDDLForm;
import cn.itcast.elec.web.form.ElecUserForm;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class ElecUserAction extends BaseAction implements ModelDriven<ElecUserForm>{
	private IElecUserService elecUserService = (IElecUserService) ServiceProvider.getService(IElecUserService.SERVICE_NAME);

	private IElecSystemDDLService elecSystemDDLService = (IElecSystemDDLService) ServiceProvider.getService(IElecSystemDDLService.SERVICE_NAME);
	
	private ElecUserForm elecUserForm = new ElecUserForm();
	@Override
	public ElecUserForm getModel() {
		return elecUserForm;
	}
	/**
	 * @Name: home
	 * @Description: 查询用户列表信息
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: null
	 * @Return:String home 跳转到userIndex.jsp页面
	 */  
	public String home() {
		List<ElecUserForm> list = elecUserService.findElecUserList(elecUserForm);
		request.setAttribute("userList", list);
		return "home";
	}
	/**
	 * @Name: add
	 * @Description: 添加用户
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: null
	 * @Return:String add 跳转到userAdd.jsp页面
	 */
	public String add() {
		this.initSystemDDl();
		
		return "add";
	}
	/**
	 * @Name: save
	 * @Description: 保存用户信息
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: null
	 * @Return:String save 重定向到userIndex.jsp
	 */
	public String save() {
		
		elecUserService.saveElecUser(elecUserForm);
		return "save";
	}
	private void initSystemDDl() {
		List<ElecSystemDDLForm> sexList = elecSystemDDLService.findElecSystemDDLByKeyWord("性别");
		List<ElecSystemDDLForm> jctList = elecSystemDDLService.findElecSystemDDLByKeyWord("所属单位");
		List<ElecSystemDDLForm> isDutyList = elecSystemDDLService.findElecSystemDDLByKeyWord("是否在职");
		request.setAttribute("sexList", sexList);
		request.setAttribute("jctList", jctList);
		request.setAttribute("isDutyList", isDutyList);
		
	}
	/**  
	* @Name: edit
	* @Description: 跳转到编辑的页面
	* @Author: 刘洋（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2011-12-26 （创建日期）
	* @Parameters: 无
	* @Return: String edit 跳转到userEdit.jsp
	*/
	public String edit(){
		elecUserForm = elecUserService.findElecUser(elecUserForm);
		//使用值栈的形式传递elecUserForm对象
		ActionContext.getContext().getValueStack().push(elecUserForm);
		this.initSystemDDl();
		/**
		 *  使用viewflag字段
		 *  判断当前用户跳转的是编辑页面还是明细页面
		 *    * 如果viewflag==null：说明当前操作的是编辑页面
		 *    * 如果viewflag==1:说明当前操作的是明细页面
		 */
		/*String viewflag = elecUserForm.getViewflag();
		request.setAttribute("viewflag", viewflag);*/
		return "edit";
	}
}
