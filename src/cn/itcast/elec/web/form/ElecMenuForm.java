package cn.itcast.elec.web.form;

/**
 * ֵ
 * 
 * @author Asus
 *
 */
@SuppressWarnings("serial")
public class ElecMenuForm implements java.io.Serializable {
	private String name;
	private String password;
	private String checkNumber;
	private String rememberMe;

	public String getRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(String rememberMe) {
		this.rememberMe = rememberMe;
	}

	public String getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
