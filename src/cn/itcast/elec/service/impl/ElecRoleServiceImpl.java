package cn.itcast.elec.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.elec.dao.IElecRolePopedomDAO;
import cn.itcast.elec.dao.IElecUserRoleDAO;
import cn.itcast.elec.domain.ElecRolePopedom;
import cn.itcast.elec.domain.ElecUserRole;
import cn.itcast.elec.service.IElecRoleService;
import cn.itcast.elec.util.XmlObject;
import cn.itcast.elec.web.form.ElecRoleForm;
import cn.itcast.elec.web.form.ElecUserForm;

@Transactional(readOnly = true)
@Service(IElecRoleService.SERVICE_NAME)
public class ElecRoleServiceImpl implements IElecRoleService {
	@Resource(name = IElecUserRoleDAO.SERVICE_NAME)
	private IElecUserRoleDAO elecUserRoleDAO;

	@Resource(name = IElecRolePopedomDAO.SERVICE_NAME)
	private IElecRolePopedomDAO elecRolePopedomDAO;

	@Override
	public List<XmlObject> readXml() {
		ServletContext servletContext = ServletActionContext
				.getServletContext();
		String realPath = servletContext
				.getRealPath("/WEB-INF/classes/Function.xml");
		File file = new File(realPath);
		SAXReader read = new SAXReader();
		List<XmlObject> xmlList = new ArrayList<XmlObject>();
		try {
			Document document = read.read(file);
			Element element = document.getRootElement();
			XmlObject xmlObject = null;
			for (@SuppressWarnings("unchecked")
			Iterator<Element> iter = element.elementIterator("Function"); iter
					.hasNext();) {
				Element xmlElement = iter.next();
				xmlObject = new XmlObject();
				xmlObject.setCode(xmlElement.elementText("FunctionCode"));
				xmlObject.setName(xmlElement.elementText("FunctionName"));
				xmlObject.setParentCode(xmlElement.elementText("ParentCode"));
				xmlObject.setParentName(xmlElement.elementText("FunctionName"));
				xmlList.add(xmlObject);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return xmlList;
	}

	@Override
	public List<XmlObject> readEditXml(String roleID) {
		ElecRolePopedom elecRolePopedom = elecRolePopedomDAO
				.findObjectById(roleID);
		String popedom = "";
		if (elecRolePopedom != null) {
			popedom = elecRolePopedom.getPopedomCode();
		}
		List<XmlObject> list = this.readXmlByPopedom(popedom);
		return list;
	}

	@Override
	public List<ElecUserForm> findElecUserListByRoleID(String roleID) {
		return this.elecUserRoleObjectListToVoList(elecUserRoleDAO
				.findElecUserListByRoleID(roleID));
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public void saveRole(ElecRoleForm elecRoleForm) {
		this.saveRolePopedom(elecRoleForm);
		this.saveUserRole(elecRoleForm);
	}

	private void saveUserRole(ElecRoleForm elecRoleForm) {
		String roleid = elecRoleForm.getRoleid();
		String[] selectuser = elecRoleForm.getSelectuser();
		/*删除*/
		String hqlWhere = " and o.roleID = ?";
		Object[] params = { roleid };
		List<ElecUserRole> entities = elecUserRoleDAO
				.findCollectionByConditionNoPage(hqlWhere, params, null);

		elecUserRoleDAO.deleteObjectByCollection(entities);
		/*插入*/
		List<ElecUserRole> list = new ArrayList<ElecUserRole>();
		for (int i = 0; selectuser != null && i < selectuser.length; i++) {
			String userID = selectuser[i];
			ElecUserRole elecUserRole = new ElecUserRole();
			elecUserRole.setUserID(userID);
			elecUserRole.setRoleID(roleid);
			list.add(elecUserRole);
		}
		elecUserRoleDAO.saveObjectByCollection(list);
	}

	private void saveRolePopedom(ElecRoleForm elecRoleForm) {
		String roleID = elecRoleForm.getRoleid();
		String[] selectoper = elecRoleForm.getSelectoper();
		StringBuilder popedom = new StringBuilder("");
		for (int i = 0; selectoper != null && i < selectoper.length; i++) {
			popedom.append(selectoper[i]);
		}
		ElecRolePopedom elecRolePopedom = elecRolePopedomDAO
				.findObjectById(roleID);
		if (elecRolePopedom != null) {
			elecRolePopedom.setPopedomCode(popedom.toString());
			elecRolePopedomDAO.update(elecRolePopedom);
		} else {
			elecRolePopedom = new ElecRolePopedom();
			elecRolePopedom.setRoleID(roleID);
			elecRolePopedom.setPopedomCode(popedom.toString());
			elecRolePopedomDAO.save(elecRolePopedom);
		}
	}

	private List<ElecUserForm> elecUserRoleObjectListToVoList(
			List<Object[]> list) {
		List<ElecUserForm> formList = new ArrayList<ElecUserForm>();
		ElecUserForm elecUserForm = null;
		for (Object[] objs : list) {
			elecUserForm = new ElecUserForm();
			elecUserForm.setFlag(objs[0].toString());
			elecUserForm.setUserID(objs[1].toString());
			elecUserForm.setUserName(objs[2].toString());
			elecUserForm.setLoginName(objs[3].toString());
			formList.add(elecUserForm);
		}
		return formList;
	}

	private List<XmlObject> readXmlByPopedom(String popedom) {
		List<XmlObject> xmlList = this.readXml();
		for (int i = 0; xmlList != null && i < xmlList.size(); i++) {
			if (popedom.contains(xmlList.get(i).getCode())) {
				xmlList.get(i).setFlag("1");
			} else {
				xmlList.get(i).setFlag("0");
			}
		}
		return xmlList;
	}

}
