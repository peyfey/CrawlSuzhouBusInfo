package edu.se.ustc.operation;

import edu.se.ustc.item.BusRouteInfo;

public interface RouteInfoOperation {

	public boolean addBusRouteInfo(BusRouteInfo bsi);
	
	public boolean deleteBusRouteInfo(BusRouteInfo bsi);
	
	public boolean updateBusRouteInfo(BusRouteInfo bsi);
	
	public boolean searchBusRouteInfo(BusRouteInfo bsi);
}
