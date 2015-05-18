package edu.se.ustc.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import edu.se.ustc.dao.BusRouteInfoDao;
import edu.se.ustc.item.BusRouteInfo;
import edu.se.ustc.util.dbUtil;

public class BusRouteInfoDaoImpl implements BusRouteInfoDao {

	@Override
	public boolean saveBusRouteInfo(BusRouteInfo bsi) throws SQLException {

	    /**
	     * try to save not empty BusStation List
	     * @param bsiList
	     */
        // TODO Auto-generated method stub
	    dbUtil db = new dbUtil();
	    if(bsi.getEnterStationTime()!=null){

	            Connection conn = dbUtil.getConnect();
	            Statement stat = conn.createStatement();
	            
	            //get current time
	            Date d = new Date();
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd "); 
	            String dateNowStr = sdf.format(d);
	            
	            //if the result have saved, skip it
	            String sql="SELECT bus_num FROM station_stats WHERE ARRIVAL_TIME =";
	            try(ResultSet result=stat.executeQuery(sql+dateNowStr+bsi.getEnterStationTime())){
	                if(result.next()){
	                    System.out.println(result.getString(1)+"has saved!");
	                }
	                else {
	                    sql="INSERT INTO station_stats(bus_num,station_code,arrival_time) VALUES("
	                            +bsi.getStationSeries()
	                            +")";
	                    if(stat.execute(sql)){
	                        
	                    }
    	            }

	            }
	        
	        }
	    
		return false;
	}

	   public boolean saveAllBRInfo(BusRouteInfo bsi) {
           /**����¼:��ʼ������,1��⵱ǰbsilist�е�һ��վ�Ƿ���ֵ,�����ֵ,����bus_num.
	        *2.�Ե�ǰ�е�վʱ���վ�ĳ��ƺŽ��в���   
	        */
	        /**
	         * try to save not empty BusStation List
	         * @param bsiList
	         */
	        // TODO Auto-generated method stub
	      
	       
	        
	        return false;
	    }
	
	@Override
	public boolean saveBusRouteInfo(List<BusRouteInfo> bsiList) {
		// TODO Auto-generated method stub
		return false;
	}

}
