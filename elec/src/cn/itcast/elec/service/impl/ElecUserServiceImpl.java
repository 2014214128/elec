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
import cn.itcast.elec.dao.IElecSystemDDLDAO;
import cn.itcast.elec.dao.IElecUserDAO;
import cn.itcast.elec.domain.ElecCommonMsg;
import cn.itcast.elec.domain.ElecUser;
import cn.itcast.elec.service.IElecCommonMsgService;
import cn.itcast.elec.service.IElecUserService;
import cn.itcast.elec.util.StringHelper;
import cn.itcast.elec.web.form.ElecCommonMsgForm;
import cn.itcast.elec.web.form.ElecUserForm;

@Transactional(readOnly = true)
@Service(IElecUserService.SERVICE_NAME)
public class ElecUserServiceImpl implements IElecUserService {
	@Resource(name = IElecUserDAO.SERVICE_NAME)
	private IElecUserDAO elecUserDAO;

	@Resource(name = IElecSystemDDLDAO.SERVICE_NAME)
	private IElecSystemDDLDAO elecSystemDDLDAO;
	
	
	
	public IElecSystemDDLDAO getElecSystemDDLDAO() {
		return elecSystemDDLDAO;
	}

	public void setElecSystemDDLDAO(IElecSystemDDLDAO elecSystemDDLDAO) {
		this.elecSystemDDLDAO = elecSystemDDLDAO;
	}

	public IElecUserDAO getElecUserDAO() {
		return elecUserDAO;
	}

	public void setElecUserDAO(IElecUserDAO elecUserDAO) {
		this.elecUserDAO = elecUserDAO;
	}
	/**
	 * @Name: findElecUserList
	 * @Description: 查询用户列表信息
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter:ElecUserForm elecUserForm
	 * @Return:List<ElecUserForm> 集合
	 */
	@Override
	public List<ElecUserForm> findElecUserList(ElecUserForm elecUserForm) {
		String hqlWhere = "";
		List<String> paramsList = new ArrayList<String>();
		if(elecUserForm != null && elecUserForm.getUserName() != null && !elecUserForm.getUserName().equals("")) {
			hqlWhere += " and o.userName like ? ";
			paramsList.add("%" + elecUserForm.getUserName() + "%");
		}
		Object [] params = paramsList.toArray();
		LinkedHashMap< String, String> orderBy = new LinkedHashMap<String, String>();
		orderBy.put(" o.onDutyDate", " desc");
		List<ElecUser> list = elecUserDAO.findCollectionByConditionNoPage(hqlWhere, params, orderBy);
		List<ElecUserForm> formList = this.elecUserPOListToVOList(list);
		return formList;
	}

	private List<ElecUserForm> elecUserPOListToVOList(List<ElecUser> list) {
		List<ElecUserForm> formList = new ArrayList<ElecUserForm>();
		ElecUserForm elecUserForm = null;
		for(int i=0 ; list != null && i<list.size() ; i++) {
			ElecUser elecUser = list.get(i);
			elecUserForm =  new ElecUserForm();
			elecUserForm.setUserID(elecUser.getUserID());
			elecUserForm.setJctID(elecUser.getJctID());
			elecUserForm.setUserName(elecUser.getUserName());
			elecUserForm.setLoginName(elecUser.getLoginName());
			elecUserForm.setLoginPassword(elecUser.getLoginPassword());
			if(elecUser.getSexID() != null && !elecUser.getSexID().equals("")) {
				elecUserForm.setSexID(elecSystemDDLDAO.findDDLName(elecUser.getSexID(), "性别"));
			} else {
				elecUserForm.setSexID("");
			}
			
			elecUserForm.setAddress(elecUser.getAddress());
			elecUserForm.setContactTel(elecUser.getContactTel());
			if(elecUser.getIsDuty() != null && !elecUser.getIsDuty().equals("")) {
				elecUserForm.setIsDuty(elecSystemDDLDAO.findDDLName(elecUser.getIsDuty(), "是否在职"));
			} else {
				elecUserForm.setIsDuty("");
			}
				
			elecUserForm.setEmail(elecUser.getEmail());
			elecUserForm.setRemark(elecUser.getRemark());
			formList.add(elecUserForm);
		}
		return formList;
	}
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	@Override
	public void saveElecUser(ElecUserForm elecUserForm) {
		ElecUser elecUser = this.elecUserVOToPO(elecUserForm);
		if(elecUserForm.getUserID() != null && elecUserForm.getUserID().equals("")) {
			elecUserDAO.update(elecUser);
		} else {
			elecUserDAO.save(elecUser);
		}
		
	}

