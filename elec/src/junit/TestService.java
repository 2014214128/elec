package junit;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.elec.container.ServiceProvider;
import cn.itcast.elec.dao.IElecTextDAO;
import cn.itcast.elec.domain.ElecText;
import cn.itcast.elec.service.IElecTextService;
import cn.itcast.elec.web.form.ElecTextForm;

public class TestService {
	/**
	 * 保存对象
	 */
	@Test
	public void saveElecText() {
		/*ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		IElecTextService elecTextService = (IElecTextService) ac.getBean(IElecTextService.SERVICE_NAME);*/
		IElecTextService elecTextService = (IElecTextService) ServiceProvider.getService(IElecTextService.SERVICE_NAME);
		
		ElecText elecText = new ElecText();
		elecText.setTextName("Serviceshabi");
		elecText.setTextDate(new Date());
		elecText.setTextRemark("Serviceshabi");
		
		elecTextService.saveElecText(elecText);
	}
	/**
	 * 通过查询条件，查询对象的列表集合
	 * 模仿Action层
	 */
	@Test
	public void findCollection() {
		IElecTextService elecTextService = (IElecTextService) ServiceProvider.getService(IElecTextService.SERVICE_NAME);
		
		ElecTextForm elecTextForm = new ElecTextForm();
		elecTextForm.setTextName("张");
		elecTextForm.setTextRemark("李");
		
		List<ElecText> list = elecTextService.findCollectionByConditionNoPage(elecTextForm);
	}
}
