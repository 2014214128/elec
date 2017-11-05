package cn.itcast.elec.domain;

import java.util.Date;

/**
 *
 * @author Asus
 *
 */

public class ElecCommonMsg{
	private String comID;
	private String stationRun;
	private String devRun;
	private Date createDate;

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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}



}
