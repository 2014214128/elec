package cn.itcast.elec.web.form;

import java.util.Date;

/**
 * ֵ
 * 
 * @author Asus
 *
 */
@SuppressWarnings("serial")
public class ElecCommonMsgForm implements java.io.Serializable {
	private String comID;
	private String stationRun;
	private String devRun;
	private String createDate;

	public String getComID() {
		return comID;
	}

	public void setComID(String comID) {
		this.comID = comID;
	}

	public String getStationRun() {
		return stationRun;
	}

	public void setStationRun(String stationRun) {
		this.stationRun = stationRun;
	}

	public String getDevRun() {
		return devRun;
	}

	public void setDevRun(String devRun) {
		this.devRun = devRun;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

}
