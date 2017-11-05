package cn.itcast.elec.web.action;

import java.util.Hashtable;
import java.util.List;

import cn.itcast.elec.container.ServiceProvider;
import cn.itcast.elec.domain.ElecUser;
import cn.itcast.elec.service.IElecCommonMsgService;
import cn.itcast.elec.service.IElecLogService;
import cn.itcast.elec.service.IElecUserService;
import cn.itcast.elec.util.LoginUtils;
import cn.itcast.elec.util.MD5keyBean;
import cn.itcast.elec.web.form.ElecCommonMsgForm;
import cn.itcast.elec.web.form.ElecMenuForm;

import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class ElecMenuAction extends BaseAction implements
		ModelDriven<ElecMenuForm> {
	private ElecMenuForm elecMenuForm = new ElecMenuForm();
	
//	private Log log = LogFactory.getLog(ElecMenuAction.class);

	private IElecCommonMsgService elecCommonMsgService = (IElecCommonMsgService) ServiceProvider
			.getService(IElecCommonMsgService.SERVICE_NAME);

	private IElecLogService elecLogService = (IElecLogService) ServiceProvider.getService(IElecLogService.SERVICE_NAME);
	
	private IElecUserService elecUserService = (IElecUserService) ServiceProvider.getService(IElecUserService.SERVICE_NAME);
	
	@Override
	public ElecMenuForm getModel() {
		return this.elecMenuForm;
	}
	/**
	 * @Name: home
	 * @Description: 验证登录名和密码
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: 无
	 * @Return: String home 跳转到index.jsp
	 */
	public String home() {
		/*添加验证码的校验功能*/
		if (!LoginUtils.checkNumber(request, elecMenuForm.getCheckNumber())) {
			this.addFieldError("error", "验证码为空或有误");
			return "error";
		}
		/*操作：
		   一、使用页面输入的登录名和密码，获取用户信息
		     * 获取登录页面中的登录名和密码
		     * 以登录名作为查询条件，查询Elec_user表，获取用户的信息
		         * 如果获取用户为空，说明输入的登录名不存在，返回到登录页面
		         * 如果获取的用户信息不为空，说明获取到了用户信息，则继续验证
		     * 从查询得到的用户信息，获取用户的密码，与登录页面中的密码进行匹配
		         * 如果匹配不成功，说明输入登录页面的密码与当前登录名获取的密码不一致，则返回到登录页面
		         * 如果匹配成功，说明输入登录页面的密码与当前登录名获取的密码一致，则继续执行
		     * 将获取到的用户信息存放到当前session中
		   二、使用页面输入的登录名，获取当前登录名具有的权限
		       * 如果当前登录名没有权限，则当前用户不能登录系统，返回到登录页面
		       * 如果当前登录名有权限，则继续执行
		   三、使用页面输入的登录名，获取当前登录名具有的角色
		       * 如果当前登录名没有分配角色，则当前用户不能登录系统，返回到登录页面
		       * 如果当前登录名已分配了角色，则继续执行
		* 如果一、二、三均验证通过，则登录成功
		*/
		String name = elecMenuForm.getName();
		/*第一步 获取用户*/
		String password = new MD5keyBean().getkeyBeanofStr(elecMenuForm.getPassword());
		ElecUser elecUser =  elecUserService.findElecUserByLoginName(name);
		if (elecUser == null) {
			this.addFieldError("error", "您当前输入的登录名不存在");
			return "error";
		}
		if (password == null || password.equals("") || !elecUser.getLoginPassword().equals(password)) {
			this.addFieldError("error", "您当前输入的密码有误或不存在");
			return "error";
		}
		request.getSession().setAttribute("global_user", elecUser);
		/*第二步 获取权限*/
		String popedom = elecUserService.findElecPopedomByLoginName(name);
		if (popedom == null || "".equals(popedom)) {
			this.addFieldError("error", "当前用户没有分配权限，请与管理员联系");
			return "error";
		}
		request.getSession().setAttribute("global_popedom", popedom);
		/*第三步 获取角色*/
		Hashtable<String, String> ht = elecUserService.findElecRoleByLoginName(name);
		if (ht == null) {
			this.addFieldError("error", "当前用户没有分配角色，请与管理员联系");
			return "error";
		}
		request.getSession().setAttribute("global_role", ht);
		/*添加记住密码功能*/
		LoginUtils.rememeberMeByCookie(request, response, elecMenuForm);
//		log.info("登录名【"+name+"】登陆系统！时间是：" + new java.sql.Date(new java.util.Date().getTime()).toString());
		elecLogService.saveElecLog(request, "登陆模块：当前用户【" + elecUser.getUserName() + "】登陆系统");
		return "home";
	}

	public String title() {
		return "title";
	}

	public String left() {
		return "left";
	}

	public String change1() {
		return "change1";
	}

	public String loading() {
		return "loading";
	}

	public String alermJX() {
		return "alermJX";
	}

	/**
	 * @Name: alermSB
	 * @Description: 查询设备当天运行的情况
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: 无
	 * @Return: String alermSB 跳转到alermSB.jsp
	 */
	public String alermSB() {
		/*操作：
		   1、获取系统的当前日期
		   2、组织where条件
		   3、查询得出列表集合
		*/
		request.setAttribute("commonList",
				elecCommonMsgService.findElecCommonMsgListByCurrentDate());
		return "alermSB";
	}

	public String alermXZ() {
		return "alermXZ";
	}

	public String alermYS() {
		return "alermYS";
	}

	/**
	 * @Name: alermZD
	 * @Description: 查询站点当天运行的情况
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: 无
	 * @Return: String alermZD 跳转到alermZD.jsp
	 */
	public String alermZD() {
		List<ElecCommonMsgForm> list = elecCommonMsgService
				.findElecCommonMsgListByCurrentDate();
		request.setAttribute("commonList", list);
		return "alermZD";
	}
	/**
	 * @Name: logout
	 * @Description: 重新登陆
	 * @Author : 郑国（作者）
	 * @Version: V1.00 (版本号)
	 * @Create Date: 2017-4-18 (创建日期)
	 * @Parameter: 无
	 * @Return: String logout 跳转到logout.jsp
	 */
	public String logout() {
		request.getSession().invalidate();
		return "logout";
	}

}
