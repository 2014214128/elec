package cn.itcast.elec.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.itcast.elec.dao.IElecSystemDDLDAO;
import cn.itcast.elec.domain.ElecSystemDDL;

@Repository(IElecSystemDDLDAO.SERVICE_NAME)
public class ElecSystemDDLDAOImpl extends CommonDAOImpl<ElecSystemDDL>
		implements IElecSystemDDLDAO {
	/**
	 * @Name: findKeyWord
	 * @Description: 查询数据类型关键字，且去掉重复值
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: null
	 * @Return:List<ElecSystemDDL> 数据类型列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findKeyWord() {
		return this.getHibernateTemplate().find(
				"select distinct o.keyWord  from ElecSystemDDL o");
	}

	/**
	 * @Name: findDDLName
	 * @Description: 用数据类型和数据项的编号获取数据项的名称
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: String ddlCode 数据项编号, String keyWord 数据类型
	 * @Return:String 数据项的名称
	 */
	@Override
	public String findDDLName(String ddlCode, String keyWord) {
		String hql = "from ElecSystemDDL where keyWord = '" + keyWord
				+ "' and ddlCode = " + ddlCode;
		@SuppressWarnings("unchecked")
		List<ElecSystemDDL> list = this.getHibernateTemplate().find(hql);
		String ddlName = "";
		if (list != null && list.size() > 0) {
			ElecSystemDDL elecSystemDDL = list.get(0);
			ddlName = elecSystemDDL.getDdlName();
		}
		return ddlName;
	}

}