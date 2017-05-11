package cn.itcast.elec.domain;

import java.util.Date;
/**
 *
 * @author Asus
 *
 */
@SuppressWarnings("serial")
public class ElecText implements java.io.Serializable {
	private String TextID;
	private String TextName;
	private Date textDate;
	private String textRemark;

	public String getTextID() {
		return TextID;
	}

	public void setTextID(String textID) {
		TextID = textID;
	}

	public String getTextName() {
		return TextName;
	}

	public void setTextName(String textName) {
		TextName = textName;
	}

	public Date getTextDate() {
		return textDate;
	}

	public void setTextDate(Date textDate) {
		this.textDate = textDate;
	}

	public String getTextRemark() {
		return textRemark;
	}

	public void setTextRemark(String textRemark) {
		this.textRemark = textRemark;
	}

}
