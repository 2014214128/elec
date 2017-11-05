package cn.itcast.elec.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import cn.itcast.elec.web.form.ElecMenuForm;

public class LoginUtils {

	public static boolean checkNumber(HttpServletRequest request,
			String checkNumber) {
		/* 从session中获取验证码 */
		HttpSession session = request.getSession(false);
		if (session == null) {
			return false;
		}
		String check_number_key = (String) session
				.getAttribute("CHECK_NUMBER_KEY");
		if (check_number_key == null || StringUtils.isBlank(check_number_key)) {
			return false;
		}
		if (checkNumber == null || StringUtils.isBlank(checkNumber)) {
			return false;
		}
		return checkNumber.equals(check_number_key);
	}

	public static void rememeberMeByCookie(HttpServletRequest request, HttpServletResponse response,
			ElecMenuForm elecMenuForm) {
		//处理Cookie中存在的中文字符
		String codeName = "";
		try {
			codeName = URLEncoder.encode(elecMenuForm.getName(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Cookie nameCookie = new Cookie("name", codeName);
		Cookie passwordCookie = new Cookie("password",
				elecMenuForm.getPassword());
		nameCookie.setPath(request.getContextPath()+"/");
		passwordCookie.setPath(request.getContextPath()+"/");
		String rememberMe = elecMenuForm.getRememberMe();
		if (rememberMe != null && rememberMe.equals("yes")) {
			nameCookie.setMaxAge(7 * 24 * 3600);
			passwordCookie.setMaxAge(7 * 24 * 3600);
		} else {
			nameCookie.setMaxAge(0);
			passwordCookie.setMaxAge(0);
		}
		response.addCookie(nameCookie);
		response.addCookie(passwordCookie);
	}

}
