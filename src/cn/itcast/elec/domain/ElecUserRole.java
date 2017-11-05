package cn.itcast.elec.domain;


public class ElecUserRole{
	private Integer seqID;
	private String userID;
	private String roleID;
	private String remark;

	public Integer getSeqID() {
		return seqID;
	}

	public void setSeqID(Integer seqID) {
		this.seqID = seqID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getRoleID() {
		return roleID;
	}

	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
