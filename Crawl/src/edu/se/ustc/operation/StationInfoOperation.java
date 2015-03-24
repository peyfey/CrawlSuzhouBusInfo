package edu.se.ustc.operation;

import edu.se.ustc.item.BusStationInfo;

public interface StationInfoOperation {

	public boolean addBusStationInfo(BusStationInfo bsi);
	
	public boolean deleteBusStationInfo(BusStationInfo bsi);
	
	public boolean updateBusStationInfo(BusStationInfo bsi);
	
	public boolean searchBusStationInfo(BusStationInfo bsi);
}
