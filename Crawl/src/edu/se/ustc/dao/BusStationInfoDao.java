package edu.se.ustc.dao;

import java.util.List;

import edu.se.ustc.item.BusStationInfo;

public interface BusStationInfoDao {
	
	public boolean saveBusStationInfo(BusStationInfo bsi);
	
	public boolean saveBusStationInfo(List<BusStationInfo> bsiList);

}
