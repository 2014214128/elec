package cn.itcast.elec.dao;

import java.util.List;

import cn.itcast.elec.domain.ElecSystemDDL;

public interface IElecSystemDDLDAO extends ICommonDAO<ElecSystemDDL> {
	public final static String SERVICE_NAME = "cn.itcast.elec.dao.impl.ElecSystemDDLDAOImpl";

	/**
	 * @Name: findKeyWord
	 * @Description: 查询数据类型关键字，且去掉重复值
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: null
	 * @Return:List<ElecSystemDDL> 数据类型列表
	 */
	public List<Object> findKeyWord();

	/**
	 * @Name: findDDLName
	 * @Description: 根据数据类型和数据项编号查询数据项名称
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: String ddlCode, String keyWord
	 * @Return:String 数据项名称
	 */
	public String findDDLName(String ddlCode, String keyWord);

}
