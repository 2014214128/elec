package cn.itcast.elec.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.elec.dao.IElecCommonMsgDAO;
import cn.itcast.elec.domain.ElecCommonMsg;
import cn.itcast.elec.service.IElecCommonMsgService;
import cn.itcast.elec.web.form.ElecCommonMsgForm;

@Transactional(readOnly = true)
@Service(IElecCommonMsgService.SERVICE_NAME)
public class ElecCommonMsgServiceImpl implements IElecCommonMsgService {
	@Resource(name = IElecCommonMsgDAO.SERVICE_NAME)
	private IElecCommonMsgDAO elecCommonMsgDAO;

	public IElecCommonMsgDAO getElecCommonMsgDAO() {
		return elecCommonMsgDAO;
	}

	public void setElecCommonMsgDAO(IElecCommonMsgDAO elecCommonMsgDAO) {
		this.elecCommonMsgDAO = elecCommonMsgDAO;
	}

	@Override
	public List<ElecCommonMsgForm> findElecCommonMsgList() {
		String hqlWhere = "";
		Object[] params = null;
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		orderBy.put("o.createDate", "desc");
		List<ElecCommonMsg> list = elecCommonMsgDAO
				.findCollectionByConditionNoPage(hqlWhere, params, orderBy);
		List<ElecCommonMsgForm> formList = this.elecCommonMsgPOListTOList(list);
		return formList;
	}

	private List<ElecCommonMsgForm> elecCommonMsgPOListTOList(
			List<ElecCommonMsg> list) {
		List<ElecCommonMsgForm> formList = new ArrayList<ElecCommonMsgForm>();
		ElecCommonMsgForm elecCommonMsgForm = null;
		for (int i = 0; list != null && i < list.size(); i++) {
			elecCommonMsgForm = new ElecCommonMsgForm();
			ElecCommonMsg elecCommonMsg = list.get(i);
			elecCommonMsgForm.setComID(elecCommonMsg.getComID());
			elecCommonMsgForm.setStationRun(elecCommonMsg.getStationRun());
			elecCommonMsgForm.setDevRun(elecCommonMsg.getDevRun());
			elecCommonMsgForm.setCreateDate(String.valueOf(elecCommonMsg
					.getCreateDate() != null ? elecCommonMsg.getCreateDate()
					: ""));
			formList.add(elecCommonMsgForm);
		}
		return formList;
	}

	@Override
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveElecCommonMsg(ElecCommonMsgForm elecCommonMsgForm) {
		//VO对象转换成PO对象
		ElecCommonMsg elecCommonMsg = this.elecCommonMsgVOToPO(elecCommonMsgForm);
		elecCommonMsgDAO.save(elecCommonMsg);
	}

	private ElecCommonMsg elecCommonMsgVOToPO(
			ElecCommonMsgForm elecCommonMsgForm) {
		ElecCommonMsg elecCommonMsg = new ElecCommonMsg();
		if(elecCommonMsgForm != null) {
			elecCommonMsg.setStationRun(elecCommonMsgForm.getStationRun());
			elecCommonMsg.setDevRun(elecCommonMsgForm.getDevRun());
			elecCommonMsg.setCreateDate(new Date());
		}
		return elecCommonMsg;
	}

	@Override
	public List<ElecCommonMsgForm> findElecCommonMsgListByCurrentDate() {
		//获取当前日期
		java.sql.Date date  = new java.sql.Date(new java.util.Date().getTime());
		String currentDate = date.toString();
		List<Object[]> list = elecCommonMsgDAO.findElecCommonMsgListByCurrentDate(currentDate);
		List<ElecCommonMsgForm> formList = this.elecCommonMsgObjectListToVOList(list);
		return formList;
	}

	private List<ElecCommonMsgForm> elecCommonMsgObjectListToVOList(
			List<Object[]> list) {
		List<ElecCommonMsgForm> formList = new ArrayList<ElecCommonMsgForm>();
		ElecCommonMsgForm elecCommonMsgForm = null;
		for(int i = 0 ; list != null && i<list.size() ; i++) {
			elecCommonMsgForm = new ElecCommonMsgForm();
			Object [] object = list.get(i);
			elecCommonMsgForm.setStationRun(object[0].toString());
			elecCommonMsgForm.setDevRun(object[1].toString());
			
			formList.add(elecCommonMsgForm);
		}
		return formList;
	}

}
