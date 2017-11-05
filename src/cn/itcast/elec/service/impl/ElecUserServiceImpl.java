package cn.itcast.elec.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.elec.dao.IElecSystemDDLDAO;
import cn.itcast.elec.dao.IElecUserDAO;
import cn.itcast.elec.domain.ElecUser;
import cn.itcast.elec.service.IElecUserService;
import cn.itcast.elec.util.GenerateSqlFromExcel;
import cn.itcast.elec.util.MD5keyBean;
import cn.itcast.elec.util.PageInfo;
import cn.itcast.elec.util.StringHelper;
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
	public List<ElecUserForm> findElecUserList(ElecUserForm elecUserForm,
			HttpServletRequest request) {
		// 组织查询条件
		String hqlWhere = "";
		List<String> paramsList = new ArrayList<String>();
		if (elecUserForm != null && elecUserForm.getUserName() != null
				&& !elecUserForm.getUserName().equals("")) {
			hqlWhere += " and o.userName like ?";
			paramsList.add("%" + elecUserForm.getUserName() + "%");
		}
		Object[] params = paramsList.toArray();
		// 组织排序
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.onDutyDate", "desc");
		// 2011-12-31修改，添加分页功能 begin
		PageInfo pageInfo = new PageInfo(request);
		// List<ElecUser> list =
		// elecUserDao.findCollectionByConditionNoPage(hqlWhere, params,
		// orderby);
		List<ElecUser> list = elecUserDAO.findCollectionByConditionWithPage(
				hqlWhere, params, orderby, pageInfo);
		request.setAttribute("page", pageInfo.getPageBean());
		// end
		List<ElecUserForm> formList = this.elecUserPOListToVOList(list);
		return formList;
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public void saveElecUser(ElecUserForm elecUserForm) {
		ElecUser elecUser = this.elecUserVOToPO(elecUserForm);
		if (elecUserForm.getUserID() != null
				&& !elecUserForm.getUserID().equals("")) {
			elecUserDAO.update(elecUser);
		} else {
			elecUserDAO.save(elecUser);
		}
	}

	@Override
	public ElecUserForm findElecUser(ElecUserForm elecUserForm) {
		return this.elecUserPOToVO(
				elecUserDAO.findObjectByID(elecUserForm.getUserID()),
				elecUserForm);
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteElecUser(ElecUserForm elecUserForm) {
		elecUserDAO.deleteObjectByIds(elecUserForm.getUserID());
	}

	@Override
	public String checkLoginName(String loginName) {
		String hqlWhere = " and o.loginName = ? ";
		Object[] params = { loginName };
		List<ElecUser> list = elecUserDAO.findCollectionByConditionNoPage(
				hqlWhere, params, null);
		String checkflag = "";
		if (list != null && list.size() > 0) {
			checkflag = "1";
		} else {
			checkflag = "2";
		}
		return checkflag;
	}

	@Override
	public ElecUser findElecUserByLoginName(String name) {
		String hqlWhere = " and o.loginName = ? ";
		Object[] params = { name };
		List<ElecUser> list = elecUserDAO.findCollectionByConditionNoPage(
				hqlWhere, params, null);
		ElecUser elecUser = null;
		if (list != null && list.size() > 0) {
			elecUser = list.get(0);
		}
		return elecUser;
	}

	@Override
	public String findElecPopedomByLoginName(String name) {
		List<Object> list = elecUserDAO.findElecPopedomByLoginName(name);
		StringBuilder popedom = new StringBuilder("");
		for (Object obj : list) {
			popedom.append(obj.toString());
		}
		return popedom.toString();
	}

	@Override
	public Hashtable<String, String> findElecRoleByLoginName(String name) {
		List<Object[]> list = elecUserDAO.findElecRoleByLoginName(name);
		Hashtable<String, String> ht = null;
		if (list != null && list.size() > 0) {
			ht = new Hashtable<String, String>();
			for (Object[] obj : list) {
				ht.put(obj[0].toString(), obj[1].toString());
			}
		}
		return ht;
	}

	@Override
	public ArrayList<Object> getExcelFiledNameList() {
		String[] titles = { "登录名", "用户姓名", "性别", "联系电话", "是否在职" };
		ArrayList<Object> fileName = new ArrayList<Object>();
		for (int i = 0; i < titles.length; i++) {
			fileName.add(titles[i]);
		}
		return fileName;
	}

	@Override
	public ArrayList<Object> getExcelFiledDataList(ElecUserForm elecUserForm) {
		// 组织查询条件
		String hqlWhere = "";
		List<String> paramsList = new ArrayList<String>();
		if (elecUserForm != null && elecUserForm.getUserName() != null
				&& !elecUserForm.getUserName().equals("")) {
			hqlWhere += " and o.userName like ?";
			paramsList.add("%" + elecUserForm.getUserName() + "%");
		}
		Object[] params = paramsList.toArray();
		// 组织排序
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.onDutyDate", "desc");
		List<ElecUser> list = elecUserDAO.findCollectionByConditionNoPage(
				hqlWhere, params, orderby);
		List<ElecUserForm> formList = this.elecUserPOListToVOList(list);
		// 构造报表导出数据
		ArrayList<Object> filedData = new ArrayList<Object>();
		for (int i = 0; formList != null && i < formList.size(); i++) {
			ArrayList<Object> dataList = new ArrayList<Object>();
			ElecUserForm userForm = formList.get(i);
			// zhugeliang 诸葛亮 男 88886666 是
			dataList.add(userForm.getLoginName());
			dataList.add(userForm.getUserName());
			dataList.add(userForm.getSexID());
			dataList.add(userForm.getContactTel());
			dataList.add(userForm.getIsDuty());
			filedData.add(dataList);
		}
		return filedData;
	}
	
	@Override
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveElecUserWithExcel(ElecUserForm elecUserForm) {
		File file = elecUserForm.getFile();
		try {
			ArrayList<Object> list =  GenerateSqlFromExcel.generateStationBugSql(file);
			List<ElecUser> userList = new ArrayList<ElecUser>();
			for (int i = 0; list != null && i < list.size(); i++) {
				String [] data =  (String[]) list.get(i);
				ElecUser elecUser = new ElecUser();
				elecUser.setLoginName(data[0]);
				elecUser.setLoginPassword(new MD5keyBean().getkeyBeanofStr(data[1]));
				elecUser.setUserName(data[2]);
				elecUser.setSexID(data[3]);
				elecUser.setJctID(data[4]);
				elecUser.setAddress(data[5]);
				elecUser.setIsDuty(data[6]);
				userList.add(elecUser);
			}
			elecUserDAO.saveObjectByCollection(userList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<ElecUserForm> findUserByChart() {
		List<Object []> list = elecUserDAO.findUserByChart();
		List<ElecUserForm> formList = this.userChartPOToVOList(list);
		return formList;
	}

	private List<ElecUserForm> userChartPOToVOList(List<Object[]> list) {
		List<ElecUserForm> formList = new ArrayList<ElecUserForm>();
		ElecUserForm elecUserForm = null;
		for (int i = 0; list != null && i < list.size(); i++) {
			Object [] objs = list.get(i);
			elecUserForm = new ElecUserForm();
			elecUserForm.setJctname(objs[0].toString());
			elecUserForm.setJctcount(objs[1].toString());
			formList.add(elecUserForm);
		}
		return formList;
	}

	private List<ElecUserForm> elecUserPOListToVOList(List<ElecUser> list) {
		List<ElecUserForm> formList = new ArrayList<ElecUserForm>();
		ElecUserForm elecUserForm = null;
		for (int i = 0; list != null && i < list.size(); i++) {
			ElecUser elecUser = list.get(i);
			elecUserForm = new ElecUserForm();
			elecUserForm.setUserID(elecUser.getUserID());
			elecUserForm.setJctID(elecUser.getJctID());
			elecUserForm.setUserName(elecUser.getUserName());
			elecUserForm.setLoginName(elecUser.getLoginName());
			elecUserForm.setLoginPassword(elecUser.getLoginPassword());
			elecUserForm.setSexID(elecUser.getSexID() != null
					&& !elecUser.getSexID().equals("") ? elecSystemDDLDAO
					.findDDLName(elecUser.getSexID(), "性别") : "");
			elecUserForm.setAddress(elecUser.getAddress());
			elecUserForm.setContactTel(elecUser.getContactTel());
			elecUserForm.setIsDuty(elecUser.getIsDuty() != null
					&& !elecUser.getIsDuty().equals("") ? elecSystemDDLDAO
					.findDDLName(elecUser.getIsDuty(), "是否在职") : "");
			elecUserForm.setEmail(elecUser.getEmail());
			elecUserForm.setRemark(elecUser.getRemark());
			formList.add(elecUserForm);
		}
		return formList;
	}

	private ElecUser elecUserVOToPO(ElecUserForm elecUserForm) {
		ElecUser elecUser = new ElecUser();
		if (elecUserForm != null) {
			if (elecUserForm.getUserID() != null
					&& !elecUserForm.getUserID().equals("")) {
				elecUser.setUserID(elecUserForm.getUserID());
				if (elecUserForm.getOffDutyDate() != null
						&& !elecUserForm.getOffDutyDate().equals("")) {
					elecUser.setOffDutyDate(StringHelper
							.stringConvertDate(elecUserForm.getOffDutyDate()));
				}
			}
			elecUser.setJctID(elecUserForm.getJctID());
			elecUser.setUserName(elecUserForm.getUserName());
			elecUser.setLoginName(elecUserForm.getLoginName());
			String md5flag = elecUserForm.getMd5flag();
			if (md5flag != null && md5flag.equals("1")) {
				elecUser.setLoginPassword(elecUserForm.getLoginPassword());
			} else {
				elecUser.setLoginPassword(new MD5keyBean()
						.getkeyBeanofStr(elecUserForm.getLoginPassword()));
			}
			elecUser.setSexID(elecUserForm.getSexID());
			if (elecUserForm.getBirthday() != null
					&& !elecUserForm.getBirthday().equals("")) {
				elecUser.setBirthday(StringHelper
						.stringConvertDate((elecUserForm.getBirthday())));
			}
			elecUser.setAddress(elecUserForm.getAddress());
			elecUser.setContactTel(elecUserForm.getContactTel());
			elecUser.setEmail(elecUserForm.getEmail());
			elecUser.setMobile(elecUserForm.getMobile());
			elecUser.setIsDuty(elecUserForm.getIsDuty());
			if (elecUserForm.getOnDutyDate() != null
					&& !elecUserForm.getOnDutyDate().equals("")) {
				elecUser.setOnDutyDate(StringHelper
						.stringConvertDate((elecUserForm.getOnDutyDate())));
			}
			elecUser.setRemark(elecUserForm.getRemark());

		}
		return elecUser;
	}

	private ElecUserForm elecUserPOToVO(ElecUser elecUser,
			ElecUserForm elecUserForm) {
		if (elecUser != null) {
			elecUserForm.setUserID(elecUser.getUserID());
			elecUserForm.setJctID(elecUser.getJctID());
			elecUserForm.setUserName(elecUser.getUserName());
			elecUserForm.setLoginName(elecUser.getLoginName());
			elecUserForm.setLoginPassword(elecUser.getLoginPassword());
			elecUserForm.setSexID(elecUser.getSexID());
			elecUserForm
					.setBirthday(String.valueOf(elecUser.getBirthday() != null
							&& !elecUser.getBirthday().equals("") ? elecUser
							.getBirthday() : ""));
			elecUserForm.setAddress(elecUser.getAddress());
			elecUserForm.setContactTel(elecUser.getContactTel());
			elecUserForm.setEmail(elecUser.getEmail());
			elecUserForm.setMobile(elecUser.getMobile());
			elecUserForm.setIsDuty(elecUser.getIsDuty());
			elecUserForm
					.setOnDutyDate(String.valueOf(elecUser.getOnDutyDate() != null
							&& !elecUser.getOnDutyDate().equals("") ? elecUser
							.getOnDutyDate() : ""));
			elecUserForm.setOffDutyDate(String.valueOf(elecUser
					.getOffDutyDate() != null
					&& !elecUser.getOffDutyDate().equals("") ? elecUser
					.getOffDutyDate() : ""));
			elecUserForm.setRemark(elecUser.getRemark());
		}
		return elecUserForm;
	}

}
