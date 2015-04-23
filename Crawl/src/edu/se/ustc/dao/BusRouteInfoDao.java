package edu.se.ustc.dao;

import java.util.List;

import edu.se.ustc.item.BusRouteInfo;

public interface BusRouteInfoDao {
	
	public boolean saveBusRouteInfo(BusRouteInfo bsi);
	
	public boolean saveBusRouteInfo(List<BusRouteInfo> bsiList);

}