	@SuppressWarnings("deprecation")
	private ElecUser elecUserVOToPO(ElecUserForm elecUserForm) {
		ElecUser elecUser = new ElecUser();
		if(elecUserForm != null) {
			if(elecUserForm.getUserID() != null && !elecUserForm.getUserID().equals("")) {
				elecUser.setUserID(elecUserForm.getUserID());
				if(elecUserForm.getOffDutyDate() != null && !elecUserForm.getOffDutyDate().equals("")) {
					elecUser.setOffDutyDate(StringHelper.stringConvertDate(elecUserForm.getOffDutyDate()));
				}
			}
			elecUser.setJctID(elecUserForm.getJctID());
			elecUser.setUserName(elecUserForm.getUserName());
			elecUser.setLoginName(elecUserForm.getLoginName());
			elecUser.setLoginPassword(elecUserForm.getLoginPassword());
			elecUser.setSexID(elecUserForm.getSexID());
			if(elecUserForm.getBirthday()!= null && !elecUserForm.getBirthday().equals("") ){
				elecUser.setBirthday(StringHelper.stringConvertDate((elecUserForm.getBirthday())));
			}
			elecUser.setAddress(elecUserForm.getAddress());
			elecUser.setContactTel(elecUserForm.getContactTel());
			elecUser.setEmail(elecUserForm.getEmail());
			elecUser.setMobile(elecUserForm.getMobile());
			elecUser.setIsDuty(elecUserForm.getIsDuty());
			if(elecUserForm.getOnDutyDate() != null && !elecUserForm.getOnDutyDate().equals("")) {
				elecUser.setOnDutyDate(StringHelper.stringConvertDate((elecUserForm.getOnDutyDate())));
			}
			elecUser.setRemark(elecUserForm.getRemark());
			
		}
		return elecUser;
	}

	@Override
	public ElecUserForm findElecUser(ElecUserForm elecUserForm) {
		String userID = elecUserForm.getUserID();
		ElecUser elecUser = elecUserDAO.findObjectByID(userID);
		//PO对象转换成VO对象
		elecUserForm = this.elecUserPOToVO(elecUser,elecUserForm);
		return elecUserForm;
	}

	private ElecUserForm elecUserPOToVO(ElecUser elecUser,
			ElecUserForm elecUserForm) {
		if(elecUser!=null){
			elecUserForm.setUserID(elecUser.getUserID());
			elecUserForm.setJctID(elecUser.getJctID());
			elecUserForm.setUserName(elecUser.getUserName());
			elecUserForm.setLoginName(elecUser.getLoginName());
			elecUserForm.setLoginPassword(elecUser.getLoginPassword());
			elecUserForm.setSexID(elecUser.getSexID());
			elecUserForm.setBirthday(String.valueOf(elecUser.getBirthday()!=null && !elecUser.getBirthday().equals("")?elecUser.getBirthday():""));
			elecUserForm.setAddress(elecUser.getAddress());
			elecUserForm.setContactTel(elecUser.getContactTel());
			elecUserForm.setEmail(elecUser.getEmail());
			elecUserForm.setMobile(elecUser.getMobile());
			elecUserForm.setIsDuty(elecUser.getIsDuty());
			elecUserForm.setOnDutyDate(String.valueOf(elecUser.getOnDutyDate()!=null && !elecUser.getOnDutyDate().equals("")?elecUser.getOnDutyDate():""));
			elecUserForm.setOffDutyDate(String.valueOf(elecUser.getOffDutyDate()!=null && !elecUser.getOffDutyDate().equals("")?elecUser.getOffDutyDate():""));
			elecUserForm.setRemark(elecUser.getRemark());
		}
		return elecUserForm;
	}

	@Override
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void deleteElecUser(ElecUserForm elecUserForm) {
		String userID = elecUserForm.getUserID();
		elecUserDAO.deleteObjectByIds(userID);
	}

	@Override
	public String checkLoginName(String loginName) {
		String hqlWhere = " and o.loginName = ? ";
		Object[] params = {loginName};
		List<ElecUser> list = elecUserDAO.findCollectionByConditionNoPage(hqlWhere, params, null);
		String checkflag = "";
		if(list != null && list.size() > 0) {
			checkflag = "1";
		}else {
			checkflag = "2";
		}
		return checkflag;
	}
	
	

}
