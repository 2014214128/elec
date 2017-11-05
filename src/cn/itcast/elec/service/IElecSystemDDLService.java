package cn.itcast.elec.service;

import java.util.List;

import cn.itcast.elec.web.form.ElecSystemDDLForm;

public interface IElecSystemDDLService {
	public final static String SERVICE_NAME = "cn.itcast.elec.service.impl.ElecSystemDDLServiceImpl";

	/**
	 * @Name: findKeyWord
	 * @Description: 查询所有的数据类型
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: null
	 * @Return: List<ElecSystemDDLForm>
	 */
	List<ElecSystemDDLForm> findKeyWord();

	/**
	 * @Name: findElecSystemDDLByKeyWord
	 * @Description: 根据数据类型查询数据字典
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: String keyWord
	 * @Return: List<ElecSystemDDLForm>
	 */
	List<ElecSystemDDLForm> findElecSystemDDLByKeyWord(String keyWord);

	/**
	 * @Name: saveElecSystemDDL
	 * @Description: 保存数据字典
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: ElecSystemDDLForm elecSystemDDLForm
	 * @Return: void
	 */
	void saveElecSystemDDL(ElecSystemDDLForm elecSystemDDLForm);

}
