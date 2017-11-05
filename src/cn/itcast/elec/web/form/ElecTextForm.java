package cn.itcast.elec.web.form;


/**
 *ֵ
 * 
 * @author Asus
 *
 */
@SuppressWarnings("serial")
public class ElecTextForm implements java.io.Serializable {
	private String TextID;
	private String TextName;
	private String textDate;
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

	public String getTextDate() {
		return textDate;
	}

	public void setTextDate(String textDate) {
		this.textDate = textDate;
	}

	public String getTextRemark() {
		return textRemark;
	}

	public void setTextRemark(String textRemark) {
		this.textRemark = textRemark;
	}

}
