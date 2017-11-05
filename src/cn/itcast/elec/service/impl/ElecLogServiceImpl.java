package cn.itcast.elec.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.elec.dao.IElecLogDAO;
import cn.itcast.elec.domain.ElecLog;
import cn.itcast.elec.domain.ElecUser;
import cn.itcast.elec.service.IElecLogService;
import cn.itcast.elec.web.form.ElecLogForm;

@Transactional(readOnly = true)
@Service(IElecLogService.SERVICE_NAME)
public class ElecLogServiceImpl implements IElecLogService {
	@Resource(name = IElecLogDAO.SERVICE_NAME)
	private IElecLogDAO elecLogDAO;

	@Override
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveElecLog(HttpServletRequest request, String details) {
		ElecLog elecLog = new ElecLog();
		ElecUser elecUser = (ElecUser) request.getSession().getAttribute("global_user");
		elecLog.setIpAddress(request.getRemoteAddr());
		elecLog.setOpeName(elecUser.getUserName());
		elecLog.setOpeTime(new Date());
		elecLog.setDetails(details);
		elecLogDAO.save(elecLog);
	}
	@Override
	public List<ElecLogForm> findElecLogListByCondition(ElecLogForm elecLogForm) {
		//组织查询和排序的条件
		String hqlWhere = "";
		List<String> paramsList = new ArrayList<String>();
		if(elecLogForm!=null && elecLogForm.getOpeName()!=null && !elecLogForm.getOpeName().equals("")){
			hqlWhere += " and o.opeName like ?";
			paramsList.add("%"+elecLogForm.getOpeName()+"%");
		}
		Object [] params = paramsList.toArray();
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.opeTime", "desc");
		List<ElecLog> list = elecLogDAO.findCollectionByConditionNoPage(hqlWhere, params, orderby);
		//PO对象的结果集转换成VO对象的结果集
		List<ElecLogForm> formList = this.elecLogPOListToVOList(list);
		return formList;
	}
	
	@Override
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void deleteElecLogByIDs(ElecLogForm elecLogForm) {
		String logID = elecLogForm.getLogID();
		String [] logids = logID.split(",");
		String [] ids = new String[logids.length];
		for(int i=0;logids!=null && i<logids.length;i++){
			String logid = logids[i];
			ids[i] = logid.trim();
		}
		elecLogDAO.deleteObjectByIds(ids);
	}

	private List<ElecLogForm> elecLogPOListToVOList(List<ElecLog> list) {
		List<ElecLogForm> formList = new ArrayList<ElecLogForm>();
		ElecLogForm elecLogForm = null;
		for(int i=0;list!=null && i<list.size();i++){
			ElecLog elecLog = list.get(i);
			elecLogForm = new ElecLogForm();
			elecLogForm.setLogID(elecLog.getLogID());
			elecLogForm.setIpAddress(elecLog.getIpAddress());
			elecLogForm.setOpeName(elecLog.getOpeName());
			elecLogForm.setOpeTime(String.valueOf(elecLog.getOpeTime()!=null?elecLog.getOpeTime():""));
			elecLogForm.setDetails(elecLog.getDetails());
			formList.add(elecLogForm);
		}
		return formList;
	}
	

}
