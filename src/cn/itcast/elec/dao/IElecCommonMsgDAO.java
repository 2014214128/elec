package cn.itcast.elec.dao;

import java.util.List;

import cn.itcast.elec.domain.ElecCommonMsg;

public interface IElecCommonMsgDAO extends ICommonDAO<ElecCommonMsg> {
	public final static String SERVICE_NAME = "cn.itcast.elec.dao.impl.ElecCommonMsgDAOImpl";
	/**
	 * @Name: findElecCommonMsgListByCurrentDate
	 * @Description: 通过当前日期查询待办事宜列表
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: String currentDate 当前日期
	 * @Return: List<Object[]> 代办事宜列表
	 */
	List<Object[]> findElecCommonMsgListByCurrentDate(String currentDate);
}
