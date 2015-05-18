package edu.se.ustc.dao;

import java.sql.SQLException;
import java.util.List;

import edu.se.ustc.item.BusRouteInfo;

public interface BusRouteInfoDao {
	
	public boolean saveBusRouteInfo(BusRouteInfo bsi) throws SQLException;
	
	public boolean saveBusRouteInfo(List<BusRouteInfo> bsiList);

}
