package junit;

import java.util.Date;


import org.junit.Test;

import cn.itcast.elec.container.ServiceProvider;
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
		
		elecTextService.findCollectionByConditionNoPage(elecTextForm);
	}
}
