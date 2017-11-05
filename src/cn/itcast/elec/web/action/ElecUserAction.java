package cn.itcast.elec.web.action;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import cn.itcast.elec.container.ServiceProvider;
import cn.itcast.elec.service.IElecLogService;
import cn.itcast.elec.service.IElecSystemDDLService;
import cn.itcast.elec.service.IElecUserService;
import cn.itcast.elec.util.ChartUtils;
import cn.itcast.elec.util.ExcelFileGenerator;
import cn.itcast.elec.web.form.ElecUserForm;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class ElecUserAction extends BaseAction implements
		ModelDriven<ElecUserForm> {
	private IElecUserService elecUserService = (IElecUserService) ServiceProvider
			.getService(IElecUserService.SERVICE_NAME);

	private IElecSystemDDLService elecSystemDDLService = (IElecSystemDDLService) ServiceProvider
			.getService(IElecSystemDDLService.SERVICE_NAME);
	
	private IElecLogService elecLogService = (IElecLogService) ServiceProvider.getService(IElecLogService.SERVICE_NAME);
	private ElecUserForm elecUserForm = new ElecUserForm();

	@Override
	public ElecUserForm getModel() {
		return elecUserForm;
	}

	/**
	 * @Name: home
	 * @Description: 查询用户列表信息
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: null
	 * @Return:String home 跳转到userIndex.jsp页面
	 */
	public String home() {
		/*
		 * 操作： 1、查询用户列表，查询Elec_User表，获取用户信息 如果从左侧进入页面的时候，无条件查询所有的用户信息
		 * 如果在userIndex.jsp页面点击查询的时候，需要将用户姓名组成查询条件查询用户信息 2、查询集合对象中的PO对象转换成VO对象
		 * 3、将VO对象中的性别和是否在职，从数据字典中的数据项编号获取到数据项名称 条件：数据项编号、数据类型 值：数据项名称
		 */
		request.setAttribute("userList",
				elecUserService.findElecUserList(elecUserForm, request));
		String initflag = request.getParameter("initflag");
		if (initflag != null && initflag.equals("1")) {
			return "userList";
		}
		return "home";
	}

	/**
	 * @Name: add
	 * @Description: 添加用户
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: null
	 * @Return:String add 跳转到userAdd.jsp页面
	 */
	public String add() {
		/*
		 * 操作： 1、新增用户页面功能，需将性别、所属单位、是否在职从数据库字典中获取相应结果集列表
		 * 使用数据类型进行查询，获取对应数据类型下的数据项编号和数据项名称 2、将PO对象转换成VO对象
		 */
		this.initSystemDDl();
		return "add";
	}

	/**
	 * @Name: save
	 * @Description: 保存用户信息
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: null
	 * @Return:String save 重定向到userIndex.jsp
	 */
	public String save() {
		/*
		 * 操作： 1、获取页面中传递的需要进行保存的用户值 2、VO对象转成PO对象 3、从VO对象中获取userID的值
		 * 如果userID==null： 执行底层的保存（save）操作 如果userID!=null: 执行递呈的更新（update）操作
		 */
		elecUserService.saveElecUser(elecUserForm);
		if (elecUserForm.getUserID() != null && !elecUserForm.getUserID().equals("")) {
			elecLogService.saveElecLog(request, "用户管理：修改当前用户【" + elecUserForm.getUserName() + "】的信息");
		} else {
			elecLogService.saveElecLog(request, "用户管理：保存当前用户【" + elecUserForm.getUserName() + "】的信息");
		}
		String roleflag = request.getParameter("roleflag");
		if (roleflag != null && roleflag.equals("1")) {
			return edit();
		}
		return "save";
	}

	/**
	 * @Name: edit
	 * @Description: 跳转到编辑的页面
	 * @Author: 刘洋（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2011-12-26 （创建日期）
	 * @Parameters: 无
	 * @Return: String edit 跳转到userEdit.jsp
	 */
	public String edit() {
		/*操作：
		   1、点击编辑的时候，从页面中获取userID，使用userID进行查询，获取用户的详细信息
		   2、PO对象转换成VO对象
		   3、使用值栈的形式存放VO对象
		
		   4、编辑用户页面功能，需将性别、所属单位、是否在职从数据库字典中获取相应结果集列表
		     * 使用数据类型进行查询，获取对应数据类型下的数据项编号和数据项名称
		   5、将PO对象转换成VO对象
		*/
		ActionContext.getContext().getValueStack().push(elecUserService.findElecUser(elecUserForm));
		this.initSystemDDl();
		/**
		 * 使用viewflag字段 判断当前用户跳转的是编辑页面还是明细页面 * 如果viewflag==null：说明当前操作的是编辑页面 *
		 * 如果viewflag==1:说明当前操作的是明细页面
		 */
		request.setAttribute("viewflag", elecUserForm.getViewflag());
		String roleflag = elecUserForm.getRoleflag();
		request.setAttribute("roleflag", roleflag);
		return "edit";
	}

	/**
	 * @Name: delete
	 * @Description: 删除用户信息
	 * @Author: 刘洋（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2011-12-26 （创建日期）
	 * @Parameters: 无
	 * @Return: String delete 跳转到userIndex.jsp
	 */
	public String delete() {
		/*操作：
		   1、从VO对象获取页面传递的userID的值
		   2、根据选择的userID的值，执行删除操作
		*/
		elecUserService.deleteElecUser(elecUserForm);
		return "delete";
	}
	/**
	 * @Name: export
	 * @Description: 导出报表
	 * @Author: 刘洋（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2011-12-26 （创建日期）
	 * @Parameters: 无
	 * @Return: String export 跳转到userIndex.jsp
	 */
	public String export() {
		ArrayList<Object> fileName = elecUserService.getExcelFiledNameList();
		ArrayList<Object> fileData = elecUserService.getExcelFiledDataList(elecUserForm);
		try {
			OutputStream out = response.getOutputStream();
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			ExcelFileGenerator generator = new ExcelFileGenerator(fileName, fileData);
			generator.expordExcel(out);
			System.setOut(new PrintStream(out));
			out.flush();
			if (out != null) {
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return home();
	}
	/**
	 * @Name: importpage
	 * @Description: 跳转导入的excel页面
	 * @Author: 刘洋（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2011-12-26 （创建日期）
	 * @Parameters: 无
	 * @Return: String importpage 跳转到userImport.jsp
	 */
	public String importpage() {
		
		return "importpage";
	}
	/**
	 * @Name: importdata
	 * @Description: 保存excel的数据到数据库中
	 * @Author: 刘洋（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2011-12-26 （创建日期）
	 * @Parameters: 无
	 * @Return: String importdata 跳转到userImport.jsp
	 */
	public String importdata() {
		elecUserService.saveElecUserWithExcel(elecUserForm);
		return "importdata";
	}
	
	/**
	 * @Name: chart
	 * @Description: 使用柱状图统计用户
	 * @Author: 刘洋（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2011-12-26 （创建日期）
	 * @Parameters: 无
	 * @Return: String chart 跳转到userReport.jsp
	 */
	public String chart() {
		List<ElecUserForm> list = elecUserService.findUserByChart();
		String filename = ChartUtils.getUserBarChart(list);
		request.setAttribute("filename", filename);
		return "chart";
	}

	private void initSystemDDl() {
		request.setAttribute("sexList",
				elecSystemDDLService.findElecSystemDDLByKeyWord("性别"));
		request.setAttribute("jctList",
				elecSystemDDLService.findElecSystemDDLByKeyWord("所属单位"));
		request.setAttribute("isDutyList",
				elecSystemDDLService.findElecSystemDDLByKeyWord("是否在职"));
	}
}
