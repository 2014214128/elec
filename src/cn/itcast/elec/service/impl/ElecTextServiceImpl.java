package cn.itcast.elec.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.elec.dao.IElecTextDAO;
import cn.itcast.elec.domain.ElecText;
import cn.itcast.elec.service.IElecTextService;
import cn.itcast.elec.web.form.ElecTextForm;

@Transactional(readOnly=true)
@Service(IElecTextService.SERVICE_NAME)
public class ElecTextServiceImpl implements IElecTextService {
	@Resource(name=IElecTextDAO.SERVICE_NAME)
	private IElecTextDAO elecTextDAO; 
	@Override	
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveElecText(ElecText elecText) {
		elecTextDAO.save(elecText);
	}
	@Override
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void updateElecText(ElecText elexText) {
		elecTextDAO.update(elexText);
	}
	public IElecTextDAO getElecTextDAO() {
		return elecTextDAO;
	}
	public void setElecTextDAO(IElecTextDAO elecTextDAO) {
		this.elecTextDAO = elecTextDAO;
	}
	@Override
	public List<ElecText> findCollectionByConditionNoPage(
			ElecTextForm elecTextForm) {
		String  hqlWhere = "";
		List<String> paramsList = new ArrayList<String>();
		if(elecTextForm != null && StringUtils.isNotBlank(elecTextForm.getTextName())) {
			hqlWhere += "and o.textName like ? ";
			paramsList.add("%"+elecTextForm.getTextName()+"%");
		}
		if(elecTextForm != null && StringUtils.isNotBlank(elecTextForm.getTextRemark())) {
			hqlWhere += "and o.textRemark like ? ";
			paramsList.add("%"+elecTextForm.getTextRemark()+"%");
		}
		Object[] params = paramsList.toArray();
		/**
		 * 组织排序语句
		 */
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		orderBy.put(" o.textDate ", " desc ");
		orderBy.put(" o.textName ", " asc ");
		//查询列表
		List<ElecText> list = elecTextDAO.findCollectionByConditionNoPage(hqlWhere, params, orderBy);
		for(int i=0 ; list != null && i<list.size() ; i++) {
			ElecText elecText = list.get(i);
			System.out.println(elecText.getTextName()+":"+elecText.getTextRemark());
		}
		
		return null;
	}
	
}
