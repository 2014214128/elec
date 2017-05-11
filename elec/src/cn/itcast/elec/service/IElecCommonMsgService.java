package cn.itcast.elec.service;

import java.util.List;

import cn.itcast.elec.domain.ElecCommonMsg;
import cn.itcast.elec.domain.ElecText;
import cn.itcast.elec.web.form.ElecCommonMsgForm;
import cn.itcast.elec.web.form.ElecTextForm;

public interface IElecCommonMsgService {
	public final static String SERVICE_NAME = "cn.itcast.elec.service.impl.ElecCommonMsgServiceImpl";

	/**
	 * @Name: findElecCommonMsgList
	 * @Description: 查询ElecCommonMsg的所有对象
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: null
	 * @Return: List<ElecCommonMsgForm>
	 */
	public List<ElecCommonMsgForm> findElecCommonMsgList();
	/**
	 * @Name: saveElecCommonMsg
	 * @Description: 保存代办事宜信息
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: ElecCommonMsgForm elecCommonMsgForm VO对象，页面传递的参数值
	 * @Return: 无
	 */
	public void saveElecCommonMsg(ElecCommonMsgForm elecCommonMsgForm);
	/**
	 * @Name: findElecCommonMsgListByCurrentDate
	 * @Description: 根据当前日期查询List<ElecCommonMsgForm>
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: 无
	 * @Return: List<ElecCommonMsgForm>
	 */
	public List<ElecCommonMsgForm> findElecCommonMsgListByCurrentDate();

}
