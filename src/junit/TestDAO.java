package junit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.elec.container.ServiceProvider;
import cn.itcast.elec.dao.IElecTextDAO;
import cn.itcast.elec.domain.ElecText;

public class TestDAO {
	//保存
	@Test
	public void saveElecText() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		IElecTextDAO elecTextDAO = (IElecTextDAO) ac.getBean(IElecTextDAO.SERVICE_NAME);
		
		ElecText elecText = new ElecText();
		elecText.setTextName("DAO");
		elecText.setTextDate(new Date());
		elecText.setTextRemark("DAO");
		
		elecTextDAO.save(elecText);
	}
	//修改
	@Test
	public void updateElecText() {
		IElecTextDAO elecTextDAO = (IElecTextDAO) ServiceProvider.getService(IElecTextDAO.SERVICE_NAME);
		ElecText elecText = new ElecText();
		elecText.setTextID("402863fb5e122f0e015e122f10f10001");
		elecText.setTextName("DAO");
		elecText.setTextDate(new Date());
		elecText.setTextRemark("DAO");
		
		elecTextDAO.update(elecText);;
	}
	//通过ID查询对象
	@Test
	public void findObjectById() {
		IElecTextDAO elecTextDAO = (IElecTextDAO) ServiceProvider.getService(IElecTextDAO.SERVICE_NAME);
		Serializable id = "40283a235b763f29015b763f2bb00001";
		
		ElecText elecText = elecTextDAO.findObjectById(id);
		System.out.println(elecText.getTextName());
	}
	//通过ID删除对象
	@Test
	public void deleteObjectByIds() {
		IElecTextDAO elecTextDAO = (IElecTextDAO) ServiceProvider.getService(IElecTextDAO.SERVICE_NAME);
		Serializable[] ids = {"40283a235b763f29015b763f2bb00001","2c90b1225b7946e8015b7946ebd00001"};
		
		elecTextDAO.deleteObjectByIds(ids);
	}
	//通过集合对象进行删除
	@Test
	public void deleteObjectByCollection() {
		IElecTextDAO elecTextDAO = (IElecTextDAO) ServiceProvider.getService(IElecTextDAO.SERVICE_NAME);
		List<ElecText> list = new ArrayList<ElecText>();
		ElecText elecText = new ElecText();
		elecText.setTextID("2c90b1225b7a3d42015b7a3d44cf0001");
		ElecText elecText1 = new ElecText();
		elecText1.setTextID("2c90b1225b7a3d42015b7a3d452f0002");
		
		list.add(elecText);
		list.add(elecText1);
		
		
		elecTextDAO.deleteObjectByCollection(list);
	}
}
