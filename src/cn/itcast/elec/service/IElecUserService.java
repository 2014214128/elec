package cn.itcast.elec.service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.itcast.elec.domain.ElecUser;
import cn.itcast.elec.web.form.ElecUserForm;

public interface IElecUserService {
	public final static String SERVICE_NAME = "cn.itcast.elec.service.impl.ElecUserServiceImpl";

	/**
	 * @param request
	 * @Name: findElecUserList
	 * @Description: 根据条件查询用户列表
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: ElecUserForm elecUserForm
	 * @Return: List<ElecUserForm>
	 */
	List<ElecUserForm> findElecUserList(ElecUserForm elecUserForm,
			HttpServletRequest request);

	/**
	 * @Name: saveElecUser
	 * @Description: 保存用户信息
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: ElecUserForm elecUserForm
	 * @Return: void
	 */
	void saveElecUser(ElecUserForm elecUserForm);

	/**
	 * @Name: findElecUser
	 * @Description: 查询用户
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: ElecUserForm elecUserForm
	 * @Return: ElecUserForm
	 */
	ElecUserForm findElecUser(ElecUserForm elecUserForm);

	/**
	 * @Name: deleteElecUser
	 * @Description: 删除用户
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: ElecUserForm elecUserForm
	 * @Return: void
	 */
	void deleteElecUser(ElecUserForm elecUserForm);

	/**
	 * @Name: checkLoginName
	 * @Description: 检查登录名是否合法
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: String loginName
	 * @Return: String
	 */
	String checkLoginName(String loginName);

	/**
	 * @Name: findElecUserByLoginName
	 * @Description: 用户登录验证
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: String name
	 * @Return: ElecUser
	 */
	ElecUser findElecUserByLoginName(String name);

	/**
	 * @Name: findElecPopedomByLoginName
	 * @Description: 根据登录名获取权限
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: String name
	 * @Return: String
	 */
	String findElecPopedomByLoginName(final String name);

	/**
	 * @Name: findElecRoleByLoginName
	 * @Description: 根据登录名获取角色
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: String name
	 * @Return: Hashtable<String, String>
	 */
	Hashtable<String, String> findElecRoleByLoginName(String name);
	
	/**
	 * @Name: getExcelFiledNameList
	 * @Description: 获取表头
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: 无
	 * @Return: ArrayList<Object>
	 */
	ArrayList<Object> getExcelFiledNameList();
	
	/**
	 * @Name: getExcelFiledDataList
	 * @Description: 获取数据
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: ElecUserForm elecUserForm
	 * @Return: ArrayList<Object>
	 */
	ArrayList<Object> getExcelFiledDataList(ElecUserForm elecUserForm);
	
	/**
	 * @Name: saveElecUserWithExcel
	 * @Description: 保存excel数据
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: ElecUserForm elecUserForm
	 * @Return: void
	 */
	void saveElecUserWithExcel(ElecUserForm elecUserForm);
	
	/**
	 * @Name: findUserByChart
	 * @Description: 用柱状图统计用户
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: 无
	 * @Return: List<ElecUserForm>
	 */
	List<ElecUserForm> findUserByChart();


}
