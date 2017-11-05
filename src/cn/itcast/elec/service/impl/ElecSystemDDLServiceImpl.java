package cn.itcast.elec.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.elec.dao.IElecSystemDDLDAO;
import cn.itcast.elec.domain.ElecSystemDDL;
import cn.itcast.elec.service.IElecSystemDDLService;
import cn.itcast.elec.web.form.ElecSystemDDLForm;

@Transactional(readOnly = true)
@Service(IElecSystemDDLService.SERVICE_NAME)
public class ElecSystemDDLServiceImpl implements IElecSystemDDLService {
	@Resource(name = IElecSystemDDLDAO.SERVICE_NAME)
	private IElecSystemDDLDAO elecSystemDDLDAO;

	/**
	 * @Name: findKeyWord
	 * @Description: 查询数据类型关键字，且去掉重复值
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: null
	 * @Return:List<ElecSystemDDLForm> 数据类型列表
	 */
	@Override
	public List<ElecSystemDDLForm> findKeyWord() {
		return this.elecSystemDDLObjectToVO(elecSystemDDLDAO.findKeyWord());
	}

	private List<ElecSystemDDLForm> elecSystemDDLObjectToVO(List<Object> list) {
		List<ElecSystemDDLForm> formList = new ArrayList<ElecSystemDDLForm>();
		ElecSystemDDLForm elecSystemDDLForm = null;
		for (int i = 0; list != null && i < list.size(); i++) {
			elecSystemDDLForm = new ElecSystemDDLForm();
			Object object = list.get(i);
			elecSystemDDLForm.setKeyWord(object.toString());
			formList.add(elecSystemDDLForm);
		}
		return formList;
	}

	/**
	 * @Name: findElecSystemDDLByKeyWord
	 * @Description: 根据选中的数据类型查询对应的数据项
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: String keyWord
	 * @Return:List<ElecSystemDDLForm> 对应数据项VO集合
	 */
	@Override
	public List<ElecSystemDDLForm> findElecSystemDDLByKeyWord(String keyWord) {
		return this
				.elecSystemDDLPOListToVOList(findSystemDDLListByCondition(keyWord));
	}

	private List<ElecSystemDDL> findSystemDDLListByCondition(String keyWord) {
		String hqlWhere = " and o.keyWord = ? ";
		Object[] params = { keyWord };
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		orderBy.put("o.ddlCode", "asc");
		return elecSystemDDLDAO.findCollectionByConditionNoPage(hqlWhere,
				params, orderBy);
	}

	private List<ElecSystemDDLForm> elecSystemDDLPOListToVOList(
			List<ElecSystemDDL> list) {
		List<ElecSystemDDLForm> formList = new ArrayList<ElecSystemDDLForm>();
		ElecSystemDDLForm elecSystemDDLForm = null;
		for (int i = 0; list != null && i < list.size(); i++) {
			elecSystemDDLForm = new ElecSystemDDLForm();
			elecSystemDDLForm.setSeqID(list.get(i).getSeqID().toString());
			elecSystemDDLForm.setKeyWord(list.get(i).getKeyWord());
			elecSystemDDLForm.setDdlCode(list.get(i).getDdlCode().toString());
			elecSystemDDLForm.setDdlName(list.get(i).getDdlName());
			formList.add(elecSystemDDLForm);
		}
		return formList;
	}

	/**
	 * @Name: saveElecSystemDDL
	 * @Description: 保存数据字典
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: ElecSystemDDLForm elecSystemDDLForm
	 * @Return:void
	 */
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public void saveElecSystemDDL(ElecSystemDDLForm elecSystemDDLForm) {
		String keywordname = elecSystemDDLForm.getKeywordname();
		String typeflag = elecSystemDDLForm.getTypeflag();
		String[] itemname = elecSystemDDLForm.getItemname();
		if (typeflag != null && typeflag.equals("new")) {
			/* 保存数据字典*/
			this.saveSystemDDLWithParams(keywordname, itemname);
		} else {
			elecSystemDDLDAO.deleteObjectByCollection(this
					.findSystemDDLListByCondition(keywordname));
			this.saveSystemDDLWithParams(keywordname, itemname);
		}

	}

	private void saveSystemDDLWithParams(String keywordname, String[] itemname) {
		List<ElecSystemDDL> list = new ArrayList<ElecSystemDDL>();
		for (int i = 0; itemname != null && i < itemname.length; i++) {
			ElecSystemDDL elecSystemDDL = new ElecSystemDDL();
			elecSystemDDL.setKeyWord(keywordname);
			elecSystemDDL.setDdlName(itemname[i]);
			elecSystemDDL.setDdlCode(new Integer(i + 1));
			list.add(elecSystemDDL);
		}
		elecSystemDDLDAO.saveObjectByCollection(list);
	}

}
