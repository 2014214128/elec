package cn.itcast.elec.dao;

import java.util.List;

import cn.itcast.elec.domain.ElecUserRole;

public interface IElecUserRoleDAO extends ICommonDAO<ElecUserRole> {
	public final static String SERVICE_NAME = "cn.itcast.elec.dao.impl.ElecUserRoleDAOImpl";

	/**
	 * @Name: findElecUserListByRoleID
	 * @Description: 根据角色查询用户
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-8-26 (创建日期)
	 * @Parameter: String roleID
	 * @Return:List<ElecUserForm>
	 */
	List<Object[]> findElecUserListByRoleID(final String roleID);

}
