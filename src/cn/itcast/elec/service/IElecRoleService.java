package cn.itcast.elec.service;

import java.util.List;

import cn.itcast.elec.util.XmlObject;
import cn.itcast.elec.web.form.ElecRoleForm;
import cn.itcast.elec.web.form.ElecUserForm;

public interface IElecRoleService {
	public final static String SERVICE_NAME = "cn.itcast.elec.service.impl.ElecRoleServiceImpl";

	/**
	 * @Name: readXml
	 * @Description: 从Function.xml文件中，获取权限的集合
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-8-26 (创建日期)
	 * @Parameter: null
	 * @Return:List<XmlObject>
	 */
	List<XmlObject> readXml();

	/**
	 * @Name: readEditXml
	 * @Description: 从Function.xml文件中，根据roleID获取权限的集合
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-8-26 (创建日期)
	 * @Parameter: String roleID
	 * @Return:List<XmlObject>
	 */
	List<XmlObject> readEditXml(String roleID);

	/**
	 * @Name: findElecUserListByRoleID
	 * @Description: 根据角色查询用户
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-8-26 (创建日期)
	 * @Parameter: String roleID
	 * @Return:List<ElecUserForm>
	 */
	List<ElecUserForm> findElecUserListByRoleID(String roleID);
	
	/**
	 * @Name: saveRole
	 * @Description: 保存角色相关连的表
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-8-26 (创建日期)
	 * @Parameter: ElecRoleForm elecRoleForm
	 * @Return:void
	 */
	void saveRole(ElecRoleForm elecRoleForm);


}
