package cn.itcast.elec.domain;

public class ElecSystemDDL {
	private Integer seqID;
	private String keyWord;
	private Integer ddlCode;
	private String ddlName;
	public Integer getSeqID() {
		return seqID;
	}
	public void setSeqID(Integer seqID) {
		this.seqID = seqID;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	
	public Integer getDdlCode() {
		return ddlCode;
	}
	public void setDdlCode(Integer ddlCode) {
		this.ddlCode = ddlCode;
	}
	public String getDdlName() {
		return ddlName;
	}
	public void setDdlName(String ddlName) {
		this.ddlName = ddlName;
	}
	@Override
	public String toString() {
		return "ElecSystemDDL [seqID=" + seqID + ", keyWord=" + keyWord
				+ ", code=" + ddlCode + ", ddlName=" + ddlName + "]";
	}
	
	
}
