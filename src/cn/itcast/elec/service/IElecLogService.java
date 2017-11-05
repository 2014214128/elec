package cn.itcast.elec.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.itcast.elec.web.form.ElecLogForm;


public interface IElecLogService {
	public final static String SERVICE_NAME = "cn.itcast.elec.service.impl.ElecLogServiceImpl";
	
	/**
	 * @Name: saveElecLog
	 * @Description: 保存日志
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-8-26 (创建日期)
	 * @Parameter: HttpServletRequest request, String details
	 * @Return:void
	 */
	void saveElecLog(HttpServletRequest request, String details);
	
	/**
	 * @Name: findElecLogListByCondition
	 * @Description: 根据条件查询日志信息
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-8-26 (创建日期)
	 * @Parameter: ElecLogForm elecLogForm
	 * @Return:List<ElecLogForm>
	 */
	List<ElecLogForm> findElecLogListByCondition(ElecLogForm elecLogForm);
	
	/**
	 * @Name: deleteElecLogByIDs
	 * @Description: 根据ID删除日志信息
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-8-26 (创建日期)
	 * @Parameter: ElecLogForm elecLogForm
	 * @Return: void
	 */
	void deleteElecLogByIDs(ElecLogForm elecLogForm);

}
