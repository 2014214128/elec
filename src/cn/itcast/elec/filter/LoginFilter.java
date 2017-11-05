package cn.itcast.elec.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.elec.domain.ElecUser;

@WebFilter(filterName="filter")
public class LoginFilter implements Filter {
	private List<String> list = new ArrayList<String>();

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String path = request.getServletPath();
		if (list != null && list.contains(path)) {
			chain.doFilter(request, response);
			return ;
		}
		ElecUser elecUser = (ElecUser) request.getSession().getAttribute("global_user");
		if (elecUser != null) {
			chain.doFilter(request, response);
			return ;
		}
		response.sendRedirect(request.getContextPath()+"/");
 	}

	/**
	 * 需要定义系统页面访问中可放行的页面
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		list.add("/index.jsp");
		list.add("/image.jsp");
		list.add("/system/elecMenuAction_home.do");
	}

}
