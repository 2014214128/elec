package cn.itcast.elec.web.action;

import cn.itcast.elec.container.ServiceProvider;
import cn.itcast.elec.domain.ElecText;
import cn.itcast.elec.service.IElecTextService;
import cn.itcast.elec.util.StringHelper;
import cn.itcast.elec.web.form.ElecTextForm;

import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class ElecTextAction extends BaseAction implements
		ModelDriven<ElecTextForm> {
	private IElecTextService elecTextService = (IElecTextService) ServiceProvider
			.getService(IElecTextService.SERVICE_NAME);
	private ElecTextForm elecTextForm = new ElecTextForm();

	@Override
	public ElecTextForm getModel() {
		return elecTextForm;
	}
	
	public String save() {
		ElecText elecText = new ElecText();
		elecText.setTextName(elecTextForm.getTextName());
		elecText.setTextDate(StringHelper.stringConvertDate(elecTextForm
				.getTextDate()));
		elecText.setTextRemark(elecTextForm.getTextRemark());
		elecTextService.saveElecText(elecText);
		return "save";
	}

}
