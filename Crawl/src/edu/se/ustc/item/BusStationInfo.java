package edu.se.ustc.item;

public class BusStationInfo {

	Integer id;
	String stationName;// the name of bus station
	String stationSeries = "-1";// the series of a station
	String busLicence = "-1";// a bus's licence
	String enterStationTime = "-1";// enter the station's time
	String attribute;// current time

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getEnterStationTime() {
		return enterStationTime;
	}

	public void setEnterStationTime(String enterStationTime) {
		this.enterStationTime = enterStationTime;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getStationSeries() {
		return stationSeries;
	}

	public void setStationSeries(String stationSeries) {
		this.stationSeries = stationSeries;
	}

	public String getBusLicence() {
		return busLicence;
	}

	public void setBusLicence(String busLicence) {
		this.busLicence = busLicence;
	}

	@Override
	public String toString() {
		return "BusStationInfo [id=" + id + ", stationName=" + stationName
				+ ", stationSeries=" + stationSeries + ", busLicence="
				+ busLicence + ", enterStationTime=" + enterStationTime
				+ ", attribute=" + attribute + "]";
	}

}
