package cn.itcast.elec.web.action;


import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.elec.container.ServiceProvider;
import cn.itcast.elec.domain.ElecText;
import cn.itcast.elec.service.IElecTextService;
import cn.itcast.elec.util.StringHelper;
import cn.itcast.elec.web.form.ElecTextForm;

import com.opensymphony.xwork2.ModelDriven;

public class ElecTextAction extends BaseAction implements ModelDriven<ElecTextForm>{
	private IElecTextService elecTextService = (IElecTextService) ServiceProvider.getService(IElecTextService.SERVICE_NAME);

	private ElecTextForm elecTextForm = new ElecTextForm();
	@Override
	public ElecTextForm getModel() {
		return elecTextForm;
	}
	
	public String save() {
		//System.out.println(elecTextForm.getTextName());
		ElecText elecText = new ElecText();
		elecText.setTextName(elecTextForm.getTextName());
		elecText.setTextDate(StringHelper.stringConvertDate(elecTextForm.getTextDate()));
		elecText.setTextRemark(elecTextForm.getTextRemark());
		
		/*ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		IElecTextService elecTextService = (IElecTextService) ac.getBean(IElecTextService.SERVICE_NAME);*/
		
		elecTextService.saveElecText(elecText);
		
		return "save";
	}
	
}
