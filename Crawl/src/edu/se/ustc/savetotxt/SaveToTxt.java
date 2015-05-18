package edu.se.ustc.savetotxt;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.se.ustc.item.BusRouteInfo;
import edu.se.ustc.util.dbUtil;

public class SaveToTxt() {
    public boolean saveBusRouteInfo(BusRouteInfo bsi) throws SQLException {

        /**
         * try to save not empty BusStation List
         * @param bsiList
         */
        //只刷新当前为空
        for (int i = 0; i < bsiList.size(); i++) {
            BusRouteInfo single =new BusRouteInfo();
            single.stringToBusRouteInfo(bsiList.get(i));
        }

                //get current time
                Date d = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd "); 
                String dateNowStr = sdf.format(d);
                
                //if the result have saved, skip it
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
