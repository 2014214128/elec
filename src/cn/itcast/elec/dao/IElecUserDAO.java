package cn.itcast.elec.dao;


import java.util.List;

import cn.itcast.elec.domain.ElecUser;

public interface IElecUserDAO extends ICommonDAO<ElecUser> {
	public final static String SERVICE_NAME = "cn.itcast.elec.dao.impl.ElecUserDAOImpl";
	
	/**
	 * @Name: findElecPopedomByLoginName
	 * @Description: 根据登录名获取权限
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: String name
	 * @Return: List<Object>
	 */
	List<Object> findElecPopedomByLoginName(String name);
	
	/**
	 * @Name: findElecRoleByLoginName
	 * @Description: 根据登录名获取角色
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: String name
	 * @Return: List<Object []>
	 */
	List<Object []> findElecRoleByLoginName(final String name);

	/**
	 * @Name: findUserByChart
	 * @Description: 用柱状图统计用户
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: 无
	 * @Return: List<Object []>
	 */
	List<Object[]> findUserByChart();
	
	

}
