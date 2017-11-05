package cn.itcast.elec.service;

import java.util.List;

import cn.itcast.elec.domain.ElecText;
import cn.itcast.elec.web.form.ElecTextForm;

public interface IElecTextService {
	public final static String SERVICE_NAME = "cn.itcast.elec.service.impl.ElecTextServiceImpl";
	/**
	 * @Name: saveElecText
	 * @Description: 增加ElecText对象的方法
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: ElecText elecText 对象
	 * @Return: 无
	 */
	public void saveElecText(ElecText elecText);
	
	/**
	 * @Name: updateElecText
	 * @Description: 修改ElecText对象的方法
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: ElecText elecText 对象
	 * @Return: 无
	 */
	public void updateElecText(ElecText elexText);
	/**
	 * @Name: findCollectionByConditionNoPage
	 * @Description: 无分页使用查询条件查询ElecText对象的方法
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: ElecTextForm elecTextForm VO对象
	 * @Return: List<ElecText>
	 */
	public List<ElecText> findCollectionByConditionNoPage(ElecTextForm elecTextForm);
}
