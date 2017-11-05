package cn.itcast.elec.web.action;

import cn.itcast.elec.container.ServiceProvider;
import cn.itcast.elec.service.IElecRoleService;
import cn.itcast.elec.service.IElecSystemDDLService;
import cn.itcast.elec.web.form.ElecRoleForm;

import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class ElecRoleAction extends BaseAction implements
		ModelDriven<ElecRoleForm> {
	private IElecRoleService elecRoleService = (IElecRoleService) ServiceProvider
			.getService(IElecRoleService.SERVICE_NAME);

	private IElecSystemDDLService elecSystemDDLService = (IElecSystemDDLService) ServiceProvider
			.getService(IElecSystemDDLService.SERVICE_NAME);

	private ElecRoleForm elecRoleForm = new ElecRoleForm();

	@Override
	public ElecRoleForm getModel() {
		return elecRoleForm;
	}

	/**
	 * @Name: home
	 * @Description: 查询数据
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: null
	 * @Return:String home 跳转到roleIndex.jsp页面
	 */
	public String home() {
		/*
		 * 操作： 1、查询数据字典，使用角色类型进行查询，获取所有的角色类型的数据项的code和数据项的名称列表 2、从PO对象转换VO对象
		 * 
		 * 1、查询Function.xml文件，获取权限的集合 2、将从xml文件中获取的权限集合，放置到XmlJavaBean中
		 */
		request.setAttribute("systemList",
				elecSystemDDLService.findElecSystemDDLByKeyWord("角色类型"));
		request.setAttribute("xmlList", elecRoleService.readXml());
		return "home";
	}

	/**
	 * @Name: edit
	 * @Description: 查询该角色下具有的权限
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: null
	 * @Return:String edit 跳转到roleEdit.jsp页面
	 */
	public String edit() {
		/*
		 * 操作： 该角色具有的权限 1、获取页面中传递的角色类型ID（即roleID） 2、使用roleID查询该角色所具有的权限
		 * 3、从Function.xml文件中获取所有的权限列表 4、匹配：选中roleID获取的权限和系统中所有的权限进行匹配，
		 * 如果匹配成功，将页面中的权限复选框选中。 如果匹配不成功，则页面中的权限复选框不被选中。
		 * （程序中在XmlObject的VO对象中使用flag字段进行判断： 如果 flag =
		 * 0，表示该角色不具有的权限，则页面中权限复选框不被选中 如果 flag = 1，表示该角色具有此权限，则页面中的权限复选框被选中）
		 * 该角色分配的用户 1、获取页面中传递的角色类型ID（即roleID） 2、使用roleID查询该角色所拥有的用户
		 * 3、从用户表中获取所有的用户列表，且用户需要在职 4、匹配：选中roleID拥有的用户和系统中所有的用户进行匹配，
		 * 如果匹配成功，将页面中的用户复选框选中。 如果匹配不成功，则页面中的用户复选框不被选中。
		 * （程序中在ElecUserForm的VO对象中使用flag字段进行判断： 如果 flag =
		 * 0，表示该角色不拥有的用户，则页面中用户复选框不被选中 如果 flag = 1，表示该角色拥有此用户，则页面中的用户复选框被选中）
		 */
		request.setAttribute("xmlList",
				elecRoleService.readEditXml(elecRoleForm.getRole()));
		request.setAttribute("userList", elecRoleService
				.findElecUserListByRoleID(elecRoleForm.getRole()));
		return "edit";
	}
	/**
	 * @Name: save
	 * @Description: 保存相应关联表
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: null
	 * @Return:String save 跳转到roleIndex.jsp页面
	 */
	public String save() {
		elecRoleService.saveRole(elecRoleForm);
		return "save";
	}
}
