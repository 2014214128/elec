package cn.itcast.elec.web.form;

import java.util.Date;

/**
 * ֵ
 * 
 * @author Asus
 *
 */
@SuppressWarnings("serial")
public class ElecUserForm implements java.io.Serializable {
	private String userID;
	private String jctID;
	private String userName;
	private String loginName;
	private String loginPassword;
	private String sexID;
	private String birthday;
	private String address;
	private String contactTel;
	private String email;
	private String mobile;
	private String isDuty;
	private String onDutyDate;
	private String offDutyDate;
	private String remark;
	private String viewflag;
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getJctID() {
		return jctID;
	}
	public void setJctID(String jctID) {
		this.jctID = jctID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	public String getSexID() {
		return sexID;
	}
	public void setSexID(String sexID) {
		this.sexID = sexID;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactTel() {
		return contactTel;
	}
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getIsDuty() {
		return isDuty;
	}
	public void setIsDuty(String isDuty) {
		this.isDuty = isDuty;
	}
	public String getOnDutyDate() {
		return onDutyDate;
	}
	public void setOnDutyDate(String onDutyDate) {
		this.onDutyDate = onDutyDate;
	}
	public String getOffDutyDate() {
		return offDutyDate;
	}
	public void setOffDutyDate(String offDutyDate) {
		this.offDutyDate = offDutyDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	public String getViewflag() {
		return viewflag;
	}
	public void setViewflag(String viewflag) {
		this.viewflag = viewflag;
	}
	@Override
	public String toString() {
		return "ElecUserForm [userID=" + userID + ", jctID=" + jctID
				+ ", userName=" + userName + ", loginName=" + loginName
				+ ", loginPassword=" + loginPassword + ", sexID=" + sexID
				+ ", birthday=" + birthday + ", address=" + address
				+ ", contactTel=" + contactTel + ", email=" + email
				+ ", mobile=" + mobile + ", isDuty=" + isDuty + ", onDutyDate="
				+ onDutyDate + ", offDutyDate=" + offDutyDate + ", remark="
				+ remark + "]";
	}

	
}
